package com.sventheeagle.buffalo_bar_server.oidc;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OidcService {

    @Value("${OIDC_ISSUER}")
    private String oidcIssuer;
    @Value("${OIDC_REDIRECT_URI}")
    private String redirectUri;
    @Value("${OIDC_CLIENT_ID}")
    private String clientId;
    @Value("${OIDC_CLIENT_SECRET}")
    private String secret;

    public HashMap<String, Object> authenticate(OidcDTO oidcDto) {
        String tokenEndpoint = oidcIssuer + "/protocol/openid-connect/token";
        RestTemplate restTemplate = new RestTemplate();

        //Encode credentials
        String credentials = clientId + ":" + secret;
        String base64Credentials = Base64.encodeBase64String(credentials.getBytes());
        String authHeader = "Basic " + base64Credentials;

        //Prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.add("Authorization", authHeader);

        //Prepare body
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("code", oidcDto.code());
        body.add("redirect_uri", redirectUri);

        //Prepare request
        HttpEntity<MultiValueMap<String, String>> sentRequest = new HttpEntity<>(body, headers);

        //Send POST request
        ResponseEntity<HashMap> oidcResponse = restTemplate.exchange(tokenEndpoint, HttpMethod.POST, sentRequest, HashMap.class);

        //Get accessToken
        String accessToken = Objects.requireNonNull(oidcResponse.getBody()).get("access_token").toString();
        String refreshToken = Objects.requireNonNull(oidcResponse.getBody()).get("refresh_token").toString();
        String idToken = Objects.requireNonNull(oidcResponse.getBody()).get("id_token").toString();

        //Prepare response
        HashMap<String, Object> payloadMap = new HashMap<>();
        payloadMap.put("accessToken", accessToken);
        payloadMap.put("refreshToken", refreshToken);
        payloadMap.put("idToken", idToken);

        //Prepare Cookie
        ResponseCookie accessTokenCookie = createCookie("accessToken", accessToken);
        ResponseCookie refreshTokenCookie = createCookie("refreshToken", refreshToken);
        ResponseCookie idTokenCookie = createCookie("idToken", idToken);

        //Add cookie to response
        payloadMap.put("accessTokenCookie", accessTokenCookie);
        payloadMap.put("refreshTokenCookie", refreshTokenCookie);
        payloadMap.put("idTokenCookie", idTokenCookie);

        return payloadMap;
    }

    private ResponseCookie createCookie(String name, String value) {
        return ResponseCookie.from(name, value)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .domain("localhost")
                .maxAge(300)
                .build();
    }
}