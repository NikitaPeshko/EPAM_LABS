package com.epam.esc.module3.repository;

import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.entity.Tag;
import com.epam.esc.module3.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface GiftRepository extends JpaRepository<Gift,Integer> {
    Gift findByName(String name);
    Page<Gift> findAll(Pageable pageable);
    Page<Gift> findByNameContaining(String partofName,Pageable pageable);
    List<Gift> findGiftsByListOfTagIn(Set<Tag> list);

    @Query(value = "select user_id from gift_certigicate where gift_id=?1",nativeQuery = true)
    Integer findUserIdInGift(int nameofgiftid);


    @Query(value = "select userinorder_id from(select sum(amount) summa,userinorder_id from orders group by userinorder_id order by summa desc limit 1)k",nativeQuery = true)
    Integer findUserIdWithMaxAmountOfAllOrders();


    @Query(value = "select gift_certigicate.*, group_concat(t1.tag_name)as tags from gift_certigicate join gift_tag ft1 on ft1.idgift = gift_certigicate.gift_id join tags t1 on t1.tag_id = ft1.idtag group by gift_certigicate.gift_id having tags like  ?",nativeQuery = true)
    List<Gift> findGiftBySeveralTags(String tags);
}
