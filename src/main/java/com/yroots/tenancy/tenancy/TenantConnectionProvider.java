package com.yroots.tenancy.tenancy;

import com.yroots.tenancy.utils.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@Component
public class TenantConnectionProvider implements MultiTenantConnectionProvider {

    @Autowired
    private AppConfig appConfig;

    private DataSource datasource;

    public TenantConnectionProvider(DataSource dataSource) {
        this.datasource = dataSource;
    }

    @Override
    public Connection getAnyConnection() throws SQLException {
        return datasource.getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        log.info("Get connection for tenant {}", tenantIdentifier);
        final Connection connection = getAnyConnection();
        try {
            connection.createStatement().execute("use "+appConfig.getSchema(tenantIdentifier));
        }catch(SQLException exception){
            throw new HibernateException("Problem in setting schema to "+tenantIdentifier,exception);
        }
        return connection;
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        log.info("Release connection for tenant {}", tenantIdentifier);

        try {
            connection.createStatement().execute("use "+appConfig.getDefaultSchema());
        }catch(SQLException exception){
            throw new HibernateException("Problem in setting schema to "+tenantIdentifier,exception);
        }

        releaseAnyConnection(connection);
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return true;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public boolean isUnwrappableAs(Class aClass) {
        return false;
    }

    @Override
    public <T> T unwrap(Class<T> aClass) {
        return null;
    }
}
