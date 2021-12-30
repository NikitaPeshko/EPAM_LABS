package com.epam.esc.module3.repository;

import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface GiftRepository extends JpaRepository<Gift,Integer> {
    Gift findByName(String name);
    Page<Gift> findAll(Pageable pageable);
    Page<Gift> findByNameContaining(String partofName,Pageable pageable);
    List<Gift> findGiftsByListOfTagIn(Set<Tag> list);


}
