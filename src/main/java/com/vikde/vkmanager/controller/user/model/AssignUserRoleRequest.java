package com.vikde.vkmanager.controller.user.model;

import javax.validation.constraints.Min;

/**
 * Created on 2017/7/26.
 *
 * @author vikde
 */
public class AssignUserRoleRequest {
    @Min(value = 1, message = "用户id错误")
    private int userId;
    @Min(value = 1, message = "角色id错误")
    private int roleId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

}
