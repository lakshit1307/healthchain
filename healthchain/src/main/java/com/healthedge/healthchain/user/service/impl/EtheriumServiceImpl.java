package com.healthedge.healthchain.user.service.impl;

import com.healthedge.healthchain.blockchain.ContractWrapper.BenefitPlan;
import com.healthedge.healthchain.common.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.ContractGasProvider;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class EtheriumServiceImpl {

    @Autowired
    private Web3j web3j;

    @Autowired
    ContractGasProviderImpl contractGasProvider;

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
        EthAccounts result = this.web3j.ethAccounts()
                .sendAsync()
                .get();
        return result;
    }

    public TransactionReceipt getTransactionFromHash(String txHash) throws IOException {
        Optional<TransactionReceipt> transactionReceipt =
                web3j.ethGetTransactionReceipt(txHash).send().getTransactionReceipt();
        return transactionReceipt.get();
    }

    public String retrieveFromLedger(String benefitplanId) throws Exception {
        Credentials credentials = getCredentials("d75b5fc0e209f93ae344f4393d46fcc642124e9bfac08c6ad279ae99297dfa22");
        BenefitPlan benefitPlanContract = loadBenefitPlan(credentials, contractGasProvider, "0x0DBfC267C7EaE2d85C4760De7B6C076769f39376");
        String benefitPlanHash = benefitPlanContract.
                getBenefitPlan(benefitplanId).send();
        return benefitPlanHash;


    }


}
