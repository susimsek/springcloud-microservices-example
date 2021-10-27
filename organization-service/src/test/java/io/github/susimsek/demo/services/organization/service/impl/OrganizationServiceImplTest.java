package io.github.susimsek.demo.services.organization.service.impl;

import io.github.susimsek.demo.services.organization.model.Organization;
import io.github.susimsek.demo.services.organization.repository.OrganizationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

;
import static io.github.susimsek.demo.services.organization.util.OrganizationUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrganizationServiceImplTest {

    @Mock
    OrganizationRepository organizationRepository;

    @InjectMocks
    OrganizationServiceImpl organizationService;

    @Test
    public void whenSaveOrganization_shouldReturnOrganization() {
        Organization organizationForSave = getSampleOrganizationForSave();
        Organization organization = getSampleOrganization();

        Mockito.when(organizationRepository.save(ArgumentMatchers.refEq(organizationForSave))).thenReturn(organization);

        Organization expected = organizationService.createOrganization(organizationForSave);
        assertNotNull(expected);
        assertEquals(expected.getId(), organization.getId());
        assertEquals(expected.getName(), organization.getName());
        assertEquals(expected.getAddress(), organization.getAddress());

        verify(organizationRepository, times(1)).save(ArgumentMatchers.refEq(organizationForSave));
    }

    @Test
    public void shouldReturnAllOrganizations() {
        List<Organization> organizations = getSampleAllOrganizations();
        Mockito.when(organizationRepository.findAll()).thenReturn(organizations);

        List<Organization> expected = organizationService.getAllOrganizations();

        assertNotNull(expected);
        assertEquals(expected.size(), organizations.size());
        assertEquals(expected, organizations);

        verify(organizationRepository, times(1)).findAll();

    }

    @Test
    public void whenGivenId_shouldReturnOrganization_ifOrganizationExists() {
        Organization organization = getSampleOrganization();
        Mockito.when(organizationRepository.findById(organization.getId())).thenReturn(Optional.of(organization));

        Organization expected = organizationService.getOrganizationById(organization.getId());

        assertNotNull(expected);
        assertEquals(expected.getId(), organization.getId());
        assertEquals(expected.getName(), organization.getName());
        assertEquals(expected.getAddress(), organization.getAddress());

        verify(organizationRepository, times(1)).findById(organization.getId());

    }

    @Test
    public void whenGivenId_shouldNull_ifOrganizationNotExists() {
        Organization organization = getSampleOrganization();
        Mockito.when(organizationRepository.findById(organization.getId())).thenReturn(Optional.empty());
        Organization expected = organizationService.getOrganizationById(organization.getId());
        assertNull(expected);

        verify(organizationRepository, times(1)).findById(organization.getId());
    }
}