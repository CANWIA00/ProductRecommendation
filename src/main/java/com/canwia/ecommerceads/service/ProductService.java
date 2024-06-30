package com.canwia.ecommerceads.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.canwia.ecommerceads.dto.SearchRequestDto;
import com.canwia.ecommerceads.model.Product;
import com.canwia.ecommerceads.repository.ProductRepository;
import com.canwia.ecommerceads.util.EsUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;



@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ElasticsearchClient elasticsearchClient;

    public List<Product> searchProducts(SearchRequestDto searchRequestDto){
        Supplier<Query> querySupplier = EsUtil.buildFieldForFieldAndValue(
                searchRequestDto.getFieldName(),searchRequestDto.getSearchValue()
        );
        SearchResponse<Product> searchResponse = null;

        try {
            searchResponse = elasticsearchClient.search(q->q.index("product_index").query(querySupplier.get()),Product.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return extractProductFromResponse(searchResponse);
    }


    public Set<String> searchProductSuggested(String name) {
        Query query = EsUtil.suggestedQuery(name);
        log.info("Elasticsearch query first = {}", query.toString());

        try {
            return elasticsearchClient.search(q -> q.index("product_index").query(query), Product.class)
                    .hits()
                    .hits()
                    .stream()
                    .map(Hit::source).filter(Objects::nonNull)
                    .map(Product::getName)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getAllProduct(String indexName) {
        var query = EsUtil.createMatchAllQuery();
        SearchResponse<Product> response = null;
        log.info("Elasticsearch response first = {}" , query.toString());

        try {
            response = elasticsearchClient.search(q->q.index(indexName).query(query), Product.class );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        log.info("Elasticsearch response second = {}", response);

        return extractProductFromResponse(response);
    }


    public List<Product> extractProductFromResponse(SearchResponse<Product> response){
        return response
                .hits()
                .hits()
                .stream()
                .map(Hit::source)
                .collect(Collectors.toList());
    }


    /***************************************/

    public void deleteAll() {
        productRepository.deleteAll();
    }

    public void saveAll(List<Product> product){
        productRepository.saveAll(product);
    }


    public List<Product> findByName(String query) {
        return productRepository.findByName(query);
    }

    public Product findById(String productId){
        return productRepository.findById(productId).orElseThrow(null);
    }

    public List<Product> findByCategory(String category) {
       return productRepository.findByCategory(category);
    }
}
