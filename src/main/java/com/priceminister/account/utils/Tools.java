package com.priceminister.account.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by TherPi on 30/03/2018.
 */
public class Tools {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(Tools.class);

    Properties properties = new Properties();
    final static String propFileName = "config.properties";
    InputStream inputStream;

    /**
     * change NULL value into zero double
     * @param value
     * @return
     */
    public Double changeNULLToZero(Double value) {
        return (null == value) ? 0.0 :value;
    }

    /**
     * Check if String can be cast in integer
     * @param str
     * @return
     */
    public boolean checkIfInteger(String str) {
        try
        {
            Integer.parseInt(str);
            return true;
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
    }

    /**
     * check if String can be cast in double
     * @param doubleValue
     * @return
     */
    public boolean checkIfDouble(String doubleValue){
        try
        {
            Double.parseDouble(doubleValue);
            LOG.debug("value {} is double format", doubleValue);
            return true;
        }
        catch(NumberFormatException e)
        {
            LOG.debug("value {} is not double format", doubleValue);
            return false;
        }
    }

    /**
     * Cacul operation
     * @param value
     * @param oldBalance
     * @return
     */
    public Double calcul(String value, Double oldBalance) {

        double result = oldBalance;
        String operator = value.replaceAll("(\\s+|\\d+|\\.)", "");

        if(checkIfDouble(value)){
            double num1 =Double.parseDouble(value);
            double num2= oldBalance;
            if (operator.equals("+")) {
                result = addCalCul(num1, num2);
            } else if (operator.equals("-")) {
                result = minusCalCul(num1, num2);
            } else if (operator.equals("")) {
                result = addCalCul(num1, num2);
            } else {
                LOG.error("operation no valid");
            }
        }
        return result;
    }

    /**
     * addition
     * @param num1 withdraw
     * @param oldBalance current balance
     * @return
     */
    public double addCalCul(double num1, double oldBalance) {
        double sum = num1 + oldBalance;
        return sum;
    }

    /**
     * Minus
     * @param withdraw withdraw
     * @param oldBalance current balance
     * @return
     */
    public double minusCalCul(double withdraw, double oldBalance) {
        double sum = 0.0;
        if( oldBalance <= 0 && withdraw <0) {
            if (oldBalance > withdraw)
                sum = withdraw - Math.abs(oldBalance);
            else
                sum = oldBalance -  Math.abs(withdraw);
        }
        else {
            if (withdraw <= 0 && oldBalance >=0)
                sum = Math.abs(oldBalance) - Math.abs(withdraw);
            else if (withdraw >= 0 && oldBalance <=0)
                sum = oldBalance - Math.abs(withdraw);
            else
                sum = Math.abs(oldBalance) - withdraw;
        }
        return sum;
    }

    /**
     * Method that allows you to recover the gap allow from the config page.
     * @param value
     * @return
     * @throws IOException
     */
    public int getAllowedWithdraw(String value) throws IOException{
        int resultValue = 0;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            String returnValue = properties.getProperty(value);
            if (checkIfInteger(returnValue)) {
                LOG.info("red value allowed {}", returnValue);
                resultValue = Integer.parseInt(returnValue);
            }

        } catch (Exception e) {
            LOG.error("Unexpected error", e);
        } finally {
            inputStream.close();
        }
        return resultValue;
    }

}
