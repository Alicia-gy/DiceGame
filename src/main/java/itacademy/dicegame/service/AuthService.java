package itacademy.dicegame.service;

import itacademy.dicegame.security.AuthResponse;
import itacademy.dicegame.domain.dtos.request.LoginRequest;
import itacademy.dicegame.domain.dtos.request.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
