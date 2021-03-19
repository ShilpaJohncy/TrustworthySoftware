package trustworthy.analyser.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import trustworthy.analyser.dataObjects.AnalyserRequestObject;
import trustworthy.analyser.dataObjects.AnalyserResponseObject;
import trustworthy.analyser.service.AnalyserService;
import trustworthy.analyser.utils.Product;

@RestController
public class AnalyserController {

    /**
     * Calculates the trustworthiness of a product
     * @param requestObject - An object of the type AnalyserRequestObject
     * @return AnalyserResponseObject with the scores for each facet and the overall verdict set.
     */
    @PostMapping(value = "/submit")
    public String submitProduct(@RequestBody AnalyserRequestObject requestObject) {

        Product product = new Product();
        product.setExecutablePath(requestObject.getExe());
        product.setVendorName(requestObject.getVendor());
        product.setProductName(requestObject.getProduct());
        product.setVersionNo(requestObject.getVersion());
        product.setSecurity(requestObject.getSecurity());
        product.setSafety(requestObject.getSafety());
        product.setAvailability(requestObject.getAvailability());
        product.setReliability(requestObject.getReliability());
        product.setResiliency(requestObject.getResiliency());

        AnalyserService service = new AnalyserService();
        AnalyserResponseObject response = service.runTests(product);

        Gson gson = new Gson();
        return gson.toJson(response);
    }

    /**
     * Calculates the security score for a product
     * @param requestObject - An object of the type AnalyserRequestObject
     * @return The security score
     */
    @PostMapping(value = "/calculateSecurity")
    public String getSecurity(@RequestBody AnalyserRequestObject requestObject) {

        Product product = new Product();
        product.setExecutablePath(requestObject.getExe());
        product.setVendorName(requestObject.getVendor());
        product.setProductName(requestObject.getProduct());
        product.setVersionNo(requestObject.getVersion());
        product.setSecurity(100);

        AnalyserService service = new AnalyserService();
        AnalyserResponseObject response = service.runSecurityTest(product);

        Gson gson = new Gson();
        return gson.toJson(response.getSecurityScore());
    }

    /**
     * Calculates the safety score for a product
     * @param requestObject - An object of the type AnalyserRequestObject
     * @return The safety score
     */
    //TODO: Return the safety verdict with the score
    @PostMapping(value = "/calculateSafety")
    public String getSafety(@RequestBody AnalyserRequestObject requestObject) {

        Product product = new Product();
        product.setExecutablePath(requestObject.getExe());
        product.setVendorName(requestObject.getVendor());
        product.setProductName(requestObject.getProduct());
        product.setVersionNo(requestObject.getVersion());
        product.setSafety(100);

        AnalyserService service = new AnalyserService();
        AnalyserResponseObject response = service.runSafetyTest(product);

        Gson gson = new Gson();
        return gson.toJson(response.getSafetyScore());
    }

    /**
     * Calculates the availability score for a product
     * @param requestObject - An object of the type AnalyserRequestObject
     * @return The availability score
     */
    @PostMapping(value = "/calculateAvailability")
    public String getAvailability(@RequestBody AnalyserRequestObject requestObject) {

        Product product = new Product();
        product.setExecutablePath(requestObject.getExe());
        product.setVendorName(requestObject.getVendor());
        product.setProductName(requestObject.getProduct());
        product.setVersionNo(requestObject.getVersion());
        product.setAvailability(100);

        AnalyserService service = new AnalyserService();
        AnalyserResponseObject response = service.runAvailabilityTest(product);

        Gson gson = new Gson();
        return gson.toJson(response.getAvailabilityScore());
    }

    /**
     * Calculates the resiliency score for a product
     * @param requestObject - An object of the type AnalyserRequestObject
     * @return The resiliency score
     */
    @PostMapping(value = "/calculateResiliency")
    public String getResiliency(@RequestBody AnalyserRequestObject requestObject) {

        Product product = new Product();
        product.setExecutablePath(requestObject.getExe());
        product.setVendorName(requestObject.getVendor());
        product.setProductName(requestObject.getProduct());
        product.setVersionNo(requestObject.getVersion());
        product.setResiliency(100);

        AnalyserService service = new AnalyserService();
        AnalyserResponseObject response = service.runResiliencyTest(product);

        Gson gson = new Gson();
        return gson.toJson(response.getResilienceScore());
    }
}
