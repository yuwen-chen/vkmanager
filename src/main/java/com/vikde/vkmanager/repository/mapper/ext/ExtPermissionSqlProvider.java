package com.vikde.vkmanager.repository.mapper.ext;

import java.util.Map;

/**
 * @author vikde
 * @date 2017/12/8
 */
public class ExtPermissionSqlProvider {
    /**
     * 生成权限查询sql
     */
    public static String readPermission(Map<String, Object> params) {
        Integer permissionId = (Integer) params.get("permissionId");
        Integer permissionGroupId = (Integer) params.get("permissionGroupId");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT p.*,pg.permissionGroupName ");
        stringBuilder.append("FROM permission p RIGHT JOIN permission_group pg ON p.permissionGroupId = pg.permissionGroupId ");
        stringBuilder.append("WHERE p.isDeleted = FALSE ");
        if (permissionId != null && permissionId > 0) {
            stringBuilder.append("AND p.permissionId=#{permissionId} ");
        }
        if (permissionGroupId != null && permissionGroupId > 0) {
            stringBuilder.append("AND p.permissionGroupId=#{permissionGroupId} ");
        }

        return stringBuilder.toString();
    }

}
