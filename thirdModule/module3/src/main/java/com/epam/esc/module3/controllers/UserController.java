package com.epam.esc.module3.controllers;

import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.entity.Order;
import com.epam.esc.module3.entity.User;
import com.epam.esc.module3.exception.DAOException;
import com.epam.esc.module3.exception.NoEntityException;
import com.epam.esc.module3.service.userService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserController {


    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(produces = { "application/hal+json" })
    public CollectionModel<User> getAllUsers(@RequestParam(name = "page",defaultValue = "1") int numberOfPage, @RequestParam(name = "content",defaultValue = "5") int numberOfItemOnPage) throws NoEntityException {

        List<User> allUsers=userService.getAllUsers(numberOfPage,numberOfItemOnPage);
        for (final User user : allUsers) {
            int userId  = user.getUserId();
            Link selfLink = linkTo(UserController.class).slash(userId)
                    .withSelfRel();
            user.add(selfLink);
            if (userService.getAllUsersOrders(userId).size() > 0) {
                final Link ordersLink = linkTo(methodOn(UserController.class).getAllUsersOrder(userId))
                        .withRel("allOrders");
                user.add(ordersLink);
            }
        }

        Link link = linkTo(UserController.class).withSelfRel();
        CollectionModel<User> result = CollectionModel.of(allUsers, link);
        return result;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") int id) throws NoEntityException {

        return userService.getUserById(id);
    }

    @PostMapping
    public User addUser(@RequestBody User user) throws DAOException {
        return userService.addUser(user);

    }

    @DeleteMapping("/{id}")
    public boolean deleteUserById(@PathVariable("id") int id) {

        return userService.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user,@PathVariable("id") int id) {

        return userService.updateUser(user,id);
    }

    @PostMapping("/buygift2/{userid}/{giftid}")
    public void bueGift(@PathVariable("userid") int userId,@PathVariable("giftid")int giftId) {
        userService.buyGift(userId,giftId);
    }


    @PostMapping("/buygift/{userid}")
    public List<Gift> bueGift(@PathVariable("userid") int userId, @RequestParam("gift")List<Integer>gifts) throws NoEntityException {
        return userService.buyGifts(userId,gifts);
    }
    @GetMapping(value = "/{id}/orders",produces = { "application/hal+json" })
    public CollectionModel<Order> getAllUsersOrder(@PathVariable("id")int id) throws NoEntityException {

        final List<Order> orders = userService.getAllUsersOrders(id);
        for (final Order order : orders) {
            final Link selfLink = linkTo(
                    methodOn(UserController.class).getUserOrderById(id, order.getOrderId())).withSelfRel();
            order.add(selfLink);
        }

        Link link = linkTo(methodOn(UserController.class).getAllUsersOrder(id)).withSelfRel();
        CollectionModel<Order> result = CollectionModel.of(orders, link);
        return result;


//        return userService.getAllUsersOrders(id);

    }
    @GetMapping("/{userid}/orders/{idorder}")
    public Order getUserOrderById(@PathVariable("userid")int userID,@PathVariable("idorder")int orderId) throws NoEntityException {
        return userService.getUserOrderById(userID,orderId);

    }




}
