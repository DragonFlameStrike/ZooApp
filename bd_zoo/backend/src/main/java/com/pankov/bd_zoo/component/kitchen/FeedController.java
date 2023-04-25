package com.pankov.bd_zoo.component.kitchen;

import com.pankov.bd_zoo.component.animal.AnimalController;
import com.pankov.bd_zoo.component.food.Food;
import com.pankov.bd_zoo.component.food.FoodController;
import com.pankov.bd_zoo.component.kitchen.dto.FeedCreateDto;
import com.pankov.bd_zoo.component.kitchen.dto.FeedGetDto;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/feeds")
public class FeedController {

    private final IFeedService feedService;
    private final FoodController foodController;
    private final AnimalController animalController;

    public FeedController(IFeedService feedService,FoodController foodController,AnimalController animalController) {
        this.feedService = feedService;
        this.foodController = foodController;
        this.animalController = animalController;
    }

    @GetMapping("/")
    public List<FeedGetDto> getAllFeeds() {
        List<FeedGetDto> dtos = new ArrayList<>();
        List<Feed> feeds = feedService.findAll();
        for (Feed feed: feeds){
            FeedGetDto feedGetDto = new FeedGetDto();
            feedGetDto.setFeed(feed);
            feedGetDto.setAnimalName(feed.getAnimal().getName());
            Set<Food> foods = feed.getFoods();
            List<String> foodsType = new ArrayList<>();
            for (Food food :foods) {
                foodsType.add(food.getType());
            }
            feedGetDto.setFoodTypes(foodsType);
            dtos.add(feedGetDto);
        }
        return dtos;
    }

    @GetMapping("/{id}")
    public Feed getFeedById(@PathVariable Long id) {
        return feedService.findById(id);
    }

    @PostMapping("/")
    public Feed createFeed(@RequestBody FeedCreateDto dto) {
        Feed feed = new Feed();
        feed.setFeedingPerWeek(dto.getFeedingPerWeek());
        Set<Food> foods= new HashSet<>();
        for (Long foodId: dto.getFoodsId()) {
            foods.add(foodController.getById(foodId));
        }
        feed.setFoods(foods);
        feed.setAnimal(animalController.getById(dto.getAnimalId()));
        feed.setSeason(dto.getSeason());
        return feedService.create(feed);
    }

    @DeleteMapping("/{id}")
    public void deleteFeedById(@PathVariable Long id) {
        feedService.deleteById(id);
    }
}
