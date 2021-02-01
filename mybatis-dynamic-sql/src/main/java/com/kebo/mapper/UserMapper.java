package com.kebo.mapper;

import com.kebo.po.SysUser;

import java.util.List;
import java.util.Map;

public interface UserMapper {

	/***
	* @description:  根据动态条件查询用户信息
	* @param: []
	* @return: java.util.List<com.kebo.po.SysUser>
	* @author: kb
	* @date: 2021-01-31
	*/
	List<SysUser> selectByUser(SysUser sysUser);


	/**
	 * 根据主键更新
	 *
	 * @param sysUser
	 * @return
	 */
	int updateByIdSelective(SysUser sysUser);

	/**
	 * 新增用户 - 使用 useGeneratedKeys 方式
	 *
	 * @param sysUser
	 * @return
	 */
	int insert2(SysUser sysUser);

	/**
	 * 根据用户 id 或用户名查询
	 *
	 * @param sysUser
	 * @return
	 */
	SysUser selectByIdOrUserName(SysUser sysUser);

	/**
	 * 根据用户 id 集合查询
	 *
	 * @param idList
	 * @return
	 */
	List<SysUser> selectByIdList(List<Long> idList);

	/**
	 * 根据用户 id 数组查询
	 *
	 * @param idList
	 * @return
	 */
	List<SysUser> selectByIdArray(Long[] idList);

	/**
	 * 批量插入用户信息
	 *
	 * @param userList
	 * @return
	 */
	int insertList(List<SysUser> userList);

	/**
	 * 通过 Map 更新列
	 *
	 * @param map
	 * @return
	 */
	int updateByMap(Map<String, Object> map);
}
