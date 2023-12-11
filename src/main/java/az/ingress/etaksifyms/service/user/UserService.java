package az.ingress.etaksifyms.service.user;

import az.ingress.etaksifyms.dto.request.user.UserCreateRequestDto;
import az.ingress.etaksifyms.dto.request.user.UserRequestDto;
import az.ingress.etaksifyms.dto.response.user.UserCreateResponseDto;
import az.ingress.etaksifyms.dto.response.user.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserResponseDto get(Long userId);

    UserCreateResponseDto create(UserCreateRequestDto createDto);

    UserResponseDto update(Long id, UserRequestDto requestDto);

    List<UserResponseDto> getAll(Long id);

    UserResponseDto delete(Long userId);
}

