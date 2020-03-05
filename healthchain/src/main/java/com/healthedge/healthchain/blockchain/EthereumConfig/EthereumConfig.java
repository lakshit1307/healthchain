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



}
