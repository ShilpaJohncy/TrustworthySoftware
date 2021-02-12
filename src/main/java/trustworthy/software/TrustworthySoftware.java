package trustworthy.software;


import java.io.IOException;

import static trustworthy.software.security.CVSSTest.*;
import static trustworthy.software.resiliency.BinaryStaticAnalyser.*;

public class TrustworthySoftware {
    public static void main(String[] args) throws IOException {
        extractCVSSScore("Zoom", "zoom", "");
        extractCVSSScore("Microsoft", "word", "");
        extractCVSSScore("notepad-plus-plus", "Notepad\\+\\+", "7.6.6");
        extractCVSSScore("adobe", "acrobat_reader_dc", "20.012.20048");
        extractCVSSScore("mcafee", "livesafe", "");
        extractCVSSScore("google", "chrome", "");
        manalyzeScoreExtraction();
    }
}
