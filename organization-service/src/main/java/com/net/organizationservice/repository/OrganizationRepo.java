package com.net.organizationservice.repository;

import com.net.organizationservice.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepo extends JpaRepository<Organization, Long> {
    Organization findByOrganizationCode(String organizationCode);
}
