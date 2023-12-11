package az.ingress.etaksifyms.controller;


import az.ingress.etaksifyms.dto.request.task.TaskCreateRequestDto;
import az.ingress.etaksifyms.dto.request.task.TaskUpdateRequestDto;
import az.ingress.etaksifyms.dto.request.user.UserListDto;
import az.ingress.etaksifyms.dto.response.task.TaskForUsersResponseDto;
import az.ingress.etaksifyms.dto.response.task.TaskResponseDto;
import az.ingress.etaksifyms.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/tasks")
@Slf4j
public class TaskController {

    private  final TaskService taskService;

    @PostMapping("/new")
    public ResponseEntity<TaskResponseDto> create(@RequestBody TaskCreateRequestDto dto){
        return ResponseEntity.ok(taskService.create(dto));
    }

    @PutMapping("/{taskId}/users")
    public ResponseEntity<TaskForUsersResponseDto> assignUsersToTasks(@PathVariable Long taskId,
                                                                      @RequestBody UserListDto list){
        return ResponseEntity.ok(taskService.assignUsersToTasks(taskId,list));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponseDto> update(@PathVariable Long taskId,
                                                  @RequestBody TaskUpdateRequestDto requestDto){
        return ResponseEntity.ok(taskService.update(taskId,requestDto));
    }
    @DeleteMapping("/taskId")
    public ResponseEntity<TaskResponseDto> delete(@PathVariable Long taskId){
        return ResponseEntity.ok(taskService.delete(taskId));
    }
    @GetMapping("/organizatin")
    public ResponseEntity<TaskResponseDto> getAllTasksByOrganizationId(@PathVariable Long organizationId){
        return ResponseEntity.ok((TaskResponseDto) taskService.getAllTasksByOrganizationId(organizationId));

    }
}