package com.canwia.ecommerceads.service;

import com.canwia.ecommerceads.model.UserPurchaseHistory;
import com.canwia.ecommerceads.model.UserSearchHistory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserHistoryService {

    private final Map<String, UserSearchHistory> searchHistoryMap = new HashMap<>();
    private final Map<String, UserPurchaseHistory> purchaseHistoryMap = new HashMap<>();

    public UserSearchHistory getSearchHistory(String userId) {
        return searchHistoryMap.get(userId);
    }

    public UserPurchaseHistory getPurchaseHistory(String userId) {
        return purchaseHistoryMap.get(userId);
    }


    public UserHistoryService() {
        initializeMockData();
    }

    private void initializeMockData() {

        searchHistoryMap.put("user1", new UserSearchHistory("user1", Arrays.asList("MacBook Air 2024", "Msi GL-65")));
        searchHistoryMap.put("user2", new UserSearchHistory("user2", Arrays.asList("Iphone ", "Msi")));



        purchaseHistoryMap.put("user1", new UserPurchaseHistory("user1", Arrays.asList("2ce65b67-efe2-4181-a170-6ea9ba430058", "edb187cd-e7a2-4c06-a540-a193f4873c36")));
        purchaseHistoryMap.put("user2", new UserPurchaseHistory("user2", List.of("f7bbc977-98d6-4f05-aabd-20473d1e0007")));

    }
    // Methods to add search queries and purchase records
    public void addSearchQuery(String userId, String query) {
        searchHistoryMap.computeIfAbsent(userId, k -> new UserSearchHistory(userId, new ArrayList<>())).getSearchQueries().add(query);
    }

    public void addPurchaseRecord(String userId, String productId) {
        purchaseHistoryMap.computeIfAbsent(userId, k -> new UserPurchaseHistory(userId, new ArrayList<>())).getPurchasedProductIds().add(productId);
    }

}
