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
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProviderServiceImpl {

    private final Logger LOGGER = LoggerFactory.getLogger(ProviderServiceImpl.class);

    @Value(value = "${contractAddress}")
    private String contractAddress;

    @Value(value = "${privateKey}")
    private String privateKey;

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
            Credentials credentials = etheriumService.getCredentials("d58611e08837b5802516e8d447becf73c40a8dcc157cab000661cd5a9053663e");

            BenefitPlan benefitPlanContract = etheriumService.loadBenefitPlan(credentials, contractGasProvider, "0x0DBfC267C7EaE2d85C4760De7B6C076769f39376");
            TransactionReceipt transactionReceipt = benefitPlanContract.setBenefitPlan(benefitplanId, ipfsHash).send();

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
        LOGGER.info("acc :" + etheriumService.getEthAccounts(1).toString());
        memberRepository.save(member);

        //assign benefit plan to member in blockchain
        Credentials credentials = etheriumService.getCredentials("d58611e08837b5802516e8d447becf73c40a8dcc157cab000661cd5a9053663e");

        BenefitPlan benefitPlanContract = etheriumService.loadBenefitPlan(credentials, contractGasProvider, "0x0DBfC267C7EaE2d85C4760De7B6C076769f39376");
        TransactionReceipt transactionReceipt = benefitPlanContract.assignMemberToBenefitPlan(member.getMemberId(), member.getBenefitPlanId()).send();
        LOGGER.info("Transaction hash from blockchain: " + transactionReceipt.getTransactionHash());

        BaseResponse baseResponse = new BaseResponse(Constants.SUCCESS, "Member successfully created");
        return baseResponse;
    }

    public List<BenefitPlanResponse> getAllBenefitPlans() {

      List<com.healthedge.healthchain.user.entity.BenefitPlan>   list =benfitPlanRepository.findAll();
      List<BenefitPlanResponse> responselist = new ArrayList<>();
        for (com.healthedge.healthchain.user.entity.BenefitPlan plan:list) {
            BenefitPlanResponse response=new BenefitPlanResponse();
            response.setBenefitPlanId(plan.getBenefitPlanId());
           Member member= memberRepository.findByBenefitPlanId(plan.getBenefitPlanId());
            response.setMemberId(member.getMemberId());
            responselist.add(response);
        }
        return responselist;
    }

    public List<BenefitPlanResponse> getBenefitPlanHistory(String benefitPlanId) throws Exception {

        List<com.healthedge.healthchain.user.entity.BenefitPlan> plans =benfitPlanRepository.findByBenefitPlanId(benefitPlanId);
        List<Integer> idList= new ArrayList<>();

        for (com.healthedge.healthchain.user.entity.BenefitPlan plan :plans) {
            idList.add(Integer.parseInt(plan.getId()));
        }
        Collections.sort(idList, Collections.reverseOrder());

        //first record will be the latest
        LOGGER.info("sorted id's:"+idList);
        //hit blockchain to get the IPFS hash
        //hit IPFS to get the payload
      List<BenefitPlan> BPlist= new ArrayList<>();
     // getBenefitPlanDetailsFromIFPShash(benefitplanid);





        return createBenefitPlan("BP_1");

    }

    public byte[] getBenefitPlanDetailsFromIFPShash(String benefitPlanid) throws Exception {
        String ipfsHash=null;
      //hit blockchain and get ipfs hash
        Credentials credentials = etheriumService.getCredentials("d58611e08837b5802516e8d447becf73c40a8dcc157cab000661cd5a9053663e");

        BenefitPlan benefitPlanContract = etheriumService.loadBenefitPlan(credentials, contractGasProvider, "0x0DBfC267C7EaE2d85C4760De7B6C076769f39376");
        ipfsHash = benefitPlanContract.getBenefitPlan(benefitPlanid).send();
        LOGGER.info("IPFS hash from blockchain: " + ipfsHash);
      byte[] payload=  ipfsService.getData(ipfsHash);
      return payload;

    }


    public static List<BenefitPlanResponse> createBenefitPlan(String benefitPlanId) {
        List<BenefitPlanResponse> benefitPlans = new ArrayList<>();
        BenefitPlanResponse benefitPlan = new BenefitPlanResponse();
//        benefitPlan.setBenefitPlanId("BP_1");
//        benefitPlan.setBenefitPlanName("BENEFIT_PLAN_1");
//        benefitPlan.setBenefitPlanData("The benefit plan data will be shown here");
        return benefitPlans;
    }



}
