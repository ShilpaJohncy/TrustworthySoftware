package trustworthy.software.security;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static trustworthy.software.utils.Constants.*;


public class CVSSTest {

    public static void extractCVSSScore(String vendor, String product, String versionNo){
        try{

            Element searchLinks;
            List<Double> scores = new ArrayList<>();
            String[] CVSSScoreArray;
            double totalScore = 0.0;
            DecimalFormat df = new DecimalFormat("#.#");

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

            // If version number is not provided, get the CVSS score for the latest available product version
            if(versionNo.isEmpty()) {
                Document parsedSearchResult = searchResults.parse();
                Element lastPage = parsedSearchResult.select("ul.pagination > li > a").last();

                // If there are multiple pages, go to the last page, last link
                if(lastPage != null) {
                    searchResults = Jsoup.connect(NVD_BASE_URL + lastPage.attr("href"))
                            .method(Connection.Method.GET)
                            .userAgent(USER_AGENT)
                            .execute();
                    parsedSearchResult = searchResults.parse();
                }
                searchLinks = parsedSearchResult.select("table[class$=table table-striped table-hover] > tbody > tr > td > div[class$=row] > div[class$=col-lg-12] > a[class$=btn btn-sm] ").last();

            }else {
                searchLinks = searchResults.parse()
                        .selectFirst("table[class$=table table-striped table-hover] > tbody > tr > td > div[class$=row] > div[class$=col-lg-12] > a[class$=btn btn-sm]");
            }
            String link = searchLinks.attr("href");

            if(link != null) {
                // Go to the CVE page extracted
                Connection.Response CVEPage = Jsoup.connect(NVD_BASE_URL + link)
                        .method(Connection.Method.GET)
                        .userAgent(USER_AGENT)
                        .execute();

                // Extract the CVSS score for the first 20 entries (if 20 exists)
                Elements vulnerabilityList = CVEPage.parse().select("table[class$=table table-striped table-hover] > tbody > tr");

                if(vulnerabilityList != null){
                    // Calculate the average of all the CVSS scores - unweighted
                    for(Element vulnerability : vulnerabilityList) {
                        CVSSScoreArray = ((vulnerability.select("#cvss3-link > a").text()).split(" "));
                        scores.add(Double.parseDouble(CVSSScoreArray[0]));
                    }
                    for(int i = 0; i < scores.size(); i++){
                        totalScore += scores.get(i);
                    }
                    totalScore /= scores.size();
                }
                System.out.println("Score = " + Double.valueOf(df.format(totalScore)));
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
