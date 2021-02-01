package com.kebo.mapper;

import com.kebo.po.SysRole;
import com.kebo.provider.SysRoleProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleMapper {

    @Select({"select id,role_name roleName, enabled, create_by createBy, create_time createTime",
            "from sys_role",
            "where id = #{id}"})
    SysRole selectById(Long id);

    //@Results有id属性需要mybatis的依赖在3.3.1以上
    @Results(id = "roleResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "enabled", column = "enabled"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "createTime", column = "create_time")
    })
    @Select("select id,role_name, enabled, create_by, create_time from sys_role where id = #{id}")
    SysRole selectById2(Long id);

    @ResultMap("roleResultMap")
    @Select("select * from sys_role")
    List<SysRole> selectAll();

    @Insert({"insert into sys_role(id, role_name, enabled, create_by, create_time)",
            "values(#{id}, #{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})"})
    int insert(SysRole sysRole);

    @Insert({"insert into sys_role(role_name, enabled, create_by, create_time)",
            "values(#{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert2(SysRole sysRole);

    @Insert({"insert into sys_role(role_name, enabled, create_by, create_time)",
            "values(#{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})"})
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", resultType = Long.class, before = false)
    int insert3(SysRole sysRole);

    @Update({"update sys_role",
            "set role_name = #{roleName},",
            "enabled = #{enabled},",
            "create_by = #{createBy},",
            "create_time = #{createTime, jdbcType=TIMESTAMP}",
            "where id = #{id}"
    })
    int updateById(SysRole sysRole);

    @Delete("delete from sys_role where id = #{id}")
    int deleteById(Long id);

    @SelectProvider(type= SysRoleProvider.class,method = "selectByIdUseProvider")
    SysRole selectByIdUseProvider(Long id);
}
