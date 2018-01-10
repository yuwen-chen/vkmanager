package com.vikde.vkmanager.common.type;

/**
 * Created on 2017/7/26.
 * 用户状态类型
 *
 * @author vikde
 */
public enum UserStatusType {
    /**
     * 未激活
     */
    INACTIVE(1),

    /**
     * 正常
     */
    ACTIVE(2);

    UserStatusType(int index) {
        this.index = index;
    }

    private int index;

    public int getIndex() {
        return index;
    }
}
