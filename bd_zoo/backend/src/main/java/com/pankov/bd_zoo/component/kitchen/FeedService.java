package com.pankov.bd_zoo.component.kitchen;

import com.pankov.bd_zoo.component.food.Food;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FeedService implements IFeedService {

    private final FeedRepository feedRepository;

    public FeedService(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }

    @Override
    public List<Feed> findAll() {
        return feedRepository.findAll();
    }

    @Override
    public Feed findById(Long id) {
        return feedRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Feed not found, id: " + id));
    }

    @Override
    public Feed create(Feed feed) {
        feed.setFeedType("Meat");
        return feedRepository.save(feed);
    }


    @Override
    public void deleteById(Long id) {
        feedRepository.deleteById(id);
    }
}

