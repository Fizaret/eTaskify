package az.ingress.etaksifyms.dto.request.address;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class AddressRequestDto {


    String address;

}

