package trustworthy.analyser.controller;

import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import trustworthy.analyser.data.AnalyserRequestObject;
import trustworthy.analyser.data.AnalyserResponseObject;
import trustworthy.analyser.service.AnalyserService;
import trustworthy.analyser.utils.Product;

import static trustworthy.analyser.utils.Constants.*;

@Api(value = "Analyser Controller", description = "Get a verdict on the trustworthiness of a software.")
@RestController
public class AnalyserController {

    /**
     * Calculates the trustworthiness of a product
     * @param requestObject - An object of the type AnalyserRequestObject
     * @return AnalyserResponseObject with the scores for each facet and the overall verdict set.
     */
    @ApiOperation(value = "Get the trustworthiness verdict of the software")
    @PostMapping(value = "/submit", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String submitProduct(@RequestBody AnalyserRequestObject requestObject) {

        Product product = new Product();
        product.setExecutablePath(requestObject.getExe());
        product.setVendorName(requestObject.getVendor());
        product.setProductName(requestObject.getProduct());
        product.setVersionNo(requestObject.getVersion());
        if(requestObject.getSecurity() + requestObject.getSafety() + requestObject.getAvailability() + requestObject.getReliability() + requestObject.getResiliency() == 0 ){
            product.setSecurity(SECURITY_WEIGHTAGE);
            product.setSafety(SAFETY_WEIGHTAGE);
            product.setAvailability(AVAILABILITY_WEIGHTAGE);
            product.setReliability(RELIABILITY_WEIGHTAGE);
            product.setResiliency(RESILIENCE_WEIGHTAGE);
        }else{
            product.setSecurity(requestObject.getSecurity());
            product.setSafety(requestObject.getSafety());
            product.setAvailability(requestObject.getAvailability());
            product.setReliability(requestObject.getReliability());
            product.setResiliency(requestObject.getResiliency());
        }


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
    @ApiOperation(value = "Calculate the security score out of 100")
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
    @ApiOperation(value = "Calculate the safety score out of 100")
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
    @ApiOperation(value = "Calculate the availability score out of 100")
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
    @ApiOperation(value = "Calculate the resiliency score out of 100")
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
        return gson.toJson(response.getResiliencyScore());
    }

    /**
     * Calculates the reliability score for a product
     * @param requestObject - An object of the type AnalyserRequestObject
     * @return The reliability score
     */
    @ApiOperation(value = "Calculate the reliability score out of 100")
    @PostMapping(value = "/calculateReliability")
    public String getReliability(@RequestBody AnalyserRequestObject requestObject) {

        Product product = new Product();
        product.setExecutablePath(requestObject.getExe());
        product.setVendorName(requestObject.getVendor());
        product.setProductName(requestObject.getProduct());
        product.setVersionNo(requestObject.getVersion());
        product.setReliability(100);

        AnalyserService service = new AnalyserService();
        AnalyserResponseObject response = service.runReliabilityTest(product);

        Gson gson = new Gson();
        return gson.toJson(response.getReliabilityScore());
    }
}
