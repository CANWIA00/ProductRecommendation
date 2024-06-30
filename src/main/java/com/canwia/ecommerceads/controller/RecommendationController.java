package com.canwia.ecommerceads.controller;

import com.canwia.ecommerceads.model.Product;
import com.canwia.ecommerceads.service.RecommendationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/v1/product/recommendation")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping("/{userId}")
    public List<Product> productRecommendation(@PathVariable String userId){
        return recommendationService.getRecommendations(userId);
    }

}
