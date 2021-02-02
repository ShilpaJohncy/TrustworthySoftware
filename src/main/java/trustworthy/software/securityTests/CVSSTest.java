package trustworthy.software.securityTests;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;

import java.io.IOException;
import java.util.logging.Logger;

import static trustworthy.software.utils.Constants.*;


public class CVSSTest {
    public static void extractCVSSScore(String url){
        try{
            Connection.Response searchBoxResponse = Jsoup.connect(url)
                    .method(Connection.Method.GET)
                    .userAgent(USER_AGENT)
                    .execute();

            FormElement searchBox = (FormElement)searchBoxResponse.parse()
                    .select("div > form#cpeSearchForm").first();

            Element searchField = searchBox.select("#SearchTextBox").first();
            searchField.val("Zoom");

            Connection.Response loginActionResponse = searchBox.submit()
                    .cookies(searchBoxResponse.cookies())
                    .userAgent(USER_AGENT)
                    .execute();

            System.out.println(loginActionResponse.body());

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
