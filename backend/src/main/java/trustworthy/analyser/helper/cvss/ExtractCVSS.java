package trustworthy.analyser.helper.cvss;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;

import static trustworthy.analyser.utils.Constants.*;


public class ExtractCVSS {

    /**
     * Function to create a map to hold the number of vulnerabilities for each severity category.
     * The number of vulnerabilities for each is initially set to 0.
     *
     * @return - The initialised map
     */
    private static HashMap<CVSSSeverity, Integer> initialiseMap(){
        HashMap<CVSSSeverity, Integer> scoreRatings = new HashMap<>();
        scoreRatings.put(CVSSSeverity.CRITICAL, 0);
        scoreRatings.put(CVSSSeverity.HIGH, 0);
        scoreRatings.put(CVSSSeverity.MEDIUM, 0);
        scoreRatings.put(CVSSSeverity.LOW, 0);
        return scoreRatings;
    }


    /**
     * Function to webscrape the NVD website and extract the CVSS v3.1 scores for product specified.
     *
     * @param vendor - The vendor of the product
     * @param product - The product name
     * @param versionNo - The version of the product to be tested
     * @return - A map with the number of vulnerabilities present in the product for each CVSS severity category.
     *         - If the NVD does not hold any data for the product specified, return null.
     */
    public static HashMap<CVSSSeverity, Integer> extractCVSSScore(String vendor, String product, String versionNo){
        try{
            HashMap<CVSSSeverity, Integer> scoreRatings = initialiseMap();
            Element searchLinks;
            String[] CVSSScoreArray;

            // Connect to the NVD search webpage
            Connection.Response searchBoxResponse = Jsoup.connect(NVD_BASE_URL + NVD_SEARCH_POSTFIX)
                    .timeout(0)
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
                    .timeout(0)
                    .cookies(searchBoxResponse.cookies())
                    .userAgent(USER_AGENT)
                    .execute();

            // If the search wasn't successful, return null
            if(searchResults == null){
                return null;
            }

            // Extracting the links to the CVEs page from the search results
            // If version number is not provided, get the CVSS score for the latest available product version
            if(versionNo == null || versionNo.isEmpty()) {
                Document parsedSearchResult = searchResults.parse();
                Element lastPage = parsedSearchResult.select("ul.pagination > li > a").last();

                // If there are multiple pages, go to the last page, last link
                if(lastPage != null) {
                    searchResults = Jsoup.connect(NVD_BASE_URL + lastPage.attr("href"))
                            .timeout(0)
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

            String link = (searchLinks == null) ? null : searchLinks.attr("href");

            if(link != null) {
                // Go to the CVE page extracted
                Connection.Response CVEPage = Jsoup.connect(NVD_BASE_URL + link)
                        .timeout(0)
                        .method(Connection.Method.GET)
                        .userAgent(USER_AGENT)
                        .execute();

                // Extract the CVSS score for the first 20 entries (if 20 exists)
                Elements vulnerabilityList = CVEPage.parse().select("table[class$=table table-striped table-hover] > tbody > tr");

                if(vulnerabilityList != null){
                    // Calculate the average of all the CVSS scores - unweighted
                    for(Element vulnerability : vulnerabilityList) {
                        CVSSScoreArray = ((vulnerability.select("#cvss3-link > a").text()).split(" "));
                        CVSSSeverity severityClass = CVSSSeverity.valueOf(CVSSScoreArray[1]);
                        scoreRatings.put(severityClass, scoreRatings.get(severityClass) + 1);
                    }
                }
                return scoreRatings;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
