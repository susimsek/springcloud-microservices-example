package io.github.susimsek.demo.services.department.util;

import io.github.susimsek.demo.services.department.model.Organization;

public class OrganizationUtil {

    public static Organization getSampleOrganization(){
        return Organization.builder()
                .id(1L)
                .name("Microsoft")
                .address("Redmond, Washington, USA")
                .build();
    }
}
