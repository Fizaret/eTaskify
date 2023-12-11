package az.ingress.etaksifyms.dto.request.user;

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
public class UserListDto {
    List<User> userList =new ArrayList<>();

    public List<User> getUsersList() {
        return null;
    }
}
