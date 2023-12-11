package az.ingress.etaksifyms.service.task;

import az.ingress.etaksifyms.dto.request.task.TaskCreateRequestDto;
import az.ingress.etaksifyms.dto.request.task.TaskUpdateRequestDto;
import az.ingress.etaksifyms.dto.request.user.UserListDto;
import az.ingress.etaksifyms.dto.response.task.TaskForUsersResponseDto;
import az.ingress.etaksifyms.dto.response.task.TaskResponseDto;

import java.util.List;

public interface TaskService {
    TaskResponseDto create(TaskCreateRequestDto requestDto);

    TaskForUsersResponseDto assignUsersToTasks(Long taskId, Object list);

    List<TaskResponseDto> getAllTasksByOrganizationId(Long organizationId);
    TaskForUsersResponseDto assignUsersToTasks(Long taskId, UserListDto list);

    TaskResponseDto delete(Long id);

    TaskResponseDto update(Long id, TaskUpdateRequestDto requestDto);
}
