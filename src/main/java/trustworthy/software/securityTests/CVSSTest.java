package trustworthy.software.securityTests;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.logging.Logger;

import static trustworthy.software.utils.Constants.*;


public class CVSSTest {

    public static void extractCVSSScore(String vendor, String product, String versionNo){
        try{
            // Connect to the NVD search webpage
            Connection.Response searchBoxResponse = Jsoup.connect(NVD_BASE_URL + NVD_SEARCH_POSTFIX)
                    .method(Connection.Method.GET)
                    .userAgent(USER_AGENT)
                    .execute();

            // Locate the search form within the page
            FormElement searchBox = (FormElement)searchBoxResponse.parse()
                    .selectFirst("div > form#cpeSearchForm");

            // Enter the vendor name, product name and version number of the app that you want to test
            Element searchField = searchBox.selectFirst("#SearchTextBox");
            searchField.val(CPE_PREFIX + vendor + ":" + product + ":" + versionNo);

            // Execute the search
            Connection.Response searchResults = searchBox.submit()
                    .cookies(searchBoxResponse.cookies())
                    .userAgent(USER_AGENT)
                    .execute();

            // Extracting the links to the CVEs page from the search results
            Elements searchLinks = searchResults.parse().select("table[class$=table table-striped table-hover] > tbody > tr > td > div[class$=row] > div[class$=col-lg-12] > a[class$=btn btn-sm] ");
            String link = searchLinks.attr("href");
            System.out.println(link);

            // Go to the CVE page extracted
            Connection.Response CVEPage = Jsoup.connect(NVD_BASE_URL + link)
                    .method(Connection.Method.GET)
                    .userAgent(USER_AGENT)
                    .execute();

            // Extract the CVSS score for the first entry from the table (first since its arranged by date)
            Element CVSSSeverity = CVEPage.parse().selectFirst("#cvss3-link > a");
            String score = CVSSSeverity.text();
            System.out.println(score);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
