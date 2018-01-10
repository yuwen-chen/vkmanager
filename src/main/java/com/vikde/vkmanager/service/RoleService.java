package com.vikde.vkmanager.service;

import com.vikde.vkmanager.repository.mapper.ext.ExtRoleMapper;
import com.vikde.vkmanager.repository.model.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author vikde
 * @date 2017/12/5
 */
@Service
public class RoleService {
    @Resource
    private ExtRoleMapper extRoleMapper;

    /**
     * 创建角色
     */
    public void createRole(String roleName, String description) {
        extRoleMapper.createRole(roleName, description);
    }

    /**
     * 分配角色权限
     */
    public void assignRolePermission(int roleId, int permissionId) {
        extRoleMapper.assignRolePermission(roleId, permissionId);
    }

    /**
     * 查询角色
     */
    public List<Role> readRole(Integer roleId, String roleName) {
        return extRoleMapper.readRole(roleId, roleName);
    }
}
