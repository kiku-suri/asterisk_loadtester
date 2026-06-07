package com.telecom.loadtester.service;

import com.telecom.loadtester.model.User;
import com.telecom.loadtester.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

/*    public boolean authenticate(
            String username,
            String password) {

        return userRepository
                .findByUsername(username)
                .filter(User::getEnabled)
                .map(user ->
                        passwordEncoder.matches(
                                password,
                                user.getPasswordHash()))
                .orElse(false);
    }
*/

	public boolean authenticate(
        String username,
        String password) {

    System.out.println("================================");
    System.out.println("LOGIN ATTEMPT");
    System.out.println("Username: " + username);

    return userRepository
            .findByUsername(username)
            .map(user -> {

                System.out.println("User Found");
                System.out.println("DB Username: " + user.getUsername());
                System.out.println("Enabled: " + user.getEnabled());
                System.out.println("Hash: " + user.getPasswordHash());

                boolean match =
                        passwordEncoder.matches(
                                password,
                                user.getPasswordHash());

                System.out.println("Password Match: " + match);

                return Boolean.TRUE.equals(
                        user.getEnabled())
                        && match;
            })
            .orElseGet(() -> {

                System.out.println("User Not Found");

                return false;
            });
	}

    public User getUser(String username) {

        return userRepository
                .findByUsername(username)
                .orElse(null);
    }
}
