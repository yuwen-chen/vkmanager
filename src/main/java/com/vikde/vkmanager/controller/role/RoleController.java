package com.vikde.vkmanager.controller.role;

import com.vikde.vkmanager.common.JsonResult;
import com.vikde.vkmanager.controller.ApiController;
import com.vikde.vkmanager.controller.role.model.AssignRolePermissionRequest;
import com.vikde.vkmanager.controller.role.model.CreateRoleRequest;
import com.vikde.vkmanager.controller.role.model.ReadRoleRequest;
import com.vikde.vkmanager.repository.model.Role;
import com.vikde.vkmanager.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.vikde.vkmanager.controller.ApiController;
import com.vikde.vkmanager.controller.role.model.CreateRoleRequest;
import com.vikde.vkmanager.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author vikde
 * @date 2017/12/5
 */
@ApiController
@RequestMapping("/api/role/")
public class RoleController {
    @Resource
    private RoleService roleService;

    /**
     * 创建角色
     */
    @RequestMapping(value = "createRole")
    public JsonResult createPermissionGroup(@Valid CreateRoleRequest createRoleRequest) {
        roleService.createRole(createRoleRequest.getRoleName(), createRoleRequest.getDescription());
        return JsonResult.getSuccessInstance();
    }

    /**
     * 分配角色权限
     */
    @RequestMapping(value = "assignRolePermission")
    public JsonResult assignRolePermission(@Valid AssignRolePermissionRequest assignRolePermissionRequest) {
        roleService.assignRolePermission(assignRolePermissionRequest.getRoleId(), assignRolePermissionRequest.getPermissionId());
        return JsonResult.getSuccessInstance();
    }

    /**
     * 查询角色
     */
    @RequestMapping(value = "readRole")
    public JsonResult readRole(@Valid ReadRoleRequest readRoleRequest) {
        PageHelper.startPage(readRoleRequest.getPageNumber(), readRoleRequest.getPageSize());
        List<Role> roleList = roleService.readRole(readRoleRequest.getRoleId(), readRoleRequest.getRoleName());

        JsonResult jsonResult = JsonResult.getSuccessInstance();
        jsonResult.setDataAndTotal(roleList);
        return jsonResult;
    }

}
