package com.ph.springBoot.modules.account.service;


import com.github.pagehelper.PageInfo;
import com.ph.springBoot.modules.account.entity.Resource;
import com.ph.springBoot.modules.common.vo.Result;
import com.ph.springBoot.modules.common.vo.SearchVo;

import java.util.List;

public interface ResourceService {

    Object editResource(Resource resource);

    Result<Resource> deleteResource(int resourceId);

    PageInfo<Resource> getResources(SearchVo searchVo);

    List<Resource> getResourcesByRoleId(int roleId);

    Resource getResourceById(int resourceId);
}
