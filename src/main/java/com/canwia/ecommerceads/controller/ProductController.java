package com.canwia.ecommerceads.controller;

import com.canwia.ecommerceads.dto.SearchRequestDto;
import com.canwia.ecommerceads.model.Product;
import com.canwia.ecommerceads.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/product")
public class ProductController {

    private final ProductService productService;

    // auto-filled search
    @GetMapping("/search")
    public List<Product> searchProduct(@RequestBody  SearchRequestDto searchRequestDto){
        return productService.searchProducts(searchRequestDto);
    }

    // auto-suggested search
    @GetMapping("/searchES/{name}")
    public Set<String> suggestedSearch(@PathVariable String name){
        return productService.searchProductSuggested(name);
    }




    /**************************************/

    @GetMapping("/getAll/{indexName}")
    public List<Product> getAllProduct(@PathVariable String indexName){
        return productService.getAllProduct(indexName);
    }

    @DeleteMapping("/deleteAll")
    public void  deleteAllProduct(){
        productService.deleteAll();
    }


}
