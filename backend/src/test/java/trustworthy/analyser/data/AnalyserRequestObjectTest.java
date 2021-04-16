package trustworthy.analyser.data;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnalyserRequestObjectTest {

    @Test
    public void setFieldsTest(){
        AnalyserRequestObject requestObject = new AnalyserRequestObject();
        requestObject.setExe("Test path to exe");
        requestObject.setVendor("Test vendor");
        requestObject.setProduct("Test product");
        requestObject.setVersion("1.0.0");
        requestObject.setParallelize(false);
        requestObject.setSecurity(100);
        requestObject.setSafety(100);
        requestObject.setAvailability(100);
        requestObject.setReliability(100);
        requestObject.setResiliency(100);

        assertEquals(100, requestObject.getSecurity());
        assertEquals(100, requestObject.getSafety());
        assertEquals(100, requestObject.getAvailability());
        assertEquals(100, requestObject.getResiliency());
        assertEquals(100, requestObject.getReliability());
        assertFalse(requestObject.isParallelize());
        assertSame("Test path to exe", requestObject.getExe());
        assertEquals("Test vendor", requestObject.getVendor());
        assertEquals("Test product", requestObject.getProduct());
        assertEquals("1.0.0", requestObject.getVersion());
    }



}