package trustworthy.software;


import static trustworthy.software.securityTests.CVSSTest.*;

public class TrustworthySoftware {
    public static void main(String[] args) {
        extractCVSSScore("https://nvd.nist.gov/products/cpe/search?");
    }
}
