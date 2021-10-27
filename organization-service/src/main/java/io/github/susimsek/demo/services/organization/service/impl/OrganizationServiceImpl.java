package io.github.susimsek.demo.services.organization.service.impl;


import io.github.susimsek.demo.services.organization.model.Organization;
import io.github.susimsek.demo.services.organization.service.OrganizationService;
import io.github.susimsek.demo.services.organization.repository.OrganizationRepository;
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
