package com.example.app.converter;

import com.example.app.domain.Identity;
import org.springframework.util.Assert;

public class IdentityConverter {

    @Deprecated
    public static Identity toDomain(com.example.app.model.Identity model) {
        Assert.notNull(model, "should not be null");

        Identity identity = new Identity();
        identity.setId(model.getId());
        identity.setLogin(model.getLogin());
        identity.setRoleString(model.getRole());
        identity.setRegistrationDate(model.getRegistrationDate());

        return identity;
    }

    public static com.example.app.model.Identity toModel(Identity identity) {
        Assert.notNull(identity, "should not be null");

        com.example.app.model.Identity model = new com.example.app.model.Identity();
        model.setId(identity.getId());
        model.setLogin(identity.getLogin());
        model.setRegistrationDate(identity.getRegistrationDate());
        model.setRole(identity.getRoleString());

        return model;
    }

}
