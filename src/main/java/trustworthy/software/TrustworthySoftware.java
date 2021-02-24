package trustworthy.software;

import org.json.JSONException;
import trustworthy.software.utils.Product;

import java.io.IOException;

import static trustworthy.software.availabilityTest.AvailabilityTests.runAvailabilityTest;
import static trustworthy.software.cvss.CVSSTest.extractCVSSScore;
import static trustworthy.software.manalyzer.Manalyzer.getManalyzeReport;
import static trustworthy.software.utils.Constants.*;
import static trustworthy.software.winchecksec.Winchecksec.getWinCheckSecScores;

public class TrustworthySoftware {
    public static void main(String[] args){

        Product product = new Product();
        product.setExecutablePath(ZOOM_EXE);
        product.setParallelize(false);

        Product product1 = new Product();
        product1.setExecutablePath(NOTEPAD_EXE);
        product1.setParallelize(true);

        extractCVSSScore("Zoom", "zoom", "");
        extractCVSSScore("Microsoft", "word", "");
        extractCVSSScore("notepad-plus-plus", "Notepad\\+\\+", "7.6.6");
        extractCVSSScore("adobe", "acrobat_reader_dc", "20.012.20048");
        extractCVSSScore("mcafee", "livesafe", "");
        getManalyzeReport(product);
        try {
            runAvailabilityTest(product);
            runAvailabilityTest(product1);
            getWinCheckSecScores(NOTEPAD_EXE);

        } catch (IOException | JSONException |InterruptedException e) {
            e.printStackTrace();
        }
    }
}
