package com.pard.repository;

import com.pard.entity.PardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PardRepository extends JpaRepository<PardEntity, String> {
}
