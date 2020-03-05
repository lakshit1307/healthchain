package com.healthedge.healthchain.user.service.impl;

import com.healthedge.healthchain.blockchain.ContractWrapper.BenefitPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.tx.gas.ContractGasProvider;

import java.util.concurrent.ExecutionException;

@Service
public class EtheriumServiceImpl {

    @Autowired
    private Web3j web3j;

    public Credentials getCredentials(String privateKey) {
        Credentials credentials = Credentials.create(privateKey);
        return credentials;
    }

    public BenefitPlan loadBenefitPlan(Credentials credentials, ContractGasProvider contractGasProvider, String contractAddress) {
        BenefitPlan benefitPlanContract = BenefitPlan.load(contractAddress, web3j,
                credentials, contractGasProvider);
        return benefitPlanContract;
    }

    public EthAccounts getEthAccounts() throws ExecutionException, InterruptedException {
        EthAccounts result = new EthAccounts();
        result = this.web3j.ethAccounts()
                .sendAsync()
                .get();
        return result;
    }
}
