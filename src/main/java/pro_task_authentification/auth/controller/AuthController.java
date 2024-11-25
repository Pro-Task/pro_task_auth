package pro_task_authentification.auth.controller;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import pro_task_authentification.auth.model.User;
import pro_task_authentification.auth.service.AuthService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @Value("${jwt.secret}")
    private String jwtSecret; // Секретный ключ для подписи JWT

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody AuthRequest authRequest){
        Optional<User> user = authService.authenticate(authRequest.getUsername(), authRequest.getPassword());
        
        if (user.isPresent()){
            //Make JWT
            Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
            String token = Jwts.builder()
                .setSubject(user.get().getUsername()) // Указываем имя пользователя в токене
                .setIssuedAt(new Date()) // Время выпуска токена
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // Время жизни токена (1 час)
                .signWith(key) // Подписываем токен секретным ключом
                .compact();
            
            return ResponseEntity.ok(new AuthResponse(token));
        } 
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
