package se.freefarm.fruit.rest;

import org.junit.Test;
import se.freefarm.fruit.rest.PriceInfoController2;

import static org.junit.Assert.assertEquals;

public class AppTest
{
    @Test
    public void testOfRestApp()
    {
        PriceInfoController2 p = new PriceInfoController2();
        assertEquals("There should not be a discount when only 2 fruits are bought", 0,p.getDiscount(2),0.01);
        assertEquals("There should a 40% discount when 3 fruits are bought", 0.4,p.getDiscount(3),0.01);
        assertEquals("There should a 40% discount when 4 fruits are bought", 0.4,p.getDiscount(4),0.01);
    }

}
