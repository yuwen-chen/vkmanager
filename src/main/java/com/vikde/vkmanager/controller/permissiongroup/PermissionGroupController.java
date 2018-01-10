package com.vikde.vkmanager.controller.permissiongroup;

import com.vikde.vkmanager.common.JsonResult;
import com.vikde.vkmanager.controller.ApiController;
import com.vikde.vkmanager.controller.permissiongroup.model.CreatePermissionGroupRequest;
import com.vikde.vkmanager.controller.permissiongroup.model.ReadPermissionGroupRequest;
import com.vikde.vkmanager.controller.permissiongroup.model.UpdatePermissionGroupRequest;
import com.vikde.vkmanager.repository.model.PermissionGroup;
import com.vikde.vkmanager.service.PermissionGroupService;
import com.github.pagehelper.PageHelper;
import com.vikde.vkmanager.controller.ApiController;
import com.vikde.vkmanager.service.PermissionGroupService;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author vikde
 * @date 2017/12/5
 */
@ApiController
@RequestMapping("/api/permissionGroup/")
public class PermissionGroupController {
    @Resource
    private PermissionGroupService permissionGroupService;

    /**
     * 创建权限组
     */
    @RequestMapping(value = "createPermissionGroup")
    public JsonResult createPermissionGroup(@Valid CreatePermissionGroupRequest createPermissionGroupRequest) {
        permissionGroupService.createPermissionGroup(createPermissionGroupRequest.getPermissionGroupName(), createPermissionGroupRequest.getDescription());
        return JsonResult.getSuccessInstance();
    }

    /**
     * 更新权限组
     */
    @RequestMapping(value = "updatePermissionGroup")
    public JsonResult updatePermissionGroup(@Valid UpdatePermissionGroupRequest updatePermissionGroupRequest) {
        permissionGroupService.updatePermissionGroup(updatePermissionGroupRequest.getPermissionGroupId(), updatePermissionGroupRequest.getPermissionGroupName(),
                                                     updatePermissionGroupRequest.getDescription());
        return JsonResult.getSuccessInstance();
    }

    /**
     * 查询权限组
     */
    @RequestMapping(value = "readPermissionGroup")
    public JsonResult readPermissionGroup(@Valid ReadPermissionGroupRequest readPermissionGroupRequest) {
        PageHelper.startPage(readPermissionGroupRequest.getPageNumber(), readPermissionGroupRequest.getPageSize());
        List<PermissionGroup> permissionGroupList = permissionGroupService.readPermissionGroup(readPermissionGroupRequest.getPermissionGroupId());

        JsonResult jsonResult = JsonResult.getSuccessInstance();
        jsonResult.setDataAndTotal(permissionGroupList);
        return jsonResult;
    }
}
