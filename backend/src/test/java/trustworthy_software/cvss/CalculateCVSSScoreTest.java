package trustworthy_software.cvss;

import trustworthy_software.utils.Product;

import static org.junit.Assert.*;
import org.junit.Test;

import static trustworthy_software.cvss.CalculateCVSSScore.calculateCVSSScore;
import static trustworthy_software.utils.Constants.ZOOM_EXE;

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

//        Product product = new Product();
//        product.setExecutablePath(ZOOM_EXE);
//        product.setVendorName("Microsoft");
//        product.setProductName("word");
//        product.setVersionNo("");
//        product.setParallelize(false);
//        extractCVSSScore("Microsoft", "word", "");
//        extractCVSSScore("notepad-plus-plus", "Notepad\\+\\+", "7.6.6");
//        extractCVSSScore("adobe", "acrobat_reader_dc", "20.012.20048");
//        extractCVSSScore("mcafee", "livesafe", "");

        calculateCVSSScore(product);
        assertEquals(product.getCvssScore(), 8.75, 0);
    }

    @Test
    public void checkValidTestCaseWithoutVersionNo(){

        Product product = new Product();
        product.setExecutablePath(ZOOM_EXE);
        product.setVendorName("Zoom");
        product.setProductName("zoom");
        product.setParallelize(false);

        calculateCVSSScore(product);
        assertEquals(8.75, product.getCvssScore() , 0);
    }
}
