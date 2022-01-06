package com.epam.esc.module3.service.userService;

import com.epam.esc.module3.dto.UserDTO;
import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.entity.Order;
import com.epam.esc.module3.entity.User;
import com.epam.esc.module3.exception.NoEntityException;
import com.epam.esc.module3.exception.ServiceException;
import com.epam.esc.module3.repository.GiftRepository;
import com.epam.esc.module3.repository.OrderRepository;
import com.epam.esc.module3.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService2 {

    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private GiftRepository giftRepository;

    @Autowired
    public UserService2(UserRepository userRepository, OrderRepository orderRepository, GiftRepository giftRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.giftRepository = giftRepository;
    }




    public List<UserDTO> getAllUsers(int numberOfPage, int numberOfItemOnPage) {
        Pageable page= PageRequest.of(numberOfPage-1,numberOfItemOnPage);
        List<User> users=userRepository.findAll(page).getContent();
        List<UserDTO> usersDTO = users.stream().map(user -> convertUserToUserDTO(user)).collect(Collectors.toList());
        return usersDTO;

    }

    public Optional<User> getUserById(int id) throws NoEntityException {

       Optional<User> user=userRepository.findById(id);
       if (user.isEmpty()){
           throw new NoEntityException("No User by this id","ERROR");
       }
       return user;
    }

    public User updateUser(int id, User user) throws NoEntityException {

        if(!userRepository.existsById(id)){
            throw new NoEntityException("No user by this id","ERROR");
        }
        User userFromDB=userRepository.getOne(id);
        if ((user.getName()!=null)&&(!user.getName().equalsIgnoreCase(userFromDB.getName()))){
            userFromDB.setName(user.getName());
        }
        if ((user.getEmail()!=null)&&(!user.getEmail().equalsIgnoreCase(userFromDB.getEmail()))){
            userFromDB.setEmail(user.getEmail());
        }
        if ((user.getLogin()!=null)&&((!user.getLogin().equalsIgnoreCase(userFromDB.getLogin())))){
            userFromDB.setLogin(user.getLogin());
        }

        return userRepository.save(userFromDB);
    }


    public boolean deleteUser(int id) throws ServiceException {
        if(userRepository.existsById(id)){
            User deletedUser=userRepository.getOne(id);
            deletedUser.setNotLocked(false);
            userRepository.save(deletedUser);
           // userRepository.deleteById(id);
        }else {
            throw new ServiceException("User by this id not exist","ERRORCODE");
        }
        return !userRepository.findAccauntStatusByUserId(id);
    }

    public List<Order> getAllOrder(int id) throws ServiceException {
        User user=new User();
        user.setUserId(id);
        List<Order> orders=orderRepository.findOrderByUserInOrder(user);
        return orders;
    }

    public Order getUserOrderById(int userID,int orderId) throws NoEntityException {

        User user=new User();
        user.setUserId(userID);

        Order order=orderRepository.findOrderByUserInOrderAndOrderId(user,orderId);
        if (order==null){
            throw new NoEntityException("No order with this ID in this user","ERRORCODE");
        }
        return order;
    }

    @Transactional
    public List<Gift> buyGifts(int userID, List<Integer>giftsID) throws NoEntityException {

        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault()));

        User user=new User();
        user.setUserId(userID);
        List<Gift> list=new LinkedList<Gift>();

        for (Integer giftID:giftsID){
            Gift gift=giftRepository.findById(giftID).get();
            Integer result=giftRepository.findUserIdInGift(giftID);

            if ((result!=null)&&(result>0)){
                throw new NoEntityException("This certificate id="+giftID+" already buy by another user","ERRORCODE");
            }
            gift.setUser(user);

            list.add(gift);
        }
        Order order=new Order();
        order.setDataOfOrder(timestamp);
        int amountOfAllOrder=0;
        for (Gift gift:list){
            amountOfAllOrder=amountOfAllOrder+gift.getPrice();
        }
        order.setAmount(amountOfAllOrder);
        order.setGiftsinorder(list);
        order.setUserInOrder(user);
        orderRepository.save(order);
        for (Gift gift:list){
            gift.setOrder(order);
        }
        return list;
    }


    private UserDTO convertUserToUserDTO(User user){
        UserDTO userDTO=new UserDTO();
        userDTO.setId(user.getUserId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoleName(user.getRole().getName());
        userDTO.setLogin(user.getLogin());
        return userDTO;
    }
}
