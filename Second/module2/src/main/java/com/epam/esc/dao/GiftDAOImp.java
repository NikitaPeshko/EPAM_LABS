package com.epam.esc.dao;


import com.epam.esc.DTO.GiftDTO;
import com.epam.esc.exception.DaoException;
import com.epam.esc.exception.NoEntityException;
import com.epam.esc.exception.ServiceException;
import com.epam.esc.entity.Gift;
import com.epam.esc.entity.TempGift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class GiftDAOImp implements GiftDAO{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GiftDAOImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<GiftDTO> getAllGifts() throws NoEntityException {
        String sql="SELECT gift_certificate.*, GROUP_CONCAT(tag.tag_name) as tags FROM gift_certificate\n" +
                "inner join gift_tag on gift_certificate.idgift_certificate=gift_tag.idgift\n" +
                "left join tag on gift_tag.idtag=tag.idtag\n" +
                "group by gift_certificate.idgift_certificate";
        List<GiftDTO>list=null;
        list=jdbcTemplate.query(sql,new GiftDTOMapper());

        if(list.isEmpty()){
            throw new NoEntityException("No certificate found","500NOTFOUNT");
        }
        return list;
    }


//    public List<GiftDTO> getGiftWithTag(int id) {
//        return jdbcTemplate.query("SELECT gift_certificate.idgift_certificate, gift_certificate.gift_name, GROUP_CONCAT(tag.tag_name) as ganre FROM gift_certificate\n" +
//                "inner join gift_tag on gift_certificate.idgift_certificate=gift_tag.idgift\n" +
//                "left join tag on gift_tag.idtag=tag.idtag\n" +
//                "group by gift_certificate.idgift_certificate\n" +
//                "having idgift_certificate=?",new Object[]{id},new GiftDTOMapper())
//                .stream().findAny().orElseThrow(()->new DaoException("no user by this id"));
//    }

    public List<GiftDTO> getAllGiftsByPart(String partOfName,String discription) throws NoEntityException, ServiceException {
        String sqlForName="SELECT gift_certificate.*, GROUP_CONCAT(tag.tag_name) as tags FROM gift_certificate\n" +
                "inner join gift_tag on gift_certificate.idgift_certificate=gift_tag.idgift\n" +
                "left join tag on gift_tag.idtag=tag.idtag\n" +
                "group by gift_certificate.idgift_certificate\n" +
                "having gift_name like ?";
        String sqlForDiscription="SELECT gift_certificate.*, GROUP_CONCAT(tag.tag_name) as tags FROM gift_certificate\n" +
                "inner join gift_tag on gift_certificate.idgift_certificate=gift_tag.idgift\n" +
                "left join tag on gift_tag.idtag=tag.idtag\n" +
                "group by gift_certificate.idgift_certificate\n" +
                "having discription like ?";

        List<GiftDTO> list = null;
        if (partOfName.isEmpty() && discription.isEmpty()) {
            list = getAllGifts();
        }
        if (!partOfName.isEmpty()) {
            list = jdbcTemplate.query(sqlForName, new String[]{"%" + partOfName + "%"}, new GiftDTOMapper());
        }
        if (!discription.isEmpty()) {
            list = jdbcTemplate.query(sqlForDiscription, new String[]{"%" + discription + "%"}, new GiftDTOMapper());
        }
        if (list.isEmpty()){
            throw new ServiceException("No gifts","404");
        }
        return list;
    }



    public List<GiftDTO> getAllGiftsSortByName(String sortingMethod) {
        String sqlForAsc="SELECT gift_certificate.*, GROUP_CONCAT(tag.tag_name) as tags FROM gift_certificate\n" +
                "inner join gift_tag on gift_certificate.idgift_certificate=gift_tag.idgift\n" +
                "left join tag on gift_tag.idtag=tag.idtag\n" +
                "group by gift_certificate.idgift_certificate\n" +
                "order by gift_name asc";
        String sqlForDesc="SELECT gift_certificate.*, GROUP_CONCAT(tag.tag_name) as tags FROM gift_certificate\n" +
                "inner join gift_tag on gift_certificate.idgift_certificate=gift_tag.idgift\n" +
                "left join tag on gift_tag.idtag=tag.idtag\n" +
                "group by gift_certificate.idgift_certificate\n" +
                "order by gift_name desc";



        List<GiftDTO> list = null;
        if(sortingMethod.equalsIgnoreCase("asc")){
            list=jdbcTemplate.query(sqlForAsc,new GiftDTOMapper());
        }
        if(sortingMethod.equalsIgnoreCase("desc")){
            list=jdbcTemplate.query(sqlForDesc,new GiftDTOMapper());
        }
        return list;
    }


    public List<GiftDTO> getGiftsByTag(String tag) {

        String sql="select gift_certificate.*, group_concat(t1.tag_name)as tags\n" +
                " from gift_certificate\n" +
                "join gift_tag ft1\n" +
                " on ft1.idgift = gift_certificate.idgift_certificate\n" +
                "join tag t1\n" +
                " on t1.idtag = ft1.idtag\n" +
                "join gift_tag ft2\n" +
                " on ft2.idgift = gift_certificate.idgift_certificate\n" +
                "join tag t2\n" +
                " on t2.idtag = ft2.idtag and t2.tag_name = ? \n" +
                "group by gift_certificate.idgift_certificate";
        List<GiftDTO>list=null;

        list= jdbcTemplate.query(sql,new String[]{tag},new GiftDTOMapper());
        return list;



    }

    @Override
    public List<GiftDTO> getAllGiftsSortByDate(String sortingMethod) throws NoEntityException {
        String sqlForAsc="SELECT gift_certificate.*, GROUP_CONCAT(tag.tag_name) as tags FROM gift_certificate\n" +
                "inner join gift_tag on gift_certificate.idgift_certificate=gift_tag.idgift\n" +
                "left join tag on gift_tag.idtag=tag.idtag\n" +
                "group by gift_certificate.idgift_certificate\n" +
                "order by create_date asc";
        String sqlForDesc="SELECT gift_certificate.*, GROUP_CONCAT(tag.tag_name) as tags FROM gift_certificate\n" +
                "inner join gift_tag on gift_certificate.idgift_certificate=gift_tag.idgift\n" +
                "left join tag on gift_tag.idtag=tag.idtag\n" +
                "group by gift_certificate.idgift_certificate\n" +
                "order by create_date desc";

        List<GiftDTO> list = null;
        if(sortingMethod.equalsIgnoreCase("asc")){
            list=jdbcTemplate.query(sqlForAsc,new GiftDTOMapper());
        }
        if(sortingMethod.equalsIgnoreCase("desc")){
            list=jdbcTemplate.query(sqlForDesc,new GiftDTOMapper());
        }
        if(list.isEmpty()){
            String errorcode=sortingMethod.toUpperCase()+sortingMethod.length()+sortingMethod.charAt(0);
            throw new NoEntityException("No gifts certficate",errorcode);
        }
        return list;
    }


    public GiftDTO getGiftById(int id) throws DaoException {
        String sql="SELECT gift_certificate.*, GROUP_CONCAT(tag.tag_name) as tags FROM gift_certificate\n" +
                "inner join gift_tag on gift_certificate.idgift_certificate=gift_tag.idgift\n" +
                "left join tag on gift_tag.idtag=tag.idtag\n" +
                "group by gift_certificate.idgift_certificate\n" +
                "having idgift_certificate=?";


        return jdbcTemplate.query(sql, new Object[]{id}, new GiftDTOMapper())
                .stream().findAny().orElseThrow(()->new DaoException("no user by this id"));
    }




    public Gift addGift(Gift gift) {
        jdbcTemplate.update("INSERT INTO gift_certificate (gift_name, discription, price, duration, create_date, last_update_date)\n" +
                        "            VALUES(?, ?, ?, ?, ?, ?)",
                        gift.getName(),gift.getDiscription(),gift.getPrice(),gift.getDuration(),
                gift.getCreateDate(),gift.getLastUpdateDate());
        return gift;
    }

    public TempGift addGifttemp(TempGift gift) throws NoEntityException {
        if (gift.getName()==null||gift.getTags()==null||gift.getDiscription()==null||
                gift.getCreateDate()==null||gift.getDuration()==0||gift.getLastUpdateDate()==null){
            throw new NoEntityException("Not enough parametars","404NOTEN");
        }
        jdbcTemplate.update("INSERT INTO gift_certificate (gift_name, discription, price, duration, create_date, last_update_date)\n" +
                        "            VALUES(?, ?, ?, ?, ?, ?)",
                gift.getName(),gift.getDiscription(),gift.getPrice(),gift.getDuration(),
                gift.getCreateDate(),gift.getLastUpdateDate());

                String[] tags=gift.getTags().split(",");
                Set<String> tagsSet=new HashSet<>(Arrays.asList(tags));
                GiftDTO giftDTO=new GiftDTO();
                giftDTO.setTags(tagsSet);

            //    System.out.println(giftDTO);
                int coutIterarions=giftDTO.getTags().size();
        List<Gift> giftFromDb=jdbcTemplate.query("select * from gift_certificate\n" +
                "where gift_name=?",new String[]{gift.getName()},new GiftMapper());
        int id=giftFromDb.get(0).getId();
        System.out.println(id);

        for (int i=0;i<coutIterarions;i++){
            jdbcTemplate.update("INSERT INTO gift_tag (idgift, idtag)\n" +
                            "            VALUES(?, (select tag.idtag from tag where tag.tag_name=?))",
                    id,tags[i]);

        }
        if(gift==null){
            throw new NoEntityException("Certificate not created","404NOTCREATED");
        }
        return gift;
    }

    @Override
    public boolean deleteGift(int id) throws ServiceException {
        boolean result=true;

        jdbcTemplate.update("delete from gift_tag where idgift=?",new Object[]{id});

        try{
            result=jdbcTemplate.update("delete from gift_certificate where idgift_certificate=?",new Object[]{id})>0;
        }catch(DataIntegrityViolationException e){
            throw new ServiceException("Not delet gift","NOTDELETE"+id);
        }


        return result;


    }

    @Override
    public Gift updateGift(int id, Gift gift) {
        jdbcTemplate.update("update gift_certificate set gift_name=? ,discription=?," +
                "price=?,duration=? , create_date=? , last_update_date=? where idgift_certificate=?",
                new Object[]{gift.getName(),gift.getDiscription(),gift.getPrice(),
                gift.getDuration(),gift.getCreateDate(),gift.getLastUpdateDate(),id}   );

        return gift;
    }
}
