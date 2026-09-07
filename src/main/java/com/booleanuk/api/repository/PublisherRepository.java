package com.booleanuk.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.booleanuk.api.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

}
