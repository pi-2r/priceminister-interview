package com.priceminister.account.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by TherPi on 30/03/2018.
 */
public class Tools {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(Tools.class);

    public Double changeNULLToZero(Double value) {
        return (null == value) ? 0.0 :value;
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
     * @param num1 withdraw
     * @param oldBalance current balance
     * @return
     */
    public double minusCalCul(double num1, double oldBalance) {
        double sum = 0.0;
        if( oldBalance <= 0 && num1 <0) {
            if (oldBalance > num1)
                sum = num1 - Math.abs(oldBalance);
            else
                sum = oldBalance -  Math.abs(num1);
        }
        else {
            sum = Math.abs(oldBalance) - Math.abs(num1);
        }
        return sum;
    }
}
