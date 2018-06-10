package com.dannysh.heartme.utils;

import android.os.Handler;
import android.os.Looper;

import com.dannysh.heartme.model.BloodTestConfig;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkUtils {

    public static void getBloodTestConfig() {
        ExecutorService netExecutor = Executors.newSingleThreadExecutor();
        final StringBuilder serverResponse = new StringBuilder();

        netExecutor.submit(new Runnable() {
            @Override
            public void run() {
                sendRequest(Constants.S3_URL, serverResponse);
                Gson gson = new Gson();
                BloodTestConfig tempConfig = gson.fromJson(serverResponse.toString(), BloodTestConfig.class);
                //update singleton with received data
                BloodTestConfig.update(tempConfig);
            }
        });


    }

    private static void sendRequest(String address, StringBuilder response) {
        HttpURLConnection connection = null;
        InputStream is = null;
        int statusCode = 0;
        try {
            URL url = new URL(address);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json;text/html;text/plain");
            connection.setReadTimeout(Constants.SOCKET_TIMEOUT);
            connection.setConnectTimeout(Constants.SOCKET_TIMEOUT);

            statusCode = connection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                //deal with error in server
            }
            is = connection.getInputStream();
            if (response != null) {
                if (is != null) {
                    Writer writer = new StringWriter();
                    char[] buffer = new char[1024];

                    Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    int n;
                    while ((n = reader.read(buffer)) != -1) {
                        writer.write(buffer, 0, n);
                    }
                    response.setLength(0);
                    response.append(writer.toString());
                }
            }

        } catch (Exception e) {
            //connection error
        } finally {
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
