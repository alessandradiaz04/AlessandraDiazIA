package org.example;

import java.io.IOException;
import javafx.fxml.FXML;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void switchToThird() throws IOException {
        App.setRoot("third");
    }

    public class QuoteOfTheDay {

        public void main(String[] args) throws IOException {
            URL url = new URL("https://quotes.rest/qod?category=inspiring");

            try{
                //make connection
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setRequestMethod("GET");
                // set the content type
                urlc.setRequestProperty("Content-Type", "application/json");
                urlc.setRequestProperty("X-TheySaidSo-Api-Secret", "YOUR API KEY HERE");
                System.out.println("Connect to: " + url.toString());
                urlc.setAllowUserInteraction(false);
                urlc.connect();

                //get result
                BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
                String l = null;
                while ((l=br.readLine())!=null) {
                    System.out.println(l);
                }
                br.close();
            } catch (Exception e){
                System.out.println("Error occured");
                System.out.println(e.toString());
            }
        } //https://theysaidso.com/api/

    }
}
