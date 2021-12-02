package com.epam.esc.controllers;

import com.epam.esc.DTO.GiftDTO;
import com.epam.esc.DTO.GiftDTOWithTag;
import com.epam.esc.dao.GiftDAOImp;
import com.epam.esc.exception.DaoException;
import com.epam.esc.exception.NoEntityException;
import com.epam.esc.exception.Response;
import com.epam.esc.exception.ServiceException;
import com.epam.esc.model.Gift;
import com.epam.esc.model.TempGift;
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
    public List<GiftDTO> getEmployees() throws NoEntityException {
        return giftDAOImp.getAllGifts();

    }


//    @RequestMapping(value = "/giftwithtag/{id}", //
//            method = RequestMethod.GET, //
//            produces = { MediaType.APPLICATION_JSON_VALUE})
//    @ResponseBody
//    public List<GiftDTO> getGiftWithTag(@PathVariable("id")int id) {
//        return giftDAOImp.getGiftWithTag(id);
//
//    }




    @RequestMapping(value = "/gifts/find", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<GiftDTO> getGiftByName(@RequestParam(name="name",defaultValue = "")String name,
                                       @RequestParam(name="discription",defaultValue = "")String discription) throws NoEntityException, ServiceException {


            return giftDAOImp.getAllGiftsByPart(name,discription);




    }


    @RequestMapping(value = "/gifts/findbytag", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<GiftDTO> getGiftByName(@RequestParam(name="tag",defaultValue = "")String tag) {

        return giftDAOImp.getGiftsByTag(tag);
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
    public List<GiftDTO> getEmployeeSortByPrice(@RequestParam(name="sortingmethod",defaultValue = "asc")String sortingMethod,
                                                @RequestParam(name = "orderby",defaultValue = "name")String orderby) throws NoEntityException {

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
    public GiftDTO getGift(@PathVariable("id")int id) throws DaoException {
        return giftDAOImp.getGiftById(id);
    }


    @RequestMapping(value = "/gift", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Gift addGift(@RequestBody Gift gift) {
        return giftDAOImp.addGift(gift);

    }


    @RequestMapping(value = "/giftaddtemp", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public TempGift addGifttemp(@RequestBody TempGift gift) throws NoEntityException {
        return giftDAOImp.addGifttemp(gift);

    }


    @RequestMapping(value = "/gifts/{id}", //
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
    public boolean deleteGift(@PathVariable("id") int id) throws ServiceException {
        return giftDAOImp.deleteGift(id);

    }


//
//    @ExceptionHandler(DaoException.class)
//    public Response handleException(DaoException e) {
//        return new Response(e.getMessage(),e.);
//    }

//    @GetMapping(value = "/testDefaultControllerAdvice", produces = {MediaType.APPLICATION_JSON_VALUE})
//    public Response testDefaultControllerAdvice(@RequestParam(required = false, defaultValue = "false") boolean exception)
//            throws ServiceException {
//        if (exception) {
//            throw new ServiceException("BusinessException in testDefaultControllerAdvice");
//        }
//        return new Response("OK");
//    }
@ExceptionHandler({ServiceException.class})
public Response handleException(ServiceException e) {
    return new Response(e.getMessage(), e.getErrorcode());
}

    @ExceptionHandler({NoEntityException.class,})
    public Response handleExceptionForNoEntityException(NoEntityException e) {
        return new Response(e.getMessage(), e.getErrorcode());
    }



}
