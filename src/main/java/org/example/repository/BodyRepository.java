package org.example.repository;

import org.example.models.Body;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BodyRepository extends JpaRepository<Body,Integer> {
    List<Body> findByType(String type);
    Body findBodyById(int id);
}
