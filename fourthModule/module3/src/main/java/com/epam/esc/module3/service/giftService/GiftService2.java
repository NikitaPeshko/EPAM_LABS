package com.epam.esc.module3.service.giftService;

import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.repository.GiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftService2 {

    private GiftRepository giftRepository;

    @Autowired
    public GiftService2(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    public List<Gift> getAllGift(Pageable page){
        return giftRepository.findAll(page).getContent();
    }

}
