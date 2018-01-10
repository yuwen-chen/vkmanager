package com.vikde.vkmanager.repository.mapper.ext;

import java.util.Map;

/**
 * @author vikde
 * @date 2017/12/8
 */
public class ExtRoleSqlProvider {
    public static String readRole(Map<String, Object> params) {
        Integer roleId = (Integer) params.get("roleId");
        String roleName = (String) params.get("roleName");

        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM role WHERE isDeleted=false ");
        if (roleId != null && roleId > 0) {
            stringBuilder.append("AND roleId=#{roleId} ");
        }
        if (roleName != null && !roleName.isEmpty()) {
            stringBuilder.append("AND roleName LIKE CONCAT('%',#{roleName},'%') ");
        }
        return stringBuilder.toString();
    }

}
