package com.epam.esc.dao;

import com.epam.esc.model.Tag;

import java.util.List;

public interface TagDAO {

    Tag addTag(Tag tag);
    List<Tag> getTags();
    Tag getTagById(int id);
    boolean deleteTag(int id);



}
