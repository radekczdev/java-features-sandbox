package com.czajor.nplusoneproblem.service;


import com.czajor.nplusoneproblem.domain.Car;
import com.czajor.nplusoneproblem.domain.Owner;
import com.czajor.nplusoneproblem.repository.CarRepository;
import com.czajor.nplusoneproblem.repository.OwnerRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarServiceTestData {

  @Autowired
  private CarRepository carRepository;

  @Autowired
  private OwnerRepository ownerRepository;

  public void populateTestData() {
    // Clear existing data
    carRepository.deleteAll();
    ownerRepository.deleteAll();

    // Create test owners
    Owner owner1 = new Owner();
    owner1.setName("John Doe");

    Owner owner2 = new Owner();
    owner2.setName("Jane Smith");

    Owner owner3 = new Owner();
    owner3.setName("Bob Johnson");

    List<Owner> savedOwners = ownerRepository.saveAll(Arrays.asList(owner1, owner2, owner3));

    // Create test cars
    Car car1 = new Car();
    car1.setModel("Camry");
    car1.setOwner(savedOwners.get(0));

    Car car2 = new Car();
    car2.setModel("Civic");
    car2.setOwner(savedOwners.get(1));

    Car car3 = new Car();
    car3.setModel("Mustang");
    car3.setOwner(savedOwners.get(0));

    Car car4 = new Car();
    car4.setModel("X5");
    car4.setOwner(savedOwners.get(2));

    carRepository.saveAll(Arrays.asList(car1, car2, car3, car4));
  }
}
