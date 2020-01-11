package se.freefarm.fruit.soapclent;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import se.freefarm.fruit.*;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;


public class SoapClient
{
    public static void main( String[] args )
    {
        new SoapClient().doSOAPCall(2,"banana");
    }


    double doSOAPCall(int units, String name) {
        System.out.println("Calling SOAP with arguments "+units  + ", "+name+" ...");
        Fruit_Service service = new Fruit_Service();
        Fruit fruitSOAP = service.getFruitSOAP();
        clientsettings(fruitSOAP);
        ObjectFactory of = new ObjectFactory();
        PriceDetailsRequest priceDetailsRequest = of.createPriceDetailsRequest();
        priceDetailsRequest.setFruit(name);
        priceDetailsRequest.setUnits(units);
        PriceDetailsResponse priceDetailsResponse = fruitSOAP.priceInformation(priceDetailsRequest);
        PriceInformationDTO priceInformationDTO = priceDetailsResponse.getPriceInformation();
        System.out.println("Response:");
        System.out.println("Fruit: "+priceInformationDTO.getFruit());
        System.out.println("Price: "+priceInformationDTO.getPrice());
        System.out.println("Units: "+priceInformationDTO.getUnits());
        System.out.println("Curreny: "+priceInformationDTO.getCurrency());
        return priceInformationDTO.getPrice();
    }
    void clientsettings(Fruit port) {
        Client client = ClientProxy.getClient(port);

        LoggingOutInterceptor outLog = new LoggingOutInterceptor();
        outLog.setLimit(-1);
        outLog.setPrettyLogging(true);
        client.getOutInterceptors().add(outLog);

        LoggingInInterceptor inLog = new LoggingInInterceptor();
        inLog.setLimit(-1);
        inLog.setPrettyLogging(true);
        client.getInInterceptors().add(inLog);
        // Set the endpoint URL if other than default
        //client.getRequestContext().put(Message.ENDPOINT_ADDRESS, "http://www.yoururl.com/priceinfosoap/ws") ;

    }
}
