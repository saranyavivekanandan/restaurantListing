package com.zomato.restaurantListing.Mapper;

import com.zomato.restaurantListing.dto.RestaurantDTO;
import com.zomato.restaurantListing.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurantMapper {


    RestaurantMapper Instance = Mappers.getMapper(RestaurantMapper.class);

    Restaurant mapRestaurantDTOToRestaurant(RestaurantDTO restaurantDTO);

    RestaurantDTO mapRestaurantToRestaurantDTO(Restaurant restaurant);

}
