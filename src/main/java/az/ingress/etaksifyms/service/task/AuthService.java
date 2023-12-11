package az.ingress.etaksifyms.service.task;

import az.ingress.etaksifyms.entity.user.User;
import az.ingress.etaksifyms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUser(String email) {
        return userRepository.findByEmail(email);
    }

    // You can add more methods based on your requirements, e.g., saveUser, deleteUser, etc.
}

