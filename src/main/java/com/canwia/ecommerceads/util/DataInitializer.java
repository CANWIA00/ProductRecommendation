package com.canwia.ecommerceads.util;

import com.canwia.ecommerceads.model.Product;
import com.canwia.ecommerceads.model.UserPurchaseHistory;
import com.canwia.ecommerceads.model.UserSearchHistory;
import com.canwia.ecommerceads.service.ProductService;
import com.canwia.ecommerceads.service.UserHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final ProductService productService;
    private final UserHistoryService userHistoryService;

    public DataInitializer(ProductService productService, UserHistoryService userHistoryService) {
        this.productService = productService;
        this.userHistoryService = userHistoryService;
    }

    @Override
    public void run(String... args)  {
        Product product = new Product();
        product.setId(String.valueOf(UUID.randomUUID()));
        product.setName("Samsung A30");
        product.setDescription("Samsung Mobile Phone");
        product.setCategory("Mobile Phone");
        product.setRating(7.5);
        product.setPrice(500.0);
        product.setBrand("Samsung");

        Product product1 = new Product();
        product1.setId(String.valueOf(UUID.randomUUID()));
        product1.setName("Iphone 7");
        product1.setDescription("Apple Mobile Phone");
        product1.setCategory("Mobile Phone");
        product1.setRating(7.8);
        product1.setPrice(600.0);
        product1.setBrand("Apple");

        Product product3 = new Product();
        product3.setId(String.valueOf(UUID.randomUUID()));
        product3.setName("Iphone 11");
        product3.setDescription("Apple Mobile Phone");
        product3.setCategory("Mobile Phone");
        product3.setRating(8.8);
        product3.setPrice(900.0);
        product3.setBrand("Apple");

        Product product4 = new Product();
        product4.setId(String.valueOf(UUID.randomUUID()));
        product4.setName("Msi GL-65");
        product4.setDescription("Msi Gaming Laptop");
        product4.setCategory("Computer");
        product4.setRating(7.8);
        product4.setPrice(1000.0);
        product4.setBrand("Msi");

        Product product5 = new Product();
        product5.setId(String.valueOf(UUID.randomUUID()));
        product5.setName("MacBook Air 2024");
        product5.setDescription("MacBook Air 255GB SSD 48GB ram");
        product5.setCategory("Computer");
        product5.setRating(9.8);
        product5.setPrice(7000.0);
        product5.setBrand("Apple");

        Product product6 = new Product();
        product6.setId(String.valueOf(UUID.randomUUID()));
        product6.setName("ASUS Tuf Gaming F15");
        product6.setDescription("Laptop ASUS TUF DASH F15 FX516PR 15,6-inch 144Hz i9-13900KF RAM-48GB 1TB-SSD");
        product6.setCategory("Computer");
        product6.setRating(8.8);
        product6.setPrice(6499.0);
        product6.setBrand("ASUS");

        Product product7 = new Product();
        product7.setId(String.valueOf(UUID.randomUUID()));
        product7.setName("ASUS TUF Gaming F15");
        product7.setDescription("ASUS TUF Gaming F15 FX506HC-HN004W laptop 15.6-inch IPS 144Hz i5-11400H 16GB-RAM 512GB-SSD");
        product7.setCategory("Computer");
        product7.setRating(7.8);
        product7.setPrice(4499.0);
        product7.setBrand("ASUS");

        Product product8 = new Product();
        product8.setId(String.valueOf(UUID.randomUUID()));
        product8.setName("ASUS TUF Gaming F15");
        product8.setDescription("ASUS TUF Gaming F15 FX506HC-HN004W laptop 16-inch IPS 144Hz i5-11400H 16GB-RAM 512GB-SSD");
        product8.setCategory("Computer");
        product8.setRating(7.8);
        product8.setPrice(5200.0);
        product8.setBrand("ASUS");

        Product product9 = new Product();
        product9.setId(String.valueOf(UUID.randomUUID()));
        product9.setName("ASUS TUF Gaming F15");
        product9.setDescription("ASUS TUF Gaming F15 FX506HC-HN004W laptop 17.3-inch IPS 144Hz i5-11400H 16GB-RAM 512GB-SSD");
        product9.setCategory("Computer");
        product9.setRating(7.8);
        product9.setPrice(6200.0);
        product9.setBrand("ASUS");

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product1);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
        productList.add(product6);
        productList.add(product7);
        productList.add(product8);
        productList.add(product9);

        productService.saveAll(productList);
        log.info("Data's has been created successfully when start-up");
        log.info("Data: {}", productList);



    }
}
