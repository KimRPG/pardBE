package com.pard.repository;

import com.pard.entity.PardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PardRepository extends JpaRepository<PardEntity, String> {
    PardEntity findByName(String name);

    boolean existsByPart(String part);
    List<PardEntity> findByPart(String part);



}
