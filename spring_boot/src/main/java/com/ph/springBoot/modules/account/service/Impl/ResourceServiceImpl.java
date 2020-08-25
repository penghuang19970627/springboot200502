package com.ph.springBoot.modules.account.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ph.springBoot.modules.account.dao.ResourceDao;
import com.ph.springBoot.modules.account.dao.RoleResourceDao;
import com.ph.springBoot.modules.account.entity.Resource;
import com.ph.springBoot.modules.account.entity.Role;
import com.ph.springBoot.modules.account.service.ResourceService;
import com.ph.springBoot.modules.common.vo.Result;
import com.ph.springBoot.modules.common.vo.SearchVo;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ResourceServiceImpl implements ResourceService    {

    @Autowired
    private ResourceDao resourceDao;
    @Autowired
    private RoleResourceDao roleResourceDao;


    @Override
    @Transactional
    public Result<Resource> editResource(Resource resource) {
        Resource resourceTemp = resourceDao.getResourceByPermission(resource.getPermission());
        if (resourceTemp != null && resourceTemp.getResourceId() != resource.getResourceId()) {
            return new Result<Resource>(Result.ResultStatus.FAILD.status, "Resource permission is repeat.");
        }

        // 添加 resource
        if (resource.getResourceId() > 0) {
            resourceDao.updateResource(resource);
        } else {
            resourceDao.addResource(resource);
        }

        // 添加 roleResource
        roleResourceDao.deletRoleResourceByResourceId(resource.getResourceId());
        if (resource.getRoles() != null && !resource.getRoles().isEmpty()) {
            for (Role role : resource.getRoles()) {
                roleResourceDao.addRoleResource(role.getRoleId(), resource.getResourceId());
            }
        }

        return new Result<Resource>(Result.ResultStatus.SUCCESS.status, "success", resource);
    }

    @Override
    @Transactional
    public Result<Resource> deleteResource(int resourceId) {
        roleResourceDao.deletRoleResourceByResourceId(resourceId);
        resourceDao.deleteResource(resourceId);
        return new Result<Resource>(Result.ResultStatus.SUCCESS.status,"");
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public PageInfo<Resource> getResources(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo(
                Optional.ofNullable(resourceDao.getResourceBySearchVo(searchVo))
                        .orElse(Collections.emptyList()));
    }

    @Override
    public List<Resource> getResourcesByRoleId(int roleId) {
        return resourceDao.getResourceByRoleId(roleId);
    }

    @Override
    public Resource getResourceById(int resourceId) {
        return resourceDao.getResourceByResourceId(resourceId);
    }
}
