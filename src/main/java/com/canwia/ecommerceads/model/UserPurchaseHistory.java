package com.canwia.ecommerceads.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPurchaseHistory {

    private String userId;
    private List<String> purchasedProductIds;
}
