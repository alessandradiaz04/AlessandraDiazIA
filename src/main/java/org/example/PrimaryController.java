package org.example;

import java.io.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;

public class PrimaryController {

    public TextArea quoteTextField;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void switchToThird() throws IOException {
        App.setRoot("third");
    }

    public void initialize() {
        String jsonQuote = executePost("https://zenquotes.io/api/random", "");
        System.out.println(jsonQuote);

        JsonParser parser = new JsonParser();
        JsonElement quoteElement = parser.parse(jsonQuote);
        JsonArray quoteArray = quoteElement.getAsJsonArray();

        String quote =quoteArray.get(0).getAsJsonObject().get("q").getAsString();
        String author = quoteArray.get(0).getAsJsonObject().get("a").getAsString();
        quoteTextField.setText(quote + " - " + author);

    }
//https://stackoverflow.com/questions/1359689/how-to-send-http-request-in-java
        public static String executePost(String targetURL, String urlParameters) {
            HttpURLConnection connection = null;

            try {
                //Create connection
                URL url = new URL(targetURL);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");

                connection.setRequestProperty("Content-Length",
                        Integer.toString(urlParameters.getBytes().length));
                connection.setRequestProperty("Content-Language", "en-US");

                connection.setUseCaches(false);
                connection.setDoOutput(true);

                //Send request
                DataOutputStream wr = new DataOutputStream (
                        connection.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.close();

                //Get Response
                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
                String line;
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                rd.close();
                return response.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }

}

