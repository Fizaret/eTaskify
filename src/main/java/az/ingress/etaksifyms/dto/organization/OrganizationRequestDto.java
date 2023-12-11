package az.ingress.etaksifyms.dto.organization;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrganizationRequestDto {
    String name;
    String confirmationCode;
}
