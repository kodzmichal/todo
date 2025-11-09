package pl.kodz.todo.application.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import pl.kodz.todo.domain.model.user.User;

public class UserFactory {
    public User create(User domainUser, PasswordEncoder encoder) {
        domainUser.setEnabled(true);
        domainUser.setPassword(encoder.encode(domainUser.getPassword()));
        return domainUser;
    }
}
