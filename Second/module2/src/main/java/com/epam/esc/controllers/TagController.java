package com.epam.esc.controllers;

import com.epam.esc.dao.TagDAOImpl;
import com.epam.esc.exception.DaoException;
import com.epam.esc.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TagController {
    private TagDAOImpl tagDAO;

    @Autowired
    public TagController(TagDAOImpl tagDAO) {
        this.tagDAO = tagDAO;
    }

    @RequestMapping(value = "/tags", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Tag> gettags() {
        return tagDAO.getTags();
    }



    @RequestMapping(value = "/tags/{id}", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Tag getTag(@PathVariable("id")int id) throws DaoException {
        return tagDAO.getTagById(id);
    }


    @RequestMapping(value = "/tags/{id}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public boolean deleteTag(@PathVariable("id") int id) {
        return tagDAO.deleteTag(id);

    }

    @RequestMapping(value = "/tag", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Tag addTag(@RequestBody Tag tag) {
        return tagDAO.addTag(tag);

    }




}
