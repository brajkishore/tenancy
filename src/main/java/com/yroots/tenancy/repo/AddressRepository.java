package com.yroots.tenancy.repo;

import com.yroots.tenancy.entities.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address,Long> {

    List<Address> findAll();

    @Query(name = "select * from Address where id:id")
    Address getAddressById(Long id);
}
