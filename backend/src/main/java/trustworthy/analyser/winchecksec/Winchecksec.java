package trustworthy.analyser.winchecksec;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import trustworthy.analyser.utils.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static trustworthy.analyser.utils.Constants.*;

public class Winchecksec {

    /**
     * Run Winchecksec application and calculate the response for each field tested.
     * Winchecksec tests for 13 flags in the PE, each of which attribute to the PE being secure, safe, and resilient.
     * @param product - The product who's flags are being tested.
     * @throws IOException
     * @throws JSONException
     */
    public static void getWinCheckSecScores(Product product) throws IOException, JSONException {
        if(!product.isRanWinchecksec()){
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(WINCHECKSEC_EXE + " --json " + product.getExecutablePath() );
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));

            // Read the output from the command
            String s;
            String output = "";

            //Get the output from the command line
            while ((s = stdInput.readLine()) != null) {
                output = output.concat(s);
            }
            // Format response to rqd JSON format
            output = output.substring(1, output.length() - 1);

            // Set the response to the response object
            JSONObject jsonResponse = new JSONObject(output);
            Gson gson = new GsonBuilder().create();
            WinchecksecResponse response = gson.fromJson(String.valueOf(jsonResponse), WinchecksecResponse.class);
            setWinchecksecPresence(response, product);
            product.setRanWinchecksec(true);
        }
    }

    /**
     * Check if the flag is present, if it is set the corresponding product field to true
     * Else false
     * @param response - The winchecksec response
     * @param product - The product who's winchecksec is calculated.
     */
    private static void setWinchecksecPresence(WinchecksecResponse response, Product product) {
        product.setAslr(checkPresence(response.getMitigations().getAslr().getPresence()));
        product.setDynamicBase(checkPresence(response.getMitigations().getDynamicBase().getPresence()));
        product.setHighEntropyVA(checkPresence(response.getMitigations().getHighEntropyVA().getPresence()));
        product.setForceIntegrity(checkPresence(response.getMitigations().getForceIntegrity().getPresence()));
        product.setIsolation(checkPresence(response.getMitigations().getIsolation().getPresence()));
        product.setNx(checkPresence(response.getMitigations().getNx().getPresence()));
        product.setSeh(checkPresence(response.getMitigations().getSeh().getPresence()));
        product.setCfg(checkPresence(response.getMitigations().getCfg().getPresence()));
        product.setRfg(checkPresence(response.getMitigations().getRfg().getPresence()));
        product.setSafeSEH(checkPresence(response.getMitigations().getSafeSEH().getPresence()));
        product.setGs(checkPresence(response.getMitigations().getGs().getPresence()));
        product.setAuthenticode(checkPresence(response.getMitigations().getAuthenticode().getPresence()));
        product.setDotNET(checkPresence(response.getMitigations().getDotNET().getPresence()));
    }

    /**
     * If the flag value is present, return true. Else return false.
     * @param presence - The flag's presence for the product
     * @return - boolean indicating presence
     */
    private static boolean checkPresence(String presence){
        if(presence.equals("Present")){
            return true;
        }else {
            return false;
        }
    }
}
