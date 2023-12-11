package az.ingress.etaksifyms.controller;

import az.ingress.etaksifyms.dto.request.address.AddressRequestDto;
import az.ingress.etaksifyms.dto.response.address.AddressResponseDto;
import az.ingress.etaksifyms.service.address.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ResolvedPointcutDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
@Slf4j

public class AddressController {
    private final AddressService addressService;

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressResponseDto> get(@PathVariable Long addressId) {
        return ResponseEntity.ok(addressService.get(addressId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AddressResponseDto>> getAll() {
        return ResponseEntity.of(addressService.getAll());
    }

//    @DeleteMapping("/{addressId}")
//    public ResponseEntity<AddressResponseDto> delete(@PathVariable Long addressid, @PathVariable String addressId) {
//        return ResponseEntity.ok(addressid.create(addressid));
//    }
//
//    @PutMapping("/{addressId}")
//    public ResponseEntity<AddressResponseDto> update(@PathVariable Long addressId,
//                                                     @RequestBody AddressRequestDto requestDto){
//        return ResponseEntity.ok(addressId.update(addressId,requestDto));
//
//  }

}
