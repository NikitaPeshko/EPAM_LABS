package com.epam.esc.module3.controllers;

import com.epam.esc.module3.dto.UserDTO;
import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.entity.Order;
import com.epam.esc.module3.entity.User;
import com.epam.esc.module3.exception.NoEntityException;
import com.epam.esc.module3.exception.ServiceException;
import com.epam.esc.module3.service.userService.UserService2;
import com.epam.esc.module3.service.userService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserService2 userService2;

    @GetMapping(produces = { "application/hal+json" })
    public CollectionModel<UserDTO> getAllUsers(@RequestParam(name = "page",defaultValue = "1") int numberOfPage,
                                                @RequestParam(name = "content",defaultValue = "5") int numberOfItemOnPage) throws NoEntityException, ServiceException {

        List<UserDTO> allUsers=userService2.getAllUsers(numberOfPage,numberOfItemOnPage);
        for (final UserDTO user : allUsers) {
            int userId  = user.getId();
            Link selfLink = linkTo(UserController.class).slash(userId)
                    .withSelfRel();
            user.add(selfLink);
            if (userService2.getAllOrder(userId).size() > 0) {
                final Link ordersLink = linkTo(methodOn(UserController.class).getAllUsersOrder(userId))
                        .withRel("allOrders");
                user.add(ordersLink);
            }
        }

        Link link = linkTo(AdminController.class).withSelfRel();
        CollectionModel<UserDTO> result = CollectionModel.of(allUsers, link);
        return result;
    }


    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable("id") int id) throws NoEntityException {
        return userService2.getUserById(id);
    }


    @DeleteMapping("/{id}")
    public boolean deleteUserById(@PathVariable("id") int id) throws ServiceException {

        return userService2.deleteUser(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") int id) throws NoEntityException {

        return userService2.updateUser(id,user);
    }


    @PostMapping("/buygift/{userid}")
    public List<Gift> bueGift(@PathVariable("userid") int userId, @RequestParam("gift")List<Integer>gifts) throws NoEntityException {
        return userService2.buyGifts(userId,gifts);
    }


    @GetMapping(value = "/{id}/orders",produces = { "application/hal+json" })
    public CollectionModel<Order> getAllUsersOrder(@PathVariable("id")int id) throws NoEntityException, ServiceException {

        final List<Order> orders = userService2.getAllOrder(id);
        for (final Order order : orders) {
            final Link selfLink = linkTo(
                    methodOn(UserController.class).getUserOrderById(id, order.getOrderId())).withSelfRel();
            order.add(selfLink);
        }

        Link link = linkTo(methodOn(UserController.class).getAllUsersOrder(id)).withSelfRel();
        CollectionModel<Order> result = CollectionModel.of(orders, link);
        return result;


    }
    @GetMapping("/{userid}/orders/{idorder}")
    public Order getUserOrderById(@PathVariable("userid")int userID,@PathVariable("idorder")int orderId) throws NoEntityException {
        return userService2.getUserOrderById(userID,orderId);

    }




}
