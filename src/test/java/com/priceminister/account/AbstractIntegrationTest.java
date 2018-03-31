package com.priceminister.account;

import java.util.Random;

/**
 * Created by zen on 31/03/18.
 */
public class AbstractIntegrationTest {

    protected static final int MIN_MONEY = 100;
    protected static final int MAX_MONEY = 100000;

    /**
     * get random number
     * @return
     */
    protected static int randomNumber(int max, int min) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}
