package az.ingress.etaksifyms.entity.user;
import az.ingress.etaksifyms.entity.address.Address;
import az.ingress.etaksifyms.entity.authority.Authority;
import az.ingress.etaksifyms.entity.organization.Organization;
import az.ingress.etaksifyms.entity.phoneNumber.PhoneNumber;
import az.ingress.etaksifyms.entity.task.Task;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String surname;
    String email;
    String password;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    Organization organization;

    @OneToOne(cascade = CascadeType.ALL)

    @JsonIgnore
    @ToString.Exclude
    Address address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    List<PhoneNumber> phoneNumbers = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)

    @Builder.Default
    @JoinTable(name = "users_tasks",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
    List<Task> tasks = new ArrayList<>();


    @ManyToMany
    @Builder.Default
    @JoinTable(
            name = "user_authorities",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
    List<Authority> authorities = new ArrayList<>();
}

