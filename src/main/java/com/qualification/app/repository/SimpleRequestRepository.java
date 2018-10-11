package com.qualification.app.repository;

import com.qualification.app.model.SimpleRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimpleRequestRepository extends JpaRepository<SimpleRequest, Long> {
    List<SimpleRequest> findAll();
}
