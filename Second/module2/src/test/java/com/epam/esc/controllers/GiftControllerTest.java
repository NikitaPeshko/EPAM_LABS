package com.epam.esc.controllers;

import com.epam.esc.config.SpringConfig;
import com.epam.esc.entity.Gift;
import com.epam.esc.entity.TempGift;
import com.epam.esc.exception.NoEntityException;
import com.epam.esc.servise.GiftServiceImp;
import com.epam.esc.util.TestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {SpringConfig.class})
class GiftControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void testfindall() throws Exception {
        mockMvc.perform(get("/gifts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("laptop"))
                .andExpect(jsonPath("$[1].id").value(3))
                .andExpect(jsonPath("$[1].name").value("electro"));

    }


    @Test
    void testGetGiftByName() throws Exception {
        this.mockMvc.perform(get("/gifts/find")
                        .param("name", "ele"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].name").value("electro"));
    }
    @Test
    void testGetGiftByDiscription() throws Exception {
        this.mockMvc.perform(get("/gifts/find")
                        .param("discription", "spa"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].name").value("laptop"));
    }

    @Test
    void getGiftById() throws Exception {
        mockMvc.perform(get("/gifts/{id}",1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("laptop"));
    }


    @Test
    void addGifttemp() throws Exception {
        this.mockMvc.perform(post("/giftaddtemp")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType("application/json"))
                .andExpect(jsonPath("$.message").value("Hello World!!!"));
    }

    @Test
    void updateGift() throws Exception {
        Gift model = new Gift();
        model.setId(1);
        model.setName("laptop");
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

        mockMvc.perform(put("/gifts/{id}", 1)
                .contentType(TestUtils.APPLICATION_JSON_UTF8)
                .content(TestUtils.convertObjectToJsonBytes(model))
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("laptop"));
    }
    @Test
    void testGetNoGiftByNameAndThrowException() throws Exception {
        this.mockMvc.perform(get("/gifts/find")
                        .param("name", "elenvfdmvnfdm"))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message").value("No gifts"))
                .andExpect(jsonPath("$.errCode").value("404"))
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(NoEntityException.class));
    }
    @Test
    void testAddGiftCertificateAndReturnEntity() throws Exception {
        TempGift model = new TempGift();
        model.setId(1);
        model.setName("laptop123");
        model.setDiscription("it minsk");
        model.setPrice(10);
        model.setDuration(7);
        model.setCreateDate(Timestamp.valueOf("2019-04-21 14:17:02"));
        model.setLastUpdateDate(Timestamp.valueOf("2020-04-21 14:17:02"));
        String tags="minsk";
        model.setTags(tags);

        mockMvc.perform(post("/giftaddtemp")
                        .contentType(TestUtils.APPLICATION_JSON_UTF8)
                        .content(TestUtils.convertObjectToJsonBytes(model))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("laptop123"));
    }

    @Test
    void testAddGiftCertificateAndThrowException() throws Exception {
        TempGift model = new TempGift();
        model.setId(1);
        model.setName("laptop1234");
        model.setDiscription("it minsk");
        model.setPrice(10);
        model.setDuration(7);
        model.setCreateDate(Timestamp.valueOf("2019-04-21 14:17:02"));
        model.setLastUpdateDate(Timestamp.valueOf("2020-04-21 14:17:02"));


        mockMvc.perform(post("/giftaddtemp")
                        .contentType(TestUtils.APPLICATION_JSON_UTF8)
                        .content(TestUtils.convertObjectToJsonBytes(model))
                )
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message").value("Not enough parametars"))
                .andExpect(jsonPath("$.errCode").value("404NOTEN"))
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(NoEntityException.class));

    }
}
