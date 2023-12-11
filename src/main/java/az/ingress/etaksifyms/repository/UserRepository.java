package az.ingress.etaksifyms.repository;

import az.ingress.etaksifyms.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u from User u join fetch u.authorities a where u.email= :email")
    Optional<User> findUserByEmail(String email);


    @Query(value = "SELECT u FROM User u " +
            "LEFT JOIN u.address a " +
            "LEFT JOIN u.tasks t " +
            "WHERE u.organization.id = :id")
    List<User> findAllUsers(Long id);

    User findByEmail(String email);

}