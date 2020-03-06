package com.healthedge.healthchain.user.service.impl;

import com.healthedge.healthchain.blockchain.ContractWrapper.BenefitPlan;
import com.healthedge.healthchain.common.util.Constants;
import com.healthedge.healthchain.user.Repository.BenfitPlanRepository;
import com.healthedge.healthchain.user.Repository.MemberRepository;
import com.healthedge.healthchain.user.dto.BaseResponse;
import com.healthedge.healthchain.user.dto.BenefitPlanRequest;
import com.healthedge.healthchain.user.dto.BenefitPlanResponse;
import com.healthedge.healthchain.user.entity.Member;
import com.healthedge.healthchain.user.service.IPFSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Service
public class ProviderServiceImpl {

    private final Logger LOGGER = LoggerFactory.getLogger(ProviderServiceImpl.class);

//    @Value(value = "${contractAddress}")
    private String contractAddress="0x8c4171A1495d5Cf048E864e006EaE770c129A31d";

//    @Value(value = "${privateKey}")
    private String privateKey="d75b5fc0e209f93ae344f4393d46fcc642124e9bfac08c6ad279ae99297dfa22";

    @Autowired
    private Web3j web3j;

    @Autowired
    private EtheriumServiceImpl etheriumService;

    @Autowired
    ContractGasProviderImpl contractGasProvider;

    @Autowired
    BenfitPlanRepository benfitPlanRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    IPFSService ipfsService;


    public String retrieveFromLedger(String benefitplanId
            , String benefitPlanPayload) throws CipherException, IOException {

        String ipfsHash = null;
        ipfsHash = ipfsService.addFileToIPFS(ipfsService.prepareForIPFSInjestion(benefitPlanPayload));
        LOGGER.info("ipfs hash from blockchain:" + ipfsHash);
        try {
            Credentials credentials = etheriumService.getCredentials(privateKey);

            BenefitPlan benefitPlanContract = etheriumService.loadBenefitPlan(credentials, contractGasProvider, contractAddress);
            TransactionReceipt transactionReceipt = benefitPlanContract.
                    setBenefitPlan(benefitplanId, ipfsHash).send();

            LOGGER.info("Transaction hash from blockchain: " + transactionReceipt.getTransactionHash());
            com.healthedge.healthchain.user.entity.BenefitPlan benefitPlan =
                    new com.healthedge.healthchain.user.entity.BenefitPlan();
            benefitPlan.setTransactionHash(transactionReceipt.getTransactionHash());
            benefitPlan.setBenefitPlanId(benefitplanId);
            benfitPlanRepository.save(benefitPlan);
            return Constants.SUCCESS;
        } catch (Exception e) { // TODO Auto-generated catch block
            LOGGER.error("Error ", e);
            return Constants.FAILED;
        }

    }


    public BaseResponse createBenefitPlan(BenefitPlanRequest benefitPlan) throws CipherException, IOException {
        retrieveFromLedger(benefitPlan.getId(), benefitPlan.getBenefitPlanPayload());
        BaseResponse baseResponse = new BaseResponse(Constants.SUCCESS, "Benefit Plan successfully created");
        return baseResponse;
    }

    public BaseResponse updateBenefitPlan(BenefitPlanRequest benefitPlan) {
        //TODO update a benefit plan
//        retrieveFromLedger(benefitPlan.getId(), benefitPlan.getBenefitPlanPayload());
        BaseResponse baseResponse = new BaseResponse(Constants.SUCCESS, "Benefit Plan successfully updated");
        return baseResponse;
    }

    public BaseResponse createMember(Member member) throws Exception {
        //create member in db
//        EthAccounts accounts = etheriumService.getEthAccounts();
//        accounts.getAccounts();

        memberRepository.save(member);

        //assign benefit plan to member in blockchain
        Credentials credentials = etheriumService.getCredentials(privateKey);

        BenefitPlan benefitPlanContract = etheriumService.loadBenefitPlan(credentials, contractGasProvider, contractAddress);
        TransactionReceipt transactionReceipt = benefitPlanContract.assignMemberToBenefitPlan(member.getMemberId(), member.getBenefitPlanId()).send();
        LOGGER.info("Transaction hash from blockchain: " + transactionReceipt.getTransactionHash());

        BaseResponse baseResponse = new BaseResponse(Constants.SUCCESS, "Member successfully created");
        return baseResponse;
    }

    public List<BenefitPlanResponse> getAllBenefitPlans() throws Exception {

        List<com.healthedge.healthchain.user.entity.BenefitPlan> list = benfitPlanRepository.findAll();
        List<BenefitPlanResponse> responselist = new ArrayList<>();

        for (com.healthedge.healthchain.user.entity.BenefitPlan plan : list) {
            BenefitPlanResponse response = new BenefitPlanResponse();
            response.setBenefitPlanId(plan.getBenefitPlanId());
            response.setTransactionHash(plan.getTransactionHash());
            String benefitPlanDeEnc = new String(getBenefitPlanDetailsFromIFPShash(plan.getBenefitPlanId())).trim();
            response.setBenefitPlanPayload(benefitPlanDeEnc);
            responselist.add(response);
        }
        return responselist;
    }

    public List<BenefitPlanResponse> getBenefitPlanHistory(String benefitPlanId) throws Exception {

        List<com.healthedge.healthchain.user.entity.BenefitPlan> plans = benfitPlanRepository.getBenefitPlanByIdSortedReverse(benefitPlanId);
        List<BenefitPlanResponse> benefitPlanResponses = new ArrayList<>();

        for (com.healthedge.healthchain.user.entity.BenefitPlan plan : plans) {
            BenefitPlanResponse benefitPlanResponse = new BenefitPlanResponse();
            benefitPlanResponse.setBenefitPlanId(plan.getBenefitPlanId());
            benefitPlanResponse.setTransactionHash(plan.getTransactionHash());
            String benefitPlanDeEnc = new String(getBenefitPlanDetailsFromIFPShash(benefitPlanId)).trim();
            benefitPlanResponse.setBenefitPlanPayload(benefitPlanDeEnc);
            benefitPlanResponses.add(benefitPlanResponse);
        }
        return benefitPlanResponses;
    }

    public byte[] getBenefitPlanDetailsFromIFPShash(String benefitPlanid) throws Exception {
        String ipfsHash = null;
        //hit blockchain and get ipfs hash
        Credentials credentials = etheriumService.getCredentials(privateKey);

        BenefitPlan benefitPlanContract = etheriumService.loadBenefitPlan(credentials, contractGasProvider, contractAddress);
        ipfsHash = benefitPlanContract.getBenefitPlan(benefitPlanid).send();
        LOGGER.info("IPFS hash from blockchain: " + ipfsHash);
        byte[] payload = ipfsService.getData(ipfsHash);
        return payload;
    }
}
