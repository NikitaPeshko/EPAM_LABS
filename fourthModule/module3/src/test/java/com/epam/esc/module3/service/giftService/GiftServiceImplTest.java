package com.epam.esc.module3.service.giftService;

import com.epam.esc.module3.dao.giftDAO.GiftDAOImpl;
import com.epam.esc.module3.dao.userDAO.UserDAOImpl;
import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.entity.Tag;
import com.epam.esc.module3.entity.User;
import com.epam.esc.module3.exception.DAOException;
import com.epam.esc.module3.exception.NoEntityException;
import com.epam.esc.module3.service.userService.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class GiftServiceImplTest {

    @Mock
    private GiftDAOImpl giftDAOMock;

    private GiftServiceImpl service;

    private Gift gift1;
    private Gift gift2;
    private Tag tag1;
    private Tag tag2;
    private Tag tag3;
    private Set<Tag> tags1;
    private Set<Tag> tags2;
    private List<Gift> gifts;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service= new GiftServiceImpl(giftDAOMock);

        tag1=new Tag(1,"jam");
        tag2=new Tag(2,"minsk");
        tag3=new Tag(3,"games");

        tags1=new HashSet<>();
        tags1.add(tag1);
        tags1.add(tag2);

        tags2=new HashSet<>();
        tags2.add(tag2);
        tags2.add(tag3);


        gift1=new Gift();
        gift1.setId(1);
        gift1.setPrice(10);
        gift1.setName("Stravberry jam");
        gift1.setListOfTag(tags1);

        gift2=new Gift();
        gift2.setId(2);
        gift2.setPrice(3);
        gift2.setName("CS:GO");
        gift2.setListOfTag(tags2);

        gifts=new LinkedList<>();
        gifts.add(gift1);
        gifts.add(gift2);


    }

    @Test
    void getAllGift() {
        when(giftDAOMock.getAllGift()).thenReturn(gifts);
        List<Gift> actual=service.getAllGift();
        verify(giftDAOMock, times(1)).getAllGift();
        assertThat(actual, is(gifts));
    }

    @Test
    void getGiftById() throws DAOException {
        when(giftDAOMock.getGiftById(1)).thenReturn(gift1);
        Gift actual=service.getGiftById(1);
        verify(giftDAOMock, times(1)).getGiftById(1);
        assertThat(actual, is(gift1));
    }

    @Test
    void addGift() throws DAOException {
        when(giftDAOMock.addGift(gift1)).thenReturn(gift1);
        Gift actual=service.addGift(gift1);
        verify(giftDAOMock, times(1)).addGift(gift1);
        assertThat(actual, is(gift1));
    }

    @Test
    void deleteGift() {
        when(giftDAOMock.deleteGift(1)).thenReturn(true);
        boolean actual=service.deleteGift(1);
        verify(giftDAOMock, times(1)).deleteGift(1);
        assertThat(actual, is(true));
    }

    @Test
    void deleteGiftIfNoUserWithThisId() {
        when(giftDAOMock.deleteGift(33)).thenReturn(false);
        boolean actual=service.deleteGift(33);
        verify(giftDAOMock, times(1)).deleteGift(33);
        assertThat(actual, is(false));
    }

    @Test
    void findByName() throws NoEntityException {
        List<Gift>giftFinabyName=new LinkedList<>();
        giftFinabyName.add(gift1);
        when(giftDAOMock.findGiftByPatName("trav")).thenReturn(giftFinabyName);
        List<Gift> actual=service.findByName("trav");
        verify(giftDAOMock, times(1)).findGiftByPatName("trav");
        assertThat(actual, is(giftFinabyName));
    }



    @Test
    void changePriceOfGift() {
        Gift giftWithNewPrice=new Gift();
        giftWithNewPrice.setName("Stravberry jam");
        giftWithNewPrice.setPrice(22);
        giftWithNewPrice.setId(1);

        when(giftDAOMock.changePriceOfGift(1,22)).thenReturn(giftWithNewPrice);
        Gift actual=service.changePriceOfGift(1,22);
        verify(giftDAOMock, times(1)).changePriceOfGift(1,22);
        assertThat(actual, is(giftWithNewPrice));
    }

    @Test
    void findGiftBySeveralTags() throws NoEntityException {
        List<String> tagsName = tags1.stream().map(tag -> tag.getTagName()).collect(Collectors.toList());
        List<Gift>giftWithNeedTags=new LinkedList<>();
        giftWithNeedTags.add(gift1);
        when(giftDAOMock.findGiftBySeveralTags(tagsName)).thenReturn(giftWithNeedTags);
        List<Gift> actual=service.findGiftBySeveralTags(tagsName);
        verify(giftDAOMock, times(1)).findGiftBySeveralTags(tagsName);
        assertThat(actual, is(giftWithNeedTags));
    }
}