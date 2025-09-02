package com.example.haveIt.repository;

import com.example.haveIt.entity.models.Items;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends MongoRepository<Items, String> {

    Items findByItemId(String itemId);

    void deleteByItemId(String itemId);
}
