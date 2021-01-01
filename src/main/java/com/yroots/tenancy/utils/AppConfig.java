package com.yroots.tenancy.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Map;

@Slf4j
@Data
@Configuration
@ConfigurationProperties("app.config")
public class AppConfig {
    private String schemaPrefix;
    private String defaultTenant;
    private Map<String,String> tenantMapper;

    public String getSchema(String tenantIdentifier){
        if(StringUtils.hasText(tenantIdentifier)) {
            if(tenantMapper.containsKey(tenantIdentifier))
                return schemaPrefix + tenantMapper.get(tenantIdentifier);
        }

        //If no valid tenant exists, using default
        return getDefaultSchema();
    }
    public String getDefaultSchema(){
        return schemaPrefix+defaultTenant;
    }

    @PostConstruct
    public void init(){
     log.info("::::::::::"+getClass().getSimpleName()+" is initialized::::::::::");
        System.out.println(this);
    }
}
