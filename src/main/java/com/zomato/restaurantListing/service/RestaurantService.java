package com.zomato.restaurantListing.service;

import com.zomato.restaurantListing.Mapper.RestaurantMapper;
import com.zomato.restaurantListing.custom.exception.BusinessException;
import com.zomato.restaurantListing.dto.RestaurantDTO;
import com.zomato.restaurantListing.entity.Restaurant;
import com.zomato.restaurantListing.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepo repo;

    public List<RestaurantDTO> getAllRestaurants() {
        try {
            List<Restaurant> restaurants = repo.findAll();
            if(restaurants.isEmpty()) {
                throw new BusinessException("604", "The restaurant list is completely empty");
            }
            return restaurants.stream().map(restaurant -> RestaurantMapper.Instance.mapRestaurantToRestaurantDTO(restaurant)).collect(Collectors.toList());
        } catch (Exception e){
            throw new BusinessException("605", "Something went wrong in service layer" +e.getMessage());
        }


    }

    public RestaurantDTO saveRestaurant(RestaurantDTO restaurantDTO) {
        try {
            if (restaurantDTO.getName().isEmpty()) {
                throw new BusinessException("601", "The restaurant name is empty");
            }
            Restaurant restaurant = RestaurantMapper.Instance.mapRestaurantDTOToRestaurant(restaurantDTO);
            Restaurant restaurant1 = repo.save(restaurant);
            return RestaurantMapper.Instance.mapRestaurantToRestaurantDTO(restaurant1);
        } catch (IllegalArgumentException e){
            throw new BusinessException("602", "The given object is null "+e.getMessage());
        } catch (Exception e){
            throw new BusinessException("603", "Something went wrong"+ e.getMessage());
        }
    }

    public RestaurantDTO getRestaurantById(int id) {
        try {
            Optional<Restaurant> restaurant = repo.findById(id);
            RestaurantDTO restaurantResult = null;
            //if(restaurant.isPresent())
                return RestaurantMapper.Instance.mapRestaurantToRestaurantDTO(restaurant.get());
        } catch (IllegalArgumentException e) {
            throw new BusinessException("606", "The restaurant id is null");
        } catch (NoSuchElementException e) {
            throw new BusinessException("607", "restaurant id doesnt exist in db");
        }
    }
}
