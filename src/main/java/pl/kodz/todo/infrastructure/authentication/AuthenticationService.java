package pl.kodz.todo.infrastructure.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.kodz.todo.api.dto.request.UserDto;
import pl.kodz.todo.api.user.UserLogInDto;
import pl.kodz.todo.api.dto.response.AuthResponse;
import pl.kodz.todo.domain.model.user.User;
import pl.kodz.todo.domain.model.user.port.UserRepository;
import pl.kodz.todo.infrastructure.security.exception.DataProcessingException;
import pl.kodz.todo.infrastructure.security.exception.UserAlreadyExistsException;
import pl.kodz.todo.infrastructure.persistance.entity.UserEntity;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public AuthResponse register(User domainUser) {

        //TODO propably is better solution
        domainUser.setEnabled(true);
        domainUser.setPassword(passwordEncoder.encode(domainUser.getPassword()));

        try {
            userRepository.save(domainUser);
            String token = jwtService.generateToken(new CustomUserDetails(domainUser));
            return new AuthResponse(token);
        } catch (DataIntegrityViolationException ex){
            if (ex.getMostSpecificCause().getMessage().contains("users_email_key")) {
                throw new UserAlreadyExistsException("email");
            }
            throw new DataProcessingException(ex);
        }
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
