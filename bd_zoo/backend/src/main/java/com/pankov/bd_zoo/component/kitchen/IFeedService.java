package com.pankov.bd_zoo.component.kitchen;

import com.pankov.bd_zoo.component.food.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IFeedService {
    List<Feed> findAll();
    Feed findById(Long id);
    Feed create(Feed feed);
    void deleteById(Long id);
}
