package com.healthedge.healthchain.blockchain.EthereumConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;


@Configuration
public class EthereumConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(EthereumConfig.class);

    @Value(value = "${ethereum.url}")
    private String URL;

    @Bean
    public Web3j getEthereumClient() throws IOException {
        Web3j web3jObject = Web3j.build(new HttpService(URL));
        Web3ClientVersion web3ClientVersion = web3jObject.web3ClientVersion().send();
        String clientVersion = ((Web3ClientVersion) web3ClientVersion).getWeb3ClientVersion();
        LOGGER.info(" Web3j Client version:"+clientVersion);
         return web3jObject;

    }

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
