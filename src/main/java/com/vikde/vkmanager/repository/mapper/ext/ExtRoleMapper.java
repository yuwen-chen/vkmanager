package com.vikde.vkmanager.repository.mapper.ext;

import com.vikde.vkmanager.repository.model.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExtRoleMapper {
    /**
     * 创建角色
     *
     * @param roleName    角色名称
     * @param description 角色描述
     */
    @Insert("INSERT INTO role (roleName,description) VALUES (#{roleName},#{description})")
    void createRole(@Param("roleName") String roleName, @Param("description") String description);

    /**
     * 分配角色权限
     *
     * @param roleId       角色id
     * @param permissionId 权限id
     */
    @Insert("REPLACE INTO role_permission (roleId,permissionId,isDeleted) VALUES (#{roleId},#{permissionId},false)")
    void assignRolePermission(@Param("roleId") int roleId, @Param("permissionId") int permissionId);

    /**
     * 查询角色
     *
     * @param roleId 角色id
     * @return 角色列表
     */
    @SelectProvider(type = ExtRoleSqlProvider.class, method = "readRole")
    List<Role> readRole(@Param("roleId") Integer roleId, @Param("roleName") String roleName);
}