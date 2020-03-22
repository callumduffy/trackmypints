package ie.cduffy.trackmypints;

import ie.cduffy.trackmypints.service.JwtService;
import ie.cduffy.trackmypints.service.PintUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private PintUserDetailsService pintUserDetailsService;
    private JwtService jwtService;

    private Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    public JwtRequestFilter(PintUserDetailsService pintUserDetailsService, JwtService jwtService){
        this.pintUserDetailsService = pintUserDetailsService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res
            , FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = req.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            jwt = authHeader.substring(7);
            username = jwtService.extractUsername(jwt);
        }
        else{
            logger.error("No Jwt present.");
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.pintUserDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken upat  = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());
            upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
            SecurityContextHolder.getContext().setAuthentication(upat);
        }
        filterChain.doFilter(req, res);
    }
}
