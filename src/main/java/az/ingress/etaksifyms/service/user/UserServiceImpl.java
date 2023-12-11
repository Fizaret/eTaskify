package az.ingress.etaksifyms.service.user;

import az.ingress.etaksifyms.config.ModelMapperConfig;
import az.ingress.etaksifyms.dto.request.user.UserCreateRequestDto;
import az.ingress.etaksifyms.dto.request.user.UserRequestDto;
import az.ingress.etaksifyms.dto.response.user.UserCreateResponseDto;
import az.ingress.etaksifyms.dto.response.user.UserResponseDto;
import az.ingress.etaksifyms.entity.address.Address;
import az.ingress.etaksifyms.entity.authority.Authority;
import az.ingress.etaksifyms.entity.authority.UserAuthority;
import az.ingress.etaksifyms.entity.organization.Organization;
import az.ingress.etaksifyms.entity.user.User;
import az.ingress.etaksifyms.repository.AddressRepository;
import az.ingress.etaksifyms.repository.AuthorRepository;
import az.ingress.etaksifyms.repository.UserRepository;
import az.ingress.etaksifyms.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AuthorRepository authorRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final AddressRepository addressRepository;

    private final JwtService jwtService;


    private final ModelMapperConfig modelMapperConfig;





    public UserCreateResponseDto create(UserCreateRequestDto createDto) {
        Optional<User> user = userRepository.findUserByEmail(createDto.getEmail());
        if (user.isPresent()) {
            throw new RuntimeException("User already present");
        }

        Authority userAuth = authorRepository.findByAuthority(UserAuthority.USER)
                .orElseThrow(() -> new RuntimeException("Authority not found"));

        if (!createDto.getPassword().equals(createDto.getConfirmPassword())) {
            throw new RuntimeException("Password did not match");
        }

        User userForSave = User.builder()
                .authorities(List.of(userAuth))
                .password(passwordEncoder.encode(createDto.getPassword()))
                .email(createDto.getEmail())
                .organization(getOrganizationFromToken(createDto.getToken()))
                .build();

        userRepository.save(userForSave);

        return UserCreateResponseDto.builder()
                .message(String.format("User registered successfully : %s", userForSave.getEmail()))
                .build();
    }







    public UserResponseDto update(Long id, UserRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("User with id %S not found", id)));

        Optional<Address> address = addressRepository.findById(requestDto.getAddressId());


        User userForUpdate = modelMapperConfig.modelMapper().map(requestDto, User.class);
        userForUpdate.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        userForUpdate.setId(id);

        if (address.isPresent()) {
            userForUpdate.getAddress().setId(address.get().getId());
            userForUpdate.getAddress().setAddress(requestDto.getAddress());
        }
        userForUpdate.getAddress().setAddress(requestDto.getAddress());
        requestDto.getPhoneNumbers().forEach(phoneNumber ->
                userForUpdate.getPhoneNumbers().add(phoneNumber));
        requestDto.getPhoneNumbers().forEach(phoneNumber -> phoneNumber.setUser(user));

        User savedUser = userRepository.save(userForUpdate);
        return modelMapperConfig.modelMapper().map(savedUser, UserResponseDto.class);
    }


    public UserResponseDto delete(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(String.format("User with id %s not found", userId)));
        userRepository.deleteById(userId);
        return modelMapperConfig.modelMapper().map(user, UserResponseDto.class);
    }


    public UserResponseDto get(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(String.format("User with id %s not found", userId)));
        return modelMapperConfig.modelMapper().map(user, UserResponseDto.class);
    }

    public List<UserResponseDto> getAll(Long id) {
        List<User> userList = userRepository.findAllUsers(id);
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        userList.forEach((user -> userResponseDtos.add
                (modelMapperConfig.modelMapper().map(user, UserResponseDto.class))));
        return userResponseDtos;

    }

    public Organization getOrganizationFromToken(String token) {
        String emailFromToken = jwtService.getEmailFromToken(token);
        Optional<User> user = userRepository.findUserByEmail(emailFromToken);
        return user.get().getOrganization();
    }



}
