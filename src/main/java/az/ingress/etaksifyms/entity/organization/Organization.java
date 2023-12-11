package az.ingress.etaksifyms.entity.organization;

import az.ingress.etaksifyms.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "organization")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @OneToMany(mappedBy = "organization",cascade = CascadeType.REMOVE)
    List<User> users=new ArrayList<>();
}
