package com.healthedge.healthchain.user.service.impl;

import com.healthedge.healthchain.blockchain.ContractWrapper.BenefitPlan;
import com.healthedge.healthchain.common.util.Constants;
import com.healthedge.healthchain.user.dto.BaseResponse;
import com.healthedge.healthchain.user.dto.BenefitPlanRequest;
import com.healthedge.healthchain.user.dto.BenefitPlanResponse;
import com.healthedge.healthchain.user.entity.Member;
import com.healthedge.healthchain.user.service.EthereumService;
import com.healthedge.healthchain.user.service.IPFSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderServiceImpl {

    private final Logger LOGGER = LoggerFactory.getLogger(ProviderServiceImpl.class);


    @Autowired
    private Web3j web3j;

    @Autowired
    EthereumService ethereumService;

    @Autowired
    com.healthedge.healthchain.user.service.IPFSService IPFSService;

    public String retrieveFromLedger(String contractAddress, String benefitplanId) throws CipherException, IOException {

        Credentials credentials = Credentials.create("d75b5fc0e209f93ae344f4393d46fcc642124e9bfac08c6ad279ae99297dfa22");
        ContractGasProvider contractGasProvider=new ContractGasProvider() {
            @Override
            public BigInteger getGasPrice(String contractFunc) {
                return BigInteger.valueOf(0);
            }

            @Override
            public BigInteger getGasPrice() {
                return BigInteger.valueOf(0);
            }

            @Override
            public BigInteger getGasLimit(String contractFunc) {
                return BigInteger.valueOf(48000000);
            }

            @Override
            public BigInteger getGasLimit() {
                return BigInteger.valueOf(48000000);
            }
        };
        String ipfsHash = null;
        BenefitPlan benefitPlanContract=BenefitPlan.load("0x8c4171A1495d5Cf048E864e006EaE770c129A31d", web3j,
                credentials, contractGasProvider);

        try {

            benefitPlanContract.setBenefitPlan("123", "kuch bhi string pass kar do").send();
            ipfsHash = benefitPlanContract.getBenefitPlan("123").send();

            LOGGER.info("ipfs hash from blockchain:" + ipfsHash);
            System.out.println("ipfs hash from blockchain:" + ipfsHash);
            return ipfsHash;

        } catch (Exception e) { // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "something went wrong";

    }


    public BaseResponse createBenefitPlan(BenefitPlanRequest benefitPlan){
        //TODO Create a benefit plan


        BaseResponse baseResponse=new BaseResponse(Constants.SUCCESS, "Benefit Plan successfully created");
        return baseResponse;
    }

    public BaseResponse updateBenefitPlan(BenefitPlanRequest benefitPlan){
        //TODO update a benefit plan
        BaseResponse baseResponse=new BaseResponse(Constants.SUCCESS, "Benefit Plan successfully updated");
        return baseResponse;
    }

    public BaseResponse createMember(Member member){
        //TODO Create a member
        BaseResponse baseResponse=new BaseResponse(Constants.SUCCESS, "Member successfully created");
        return baseResponse;
    }

    public List<BenefitPlanResponse> getAllBenefitPlans(){
        //TODO
        return createBenefitPlan("BP_1");
    }

    public List<BenefitPlanResponse> getBenefitPlanHistory(String benefitPlanId){
        //TODO
        return createBenefitPlan("BP_1");

    }

    public static List<BenefitPlanResponse> createBenefitPlan(String benefitPlanId){
        List<BenefitPlanResponse> benefitPlans= new ArrayList<>();
        BenefitPlanResponse benefitPlan=new BenefitPlanResponse();
//        benefitPlan.setBenefitPlanId("BP_1");
//        benefitPlan.setBenefitPlanName("BENEFIT_PLAN_1");
//        benefitPlan.setBenefitPlanData("The benefit plan data will be shown here");
        return benefitPlans;
    }
}
