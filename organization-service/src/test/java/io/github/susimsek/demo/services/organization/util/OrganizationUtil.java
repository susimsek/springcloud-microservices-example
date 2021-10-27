package io.github.susimsek.demo.services.organization.util;



import io.github.susimsek.demo.services.organization.model.Organization;

import java.util.List;

public class OrganizationUtil {

    public static final Long DEFAULT_ORGANIZATION_ID = 1L;

    public static Organization getSampleOrganizationForSave(){
        return Organization.builder()
                .name("Microsoft")
                .address("Redmond, Washington, USA")
                .build();
    }

    public static Organization getSampleOrganization(){
        return Organization.builder()
                .id(DEFAULT_ORGANIZATION_ID)
                .name("Microsoft")
                .address("Redmond, Washington, USA")
                .build();
    }

    public static List<Organization> getSampleAllOrganizations(){
        Organization organization1 = Organization.builder()
                .id(DEFAULT_ORGANIZATION_ID)
                .name("Microsoft").address("Redmond, Washington, USA").build();
        Organization organization2 = Organization.builder()
                .id(2L)
                .name("Oracle").address("Redwood City, California, USA").build();
        return List.of(organization1, organization2);
    }
}
