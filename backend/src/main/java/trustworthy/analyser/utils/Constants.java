package trustworthy.analyser.utils;

public final class Constants {

    // Storing the file path of each of the applications that will be tested.
    public static final String ZOOM_EXE = "src/main/resources/zoom/Zoom.exe";
    public static final String TEAMS_EXE = "C:\\Users\\shilp\\AppData\\Local\\Microsoft\\Teams\\current\\Teams.exe";
    public static final String NOTEPAD_EXE = "C:\\Users\\shilp\\Desktop\\Uni\\Project\\TrustworthySoftware\\backend\\src\\main\\resources\\notepad2\\Notepad2.exe";
    public static final String DISCORD_EXE = "C:\\Users\\shilp\\AppData\\Local\\Discord\\app-0.0.309\\Discord.exe";

    //The user agent for Web scraping
    public static final String USER_AGENT = "Chrome/51.0.2704.103";
    public static final String NVD_BASE_URL = "https://nvd.nist.gov";
    public static final String NVD_SEARCH_POSTFIX = "/products/cpe/search?";
    public static final String CPE_PREFIX = "cpe:2.3:a:";
    public static final String MANALYZER_URL = "https://manalyzer.org";
    public static final String WINCHECKSEC_EXE = "C:\\Users\\shilp\\Desktop\\Uni\\Project\\TrustworthySoftware\\backend\\src\\main\\resources\\winchecksec\\winchecksec.exe";

    public static final int NO_OF_TRIES = 5;
    public static final long NAIVE_TIMEOUT = 2000L;
    public static final long LONGER_TIMEOUT = 50000L;

    // Weightage for the CVSS severity ratings
    public static final int CRITICAL_WEIGHTAGE = 4;
    public static final int HIGH_WEIGHTAGE = 3;
    public static final int MEDIUM_WEIGHTAGE = 2;
    public static final int LOW_WEIGHTAGE = 1;

    // The weightage of each facet of trustworthiness
    public static int SECURITY_WEIGHTAGE = 20;
    public static int AVAILABILITY_WEIGHTAGE = 20;
    public static int SAFETY_WEIGHTAGE = 20;
    public static int RESILIENCE_WEIGHTAGE = 20;
    public static int RELIABILITY_WEIGHTAGE = 20;

    // Trustworthy Verdict - possible values
    public static final String VERY_HIGH = "Highly Trustworthy";
    public static final String HIGH = "Trustworthy";
    public static final String INCONCLUSIVE = "Inconclusive analysis";
    public static final String LOW = "Untrustworthy ";
    public static final String VERY_LOW = "Highly Untrustworthy";

}