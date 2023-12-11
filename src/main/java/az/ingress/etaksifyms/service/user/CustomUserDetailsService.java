package az.ingress.etaksifyms.service.user;

import az.ingress.etaksifyms.entity.user.User;
import az.ingress.etaksifyms.security.UserPrincipal;
import az.ingress.etaksifyms.service.task.AuthService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomUserDetailsService {

    private  final  AuthService authService;

    @Lazy
    public CustomUserDetailsService(AuthService authService){
        this.authService =authService;
    }

    public UserDetails loadByUsername(String email) {
        User user = authService.findUser(email);
        return UserPrincipal.create(user);
    }

}
