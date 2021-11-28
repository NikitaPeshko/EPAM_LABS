package com.epam.esc.dao;

import com.epam.esc.exception.DaoException;
import com.epam.esc.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class TagDAOImpl implements TagDAO{

    JdbcTemplate jdbcTemplate;

    @Autowired
    public TagDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Tag addTag(Tag tag) {
        jdbcTemplate.update("INSERT INTO tag (tag_name) VALUES(?)",
                tag.getTagName());
        return tag;
    }

    @Override
    public List<Tag> getTags() {
        return jdbcTemplate.query("SELECT *FROM tag",new BeanPropertyRowMapper<>(Tag.class));
    }

    @Override
    public Tag getTagById(int id) {
        return jdbcTemplate.query("SELECT * FROM tag WHERE idtag=?", new Object[]{id}, new BeanPropertyRowMapper<>(Tag.class))
                .stream().findAny().orElseThrow(()->new DaoException("no tag by this id"));
    }

    @Override
    public boolean deleteTag(int id) {
        return jdbcTemplate.update("delete from tag where idtag=?",new Object[]{id})>0;
    }
}
