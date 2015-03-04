package com.incra.lightadmin;

import com.incra.models.User;
import org.lightadmin.api.config.AdministrationConfiguration;
import org.lightadmin.api.config.builder.EntityMetadataConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.FieldSetConfigurationUnitBuilder;
import org.lightadmin.api.config.builder.ScreenContextConfigurationUnitBuilder;
import org.lightadmin.api.config.unit.EntityMetadataConfigurationUnit;
import org.lightadmin.api.config.unit.FieldSetConfigurationUnit;
import org.lightadmin.api.config.unit.ScreenContextConfigurationUnit;

/**
 * Created by Jeffrey Risberg on 2/4/2015.
 */
public class UserAdministration extends AdministrationConfiguration<User> {

    public EntityMetadataConfigurationUnit configuration(EntityMetadataConfigurationUnitBuilder configurationBuilder) {
        return configurationBuilder.nameField("firstName").build();
    }

    public ScreenContextConfigurationUnit screenContext(ScreenContextConfigurationUnitBuilder screenContextBuilder) {
        return screenContextBuilder
                .screenName("User Administration").build();
    }

    public FieldSetConfigurationUnit listView(final FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("firstName").caption("First Name")
                .field("lastName").caption("Last Name")
                .build();
    }

    public FieldSetConfigurationUnit quickView(final FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("firstName").caption("First Name")
                .field("lastName").caption("Last Name")
                .field("dateCreated").caption("Date Created")
                .field("lastUpdated").caption("Last Updated")
                .build();
    }

    public FieldSetConfigurationUnit showView(final FieldSetConfigurationUnitBuilder fragmentBuilder) {
        return fragmentBuilder
                .field("id").caption("Id")
                .field("firstName").caption("First Name")
                .field("lastName").caption("Last Name")
                .field("email").caption("Email")
                .field("password").caption("Password")
                .field("dateCreated").caption("Date Created")
                .field("lastUpdated").caption("Last Updated")
                .build();
    }
}