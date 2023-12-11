package az.ingress.etaksifyms.service.address;

import az.ingress.etaksifyms.dto.request.address.AddressRequestDto;
import az.ingress.etaksifyms.dto.response.address.AddressResponseDto;
import az.ingress.etaksifyms.entity.address.Address;
import az.ingress.etaksifyms.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j


public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final ModelMapper modelMapper;



    public AddressResponseDto get(Long id){
        Address address = addressRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format("Address with id %s not found")));
        return modelMapper.map(address,AddressResponseDto.class);
    }
    public AddressRequestDto create(AddressRequestDto requestDto){
        Address address = Address.builder()
                .address(requestDto.getAddress())
                .build();
        Address savedAddress = addressRepository.save(address);
        return modelMapper.map(savedAddress,AddressRequestDto.class);
    }
    public AddressResponseDto update(Long id,AddressRequestDto requestDto){
        Address address = addressRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format("address with id %s not found")));
        Address addressForUpdate = modelMapper.map(requestDto,Address.class);
        addressForUpdate.setId(id);
        return modelMapper.map(addressRepository.save(addressForUpdate),AddressResponseDto.class);

    }

    @Override
    public Optional<List<AddressResponseDto>> getAll() {
        return Optional.empty();
    }


}
