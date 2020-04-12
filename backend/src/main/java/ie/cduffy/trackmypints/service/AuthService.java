package ie.cduffy.trackmypints.service;

import ie.cduffy.trackmypints.dao.ConsumerRepository;
import ie.cduffy.trackmypints.model.AuthRequest;
import ie.cduffy.trackmypints.model.Consumer;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private ConsumerRepository consumerRepository;
    private PasswordEncoder passwordEncoder;

    public AuthService(ConsumerRepository consumerRepository, PasswordEncoder passwordEncoder){
        this.consumerRepository = consumerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean registerConsumer(AuthRequest authRequest){
        String username = authRequest.getUsername();
        String password = hashPassword(authRequest.getPassword());

        return consumerRepository.registerConsumer(new Consumer(username, password));
    }

    public String getConsumerHashedPasswordByUsername(String username){
        Consumer consumer = consumerRepository.getConsumerByUsername(username);
        if(consumer != null){
            return consumer.getHashedPassword();
        }
        throw new UsernameNotFoundException("Username not found. Please register.");
    }

    private String hashPassword(String password){
        return passwordEncoder.encode(password);
    }
}
