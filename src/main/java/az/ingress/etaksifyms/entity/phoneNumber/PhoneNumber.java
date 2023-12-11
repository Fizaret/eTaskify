package az.ingress.etaksifyms.entity.phoneNumber;

import az.ingress.etaksifyms.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "phone_number")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long id;

    String number;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    User user;
}




