package com.epam.esc.module3.service.giftService;

import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.entity.Tag;
import com.epam.esc.module3.exception.NoEntityException;
import com.epam.esc.module3.exception.ServiceException;
import com.epam.esc.module3.repository.GiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GiftService2 {

    private GiftRepository giftRepository;

    @Autowired
    public GiftService2(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    public List<Gift> getAllGift(int numberOfPage,int numberOfItemOnPage,String sortingBy,String sortingMethod){
        Pageable page;
        if(sortingBy.equalsIgnoreCase("name")){
            if(sortingMethod.equalsIgnoreCase("asc")){
                page = PageRequest.of(numberOfPage-1,numberOfItemOnPage, Sort.by(sortingBy).ascending());
                return giftRepository.findAll(page).getContent();
            }else{
                page = PageRequest.of(numberOfPage-1,numberOfItemOnPage, Sort.by(sortingBy).descending());
                return giftRepository.findAll(page).getContent();
            }
        }
        page=PageRequest.of(numberOfPage-1,numberOfItemOnPage);

        return giftRepository.findAll(page).getContent();
    }

    public Optional<Gift> getGiftById(int id) {
        return giftRepository.findById(id);
    }

    public Gift saveGift(Gift gift) {
        return giftRepository.save(gift);
    }

    public boolean deleteGift(int id) throws ServiceException {
        if(giftRepository.existsById(id)){
            giftRepository.deleteById(id);
        }else {
            throw new ServiceException("Gift by this id not exist","ERRORCODE");
        }
        return !giftRepository.existsById(id);
    }

    public List<Gift> findByPartName(String partOfName,Pageable pageable) throws NoEntityException {
        List<Gift> list=giftRepository.findByNameContaining(partOfName,pageable).getContent();

        if(list.isEmpty()){
            throw new NoEntityException("No gift by this name:"+partOfName,"ERRORCODE");
        }
        return list;

    }

    public List<Gift> findGiftBySeveralTags(List<String> tags) {
        return null;

    }


    public Gift changePriceOfgift(int id,int newprice) {
        Gift giftFromDB=giftRepository.getOne(id);
        giftFromDB.setPrice(newprice);
        return giftRepository.save(giftFromDB);
    }



}
