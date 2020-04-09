package ie.cduffy.trackmypints.service;

import ie.cduffy.trackmypints.dao.ConsumerRepository;
import ie.cduffy.trackmypints.model.AuthRequest;
import ie.cduffy.trackmypints.model.Consumer;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private ConsumerRepository consumerRepository;

    public AuthService(ConsumerRepository consumerRepository){
        this.consumerRepository = consumerRepository;
    }

    public boolean registerConsumer(AuthRequest authRequest){
        String username = authRequest.getUsername();
        String password = hashPassword(authRequest.getPassword());

        return consumerRepository.registerConsumer(new Consumer(username, password));
    }

    //TODO reseach best way to integrate this with JWT password
    private String hashPassword(String password){
        return password;
    }
}
