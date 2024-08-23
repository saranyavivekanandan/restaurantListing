package com.zomato.restaurantListing.custom.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControllerException extends RuntimeException {

    private String errorCode;
    private String errorMessage;
}
