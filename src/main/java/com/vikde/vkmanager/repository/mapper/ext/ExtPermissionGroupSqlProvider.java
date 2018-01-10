package com.vikde.vkmanager.repository.mapper.ext;

import java.util.Map;

/**
 * @author vikde
 * @date 2017/12/8
 */
public class ExtPermissionGroupSqlProvider {
    /**
     * 生成权限组查询sql
     */
    public static String readPermissionGroup(Map<String, Object> params) {
        Integer permissionGroupId = (Integer) params.get("permissionGroupId");

        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM permission_group WHERE isDeleted = FALSE ");
        if (permissionGroupId != null && permissionGroupId > 0) {
            stringBuilder.append("AND permissionGroupId=#{permissionGroupId} ");
        }
        return stringBuilder.toString();
    }

}
