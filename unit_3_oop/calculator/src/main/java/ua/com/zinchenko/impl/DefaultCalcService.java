package ua.com.zinchenko.impl;

import ua.com.zinchenko.abstr.CalcService;

import java.math.BigInteger;

public class DefaultCalcService implements CalcService {

    @Override
    public BigInteger sum(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    @Override
    public BigInteger multi(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }

    @Override
    public BigInteger minus(BigInteger a, BigInteger b) {
        return a.min(b);
    }

    @Override
    public BigInteger divide(BigInteger a, BigInteger b) {
        return a.divide(b);
    }
}
