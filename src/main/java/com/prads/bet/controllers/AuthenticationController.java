package com.prads.bet.controllers;

import com.prads.bet.config.security.TokenService;
import com.prads.bet.controllers.dto.TokenDTO;
import com.prads.bet.controllers.form.LoginForm;
import com.prads.bet.exception.NonExpiredJwtException;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;


    @PostMapping
    public ResponseEntity authenticate(@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken loginData = form.convert();
        try {
            Authentication authentication = authManager.authenticate(loginData);
            String token = tokenService.generateToken(authentication);

            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
        } catch (AuthenticationException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/refreshtoken")
    public ResponseEntity<?> refreshtoken(/*@RequestHeader("Authorization") String bearerToken*/ HttpServletRequest request) throws Exception {
        try {
            DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");

            Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
            String token = tokenService.refreshToken(expectedMap, expectedMap.get("sub").toString());
            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
        } catch (NonExpiredJwtException exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
        try {
            Map<String, Object> expectedMap = new HashMap<String, Object>();
            for (Map.Entry<String, Object> entry : claims.entrySet()) {
                expectedMap.put(entry.getKey(), entry.getValue());
            }
            return expectedMap;
        } catch (NullPointerException | NonExpiredJwtException exception) {
            throw new NonExpiredJwtException("JWT is not expired.", exception);
        }
    }
}
