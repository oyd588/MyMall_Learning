package com.atguigu;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class HelloElasticSearch {
    public static void main(String[] args) throws IOException {
        //创建客户端对象
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

        System.out.println(esClient);

        //关闭客户端链接
        esClient.close();
    }
}
