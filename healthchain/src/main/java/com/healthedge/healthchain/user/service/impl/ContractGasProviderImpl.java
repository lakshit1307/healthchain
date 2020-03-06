package com.healthedge.healthchain.user.service.impl;

import org.springframework.stereotype.Service;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;

@Service
public class ContractGasProviderImpl implements ContractGasProvider {

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
        return BigInteger.valueOf(999999);
    }

    @Override
    public BigInteger getGasLimit() {
        return BigInteger.valueOf(999999);
    }
}
