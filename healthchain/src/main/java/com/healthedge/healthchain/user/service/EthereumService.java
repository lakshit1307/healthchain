package com.healthedge.healthchain.user.service;

import com.healthedge.healthchain.blockchain.EthereumConfig.EthereumConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import java.io.IOException;
import java.math.BigInteger;

@Service
public class EthereumService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EthereumConfig.class);

    public Credentials getCredentials() throws IOException, CipherException {
        Credentials credentials = null;
        try {
            credentials = WalletUtils.loadCredentials("",
                    "/home/ubuntu/payal/arbour/springboot/projectcode/UTC--2019-04-25T07-40-19.131608579Z--1589525e9c86049f287999523a11e4dc3a77f15a");
            LOGGER.info("credentials: " + credentials);
        } catch (Exception e) {
            LOGGER.error("failure", e);
        }
        return credentials;
    }

    public BigInteger getGasPrice() {
        BigInteger gasprice = BigInteger.valueOf(4700000);
        return gasprice;
    }

    public BigInteger getGasLimit() {
        BigInteger gaslimit = BigInteger.valueOf(4700000);
        return gaslimit;
    }
}
