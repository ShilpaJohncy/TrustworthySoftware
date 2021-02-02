package trustworthy.software.securityTests;

import trustworthy.software.utils.*;
import us.springett.cvss.CvssV3;
import us.springett.cvss.CvssV3.*;
import us.springett.cvss.Score;

import static trustworthy.software.utils.ExecuteApp.executeApp;


public class CVSSTest {

    public static void getScore() {
        CvssV3 cvssV3 = new CvssV3()
                .attackVector(AttackVector.NETWORK)
                .attackComplexity(AttackComplexity.LOW)
                .privilegesRequired(PrivilegesRequired.HIGH)
                .userInteraction(UserInteraction.NONE)
                .scope(Scope.UNCHANGED)
                .confidentiality(CIA.HIGH)
                .integrity(CIA.HIGH)
                .availability(CIA.HIGH);

        Score score = cvssV3.calculateScore();

//        executeApp(Constants.ZOOM_EXE);
        executeApp(Constants.NOTEPAD_EXE);
//        executeApp(Constants.TEAMS_EXE);
//        executeApp(Constants.DISCORD_EXE);
    }



}
