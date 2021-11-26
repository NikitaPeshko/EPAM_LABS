package com.epam.esc.controllers;

import com.epam.esc.DTO.GiftDTO;
import com.epam.esc.dao.GiftDAO;
import com.epam.esc.exception.DaoException;
import com.epam.esc.exception.Response;
import com.epam.esc.model.Employee;
import com.epam.esc.model.Gift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GiftController {

    private GiftDAO giftDAO;

    @Autowired
    public GiftController(GiftDAO giftDAO) {
        this.giftDAO = giftDAO;
    }

    @RequestMapping(value = "/gifts", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<GiftDTO> getEmployees() {
        return giftDAO.getAllGifts();

    }
    @RequestMapping(value = "/gifts/{id}", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public GiftDTO getEmployee(@PathVariable("id")int id) {
        return giftDAO.getGiftById(id);
    }


    @RequestMapping(value = "/gift", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Gift addEmployee(@RequestBody Gift gift) {
        return giftDAO.addGift(gift);

    }



    @ExceptionHandler(DaoException.class)
    public Response handleException(DaoException e) {
        return new Response(e.getMessage());
    }
}
