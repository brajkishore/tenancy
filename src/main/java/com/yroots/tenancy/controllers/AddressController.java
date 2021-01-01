package com.yroots.tenancy.controllers;

import com.yroots.tenancy.dto.AddressDto;
import com.yroots.tenancy.entities.Address;
import com.yroots.tenancy.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<?> getAllAddresses(){
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

    @PostMapping
    public ResponseEntity<?> createAddress(@RequestBody AddressDto addressDto){
        Address address=addressService.createAddress(addressDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(address);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneAddress(@PathVariable(required = true,name = "id") Long id){
        return ResponseEntity.ok(addressService.getOneAddress(id));
    }

}
