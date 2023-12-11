package az.ingress.etaksifyms.controller;

import az.ingress.etaksifyms.dto.request.user.UserCreateRequestDto;
import az.ingress.etaksifyms.dto.request.user.UserRequestDto;
import az.ingress.etaksifyms.dto.response.user.UserCreateResponseDto;
import az.ingress.etaksifyms.dto.response.user.UserResponseDto;
import az.ingress.etaksifyms.service.user.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/users")
public class UserController {
    private final UserService userService;




    @PostMapping("/new")
    public ResponseEntity<UserCreateResponseDto> create(@RequestBody UserCreateRequestDto dto){
        return ResponseEntity.ok(userService.create(dto));
    }



    @GetMapping("/userId")
    public ResponseEntity<UserResponseDto> get(@PathVariable Long userId){
        return ResponseEntity.ok(userService.get(userId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserResponseDto>> getAll(@PathVariable Long organizationId){
        return ResponseEntity.ok(userService.getAll(organizationId));

    }




    @PutMapping("/userId")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long userId,
                                                  @RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.ok(userService.update(userId,userRequestDto));

    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<UserResponseDto> delete(@PathVariable Long userId){
        return ResponseEntity.ok(userService.delete(userId));
    }


}
