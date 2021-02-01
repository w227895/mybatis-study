package com.kebo.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 * @description:
 * @Author: kb
 * @Date: 2021-02-01 15:37
 */
public class SysRoleProvider {
    public String selectByIdUseProvider(final long id) {
        return "select * from sys_role where id = #{id }";
    }
}
