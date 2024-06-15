package com.example.pos.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface ItemRepository
    extends JpaRepository <Item, Long> {

    Optional <Item> findItemByName (String item_name);

    //update item quantity
    @Modifying
    @Query("UPDATE Item i SET i.quantity = i.quantity + :quantity WHERE i.id = :id")
    void updateItemQuantity(@Param("id") Long id, @Param("quantity") int quantity);




}
