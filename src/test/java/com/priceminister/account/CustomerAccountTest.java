package com.priceminister.account;


import static org.junit.Assert.*;

import org.junit.*;

import com.priceminister.account.implementation.*;


/**
 * Please create the business code, starting from the unit tests below.
 * Implement the first test, the develop the code that makes it pass.
 * Then focus on the second test, and so on.
 * 
 * We want to see how you "think code", and how you organize and structure a simple application.
 * 
 * When you are done, please zip the whole project (incl. source-code) and send it to recrutement-dev@priceminister.com
 * 
 */
public class CustomerAccountTest extends AbstractIntegrationTest{
    
    Account customerAccount;
    AccountRule rule;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        customerAccount = new CustomerAccount();
        rule =  new CustomerAccountRule();
    }


    /**
     * Tests that an empty account always has a balance of 0.0, not a NULL.
     */
    @Test
    public void testAccountWithoutMoneyHasZeroBalance() {
        Assert.assertEquals(customerAccount.getBalance(), 0.0, 0);
    }
    
    /**
     * Adds money to the account and checks that the new balance is as expected.
     */
    @Test
    public void testAddPositiveAmount() {
        Double tmp = customerAccount.getBalance();
        Double balance = Double.valueOf(randomNumber(MAX_MONEY, MIN_MONEY));
        customerAccount.add(balance);
        Assert.assertNotEquals(customerAccount.getBalance(), tmp);
    }

    /**
     * Test simple withdrawal without exception.
     *
     */
    @Test
    public void testWithdrawAndReportBalance() throws IllegalBalanceException{

        //---- simple step to create a new balance
        Double tmp = customerAccount.getBalance();
        Double balance = 100d;
        customerAccount.add(balance);
        Assert.assertNotEquals(customerAccount.getBalance(), tmp);

        Double withdrawAmount = 50d;
        Assert.assertEquals(customerAccount.withdrawAndReportBalance(withdrawAmount, rule), 50, 0);
    }

    /**
     * Tests that an illegal withdrawal throws the expected exception.
     * Use the logic contained in CustomerAccountRule; feel free to refactor the existing code.
     */
    @Test(expected = IllegalBalanceException.class)
    public void testWithdrawAndReportBalanceIllegalBalance() throws IllegalBalanceException{

        //---- simple step to create a new balance
        Double tmp = customerAccount.getBalance();
        Double balance = 100d;
        customerAccount.add(balance);
        Assert.assertNotEquals(customerAccount.getBalance(), tmp);

        Assert.assertTrue(rule.withdrawPermitted( 0.0));
        Double withdrawAmount =  Double.valueOf(randomNumber(MAX_MONEY, MIN_MONEY));
        customerAccount.withdrawAndReportBalance(withdrawAmount, rule);
    }

    /**
     * Test simple withdraw with null value
     * @throws IllegalBalanceException
     */
    @Test
    public void testSimpleNULLWithdraw() throws IllegalBalanceException{
        Double withdrawAmount =  null;
        customerAccount.withdrawAndReportBalance(withdrawAmount, rule);
        Assert.assertEquals(customerAccount.getBalance(), 0.0,0);
    }

    /**
     * Test simple add with null value
     */
    @Test
    public void testSimpleNULLAdd() {
        Double tmp = customerAccount.getBalance();
        Double balance = null;
        customerAccount.add(balance);
        Assert.assertEquals(customerAccount.getBalance(), tmp);
    }

    /**
     * Test rule of withdrawPermitted
     */
    @Test
    public void testSimpleWithdrawPermitted() {
        Assert.assertTrue(rule.withdrawPermitted( 0.0));
        Assert.assertTrue(rule.withdrawPermitted( 100.0));
        Assert.assertNotEquals(rule.withdrawPermitted((double) -100), true);
    }
}
