package com.canwia.ecommerceads.service;

import com.canwia.ecommerceads.model.Product;
import com.canwia.ecommerceads.model.UserPurchaseHistory;
import com.canwia.ecommerceads.model.UserSearchHistory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RecommendationService {

    private final ProductService productService;

    private final UserHistoryService userHistoryService;



    public List<Product> getRecommendations(String userId) {
        List<Product> recommendationList = new ArrayList<>();

        //Search History
        UserSearchHistory userSearchHistory = userHistoryService.getSearchHistory(userId);
        log.info("*************** Recommendation Logs *****************");
        log.info("User history results = {}", userSearchHistory);
        if(userSearchHistory != null){
            for (String query : userSearchHistory.getSearchQueries()){
                List<Product> products = productService.findByName(query);
                recommendationList.addAll(products);
                log.info("Search history results = {}", products);
            }
        }

        //Purchase History
        UserPurchaseHistory userPurchaseHistory = userHistoryService.getPurchaseHistory(userId);
        if(userPurchaseHistory != null){
            for (String query : userPurchaseHistory.getPurchasedProductIds()){

                //Recommend other products in the category of purchased products
                Product purchasedProduct = productService.findById(query);
                if (purchasedProduct != null) {
                    List<Product> similarProducts = productService.findByCategory(purchasedProduct.getCategory());
                    recommendationList.addAll(similarProducts);
                }
            }
        }

        // Clear the same resource and make the results unique
        return recommendationList.stream().distinct().collect(Collectors.toList());

    }

}
