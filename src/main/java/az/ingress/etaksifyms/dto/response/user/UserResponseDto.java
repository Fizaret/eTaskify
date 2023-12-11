package az.ingress.etaksifyms.dto.response.user;

import az.ingress.etaksifyms.entity.address.Address;
import az.ingress.etaksifyms.entity.phoneNumber.PhoneNumber;
import az.ingress.etaksifyms.entity.task.Task;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDto {

    Long id;

    String name;

    String surname;

    List<PhoneNumber> phoneNumbers;

    Address address;

    @Builder.Default
    List<Task> taskList = new ArrayList<>();
}