package com.epam.esc.module3.repository;

import com.epam.esc.module3.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Integer> {
    Tag findByTagName(String name);
}
