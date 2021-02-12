package trustworthy.software.resiliency;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static trustworthy.software.utils.Constants.*;

public class BinaryStaticAnalyser {

    public static void manalyzeScoreExtraction() throws IOException {

//        Document index = Jsoup.connect("http://direct.infohound.net/tidy/").get();
////        FormElement form = index.select("[name=tidy]").;
//        FormElement form = ((FormElement) index.select("[name=tidy]").first());
//
//        Connection post = form.submit();
//
//
////        File uploadFile = ParseTest.getFile("/htmltests/google-ipod.html");
//        FileInputStream stream = new FileInputStream("C:\\Users\\shilp\\Desktop\\Zuly\\182_negative_ratings.html");
//
//        Connection.KeyVal fileData = post.data("_file");
//        fileData.value("check.html");
//        fileData.inputStream(stream);
//
//        Connection.Response res;
//        try {
//            res = post.execute();
//        } finally {
//            stream.close();
//        }
//
//        Document out = res.parse();
//        System.out.println("OUT\n" + out);

         Document index = Jsoup.connect(MANALYZER_URL).get();
//        FormElement form = index.select("[name=tidy]").;
        FormElement form = ((FormElement) index.select("[id=upload_form]").first());

        Element browse = form.selectFirst("[class=glyphicon glyphicon-folder-open");

        Connection post = form.submit();

        FileInputStream stream = new FileInputStream("C:\\Users\\shilp\\Desktop\\Uni\\Third Year Project\\TrustworthySoftware\\src\\main\\resources\\zoom\\Zoom.exe");


        Connection.KeyVal fileData = post.data("file_chooser");
        fileData.value("check.exe");
        fileData.inputStream(stream);

    }
}
