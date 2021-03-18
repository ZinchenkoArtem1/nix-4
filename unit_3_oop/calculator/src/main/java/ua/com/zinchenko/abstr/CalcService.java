package ua.com.zinchenko.abstr;

import java.math.BigInteger;

public interface CalcService {
    BigInteger sum(BigInteger a, BigInteger b);

    BigInteger multi(BigInteger a, BigInteger b);

    BigInteger minus(BigInteger a, BigInteger b);

    BigInteger divide(BigInteger a, BigInteger b);
}
