package com.epam.esc.servise;

import com.epam.esc.DTO.GiftDTO;
import com.epam.esc.dao.GiftDAOImp;
import com.epam.esc.exception.NoEntityException;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GiftServiceImpTest {

    private GiftServiceImp service;
    private GiftDAOImp repositoryMock;


    @Before
    public void setUp() {
        repositoryMock=mock(GiftDAOImp.class);
        service = new GiftServiceImp(repositoryMock);
    }


    @Test
    void getAllGifts() throws NoEntityException {
        List<GiftDTO> models = new ArrayList<>();
        when(repositoryMock.getAllGifts()).thenReturn(models);

        List<GiftDTO> actual = service.getAllGifts();

        verify(repositoryMock, times(1)).getAllGifts();
        verifyNoMoreInteractions(repositoryMock);

        assertThat(actual, is(models));
    }

    @Test
    void getAllGiftsByPart() {
    }

    @Test
    void getAllGiftsSortByName() {
    }

    @Test
    void getGiftsByTag() {
    }

    @Test
    void getAllGiftsSortByDate() {
    }

    @Test
    void getGiftById() {
    }

    @Test
    void addGift() {
    }

    @Test
    void addGifttemp() {
    }

    @Test
    void deleteGift() {
    }

    @Test
    void updateGift() {
    }
}