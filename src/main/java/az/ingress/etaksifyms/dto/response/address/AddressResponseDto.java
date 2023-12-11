package az.ingress.etaksifyms.dto.response.address;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class AddressResponseDto {

    Long id;

    String address;

}
