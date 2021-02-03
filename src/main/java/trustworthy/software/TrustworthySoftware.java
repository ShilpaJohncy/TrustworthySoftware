package trustworthy.software;


import static trustworthy.software.security.CVSSTest.*;

public class TrustworthySoftware {
    public static void main(String[] args) {
        extractCVSSScore("Zoom", "zoom", "");
//        extractCVSSScore("Microsoft", "Teams", "");
        extractCVSSScore("notepad-plus-plus", "Notepad\\+\\+", "7.6.6");
    }
}
