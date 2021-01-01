package com.yroots.tenancy.entities;

import com.yroots.tenancy.dto.AddressDto;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public static Address mapFrom(AddressDto addressDto){
        Address address=new Address();
        BeanUtils.copyProperties(addressDto,address);
        return address;
    }

}
