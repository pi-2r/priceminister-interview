package com.priceminister.account.implementation;

import com.priceminister.account.*;
import com.priceminister.account.utils.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CustomerAccount implements Account {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(CustomerAccount.class);

    private Double myAccount = null;
    Tools tools = new Tools();


    public void add(Double addedAmount) {
        LOG.info("addedAmount {}", addedAmount);
        setMyAccount(tools.addCalCul(tools.changeNULLToZero(addedAmount), getBalance()));
        LOG.info("new resul {}", getBalance());
    }

    public Double getBalance() {
        setMyAccount(tools.changeNULLToZero(getMyAccount()));
        LOG.info("my balance {}", getMyAccount());
        return getMyAccount();
    }

    public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule)  throws IllegalBalanceException {

        setMyAccount(tools.minusCalCul(tools.changeNULLToZero(withdrawnAmount), getBalance()));

        if (!rule.withdrawPermitted(getMyAccount())) {
            throw new IllegalBalanceException(getMyAccount());
        }
        LOG.info("withdraw with new balance {} ", getMyAccount());
        return getMyAccount();

    }


    private Double getMyAccount() {return myAccount;}
    private void setMyAccount(Double myAccount) {this.myAccount = myAccount;}
}
