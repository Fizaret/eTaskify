package az.ingress.etaksifyms.repository;

import az.ingress.etaksifyms.entity.organization.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization,Long> {
    Optional<Organization> findByName(String name);

}
