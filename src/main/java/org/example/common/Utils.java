package org.example.common;

import com.fasterxml.jackson.databind.ser.Serializers;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Utils {

    public String base64Encode(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    public String base64Decode(String str) {
        return new String(Base64.getDecoder().decode(str), StandardCharsets.UTF_8);
    }

}
