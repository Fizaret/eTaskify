package az.ingress.etaksifyms.dto.request.user;

import az.ingress.etaksifyms.entity.phoneNumber.PhoneNumber;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDto {
    String name;
    String surname;
    String password;
    String confirmPassword;
    Long addressId;
    String address;
    List<PhoneNumber> phoneNumbers;
}
