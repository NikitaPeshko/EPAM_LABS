package com.epam.esc.module3.repository;

import com.epam.esc.module3.entity.Gift;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GiftRepository extends JpaRepository<Gift,Integer> {
    Gift findByName(String name);
    Page<Gift> findAll(Pageable pageable);
}
