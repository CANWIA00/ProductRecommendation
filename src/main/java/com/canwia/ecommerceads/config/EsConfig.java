package com.canwia.ecommerceads.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.io.IOException;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.canwia.ecommerceads.repository")
@ComponentScan(basePackages = "com.canwia.ecommerceads")
public class EsConfig extends ElasticsearchConfiguration {

    @Value("${elastic-search-url}")
    private String url;

    @Override
    public ClientConfiguration clientConfiguration() {

        return ClientConfiguration.builder().connectedTo(url).build();
    }











}
