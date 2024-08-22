package com.sventheeagle.buffalo_bar_server.jwt;

import lombok.NonNull;
import org.json.JSONObject;

import java.util.Base64;
import java.util.Map;

public class JWTDecoder {
    public Map<String, Object> decodeJWTPayload(@NonNull String jwt) {
        String[] chunks = jwt.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        JSONObject json = new JSONObject(payload);
        Map<String, Object> map = json.toMap();

        return map;
    }
}