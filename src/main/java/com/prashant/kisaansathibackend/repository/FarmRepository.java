package com.prashant.kisaansathibackend.repository;

import com.prashant.kisaansathibackend.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FarmRepository extends JpaRepository<Farm, Long> {

    List<Farm> findByUserIdOrderByIdDesc(Long userId);

    Optional<Farm> findByIdAndUserId(Long id, Long userId);
}
