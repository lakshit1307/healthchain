package com.healthedge.healthchain.user.service.impl;

import com.healthedge.healthchain.blockchain.ContractWrapper.BenefitPlan;
import com.healthedge.healthchain.common.util.Constants;
import com.healthedge.healthchain.user.Repository.BenfitPlanRepository;
import com.healthedge.healthchain.user.dto.BaseResponse;
import com.healthedge.healthchain.user.dto.BenefitPlanRequest;
import com.healthedge.healthchain.user.dto.BenefitPlanResponse;
import com.healthedge.healthchain.user.entity.Member;
import com.healthedge.healthchain.user.service.EthereumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ProviderServiceImpl {

    private final Logger LOGGER = LoggerFactory.getLogger(ProviderServiceImpl.class);


    @Autowired
    private Web3j web3j;

    @Autowired
    private EtheriumServiceImpl etheriumService;

    @Autowired
    ContractGasProviderImpl contractGasProvider;

    @Autowired
    BenfitPlanRepository benfitPlanRepository;

    @Autowired
    com.healthedge.healthchain.user.service.IPFSService ipfsService;

    public String retrieveFromLedger(String benefitplanId
            , String benefitPlanPayload) throws CipherException, IOException {

        Credentials credentials = etheriumService.getCredentials("d75b5fc0e209f93ae344f4393d46fcc642124e9bfac08c6ad279ae99297dfa22");
        String ipfsHash = null;
        BenefitPlan benefitPlanContract = etheriumService.loadBenefitPlan(credentials, contractGasProvider, "0x8c4171A1495d5Cf048E864e006EaE770c129A31d");
        ipfsHash = ipfsService.addFileToIPFS(ipfsService.prepareForIPFSInjestion(benefitPlanPayload));
        LOGGER.info("ipfs hash from blockchain:" + ipfsHash);
        try {
            benefitPlanContract.getContractAddress();
            TransactionReceipt transactionReceipt = benefitPlanContract.setBenefitPlan(benefitplanId, ipfsHash).send();

            LOGGER.info("Transaction hash from blockchain: " + transactionReceipt.getTransactionHash());
            com.healthedge.healthchain.user.entity.BenefitPlan benefitPlan = new com.healthedge.healthchain.user.entity.BenefitPlan();
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

    public BaseResponse createMember(Member member) {
        //TODO Create a member
        BaseResponse baseResponse = new BaseResponse(Constants.SUCCESS, "Member successfully created");
        return baseResponse;
    }

    public List<BenefitPlanResponse> getAllBenefitPlans() {
        //TODO
        return createBenefitPlan("BP_1");
    }

    public List<BenefitPlanResponse> getBenefitPlanHistory(String benefitPlanId) {
        //TODO
        return createBenefitPlan("BP_1");

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
