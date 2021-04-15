package trustworthy.analyser.controller;

import org.junit.Test;
import trustworthy.analyser.data.AnalyserRequestObject;

import static org.junit.jupiter.api.Assertions.*;

public class AnalyserControllerTest {
    AnalyserRequestObject request;

    public void setUp(){
        request = new AnalyserRequestObject();
        request.setExe("Path");
        request.setVendor("vendor");
        request.setProduct("product");
    }
    @Test
    public void testSubmitApi() {
        setUp();
        String mockResponse = "{\"securityScore\":80.0,\"safetyScore\":23.0,\"availabilityScore\":-1.0,\"resiliencyScore\":-10.0,\"reliabilityScore\":-1.0,\"trustworthyScore\":18,\"verdict\":\"Highly Untrustworthy\",\"confidence\":12.7}";
        AnalyserController controller = new AnalyserController();
        assertEquals(mockResponse, controller.submitProduct(request));
    }

    @Test
    public void testSecurityApi(){
        setUp();
        String mockResponse = "80.0";
        AnalyserController controller = new AnalyserController();
        assertEquals(mockResponse, controller.getSecurity(request));
    }

    @Test
    public void testSafetyApi(){
        setUp();
        String mockResponse = "23.0";
        AnalyserController controller = new AnalyserController();
        assertEquals(mockResponse, controller.getSafety(request));
    }

    @Test
    public void testAvailabilityApi(){
        setUp();
        String mockResponse = "-1.0";
        AnalyserController controller = new AnalyserController();
        assertEquals(mockResponse, controller.getAvailability(request));
    }

    @Test
    public void testResiliencyApi(){
        setUp();
        String mockResponse = "-10.0";
        AnalyserController controller = new AnalyserController();
        assertEquals(mockResponse, controller.getResiliency(request));
    }

    @Test
    public void testReliabilityApi(){
        setUp();
        String mockResponse = "0.0";
        AnalyserController controller = new AnalyserController();
        assertEquals(mockResponse, controller.getReliability(request));
    }
}