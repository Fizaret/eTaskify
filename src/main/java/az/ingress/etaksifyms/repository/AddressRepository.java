package az.ingress.etaksifyms.repository;

import az.ingress.etaksifyms.entity.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
