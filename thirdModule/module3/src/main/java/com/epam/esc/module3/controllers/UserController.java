package com.epam.esc.module3.controllers;

import com.epam.esc.module3.entity.Order;
import com.epam.esc.module3.entity.User;
import com.epam.esc.module3.exception.DAOException;
import com.epam.esc.module3.exception.NoEntityException;
import com.epam.esc.module3.exception.Response;
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


    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(produces = { "application/hal+json" })
    public CollectionModel<User> getAllUsers(@RequestParam(name = "page",defaultValue = "1") int numberOfPage, @RequestParam(name = "content",defaultValue = "5") int numberOfItemOnPage){

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
    public void updateUser(@RequestBody User user,@PathVariable("id") int id) {
        userService.updateUser(user,id);
    }

    @PostMapping("/buygift/{userid}/{giftid}")
    public void bueGift(@PathVariable("userid") int userId,@PathVariable("giftid")int giftId) {
        userService.buyGift(userId,giftId);
    }

    @GetMapping("/{id}/orders")
    public List<Order> getAllUsersOrder(@PathVariable("id")int id){
        return userService.getAllUsersOrders(id);

    }

}
