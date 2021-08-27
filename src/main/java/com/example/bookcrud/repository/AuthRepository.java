package com.example.bookcrud.repository;

import com.example.bookcrud.entity.Logininfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends CrudRepository<Logininfo, Integer> {

    Logininfo findByUsername(String userName);
}
