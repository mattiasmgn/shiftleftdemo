package se.freefarm.fruit.soap;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import se.freefarm.fruit.rest.PriceInfoController2;

public class AppTest
{
    @Test
    public void testOfSoapApp()
    {
        PriceInfoSoapEndpoint p = new PriceInfoSoapEndpoint();
        assertEquals("Wrong price of apples", p.getFruitPrice("apple"),40.0,1);
        assertEquals("Wrong price of bananas", p.getFruitPrice("banana"),70.0,1);
        assertEquals("Wrong VAT", p.addVAT(1),1.25,0.1);
    }

}
