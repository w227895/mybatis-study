package com.kebo.mapper;

import com.kebo.po.SysRole;
import com.kebo.po.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /***
     * @description: 通过id查询用户
     * @param: [id]
     * @return: com.kebo.po.SysUser
     * @author: kb
     * @date: 2021-01-31
     */
    SysUser findById(Long id);

    /***
     * @description: 查询全部用户
     * @param: []
     * @return: java.util.List<com.kebo.po.SysUser>
     * @author: kb
     * @date: 2021-01-31
     */
    List<SysUser> selectAll();

    /***
     * @description: 根据用户id获取角色信息
     * @param: [id]
     * @return: java.util.List<com.kebo.po.SysRole>
     * @author: kb
     * @date: 2021-01-31
     */
    List<SysRole> selectRolesByUserId(Long id);

    /***
     * @description: 新增用户
     * @param: [sysUser]
     * @return: int
     * @author: kb
     * @date: 2021-01-31
     */
    int insert(SysUser sysUser);

    /***
     * @description: 新增用户使用useGeneratedKeys方式
     * @param: [sysUser]
     * @return: int
     * @author: kb
     * @date: 2021-01-31
     */
    int insert2(SysUser sysUser);

    /***
     * @description: 新增用户使用selectKeys方式
     * @param: [sysUser]
     * @return: int
     * @author: kb
     * @date: 2021-01-31
     */
    int insert3(SysUser sysUser);

    /***
     * @description: 根据主键更新
     * @param: [sysUser]
     * @return: int
     * @author: kb
     * @date: 2021-01-31
     */
    int updateById(SysUser sysUser);

    /***
     * @description: 根据id和角色的enabled状态来获取用户的角色
     * @param: [userId, enabled]
     * @return: java.util.List<com.kebo.po.SysRole>
     * @author: kb
     * @date: 2021-01-31
     */
    List<SysRole> selectRolesByUserIdAndRoleEnabled(@Param("userId") Long userId,
                                                    @Param("enabled") Integer enabled);

    /***
     * @description: 根据id删除信息
     * @param: [id]
     * @return: int
     * @author: kb
     * @date: 2021-02-01
     */
    int deleteById(Long id);

    SysUser selectById(Long id);
}
