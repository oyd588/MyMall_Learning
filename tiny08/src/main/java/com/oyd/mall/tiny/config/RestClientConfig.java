package com.oyd.mall.tiny.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

public class RestClientConfig extends AbstractElasticsearchConfiguration {
    @Override
    public RestHighLevelClient elasticsearchClient() {
        return null;
    }
}
