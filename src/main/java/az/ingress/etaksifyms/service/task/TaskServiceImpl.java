package az.ingress.etaksifyms.service.task;

import az.ingress.etaksifyms.dto.request.task.TaskCreateRequestDto;
import az.ingress.etaksifyms.dto.request.task.TaskUpdateRequestDto;
import az.ingress.etaksifyms.dto.request.user.UserListDto;
import az.ingress.etaksifyms.dto.response.task.TaskForUsersResponseDto;
import az.ingress.etaksifyms.dto.response.task.TaskResponseDto;
import az.ingress.etaksifyms.entity.task.Task;
import az.ingress.etaksifyms.entity.task.TaskStatus;
import az.ingress.etaksifyms.entity.user.User;
import az.ingress.etaksifyms.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j

public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;


    public TaskResponseDto create(TaskCreateRequestDto requestDto){
        Task newTask = Task.builder()
                .title(requestDto.getTitle())
                .description(requestDto.getDescription())
                .deadline(requestDto.getDeadline())
                .status(TaskStatus.NEW)
                .build();
        Task savedTask=taskRepository.save(newTask);
        return modelMapper.map(savedTask,TaskResponseDto.class);

    }

    @Override
    public TaskForUsersResponseDto assignUsersToTasks(Long taskId, Object list) {
        return null;
    }

    public TaskForUsersResponseDto assignUsersToTasks(Long taskId, UserListDto list){
        Task task=taskRepository.findById(taskId).orElseThrow(()->
                new RuntimeException(String.format("Task with Id %s not found")));
        for (User user :list.getUsersList()){
            user.getTasks().add(task);
        }
        TaskForUsersResponseDto taskForUsersResponseDto=modelMapper.map(task,TaskForUsersResponseDto.class);
        taskForUsersResponseDto.setUserList(list.getUsersList());
        return taskForUsersResponseDto;

    }


    public TaskResponseDto delete(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Task with id %s not found",id)));
        taskRepository.deleteById(id);
        return modelMapper.map(task,TaskResponseDto.class);

    }

    public  TaskResponseDto update(Long id, TaskUpdateRequestDto requestDto){
        Task task = taskRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Task with id %s not found",id)));
        Task taskForUpdate= modelMapper.map(requestDto,Task.class);
        taskForUpdate.setId(id);
//        taskForUpdate.setDeadline(requestDto.getDeadline());
        return modelMapper.map(taskRepository.save(taskForUpdate),TaskResponseDto.class);

    }

    public TaskResponseDto get(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Task with id %s not found",id)));
        return modelMapper.map(task,TaskResponseDto.class);

    }

    public List<TaskResponseDto> getAllTasksByOrganizationId(Long organizationId){
        List<Task> taskList = taskRepository.findAllTasksByOrganizationId(organizationId);
        List<TaskResponseDto> taskResponseDtos = new ArrayList<>();
        taskList.forEach(task ->
                taskResponseDtos.add(modelMapper.map(task,TaskResponseDto.class)));
        return  taskResponseDtos;
    }
    public List<TaskResponseDto> getAllTasksByUserId(Long organizationId){
        List<Task> taskList = taskRepository.findAllTasksByUsersId(organizationId);
        List<TaskResponseDto> taskResponseDtos = new ArrayList<>();
        taskList.forEach(task ->
                taskResponseDtos.add(modelMapper.map(task,TaskResponseDto.class)));
        return  taskResponseDtos;
    }
}
