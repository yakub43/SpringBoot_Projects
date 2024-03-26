package com.net.organizationservice.service;

import com.net.organizationservice.dto.OrganizationDto;
import com.net.organizationservice.entity.Organization;

public interface OrganizationService {
    OrganizationDto saveOrganizationDto(OrganizationDto organizationDto);
    OrganizationDto getOrganizationByCode(String organizationCode);
}
