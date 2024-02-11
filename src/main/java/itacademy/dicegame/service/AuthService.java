package itacademy.dicegame.service;

import itacademy.dicegame.security.data.AuthResponse;
import itacademy.dicegame.security.data.LoginRequest;
import itacademy.dicegame.security.data.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
