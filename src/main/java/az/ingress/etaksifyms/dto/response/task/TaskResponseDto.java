package az.ingress.etaksifyms.dto.response.task;

import az.ingress.etaksifyms.entity.task.TaskStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskResponseDto {



    Long id;

    String title;

    String description;

    @JsonFormat(pattern = "dd.MM.yyyy")
    LocalDateTime deadline;

    @JsonFormat(pattern = "dd.MM.yyyy")
    LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    TaskStatus status;
}
