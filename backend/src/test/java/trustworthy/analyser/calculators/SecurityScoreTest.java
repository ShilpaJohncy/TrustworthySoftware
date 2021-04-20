package trustworthy.analyser.calculators;

import org.junit.Test;
import trustworthy.analyser.utils.Product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static trustworthy.analyser.calculators.SecurityScore.runSecurityTests;
import static trustworthy.analyser.utils.Constants.ZOOM_EXE;

public class SecurityScoreTest {
    @Test
    public void securityTestForZoom(){
        Product product = new Product();
        product.setExecutablePath(ZOOM_EXE);
        product.setVendorName("Zoom");
        product.setProductName("zoom");
        product.setVersionNo("4.6.10");
        assertEquals(73, runSecurityTests(product), 0);
    }

    @Test
    public void securityTestForAppWithSecurityFeaturesOff(){
        final String failAllTheTime = "C:\\Users\\shilp\\Desktop\\Uni\\Project\\TrustworthySoftware\\backend\\src\\test\\resources\\TestApps\\AppFailingAllTheTime.exe";
        Product testProduct = new Product();
        testProduct.setExecutablePath(failAllTheTime);
        assertEquals(31, runSecurityTests(testProduct), 0);
        assertFalse(testProduct.isCfg());
        assertFalse(testProduct.isAslr());
        assertTrue(testProduct.isDynamicBase());
    }

}