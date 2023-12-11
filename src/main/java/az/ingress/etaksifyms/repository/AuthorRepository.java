package az.ingress.etaksifyms.repository;

import az.ingress.etaksifyms.entity.authority.Authority;
import az.ingress.etaksifyms.entity.authority.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Authority,Long> {
    Optional<Authority> findByAuthority(UserAuthority userAuthority);
}
