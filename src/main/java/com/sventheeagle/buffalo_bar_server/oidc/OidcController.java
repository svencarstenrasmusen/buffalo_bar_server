package com.sventheeagle.buffalo_bar_server.oidc;

import com.sventheeagle.buffalo_bar_server.model.Player;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("api/oidc")
public class OidcController {

    private final OidcService oidcService;

    @Autowired
    public OidcController(OidcService oidcService) {
        this.oidcService = oidcService;
    }

    @PostMapping
    @Operation(
            summary = "Authenticate via OIDC",
            description = "Authenticate a user via OIDC with authorization code",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Authentication successful"),
                    @ApiResponse(responseCode = "401", description = "Authentication failed", content = @Content)
            })
    public ResponseEntity<Player> authenticate(@RequestBody OidcDTO oidcDto, HttpServletResponse response) {
        HashMap<String, Object> oidcResponse = oidcService.authenticate(oidcDto);
        ResponseCookie accessTokenCookie = (ResponseCookie) oidcResponse.get("accessTokenCookie");
        response.addHeader(HttpHeaders.SET_COOKIE, accessTokenCookie.toString());

        Player player = (Player) oidcResponse.get("player");

        return new ResponseEntity<>(player, HttpStatus.OK);
    }
}
