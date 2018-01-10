package com.vikde.vkmanager.controller.role.model;

import javax.validation.constraints.Min;

/**
 * Created on 2017/7/26.
 *
 * @author vikde
 */
public class AssignRolePermissionRequest {
    @Min(value = 1, message = "角色id错误")
    private int roleId;
    @Min(value = 1, message = "权限id错误")
    private int permissionId;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }
}
