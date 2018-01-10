package com.vikde.vkmanager.repository.mapper.ext;

import java.util.Map;

/**
 * @author vikde
 * @date 2017/12/8
 */
public class ExtUserSqlProvider {
    /**
     * 生成用户查询sql
     */
    public static String readUser(Map<String, Object> params) {
        Integer userId = (Integer) params.get("userId");
        String username = (String) params.get("username");
        String name = (String) params.get("name");

        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM user WHERE isDeleted = FALSE ");
        if (userId != null && userId > 0) {
            stringBuilder.append("AND userId=#{userId} ");
        }
        if (username != null && !username.isEmpty()) {
            stringBuilder.append("AND username LIKE CONCAT('%',#{username},'%') ");
        }
        if (name != null && !name.isEmpty()) {
            stringBuilder.append("AND name LIKE CONCAT('%',#{name},'%') ");
        }
        return stringBuilder.toString();
    }

}
