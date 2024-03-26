package com.net.organizationservice.service.impl;

import com.net.organizationservice.dto.OrganizationDto;
import com.net.organizationservice.entity.Organization;
import com.net.organizationservice.mapper.OrganizationMapper;
import com.net.organizationservice.repository.OrganizationRepo;
import com.net.organizationservice.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationRepo organizationRepo;
    @Override
    public OrganizationDto saveOrganizationDto(OrganizationDto organizationDto) {
        // convert OrganizationDto to Organization jpa Entity
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);

        Organization savedOrganization = organizationRepo.save(organization);

        return OrganizationMapper.mapToOrganzationDto(savedOrganization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepo.findByOrganizationCode(organizationCode);

        return OrganizationMapper.mapToOrganzationDto(organization);
    }
}
