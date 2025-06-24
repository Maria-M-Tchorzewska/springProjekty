
package com.example.BookStoreFinalProject.repositories;

import com.example.BookStoreFinalProject.entities.OrderReference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderReferenceRepository extends JpaRepository<OrderReference, Long> {

    Optional<OrderReference> findByReferenceNo(String referenceNo);

}
