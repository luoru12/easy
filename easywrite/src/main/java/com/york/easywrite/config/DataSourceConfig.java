package com.york.easywrite.config;

import com.york.easywrite.contant.DBTypeEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean("masterDataSource")
    @ConfigurationProperties("spring.datasource")
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("slave1DataSource")
    @ConfigurationProperties("spring.datasource.slave1")
    public DataSource slave1DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("slave2DataSource")
    @ConfigurationProperties("spring.datasource.slave2")
    public DataSource slave2DataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("targetDataSource")
    public DataSource myRoutingDataSource(@Qualifier("masterDataSource")DataSource masterDataSource,
                                          @Qualifier("slave1DataSource")DataSource slave1DataSource,
                                          @Qualifier("slave2DataSource")DataSource slave2DataSource){
        Map<Object,Object> targetDatasource=new HashMap<>();
        targetDatasource.put(DBTypeEnum.MASTER,masterDataSource);
        targetDatasource.put(DBTypeEnum.SLAVE1,slave1DataSource);
        targetDatasource.put(DBTypeEnum.SALVE2,slave2DataSource);
        RoutingDataSource myRoutingDataSource=new RoutingDataSource();
        myRoutingDataSource.setDefaultTargetDataSource(masterDataSource);
        myRoutingDataSource.setTargetDataSources(targetDatasource);
        return myRoutingDataSource;
    }
}
