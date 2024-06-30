package com.canwia.ecommerceads.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;


@Document(indexName = "product_index")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Setting(settingPath = "static/es-settings.json")
public class Product {

    @Id
    @Field(name = "id",type = FieldType.Text)
    private String id;
    @Field(name="name",type = FieldType.Text, analyzer = "custom_index", searchAnalyzer = "custom_search")
    private String name;
    @Field(name="description",type = FieldType.Text)
    private String description;
    @Field(name="category",type = FieldType.Text)
    private String category;
    @Field(name="price",type = FieldType.Double)
    private Double price;
    @Field(name="brand",type = FieldType.Text)
    private String brand;
    @Field(name="rating",type = FieldType.Double)
    private Double rating;
}
