package com.vikde.vkmanager.service;

import com.vikde.vkmanager.repository.mapper.ext.ExtPermissionMapper;
import com.vikde.vkmanager.repository.model.Permission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author vikde
 * @date 2017/12/5
 */
@Service
public class PermissionService {
    @Resource
    private ExtPermissionMapper extPermissionMapper;

    /**
     * 创建权限
     */
    public void createPermission(String permissionName, int permissionGroupId, String path, String description) {
        extPermissionMapper.createPermission(permissionName, permissionGroupId, path, description);
    }

    /**
     * 查询权限
     */
    public List<Permission> readPermission(Integer permissionId, Integer permissionGroupId) {
        return extPermissionMapper.readPermission(permissionId, permissionGroupId);
    }
}
