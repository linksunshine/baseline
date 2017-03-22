package com.linksunshine.baseline.server.web.service.impl;

import com.linksunshine.baseline.server.web.core.orika.OrikaBeanMapper;
import com.linksunshine.baseline.server.web.dao.OrganizationMapper;
import com.linksunshine.baseline.server.web.model.Organization;
import com.linksunshine.baseline.server.web.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ucmed on 2017/3/20.
 */
@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrikaBeanMapper orikaBeanMapper;
    @Autowired
    private OrganizationMapper organizationMapper;

    public List<Organization> loadList() {
        Organization organization = new Organization();
        return orikaBeanMapper.mapAsList(organizationMapper.select(organization), Organization.class);
    }
}
