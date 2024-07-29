package org.example.dao;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.model.User;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ClientCredentialsDAO {

    public Map<String,String> loadClientCredentials() {
        Map<String,String> clientCredentials = new HashMap<>();
        try (FileReader reader = new FileReader("src/main/resources/ClientCredentials.json")) {
            Gson gson = new Gson();
            clientCredentials = gson.fromJson(reader, new TypeToken<Map<String, String>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientCredentials;
    }

}
