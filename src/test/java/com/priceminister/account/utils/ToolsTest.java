package com.priceminister.account.utils;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by TherPi on 30/03/2018.
 */
public class ToolsTest {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(ToolsTest.class);
    public Tools tools = new Tools();

    @Test
    public void checkIfDouble() throws Exception {
        Assert.assertTrue(tools.checkIfDouble("42"));
        Assert.assertFalse(tools.checkIfDouble(")àçéi&àéke&j&joi&péoj&o"));
        Assert.assertFalse(tools.checkIfDouble("123e4567-e89b-12d3-a456-556642440000"));
        Assert.assertTrue(tools.checkIfDouble("42.987543"));
    }

    @Test
    public void calculOperationAddition() {
        Assert.assertEquals(tools.calcul("100", 100.0).toString(), "200.0");
        Assert.assertEquals(tools.calcul("+100", 100.0).toString(), "200.0");
        //--- no addition, we save old balance
        Assert.assertEquals(tools.calcul("çà_àç_çà", 100.0).toString(), "100.0");
    }

    @Test
    public void calculOperationSubstraction() {
        Assert.assertEquals(tools.calcul("-100", 100.0).toString().toString(), "0.0");
        Assert.assertEquals(tools.calcul("-100", 200.0).toString(), "100.0");
        Assert.assertEquals(tools.calcul("-100", -200.0).toString(), "-300.0");
        Assert.assertEquals(tools.calcul("-100", 0.0).toString(), "-100.0");
        Assert.assertEquals(tools.calcul("-100", -0.0).toString(), "-100.0");
        Assert.assertEquals(tools.calcul("-300", -200.0).toString(), "-500.0");
        Assert.assertEquals(tools.calcul("-100", +100.0).toString(), "0.0");
        //--- no addition, we save old balance
        Assert.assertEquals(tools.calcul("çà_àç_çà", -100.0).toString(), "-100.0");
        Assert.assertEquals(tools.calcul("çà_àç_çà", 100.0).toString(), "100.0");
    }

    @Test
    public void DoubleCalculOperationSubstraction() {
        Assert.assertEquals(tools.minusCalCul(100.0, 100.0), 0.0, 0);
        Assert.assertEquals(tools.minusCalCul(-100, 200.0), 100.0, 0);
        Assert.assertEquals(tools.minusCalCul(100, -200.0), -300.0, 0);
        Assert.assertEquals(tools.minusCalCul(-100, 0.0), -100.0, 0);
        Assert.assertEquals(tools.minusCalCul(-100, -0.0), -100.0, 0);
        Assert.assertEquals(tools.minusCalCul(-300, -200.0), -500.0, 0);
        Assert.assertEquals(tools.minusCalCul(-100, +100.0), 0.0, 0);
        Assert.assertEquals(tools.minusCalCul(150, -200.0), -350.0, 0);
        Assert.assertEquals(tools.minusCalCul(-200, 150.0), -50.0, 0);
        Assert.assertEquals(tools.minusCalCul(200, 150.0), -50.0, 0);
    }

    @Test
    public void DoublecalculOperationAddition() {
        Assert.assertEquals(tools.addCalCul(100, 100.0), 200.0, 0);
        Assert.assertEquals(tools.addCalCul(+100, 100.0), 200.0, 0);
    }

    @Test
    public void checkDoubleNULLValue()
    {
        Assert.assertEquals(tools.changeNULLToZero(null), 0.0, 0);
        Assert.assertEquals(tools.changeNULLToZero(100d), 100d, 0);
    }

    @Test
    public void readGAPAllowed() throws Exception
    {
        Assert.assertEquals(tools.getAllowedWithdraw("GAP_ALLOWED"), -800);
        Assert.assertEquals(tools.getAllowedWithdraw("kqjsdqsjdmsjqdmqjml"), 0);
    }
}
