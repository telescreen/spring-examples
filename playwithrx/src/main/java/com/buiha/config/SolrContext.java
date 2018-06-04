package com.buiha.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolrContext {

    @Value("${solr.solrUrl}")
    private String solrUrl;

    @Value("${solr.connectionTimeout}")
    private int connectionTimeout;

    @Value("${solr.socketTimeout}")
    private int socketTimeout;

    @Bean
    public SolrClient solrClient() {
        return new HttpSolrClient.Builder(solrUrl)
                .withConnectionTimeout(connectionTimeout)
                .withSocketTimeout(socketTimeout)
                .build();
    }
}
