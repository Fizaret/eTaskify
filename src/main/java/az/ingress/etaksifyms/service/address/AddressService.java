package az.ingress.etaksifyms.service.address;

import az.ingress.etaksifyms.dto.request.address.AddressRequestDto;
import az.ingress.etaksifyms.dto.response.address.AddressResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AddressService {
    public AddressResponseDto get(Long id);

    AddressResponseDto update(Long id, AddressRequestDto requestDto);

    Optional<List<AddressResponseDto>> getAll();
}
