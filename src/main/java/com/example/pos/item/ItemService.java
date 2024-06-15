package com.example.pos.item;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService (ItemRepository itemRepository) {this.itemRepository = itemRepository;}

    public List<Item> getItems() {return itemRepository.findAll();}

    public Map<String,String> addNewItem(Item item) {
        Optional<Item> itemOptional = itemRepository
                .findItemByName (item.getName());
        if (itemOptional.isPresent()){
            return Map.of(
                    "status", "error",
                    "message", "Name taken"
            );


        }
        itemRepository.save(item);
        return Map.of(
                "status", "success"

        );
    }

    public void deleteItem(Long itemId) {
        boolean exists = itemRepository.existsById(itemId);
        if (!exists){
            throw new IllegalStateException("Item with id" + itemId + "does not exist");

        }
        itemRepository.deleteById(itemId);

    }

    @Transactional
    public void updateItemStock(Long itemId, int stock) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException(
                        "Item with id " + itemId + " does not exist"
                ));

            item.setQuantity(stock);

    }


    @Transactional
    public void updateItem(Item item) {
        Item itemToUpdate = itemRepository.findById(item.getId())
                .orElseThrow(() -> new IllegalStateException(
                        "Item with id " + item.getId() + " does not exist"
                ));

        if (item.getName() != null &&
                item.getName().length() > 0 &&
                !item.getName().equals(itemToUpdate.getName())){
            itemToUpdate.setName(item.getName());
        }

        if (item.getPrice() != 0 &&
                item.getPrice() != itemToUpdate.getPrice()){
            itemToUpdate.setPrice(item.getPrice());
        }

        if (item.getQuantity() != 0 &&
                item.getQuantity() != itemToUpdate.getQuantity()){
            itemToUpdate.setQuantity(item.getQuantity());
        }
    }
}
