package com.example.pos.item;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping (path = "api/v1/item")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getItems() {
        return itemService.getItems();
    }


    @PostMapping
    public Map<String,String> addAnewItem(@RequestBody Item item) {
         return itemService.addNewItem(item);
    }

    @DeleteMapping (path = "{itemId}")
    public void deleteItem(@PathVariable ("itemId") Long itemId){
        itemService.deleteItem(itemId);
    }

    //change stock
    @PutMapping(path = "stock/{itemId}")
    public void updateItemStock(
            @PathVariable("itemId") Long itemId,
            @RequestParam(required = false) int stock
    ){
        itemService.updateItemStock(itemId, stock);
    }
    @PutMapping()
    public void updateItem(

            @RequestBody Item item
    ){
        itemService.updateItem(item);
    }
}

