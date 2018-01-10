package com.vikde.vkmanager.controller.user;

import com.vikde.vkmanager.common.JsonResult;
import com.vikde.vkmanager.common.exception.ApiException;
import com.vikde.vkmanager.common.type.JsonResultType;
import com.vikde.vkmanager.controller.ApiController;
import com.vikde.vkmanager.controller.user.model.*;
import com.vikde.vkmanager.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created on 2017/07/02.
 *
 * @author vikde
 */
@ApiController
@RequestMapping("/api/user/")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 创建用户
     */
    @RequestMapping(value = "createUser")
    public JsonResult createUser(@Valid CreateUserRequest createUserRequest) throws ApiException {
        userService.createUser(createUserRequest.getUsername(), createUserRequest.getName());
        return JsonResult.getSuccessInstance();
    }

    /**
     * 分配用户角色
     */
    @RequestMapping(value = "assignUserRole")
    public JsonResult assignUserRole(@Valid AssignUserRoleRequest assignUserRoleRequest) {
        userService.assignUserRole(assignUserRoleRequest.getUserId(), assignUserRoleRequest.getRoleId());
        return JsonResult.getSuccessInstance();
    }

    /**
     * 查询用户
     */
    @RequestMapping(value = "readUser")
    public JsonResult readUser(@Valid ReadUserRequest readUserRequest) {
        return userService.readUser(readUserRequest);
    }

    /**
     * 登录
     */
    @RequestMapping(value = "login")
    public JsonResult login(HttpSession httpSession, @Valid LoginRequest loginRequest) throws ApiException {
        userService.login(httpSession, loginRequest.getUsername(), loginRequest.getPassword());
        return JsonResult.getInstance(JsonResultType.SYSTEM_SUCCESS, "登录成功");
    }

    /**
     * 登出
     */
    @RequestMapping(value = "logout")
    public JsonResult logout(HttpSession httpSession) {
        userService.logout(httpSession);
        return JsonResult.getSuccessInstance();
    }

    /**
     * 获取当前用户信息与权限列表
     */
    @RequestMapping(value = "readOwn")
    public ReadOwnResponse readOwn(HttpSession httpSession) throws ApiException {
        return userService.readOwn(httpSession);
    }

    /**
     * 修改密码
     */
    @RequestMapping(value = "changePassword")
    public JsonResult changePassword(HttpSession httpSession, @Valid ChangePasswordRequest changePasswordRequest) throws ApiException {
        userService.changePassword(httpSession, changePasswordRequest.getUsername(), changePasswordRequest.getPassword(),
                                   changePasswordRequest.getFirstPassword(), changePasswordRequest.getSecondPassword());
        return JsonResult.getSuccessInstance();
    }
}
