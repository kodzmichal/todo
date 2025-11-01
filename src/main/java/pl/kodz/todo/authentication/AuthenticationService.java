package pl.kodz.todo.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.kodz.todo.model.request.UserDto;
import pl.kodz.todo.model.request.UserLogInDto;
import pl.kodz.todo.model.response.AuthResponse;
import pl.kodz.todo.model.technical.CustomUserDetails;
import pl.kodz.todo.modeldata.User;
import pl.kodz.todo.repository.UserRepository;

import java.util.Optional;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public AuthResponse register(UserDto request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .enabled(true)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(new CustomUserDetails(user));

        return new AuthResponse(token);
    }

    public AuthResponse login(UserLogInDto request) {
       User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new ResponseStatusException(UNAUTHORIZED, "Wrong login/password");
        }
        String token = jwtService.generateToken(new CustomUserDetails(user));

        return new AuthResponse(token);
    }
}
