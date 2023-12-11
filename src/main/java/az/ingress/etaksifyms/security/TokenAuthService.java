package az.ingress.etaksifyms.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenAuthService {

    private final static String BEARER = "Bearer ";
    private final static String AUTHORITY_CLAIM = "authority";
    private final JwtService baseJwtService;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private String token;

    public Optional<Authentication> getAuthentication(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Authorization"))
                .filter(this::isBearerAuth)
                .flatMap(this::getAuthenticationBearer);
    }

    private boolean isBearerAuth(String header) {
        return header.toLowerCase().startsWith(BEARER.toLowerCase());
    }


    private Optional<Authentication> getAuthenticationBearer(String header) {
        token = header.substring(BEARER.length()).trim();
        Claims claims = baseJwtService.parseToken(token);
        log.info("The claims parsed {}", claims);

        if (claims.getExpiration().before(new Date())) {
            return Optional.empty();
        }
        return Optional.of(getAuthenticationBearer(claims));
    }

    private Authentication getAuthenticationBearer(Claims claims) {
        String username = jwtService.getEmailFromToken(token);
        var userDetails = userDetailsService.loadUserByUsername(username);

        List<?> roles = claims.get(AUTHORITY_CLAIM, List.class);
        List<GrantedAuthority> authorityList = roles
                .stream()
                .map((role) -> new SimpleGrantedAuthority(role.toString()))
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(userDetails, null, authorityList);
    }
}
