package com.example.haveIt.service;

import com.example.haveIt.entity.exception.ItemNotFoundException;
import com.example.haveIt.entity.models.Items;
import com.example.haveIt.repository.ItemsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ItemsService {

    private ItemsRepository itemsRepository;

    public ItemsService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public Items createItem(Items items) {

        items.setId(UUID.randomUUID().toString());
        items.setItemId(items.getName() + "-" + UUID.randomUUID());
        return itemsRepository.save(items);
    }

    public void removeItem(String itemId) {

        Items item = findItemByItemId(itemId);
        itemsRepository.deleteByItemId(item.getItemId());
    }

    public Items fetchItemInformation(String itemId) {

        return itemsRepository.findByItemId(itemId);
    }

    public Items findItemByItemId(String itemId) {

        return Optional.ofNullable(itemsRepository.findByItemId(itemId))
                .orElseThrow(() -> new ItemNotFoundException("Item not present in application"));
    }
}
