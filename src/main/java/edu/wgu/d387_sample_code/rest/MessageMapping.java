package edu.wgu.d387_sample_code.rest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class MessageMapping {
    static ExecutorService messageExecutor=newFixedThreadPool(5);
    public static String[] translationArray = new String[2];;
    @RequestMapping(path = "/presentation", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public static String[] showPresentation() {
        Properties properties=new Properties();
        messageExecutor.execute(()-> {
            try {
                InputStream stream = new ClassPathResource("translation_fr_CA.properties").getInputStream();
                properties.load(stream);
                translationArray[0]=properties.getProperty("welcome");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        messageExecutor.execute(()-> {
            try {
                InputStream stream = new ClassPathResource("translation_en_CA.properties").getInputStream();
                properties.load(stream);
                translationArray[1]=properties.getProperty("welcome");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return translationArray;
    }

}