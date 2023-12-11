package az.ingress.etaksifyms.service.organization;

import az.ingress.etaksifyms.dto.organization.OrganizationRequestDto;
import az.ingress.etaksifyms.dto.response.organization.OrganizationResponseDto;
import az.ingress.etaksifyms.entity.organization.Organization;
import az.ingress.etaksifyms.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class OrganizationServiceImpl {
    private final OrganizationRepository organizationRepository;
    private final ModelMapper modelMapper;

    public OrganizationResponseDto create(OrganizationRequestDto requestDto){
        Optional<Organization> organization= organizationRepository.findByName(requestDto.getName());
        if (organization.isPresent()){
            throw new RuntimeException(String.format("Organization with id %s not found"));

        }
        Organization organizationForSave=Organization.builder()
                .name(requestDto.getName())
                .build();
        Organization savedOrg = organizationRepository.save(organizationForSave);
        return modelMapper.map(savedOrg,OrganizationResponseDto.class);

    }
}
