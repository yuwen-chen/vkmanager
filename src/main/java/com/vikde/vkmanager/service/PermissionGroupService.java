package com.vikde.vkmanager.service;

import com.vikde.vkmanager.repository.mapper.PermissionGroupMapper;
import com.vikde.vkmanager.repository.mapper.ext.ExtPermissionGroupMapper;
import com.vikde.vkmanager.repository.model.PermissionGroup;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author vikde
 * @date 2017/12/5
 */
@Service
public class PermissionGroupService {
    @Resource
    private PermissionGroupMapper permissionGroupMapper;
    @Resource
    private ExtPermissionGroupMapper extPermissionGroupMapper;

    /**
     * 创建权限组
     */
    public void createPermissionGroup(String permissionGroupName, String description) {
        PermissionGroup permissionGroup = new PermissionGroup();
        permissionGroup.setPermissionGroupName(permissionGroupName);
        permissionGroup.setDescription(description);
        permissionGroupMapper.insertSelective(permissionGroup);
    }

    /**
     * 更新权限组
     */
    public void updatePermissionGroup(int permissionGroupId, String permissionGroupName, String description) {
        PermissionGroup permissionGroup = new PermissionGroup();
        permissionGroup.setPermissionGroupId(permissionGroupId);
        permissionGroup.setPermissionGroupName(permissionGroupName);
        permissionGroup.setDescription(description);
        permissionGroupMapper.updateByPrimaryKeySelective(permissionGroup);
    }

    /**
     * 查询权限组
     */
    public List<PermissionGroup> readPermissionGroup(Integer permissionGroupId) {
        return extPermissionGroupMapper.readPermissionGroup(permissionGroupId);
    }
}
