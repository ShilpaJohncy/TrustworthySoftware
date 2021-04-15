package trustworthy.analyser.helpers.cvss;

import trustworthy.analyser.utils.Product;

import static org.junit.Assert.*;
import org.junit.Test;

import static trustworthy.analyser.helpers.cvss.CalculateCVSSScore.calculateCVSSScore;
import static trustworthy.analyser.utils.Constants.ZOOM_EXE;

/**
 * Test cases to test possibly:
 *  1) Valid test case
 *  3) Product that doesn't exist
 *  4) With version number
 *  5) Without version number
 *  6) With vendor name
 *  7) Without vendor name
 *  8) With Product name
 *  9) Without product name
 */
public class CalculateCVSSScoreTest {

    @Test
    public void checkValidTestCase(){

        Product product = new Product();
        product.setExecutablePath(ZOOM_EXE);
        product.setVendorName("Zoom");
        product.setProductName("zoom");
        product.setVersionNo("4.6.10");
        product.setParallelize(false);
        calculateCVSSScore(product);
        assertEquals(7.5, product.getCvssScore(),0);
    }

    @Test
    public void checkValidTestCaseWithoutVersionNo(){
        Product product = new Product();
        product.setExecutablePath(ZOOM_EXE);
        product.setVendorName("Zoom");
        product.setProductName("zoom");
        product.setParallelize(false);

        calculateCVSSScore(product);
        assertEquals(7.5, product.getCvssScore() , 0);
    }
}
