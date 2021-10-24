package io.github.susimsek.services.organization.service.impl;


import io.github.susimsek.services.organization.model.Organization;
import io.github.susimsek.services.organization.repository.OrganizationRepository;
import io.github.susimsek.services.organization.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public Organization createOrganization(Organization organizationForSave) {
        return organizationRepository.save(organizationForSave);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    @Override
    public Organization getOrganizationById(Long id) {
        return organizationRepository.findById(id).orElse(null);
    }
}
