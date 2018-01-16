package com.mood.tomoapp.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitJoinColumnNameSource;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
public class CustomImplicitNamingStrategy extends SpringImplicitNamingStrategy {
    @Override
    public Identifier determineJoinColumnName(ImplicitJoinColumnNameSource source) {
        return toIdentifier(source.getReferencedColumnName().getText(), source.getBuildingContext());
    }
}
