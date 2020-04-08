package ie.cduffy.trackmypints.controller;

import ie.cduffy.trackmypints.model.AuthRequest;
import ie.cduffy.trackmypints.model.AuthResponse;
import ie.cduffy.trackmypints.service.JwtService;
import ie.cduffy.trackmypints.service.PintUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthenticationManager authenticationManager, PintUserDetailsService pintUserDetailsService,
                          JwtService jwtService){
        this.authenticationManager = authenticationManager;
        this.userDetailsService = pintUserDetailsService;
        this.jwtService = jwtService;
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) throws Exception{
        logger.info("JWT Auth request received.");
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch(BadCredentialsException bce){
            throw new Exception("Incorrect Username or Password", bce);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        logger.info(userDetails.getUsername());
        final String jwt = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
