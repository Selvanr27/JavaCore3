package com.tam.testing.service;

import com.tam.testing.domain.Car;

import java.util.Optional;

public interface CarService {
    Car saveCar(Car car);

    Optional<Car> findCarById(Long id);
}