package com.yroots.tenancy.services;

import com.yroots.tenancy.dto.AddressDto;
import com.yroots.tenancy.entities.Address;
import com.yroots.tenancy.repo.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address createAddress(AddressDto addressDto) {
        return addressRepository.save(Address.mapFrom(addressDto));
    }

    public Address getOneAddress(Long id) {
        return addressRepository.getAddressById(id);
    }
}
