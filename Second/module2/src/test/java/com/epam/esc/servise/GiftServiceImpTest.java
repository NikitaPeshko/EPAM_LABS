package com.epam.esc.servise;

import com.epam.esc.DTO.GiftDTO;
import com.epam.esc.dao.GiftDAOImp;
import com.epam.esc.entity.Gift;
import com.epam.esc.entity.TempGift;
import com.epam.esc.exception.DaoException;
import com.epam.esc.exception.NoEntityException;
import com.epam.esc.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

class GiftServiceImpTest {

//    @Mock
    private GiftServiceImp service;
    @Mock
    private GiftDAOImp repositoryMock;


    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        service= new GiftServiceImp(repositoryMock);

    }


    @Test
    void getAllGifts() throws NoEntityException {
        List<GiftDTO> models = new ArrayList<>();
        when(repositoryMock.getAllGifts()).thenReturn(models);
        List<GiftDTO> actual = service.getAllGifts();
        assertThat(actual, is(models));
    }

    @Test
    void getAllGiftsByPart() throws ServiceException, NoEntityException {
        List<GiftDTO> list=new ArrayList<>();
        GiftDTO model = new GiftDTO();

        model.setId(1);
        model.setName("laptop");
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
        GiftDTO model2 = new GiftDTO();

        model2.setId(1);
        model2.setName("magazine");
        model2.setDiscription("temp discription");
        model2.setPrice(10);
        model2.setDuration(7);
        model2.setCreateDate("1970-01-19T23:06Z");
        model2.setLastUpdateDate("1970-01-19T23:06Z");
        model2.setTags(tags);

        list.add(model2);
        when(repositoryMock.getAllGiftsByPart("maga","")).thenReturn(list);
        List<GiftDTO> actual=service.getAllGiftsByPart("maga","");

        assertThat(actual,is(list));


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
        assertThat(actual, is(model));
    }


    @Test
    void addGifttemp() throws NoEntityException {
        TempGift model = new TempGift();
        model.setId(1);
        model.setDiscription("iphones");
        model.setPrice(10);
        model.setDuration(7);
        model.setCreateDate(Timestamp.valueOf("2019-04-21 14:17:02"));
        model.setLastUpdateDate(Timestamp.valueOf("2020-04-21 14:17:02"));
        String tags="minsk,it,sales";
        model.setTags(tags);
        when(repositoryMock.addGifttemp(model)).thenReturn(model);
        TempGift actual = service.addGifttemp(model);
        verify(repositoryMock, times(1)).addGifttemp(model);
        assertThat(actual, is(model));
    }

    @Test
    void deleteGift() throws ServiceException, DaoException {
        when(repositoryMock.deleteGift(1)).thenReturn(true);
        Boolean actual = service.deleteGift(1);
        verify(repositoryMock, times(1)).deleteGift(1);
        assertThat(actual, is(true));
    }

    @Test
    void updateGift() {
        Gift model = new Gift();
        model.setId(1);
        model.setDiscription("spa salon minsk nikita 123");
        model.setPrice(10);
        model.setDuration(7);
        model.setCreateDate(Timestamp.valueOf("2019-04-21 14:17:02"));
        model.setLastUpdateDate(Timestamp.valueOf("2020-04-21 14:17:02"));
        Set<String> tags=new HashSet<>();
        tags.add("spa");
        tags.add("minsk");
        tags.add("sport");
        model.setListOfTag(tags);

        Gift modelNew = new Gift();
        modelNew.setId(1);
        modelNew.setDiscription("asus");
        modelNew.setPrice(10);
        modelNew.setDuration(7);
        modelNew.setCreateDate(Timestamp.valueOf("2019-04-21 14:17:02"));
        modelNew.setLastUpdateDate(Timestamp.valueOf("2020-04-21 14:17:02"));
        modelNew.setListOfTag(tags);

        when(repositoryMock.updateGift(1,modelNew)).thenReturn(modelNew);
        Gift actual = service.updateGift(1,modelNew);
        verify(repositoryMock, times(1)).updateGift(1,modelNew);
        assertThat(model.getId(), is(model.getId()));
        assertThat(actual, is(modelNew));
    }
}
