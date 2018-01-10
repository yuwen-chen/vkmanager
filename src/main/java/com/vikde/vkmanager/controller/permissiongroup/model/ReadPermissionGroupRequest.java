package com.vikde.vkmanager.controller.permissiongroup.model;

/**
 * Created on 2017/7/26.
 *
 * @author vikde
 */
public class ReadPermissionGroupRequest {
    private Integer permissionGroupId;
    private Integer pageNumber;
    private Integer pageSize;

    public Integer getPermissionGroupId() {
        return permissionGroupId;
    }

    public void setPermissionGroupId(Integer permissionGroupId) {
        this.permissionGroupId = permissionGroupId;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
