package com.czajor.nplusoneproblem.service;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.czajor.nplusoneproblem.domain.Car;
import com.czajor.nplusoneproblem.repository.CarRepository;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest(showSql = false)
@Import(CarServiceTestData.class)
class CarServiceTest {

  @Autowired
  private CarServiceTestData testData;

  @Autowired
  private CarRepository carRepository;

  @Autowired
  private EntityManager entityManager;

  @BeforeEach
  void setUp(TestInfo testInfo) {
    testData.populateTestData();
    Logger sqlLogger = (Logger) LoggerFactory.getLogger("org.hibernate.SQL");
    sqlLogger.setLevel(Level.DEBUG);
    System.out.printf("============= %s ================%n", testInfo.getDisplayName());
  }

  @AfterEach
  void tearDown() {
    System.out.println("=============================\n");
    Logger sqlLogger = (Logger) LoggerFactory.getLogger("org.hibernate.SQL");
    sqlLogger.setLevel(Level.OFF);
  }

  @Test
  void testGetAllCarsNPlusOne() {
    entityManager.clear();
    final List<Car> all = carRepository.findAll();
    all.forEach(car -> car.getOwner().getName());
  }

  @Test
  void testGetAllCarsWithOwnerEntityGraph() {
    entityManager.clear();
    final List<Car> all = carRepository.findAllWithOwnerEntityGraph();
    all.forEach(car -> car.getOwner().getName());
  }

  @Test
  void testGetAllCarsWithOwnerFetchJoin() {
    entityManager.clear();
    final List<Car> all = carRepository.findAllWithOwnerFetchJoin();
    all.forEach(car -> car.getOwner().getName());
  }

}