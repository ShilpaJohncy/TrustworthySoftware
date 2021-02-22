package trustworthy.software;


import org.json.JSONException;

import java.io.IOException;

import static trustworthy.software.cvss.CVSSTest.extractCVSSScore;
import static trustworthy.software.winchecksec.Winchecksec.getWinCheckSecScores;
import static trustworthy.software.utils.Constants.NOTEPAD_EXE;

public class TrustworthySoftware {
    public static void main(String[] args) {
        extractCVSSScore("Zoom", "zoom", "");
        extractCVSSScore("Microsoft", "word", "");
        extractCVSSScore("notepad-plus-plus", "Notepad\\+\\+", "7.6.6");
        extractCVSSScore("adobe", "acrobat_reader_dc", "20.012.20048");
        extractCVSSScore("mcafee", "livesafe", "");
        try {
            getWinCheckSecScores(NOTEPAD_EXE);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }
}
