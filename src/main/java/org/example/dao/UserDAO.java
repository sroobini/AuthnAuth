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
public class UserDAO {

    private boolean saveUser(User user) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Map<String, User> users = loadUsers();
        users.put(user.getUsername(), user);

        String jsonArrayString = gson.toJson(user);

        try (FileWriter writer = new FileWriter("Users.json")) {
            writer.write(jsonArrayString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  true;
    }


    public Map<String, User> loadUsers() {
        Map<String, User> users = new HashMap<>();
        try (FileReader reader = new FileReader("src/main/resources/Users.json")) {
            Gson gson = new Gson();
            users = gson.fromJson(reader, new TypeToken<Map<String, User>>() {

            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

}
