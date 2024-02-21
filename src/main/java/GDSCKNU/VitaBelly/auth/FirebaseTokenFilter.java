package GDSCKNU.VitaBelly.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@WebFilter
public class FirebaseTokenFilter extends OncePerRequestFilter {

    private final CustomUserDetailService userDetailService;

    public FirebaseTokenFilter(CustomUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        FirebaseToken decodedToken;

        String header = request.getHeader("Authorization");
        if (header == null) {
            filterChain.doFilter(request, response);
            return;
        }
        try{
            decodedToken = firebaseAuth.verifyIdToken(header);
        } catch (FirebaseAuthException e) {
            System.err.println("Failed to verify ID token: " + e.getMessage());
            return;
        }

        UserDetails user = userDetailService.loadUserByUsername(decodedToken.getEmail());
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
