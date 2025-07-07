package com.czajor.nplusoneproblem.repository;

import com.czajor.nplusoneproblem.domain.Car;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
  // N+1 problem
  List<Car> findAll();

  // Solution 1: EntityGraph
  @EntityGraph(attributePaths = "owner")
  @Query("select c from Car c")
  List<Car> findAllWithOwnerEntityGraph();

  // Solution 2: JPQL fetch join
  @Query("select c from Car c join fetch c.owner")
  List<Car> findAllWithOwnerFetchJoin();

}

