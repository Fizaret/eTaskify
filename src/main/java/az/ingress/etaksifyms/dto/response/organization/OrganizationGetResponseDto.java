package az.ingress.etaksifyms.dto.response.organization;

import az.ingress.etaksifyms.entity.user.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrganizationGetResponseDto {
Long id;
String name;
String confirmationCode;

@Builder.Default
    List<User> users = new ArrayList<>();


}
