package io.github.susimsek.services.organization.repository;

import io.github.susimsek.services.organization.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
