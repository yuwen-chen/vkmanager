package com.vikde.vkmanager.controller.permission;

import com.vikde.vkmanager.common.JsonResult;
import com.vikde.vkmanager.controller.ApiController;
import com.vikde.vkmanager.controller.permission.model.CreatePermissionRequest;
import com.vikde.vkmanager.controller.permission.model.ReadPermissionRequest;
import com.vikde.vkmanager.repository.model.Permission;
import com.vikde.vkmanager.service.PermissionService;
import com.github.pagehelper.PageHelper;
import com.vikde.vkmanager.controller.ApiController;
import com.vikde.vkmanager.service.PermissionService;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author vikde
 * @date 2017/12/5
 */
@ApiController
@RequestMapping("/api/permission/")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    /**
     * 创建权限
     */
    @RequestMapping(value = "createPermission")
    public JsonResult createPermissionGroup(@Valid CreatePermissionRequest createPermissionRequest) {
        permissionService.createPermission(createPermissionRequest.getPermissionName(), createPermissionRequest.getPermissionGroupId(),
                                           createPermissionRequest.getPath(), createPermissionRequest.getDescription());
        return JsonResult.getSuccessInstance();
    }

    /**
     * 查询权限组
     */
    @RequestMapping(value = "readPermission")
    public JsonResult readPermission(@Valid ReadPermissionRequest readPermissionRequest) {
        PageHelper.startPage(readPermissionRequest.getPageNumber(), readPermissionRequest.getPageSize());
        List<Permission> permissionList = permissionService.readPermission(readPermissionRequest.getPermissionId(), readPermissionRequest.getPermissionGroupId());

        JsonResult jsonResult = JsonResult.getSuccessInstance();
        jsonResult.setDataAndTotal(permissionList);
        return jsonResult;
    }
}
