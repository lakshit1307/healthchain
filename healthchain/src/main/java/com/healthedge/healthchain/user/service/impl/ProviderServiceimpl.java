/*
package com.healthedge.healthchain.user.service.impl;

import com.healthedge.healthchain.blockchain.ContractWrapper.BenefitPlan;
import com.healthedge.healthchain.user.service.EthereumService;
import com.healthedge.healthchain.user.service.IPFSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.web3j.crypto.CipherException;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jService;

import java.io.IOException;

public class ProviderServiceimpl {

    private final Logger LOGGER = LoggerFactory.getLogger(ProviderServiceimpl.class);


    @Value(value = "${contractAddress}")
    private String contractAddress;

    @Autowired
    Web3j web3j;

    @Autowired
    EthereumService ethereumService;

    @Autowired
    IPFSService IPFSService;

    public String retrieveFromLedger(String contractAddress, String benefitplanId) throws CipherException, IOException {


        String ipfsHash = null;
        BenefitPlan benefitPlanContract=BenefitPlan.load(contractAddress,web3j,ethereumService.getCredentials(),ethereumService.getGasPrice(),
                ethereumService.getGasLimit()  );

        try {

            ipfsHash = benefitPlanContract.getBenefitPlan()

            logger.info("ipfs hash from blockchain:" + ipfsHash);
            System.out.println("ipfs hash from blockchain:" + ipfsHash);
            return ipfsHash;

        } catch (Exception e) { // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "something went wrong";

    }

    public byte[] findByUuid(String uuid) throws IOException, CipherException {

        String ipfsHash = null;
        byte[] content = null;
        // hit blockchain to get the hash

        ipfsHash = retrieveFromLedger(contractAddress, uuid);
        //System.out.println("ipfs hash in findbyuuid :" + ipfsHash);
        // hit ipfs to get the link for pdf
        if(ipfsHash!=null && !ipfsHash.isEmpty()) {
            content = IPFSConfig.getData(ipfsHash);
            return content;
        }

        throw new ArbourException(ErrorCode.INVALID_UUID,Constants.INVALID_UUID);



    }

}
*/
