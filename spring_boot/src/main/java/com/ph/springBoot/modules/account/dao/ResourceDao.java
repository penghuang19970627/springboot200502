package com.ph.springBoot.modules.account.dao;


import com.ph.springBoot.modules.account.entity.Resource;
import com.ph.springBoot.modules.common.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ResourceDao {
    @Insert("insert resource(resource_name, resource_uri, permission) value(#{resourceName}, "
            + "#{resourceUri}, #{permission})")
    @Options(useGeneratedKeys = true, keyProperty = "resourceId", keyColumn = "resource_id")
    void addResource(Resource resource);

    @Update("update resource set resource_name = #{resourceName}, resource_uri = #{resourceUri}, "
            + "permission=#{permission} where resource_id=#{resourceId}")
    void updateResource(Resource resource);

    @Delete("delete from resource where resource_id = #{resourceId}")
    void deleteResource(int resourceId);

    @Select("select * from resource")
    List<Resource> getResource();

    @Select("<script>" +
            "select * from resource "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + "and resource_name like '%${keyWord}%' "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + "order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + "order by resource_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Resource> getResourceBySearchVo(SearchVo searchVo);

    @Select("select * from resource resource left join role_resource roleResource on "
            + "resource.resource_id = roleResource.resource_id where roleResource.role_id = #{roleId}")
    List<Resource> getResourceByRoleId(int roleId);

    @Select("select * from resource where resource_id=#{resourceId}")
    @Results(id="resourceResult", value={
            @Result(column="resource_id", property="resourceId"),
            @Result(column="resource_id",property="roles",
                    javaType=List.class,
                    many=@Many(select="com.sfac.javaSpringBoot.modules.account.dao.RoleDao.getRolesByResourceId"))
    })
    Resource getResourceByResourceId(int resourceId);

    @Select("select * from resource where permission = #{permission}")
    Resource getResourceByPermission(String permission);
}
