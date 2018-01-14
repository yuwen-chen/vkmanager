package com.vikde.vkmanager.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vikde.vkmanager.common.Config;
import com.vikde.vkmanager.common.JsonResult;
import com.vikde.vkmanager.common.exception.ApiException;
import com.vikde.vkmanager.common.type.JsonResultType;
import com.vikde.vkmanager.common.type.UserStatusType;
import com.vikde.vkmanager.common.util.PasswordUtil;
import com.vikde.vkmanager.controller.user.model.ReadOwnResponse;
import com.vikde.vkmanager.controller.user.model.ReadUserRequest;
import com.vikde.vkmanager.controller.user.model.ReadUserResponse;
import com.vikde.vkmanager.repository.mapper.UserMapper;
import com.vikde.vkmanager.repository.mapper.ext.ExtUserMapper;
import com.vikde.vkmanager.repository.model.Permission;
import com.vikde.vkmanager.repository.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created on 2017/7/3.
 *
 * @author vikde
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private ExtUserMapper extUserMapper;

    /**
     * 创建用户
     */
    public void createUser(String username, String name) throws ApiException {
        User oldUser = extUserMapper.readUserByUsername(username);
        if (oldUser != null) {
            throw new ApiException(JsonResultType.USER_ALREADY_EXIST);
        }
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setPassword(PasswordUtil.encryptPassword(Config.INIT_PASSWORD));
        user.setUserStatusType(UserStatusType.INACTIVE.getIndex());
        userMapper.insertSelective(user);
    }

    /**
     * 分配用户角色
     */
    public void assignUserRole(int userId, int roleId) {
        extUserMapper.assignUserRole(userId, roleId);
    }

    /**
     * 查询用户
     */
    public JsonResult readUser(ReadUserRequest readUserRequest) {
        PageHelper.startPage(readUserRequest.getPageNumber(), readUserRequest.getPageSize());
        List<User> userList = extUserMapper.readUser(readUserRequest.getUserId(), readUserRequest.getUsername(), readUserRequest.getName());

        JsonResult jsonResult = JsonResult.getSuccessInstance();
        jsonResult.setTotal(userList instanceof Page ? ((Page) userList).getTotal() : userList.size());
        List<ReadUserResponse> readUserResponses = new ArrayList<>(userList.size());
        for (User user : userList) {
            readUserResponses.add(new ReadUserResponse(user));
        }
        jsonResult.setData(readUserResponses);
        return jsonResult;
    }

    /**
     * 登录
     */
    public void login(HttpSession httpSession, String username, String password) throws ApiException {
        User user = extUserMapper.readUserByUsername(username);
        if (user == null || !password.equals(PasswordUtil.decryptPassword(user.getPassword()))) {
            throw new ApiException(JsonResultType.USER_NAME_OR_PASSWORD_ERROR);
        }
        if (user.getIsDeleted()) {
            throw new ApiException(JsonResultType.USER_IS_DELETED);
        }
        if (user.getUserStatusType() == UserStatusType.INACTIVE.getIndex()) {
            throw new ApiException(JsonResultType.USER_NOT_ACTIVE);
        }
        //记录登陆数据
        extUserMapper.login(user.getUserId(), new Date());
        user.setLoginTime(new Date());

        ReadOwnResponse readOwnResponse = new ReadOwnResponse(user);
        List<Permission> permissions = extUserMapper.readUserPermission(readOwnResponse.getUserId());
        for (Permission permission : permissions) {
            readOwnResponse.getPermissionList().add(permission);
        }
        httpSession.setAttribute("user", readOwnResponse);
    }

    /**
     * 登录
     */
    public void logout(HttpSession httpSession) {
        httpSession.invalidate();
    }

    /**
     * 获取当前用户信息与权限列表
     */
    public ReadOwnResponse readOwn(HttpSession httpSession) throws ApiException {
        Object object = httpSession.getAttribute("user");
        if (object == null || !(object instanceof ReadOwnResponse)) {
            throw new ApiException(JsonResultType.USER_NOT_LOGIN);
        }
        ReadOwnResponse readOwnResponse = (ReadOwnResponse) object;
        if (readOwnResponse.getUserStatusType() == UserStatusType.INACTIVE.getIndex()) {
            throw new ApiException(JsonResultType.USER_NOT_ACTIVE);
        }
        return (ReadOwnResponse) object;
    }

    /**
     * 获取用户权限
     */
    public List<Permission> readUserPermission(int userId) {
        return extUserMapper.readUserPermission(userId);
    }

    /**
     * 修改密码
     */
    public void changePassword(HttpSession httpSession, String username, String password, String firstPassword, String secondPassword) throws ApiException {
        if (!firstPassword.equalsIgnoreCase(secondPassword)) {
            throw new ApiException(JsonResultType.USER_PASSWORD_DIFFERENT);
        }
        User user = extUserMapper.readUserByUsername(username);
        if (user == null || !password.equals(PasswordUtil.decryptPassword(user.getPassword()))) {
            throw new ApiException(JsonResultType.USER_NAME_OR_PASSWORD_ERROR);
        }
        String encryptPassword = PasswordUtil.encryptPassword(firstPassword);
        if (encryptPassword == null) {
            throw new ApiException(JsonResultType.SYSTEM_EXCEPTION);
        }
        extUserMapper.changePassword(username, encryptPassword, UserStatusType.ACTIVE.getIndex());
        httpSession.invalidate();
    }
}
