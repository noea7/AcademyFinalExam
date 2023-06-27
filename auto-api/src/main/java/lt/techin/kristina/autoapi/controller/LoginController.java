package lt.techin.kristina.autoapi.controller;

import lt.techin.kristina.autoapi.exception.AutoserviceValidationException;
import lt.techin.kristina.autoapi.model.User;
import lt.techin.kristina.autoapi.model.UserCredentials;
import lt.techin.kristina.autoapi.repository.UserRepository;
import lt.techin.kristina.autoapi.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody UserCredentials credentials) {
        User user = userRepository.findByUsername(credentials.getUsername()).orElse(null);
        if (user != null && passwordEncoder.matches(credentials.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(credentials.getUsername(), user.getRole());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserCredentials credentials) {
        if (userRepository.existsByUsernameIgnoreCase(credentials.getUsername())) {
            throw new AutoserviceValidationException("Username must be unique");
        }
        User user = new User();
        user.setUsername(credentials.getUsername());
        user.setPassword(passwordEncoder.encode(credentials.getPassword()));
        user.setRole("user");
        return ResponseEntity.ok(userRepository.save(user));
    }
}
