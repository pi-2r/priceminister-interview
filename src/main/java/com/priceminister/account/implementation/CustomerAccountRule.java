/*
 * =============================================================================
 *
 *   PRICE MINISTER APPLICATION
 *   Copyright (c) 2000 Babelstore.
 *   All Rights Reserved.
 *
 *   $Source$
 *   $Revision$
 *   $Date$
 *   $Author$
 *
 * =============================================================================
 */
package com.priceminister.account.implementation;

import com.priceminister.account.*;
import com.priceminister.account.utils.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class CustomerAccountRule implements AccountRule {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(CustomerAccountRule.class);
    final static String GAP_ALLOWED = "GAP_ALLOWED";
    Tools tools = new Tools();


    /* (non-Javadoc)
     * @see com.priceminister.account.AccountRule#withdrawPermitted(java.lang.Double)
     */
    public boolean withdrawPermitted(Double resultingAccountBalance)  {
    int value = 0;
        try {
            value = tools.getAllowedWithdraw(GAP_ALLOWED);
        }catch (IOException e) {
            LOG.error("Unexpected error", e);
        }
        return resultingAccountBalance >= value;
    }



}
