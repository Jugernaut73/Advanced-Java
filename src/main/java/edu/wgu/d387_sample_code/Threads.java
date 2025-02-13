package edu.wgu.d387_sample_code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

@SpringBootApplication
public class Threads {
    static ExecutorService messageExecutor=newFixedThreadPool(5);
    public static String[] translationArray = new String[2];;
    public static String[] getThreads() {
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