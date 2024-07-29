package org.example.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PolicyDAO {

    public Map<String,List<String>> loadResourcePolicies() {
        Map<String, List<String>>resourcePolicyMap = new HashMap<>();
        try (FileReader reader = new FileReader("src/main/resources/ResourcePolicy.json")) {
            Gson gson = new Gson();
            resourcePolicyMap = gson.fromJson(reader, new TypeToken<Map<String, List<String>>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return resourcePolicyMap;
    }

    public Map<String,List<String>> loadRolePolicies() {
        Map<String, List<String>>rolePolicyMap = new HashMap<>();
        try (FileReader reader = new FileReader("src/main/resources/RolePolicy.json")) {
            Gson gson = new Gson();
            rolePolicyMap = gson.fromJson(reader, new TypeToken<Map<String, List<String>>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return rolePolicyMap;
    }

}
