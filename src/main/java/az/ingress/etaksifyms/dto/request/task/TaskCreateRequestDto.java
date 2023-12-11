package az.ingress.etaksifyms.dto.request.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskCreateRequestDto {

    String title;

    String description;

    @JsonFormat(pattern = "dd.MM.yyyy")
    LocalDateTime deadline;

    @JsonFormat(pattern = "dd.MM.yyyy")
    LocalDateTime createdAt;
}