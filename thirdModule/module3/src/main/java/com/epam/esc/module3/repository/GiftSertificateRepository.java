package com.epam.esc.module3.repository;


import com.epam.esc.module3.entity.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface GiftSertificateRepository extends JpaRepository<Gift, Integer> {
}
