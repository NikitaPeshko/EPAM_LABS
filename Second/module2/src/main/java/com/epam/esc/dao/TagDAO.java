package com.epam.esc.dao;

import com.epam.esc.exception.DaoException;
import com.epam.esc.entity.Tag;

import java.util.List;

public interface TagDAO {

    Tag addTag(Tag tag);
    List<Tag> getTags();
    Tag getTagById(int id) throws DaoException;
    boolean deleteTag(int id);



}
