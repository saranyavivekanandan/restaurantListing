package com.zomato.restaurantListing.controller;

import com.zomato.restaurantListing.custom.exception.BusinessException;
import com.zomato.restaurantListing.custom.exception.ControllerException;
import com.zomato.restaurantListing.dto.RestaurantDTO;
import com.zomato.restaurantListing.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping("/getAllRestaurants")
    public ResponseEntity<List<RestaurantDTO>>  getAllRestaurants(){
        List<RestaurantDTO> allRestaurants = restaurantService.getAllRestaurants();
        return new ResponseEntity<>(allRestaurants, HttpStatus.OK);
    }

    @PostMapping("/addRestaurant")
    public ResponseEntity<?> saveRestaurant(@RequestBody RestaurantDTO restaurantDTO){
        try {
            RestaurantDTO restaurantedAdded = restaurantService.saveRestaurant(restaurantDTO);
            return new ResponseEntity<RestaurantDTO>(restaurantedAdded, HttpStatus.CREATED);
        } catch (BusinessException e){
            ControllerException controllerException = new ControllerException(e.getErrorCode() ,e.getErrorMessage());
            return new ResponseEntity<ControllerException>(controllerException, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            ControllerException controllerException = new ControllerException("611","Something went wrong in controller");
            return new ResponseEntity<ControllerException>(controllerException, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getRestaurant/{id}")
    public ResponseEntity<RestaurantDTO> getById(@PathVariable int id){
        RestaurantDTO restaurant = restaurantService.getRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
}
