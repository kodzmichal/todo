package pl.kodz.todo.application.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.kodz.todo.api.auth.AuthResponse;
import pl.kodz.todo.api.auth.UserLogInDto;
import pl.kodz.todo.application.user.UserFactory;
import pl.kodz.todo.domain.model.user.User;
import pl.kodz.todo.domain.model.user.port.UserRepository;
import pl.kodz.todo.infrastructure.authentication.CustomUserDetails;
import pl.kodz.todo.infrastructure.authentication.JwtService;
import pl.kodz.todo.infrastructure.security.exception.DataProcessingException;
import pl.kodz.todo.infrastructure.security.exception.UserAlreadyExistsException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public AuthResponse register(User domainUser) {
        Optional<User> existingUser = userRepository.findByEmail(domainUser.getEmail());
        if (existingUser.isPresent()) {
            //add to handler
            throw new UserAlreadyExistsException("email");
        }

        UserFactory userFactory = new UserFactory();
        User user = userFactory.create(domainUser, passwordEncoder);

        try {
            userRepository.save(user);
            String token = jwtService.generateToken(new CustomUserDetails(user));
            return new AuthResponse(token);
        } catch (DataIntegrityViolationException ex){
            if (ex.getMostSpecificCause().getMessage().contains("users_email_key")) {
                throw new UserAlreadyExistsException("email");
            }
            throw new DataProcessingException(ex);
        }
    }

    public AuthResponse login(UserLogInDto request) {
       User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if(!passwordEncoder.matches(request.password(), user.getPassword())){
            throw new ResponseStatusException(UNAUTHORIZED, "Wrong login/password");
        }
        String token = jwtService.generateToken(new CustomUserDetails(user));

        return new AuthResponse(token);
    }
}
