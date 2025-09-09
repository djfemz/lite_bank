package dev.litebank.security.service;

import dev.litebank.dto.responses.TokenResponse;

public interface AuthService {
    TokenResponse generateTokens(String refreshToken);
}
