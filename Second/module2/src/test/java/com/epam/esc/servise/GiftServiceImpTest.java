package com.epam.esc.servise;

import com.epam.esc.DTO.GiftDTO;
import com.epam.esc.dao.GiftDAOImp;
import com.epam.esc.exception.DaoException;
import com.epam.esc.exception.NoEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

class GiftServiceImpTest {

    @Mock
    private GiftServiceImp service;
    @Mock
    private GiftDAOImp repositoryMock;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void getAllGifts() throws NoEntityException {
        List<GiftDTO> models = new ArrayList<>();
        when(repositoryMock.getAllGifts()).thenReturn(models);
        List<GiftDTO> actual = service.getAllGifts();
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
    void getGiftById() throws DaoException {
        GiftDTO model = new GiftDTO();

        model.setId(1);
        model.setDiscription("spa salon minsk nikita 123");
        model.setPrice(10);
        model.setDuration(7);
        model.setCreateDate("1970-01-19T23:06Z");
        model.setLastUpdateDate("1970-01-19T23:06Z");
        Set<String> tags=new HashSet<>();
        tags.add("spa");
        tags.add("minsk");
        tags.add("sport");
        model.setTags(tags);

        when(repositoryMock.getGiftById(1)).thenReturn(model);

        GiftDTO actual = service.getGiftById(1);

        verify(repositoryMock, times(1)).getGiftById(1);
        verifyNoMoreInteractions(repositoryMock);

        assertThat(actual, is(model));
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
