package trustworthy.analyser.helpers.winchecksec;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WinchecksecResponseTest {

    @Test
    public void setFieldsTest(){
        WinchecksecResponse response = new WinchecksecResponse();
        response.setPath("Some path");
        response.setMitigations(null);
        assertEquals("Some path", response.getPath());
        assertNull(response.getMitigations());
    }
}