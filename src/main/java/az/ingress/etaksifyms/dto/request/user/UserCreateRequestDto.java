package az.ingress.etaksifyms.dto.request.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequestDto {



    String email;

    String password;

    String confirmPassword;

    String token;
}
