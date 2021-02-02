package com.kebo.mapper;

import com.kebo.po.SysUser;

public interface UserMapper {

	/**
	 * 根据用户 id 获取用户信息和用户的角色信息
	 *
	 * @param id
	 * @return
	 */
	SysUser selectUserAndRoleById(Long id);

	/**
	 * 根据用户 id 获取用户信息和用户的角色信息
	 *
	 * @param id
	 * @return
	 */
	SysUser selectUserAndRoleById2(Long id);

	/**
	 * 根据用户 id 获取用户信息和用户的角色信息，嵌套查询方式
	 *
	 * @param id
	 * @return
	 */
	SysUser selectUserAndRoleByIdSelect(Long id);
}
