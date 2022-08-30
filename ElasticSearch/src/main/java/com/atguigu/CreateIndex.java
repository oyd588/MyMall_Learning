package com.atguigu;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.io.IOException;

public class CreateIndex {
    public static void main(String[] args) throws IOException {
        //创建客户端对象
        RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

       //创建索引 -请求对象
        CreateIndexRequest indexRequest = new CreateIndexRequest("api");
        //发送请求，获取响应
        CreateIndexResponse createIndexResponse = esClient.indices().create(indexRequest, RequestOptions.DEFAULT);

        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println(acknowledged);

        //关闭客户端链接
        esClient.close();
    }
}
