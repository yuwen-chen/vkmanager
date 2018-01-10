package com.vikde.vkmanager.repository.mapper;

import com.vikde.vkmanager.repository.model.PermissionGroup;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PermissionGroupMapper {
    @Delete({
        "delete from permission_group",
        "where permissionGroupId = #{permissionGroupId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer permissionGroupId);

    @Insert({
        "insert into permission_group (permissionGroupName, description, ",
        "createTime, isDeleted, ",
        "updateTime)",
        "values (#{permissionGroupName,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{isDeleted,jdbcType=BIT}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="permissionGroupId", before=false, resultType=Integer.class)
    int insert(PermissionGroup record);

    @InsertProvider(type=PermissionGroupSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="permissionGroupId", before=false, resultType=Integer.class)
    int insertSelective(PermissionGroup record);

    @Select({
        "select",
        "permissionGroupId, permissionGroupName, description, createTime, isDeleted, ",
        "updateTime",
        "from permission_group",
        "where permissionGroupId = #{permissionGroupId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="permissionGroupId", property="permissionGroupId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="permissionGroupName", property="permissionGroupName", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="createTime", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="isDeleted", property="isDeleted", jdbcType=JdbcType.BIT),
        @Result(column="updateTime", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    PermissionGroup selectByPrimaryKey(Integer permissionGroupId);

    @UpdateProvider(type=PermissionGroupSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PermissionGroup record);

    @Update({
        "update permission_group",
        "set permissionGroupName = #{permissionGroupName,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "createTime = #{createTime,jdbcType=TIMESTAMP},",
          "isDeleted = #{isDeleted,jdbcType=BIT},",
          "updateTime = #{updateTime,jdbcType=TIMESTAMP}",
        "where permissionGroupId = #{permissionGroupId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(PermissionGroup record);
}