package trustworthy.software;


import static trustworthy.software.securityTests.CVSSTest.*;

public class TrustworthySoftware {
    public static void main(String[] args) {
        extractCVSSScore("Zoom", "zoom", "4.6.10");
        extractCVSSScore("Microsoft", "Teams", "");
        extractCVSSScore("notepad-plus-plus", "Notepad\\+\\+", "");
    }
}
