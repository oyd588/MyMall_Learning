package com.oyd.mybatis;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

public class DruidDataSource implements DataSourceFactory {
    private Properties properties;

    @Override
    public void setProperties(Properties properties) {
        this.properties=properties;
    }

    @Override
    public DataSource getDataSource() {
        DataSource dataSource = null;
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}
