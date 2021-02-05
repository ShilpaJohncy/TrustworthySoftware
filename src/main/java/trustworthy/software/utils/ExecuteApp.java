package trustworthy.software.utils;

import java.io.IOException;

public class ExecuteApp {

    public static void executeApp(String executablePath) {
        try {
            Runtime runTime = Runtime.getRuntime();
            Process process = runTime.exec(executablePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
