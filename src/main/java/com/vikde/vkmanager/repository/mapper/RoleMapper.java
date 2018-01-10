package com.vikde.vkmanager.repository.mapper;

import com.vikde.vkmanager.repository.model.Role;
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
public interface RoleMapper {
    @Delete({
        "delete from role",
        "where roleId = #{roleId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer roleId);

    @Insert({
        "insert into role (roleName, createTime, ",
        "description, isDeleted, ",
        "updateTime)",
        "values (#{roleName,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{description,jdbcType=VARCHAR}, #{isDeleted,jdbcType=BIT}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="roleId", before=false, resultType=Integer.class)
    int insert(Role record);

    @InsertProvider(type=RoleSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="roleId", before=false, resultType=Integer.class)
    int insertSelective(Role record);

    @Select({
        "select",
        "roleId, roleName, createTime, description, isDeleted, updateTime",
        "from role",
        "where roleId = #{roleId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="roleId", property="roleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="roleName", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="createTime", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="isDeleted", property="isDeleted", jdbcType=JdbcType.BIT),
        @Result(column="updateTime", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Role selectByPrimaryKey(Integer roleId);

    @UpdateProvider(type=RoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Role record);

    @Update({
        "update role",
        "set roleName = #{roleName,jdbcType=VARCHAR},",
          "createTime = #{createTime,jdbcType=TIMESTAMP},",
          "description = #{description,jdbcType=VARCHAR},",
          "isDeleted = #{isDeleted,jdbcType=BIT},",
          "updateTime = #{updateTime,jdbcType=TIMESTAMP}",
        "where roleId = #{roleId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Role record);
}