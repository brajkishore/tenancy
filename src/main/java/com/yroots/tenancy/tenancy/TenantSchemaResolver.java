package com.yroots.tenancy.tenancy;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TenantSchemaResolver implements CurrentTenantIdentifierResolver {
    @Override
    public String resolveCurrentTenantIdentifier() {
        return Optional.ofNullable(TenantContext.getCurrentTenant()).orElse("");
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
