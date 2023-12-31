package az.ingress.etaksifyms.entity.authority;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Authority  implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    UserAuthority authority;


    @Override
    public String getAuthority() {
        return authority.name();
    }
}
