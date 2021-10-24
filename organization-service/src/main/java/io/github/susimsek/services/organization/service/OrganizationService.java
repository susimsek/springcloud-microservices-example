package io.github.susimsek.services.organization.service;



import io.github.susimsek.services.organization.model.Organization;

import java.util.List;

public interface OrganizationService {

    Organization createOrganization(Organization organizationForSave);

    List<Organization> getAllOrganizations();

    Organization getOrganizationById(Long id);
}
