package com.sventheeagle.buffalo_bar_server.oidc;

import com.sventheeagle.buffalo_bar_server.jwt.JWTDecoder;
import com.sventheeagle.buffalo_bar_server.model.Player;
import com.sventheeagle.buffalo_bar_server.repository.PlayerRepository;
import com.sventheeagle.buffalo_bar_server.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class OidcService {

    private final PlayerRepository playerRepository;
    private final PlayerService playerService;

    @Value("${OIDC_ISSUER}")
    private String oidcIssuer;
    @Value("${OIDC_REDIRECT_URI}")
    private String redirectUri;
    @Value("${OIDC_CLIENT_ID}")
    private String clientId;
    @Value("${OIDC_CLIENT_SECRET}")
    private String secret;

    public OidcService(PlayerRepository playerRepository, PlayerService playerService) {
        this.playerRepository = playerRepository;
        this.playerService = playerService;
    }

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

        //Prepare response
        HashMap<String, Object> payloadMap = new HashMap<>();
        payloadMap.put("accessToken", accessToken);

        //Prepare Cookie
        ResponseCookie accessTokenCookie = createCookie("accessToken", accessToken);

        //Add cookie to response
        payloadMap.put("accessTokenCookie", accessTokenCookie);

        //Extract Authenticated User's ID
        Map<String, Object> player = extractUserInfo(accessToken);

        //Check If First Login
        if (isFirstLogin(player.get("sub").toString())) {
            Player newPlayer = new Player();
            newPlayer.setId(player.get("sub").toString());
            newPlayer.setEmail(player.get("email").toString());
            newPlayer.setUsername(player.get("preferred_username").toString());
            saveNewPlayer(newPlayer);
        }

        payloadMap.put("player", playerService.getPlayerById(player.get("sub").toString()));

        return payloadMap;
    }

    private ResponseCookie createCookie(String name, String value) {
        return ResponseCookie.from(name, value)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .domain("localhost")
                .maxAge(3600)
                .build();
    }

    private Map<String, Object> extractUserInfo(String token) {
        return new JWTDecoder().decodeJWTPayload(token);
    }

    private boolean isFirstLogin(String userId) {
        return !playerRepository.existsById(userId);
    }

    private void saveNewPlayer(Player player) {
        playerRepository.save(player);
    }
}