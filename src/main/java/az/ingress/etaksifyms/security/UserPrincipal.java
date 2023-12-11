package az.ingress.etaksifyms.security;

import az.ingress.etaksifyms.entity.user.User;
import az.ingress.etaksifyms.util.Util;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserPrincipal implements UserDetails {


    Long id;
    String name;
    @JsonIgnore
    String email;
    @JsonIgnore
    String password;
    private Util util;
    Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(User user) {


        return UserPrincipal.builder()
                .id(user.getId())
                .name(Util.formatNameSurname(user.getName(), user.getSurname()))
                .email(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getAuthorities())
                .build();
    }

    public static UserPrincipal create(Long id) {
        return UserPrincipal.builder()
                .id(id)
                .build();
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
