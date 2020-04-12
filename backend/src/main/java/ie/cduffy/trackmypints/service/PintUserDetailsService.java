package ie.cduffy.trackmypints.service;

import ie.cduffy.trackmypints.model.Consumer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PintUserDetailsService implements UserDetailsService {

    private AuthService authService;

    public PintUserDetailsService(AuthService authService){
        this.authService = authService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(username, authService.getConsumerHashedPasswordByUsername(username), new ArrayList<>());
    }
}
