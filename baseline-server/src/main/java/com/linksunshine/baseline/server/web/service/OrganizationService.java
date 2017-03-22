package com.linksunshine.baseline.server.web.service;

import com.linksunshine.baseline.server.web.model.Organization;

import java.util.List;

/**
 * Created by ucmed on 2017/3/20.
 */
public interface OrganizationService {

    List<Organization> loadList();
}
