package com.epam.esc.controllers;

import com.epam.esc.DTO.GiftDTO;
import com.epam.esc.dao.GiftDAOImp;
import com.epam.esc.exception.DaoException;
import com.epam.esc.exception.Response;
import com.epam.esc.model.Gift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GiftController {

    private GiftDAOImp giftDAOImp;

    @Autowired
    public GiftController(GiftDAOImp giftDAOImp) {
        this.giftDAOImp = giftDAOImp;
    }

    @RequestMapping(value = "/gifts", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<GiftDTO> getEmployees() {
        return giftDAOImp.getAllGifts();

    }


//    @RequestMapping(value = "/gifts/sort", //
//            method = RequestMethod.GET, //
//            produces = { MediaType.APPLICATION_JSON_VALUE})
//    @ResponseBody
//    public List<GiftDTO> getEmployeeSortByPrice(@RequestParam(name="sortingmethod")String sortingMethod) {
//
//        return giftDAOImp.getAllGiftsSortByName(sortingMethod);
//
//    }

    @RequestMapping(value = "/gifts/sort", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<GiftDTO> getEmployeeSortByPrice(@RequestParam(name="sortingmethod")String sortingMethod,
                                                @RequestParam(name = "orderby",defaultValue = "name")String orderby) {

        List<GiftDTO> list=null;
        if(orderby.equalsIgnoreCase("name")){
            list=giftDAOImp.getAllGiftsSortByName(sortingMethod);
        }
        if(orderby.equalsIgnoreCase("date")){
            list=giftDAOImp.getAllGiftsSortByDate(sortingMethod);
        }
        return list;


    }



    @RequestMapping(value = "/gifts/{id}", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public GiftDTO getGift(@PathVariable("id")int id) {
        return giftDAOImp.getGiftById(id);
    }


    @RequestMapping(value = "/gift", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Gift addGift(@RequestBody Gift gift) {
        return giftDAOImp.addGift(gift);

    }


    @RequestMapping(value = "/gift/{id}", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Gift updateGift(@RequestBody Gift gift,@PathVariable("id") int id) {
        return giftDAOImp.updateGift(id,gift);

    }


    @RequestMapping(value = "/gifts/{id}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public boolean deleteGift(@PathVariable("id") int id) {
        return giftDAOImp.deleteGift(id);

    }











    @ExceptionHandler(DaoException.class)
    public Response handleException(DaoException e) {
        return new Response(e.getMessage());
    }
}
