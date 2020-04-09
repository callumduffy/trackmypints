package ie.cduffy.trackmypints.controller;

import ie.cduffy.trackmypints.model.AuthRequest;
import ie.cduffy.trackmypints.model.AuthResponse;
import ie.cduffy.trackmypints.service.AuthService;
import ie.cduffy.trackmypints.service.JwtService;
import ie.cduffy.trackmypints.service.PintUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private AuthenticationManager authenticationManager;
    private PintUserDetailsService userDetailsService;
    private JwtService jwtService;
    private AuthService authService;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthenticationManager authenticationManager, PintUserDetailsService pintUserDetailsService,
                          JwtService jwtService, AuthService authService){
        this.authenticationManager = authenticationManager;
        this.userDetailsService = pintUserDetailsService;
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) throws Exception{
        logger.info("JWT Auth request received.");
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch(BadCredentialsException bce){
            return new ResponseEntity<>("Incorrect Username or Password", HttpStatus.NOT_FOUND);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        logger.info(userDetails.getUsername());
        final String jwt = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerNewConsumer(@RequestBody AuthRequest authRequest) throws Exception{
        logger.info("New user registration received.");
        boolean wasRegistered = authService.registerConsumer(authRequest);
        if(wasRegistered){
            return ResponseEntity.ok("User registered, needs to be verified.");
        }
        else{
            return new ResponseEntity<>("Error registering user", HttpStatus.BAD_REQUEST);
        }
    }
}
