package com.haven.app.haven;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Sms8Api {
    private final String smsText;
    private final String phoneNumber;

    public Sms8Api(String txt, String number) {
        this.smsText = txt;
        this.phoneNumber = number;
    }

    public void sendSms() {
        try {
            // Properly encode the message and number
            String encodedMessage = URLEncoder.encode(smsText, StandardCharsets.UTF_8.toString());
            String encodedNumber = URLEncoder.encode(phoneNumber, StandardCharsets.UTF_8.toString());
            //String encodedDevice = URLEncoder.encode("%5B%22182%7C0%22%2C%22182%7C1%22%5D", StandardCharsets.UTF_8.toString());

            // Build URL with encoded parameters
            String urlStr = String.format(
                    "https://app.sms8.io/services/send.php?key=8e24d95af4f83d3aff44b56bcd923692d9a39e8f&number=%s&message=%s&option=1&type=sms&prioritize=1",
                    encodedNumber, encodedMessage
            );

            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read response
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                System.out.println("Response: " + content.toString());
            }

            connection.disconnect();
        } catch (Exception e) {
            System.out.println("Error sending SMS: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Sms8Api sms = new Sms8Api("Hello, this is a test!", "+8801613392018");
        sms.sendSms();
    }
}
