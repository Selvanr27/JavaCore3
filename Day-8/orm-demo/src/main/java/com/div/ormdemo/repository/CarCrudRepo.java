package com.div.ormdemo.repository;

import com.div.ormdemo.domain.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarCrudRepo extends CrudRepository<Car, Long > { }