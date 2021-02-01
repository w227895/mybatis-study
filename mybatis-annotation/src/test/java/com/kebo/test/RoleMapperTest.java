package com.kebo.test;

import com.kebo.mapper.RoleMapper;
import com.kebo.po.SysRole;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @Author: kb
 * @Date: 2021-02-01 14:55
 */
public class RoleMapperTest extends BaseMapperTest {
    @Test
    public void testSelectById() {
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取 RoleMapper 接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            //调用 selectById 方法，查询 id = 1 的角色
            SysRole role = roleMapper.selectById(1l);
            System.out.println(role);
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectById2() {
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取 RoleMapper 接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            //调用 selectById 方法，查询 id = 1 的角色
            SysRole role = roleMapper.selectById2(1l);
            //role 不为空
            Assert.assertNotNull(role);
            //roleName = 管理员
            Assert.assertEquals("管理员", role.getRoleName());
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            //调用 selectAll 方法查询所有角色
            List<SysRole> roleList = roleMapper.selectAll();
            //结果不为空
            Assert.assertNotNull(roleList);
            //角色数量大于 0 个
            Assert.assertTrue(roleList.size() > 0);
            //验证下划线字段是否映射成功
            Assert.assertNotNull(roleList.get(0).getRoleName());
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testInsert1() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = new SysRole();
            sysRole.setCreateTime(new Date());
            sysRole.setRoleName("测试角色");
            sysRole.setEnabled(1);
            //插入一个角色
            int result = roleMapper.insert(sysRole);
            System.out.println(result);
            //无法获取主键
            System.out.println(sysRole.getId());
        } finally {
            sqlSession.commit();
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testInsert2() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = new SysRole();
            sysRole.setCreateTime(new Date());
            sysRole.setRoleName("测试角色");
            sysRole.setEnabled(1);
            //插入一个角色
            int result = roleMapper.insert2(sysRole);
            System.out.println(result);
            //返回自增主键
            System.out.println(sysRole.getId());
        } finally {
            sqlSession.commit();
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testInsert3() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = new SysRole();
            sysRole.setCreateTime(new Date());
            sysRole.setRoleName("测试角色");
            sysRole.setEnabled(1);
            //插入一个角色
            int result = roleMapper.insert3(sysRole);
            System.out.println(result);
            //返回非自增主键，自增主键同样有效
            System.out.println(sysRole.getId());
        } finally {
            sqlSession.commit();
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById() {
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取 RoleMapper 接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            //由于数据库数据 enable 都是 1，所以我们给其中一个角色的 enable 赋值为 0
            SysRole role = roleMapper.selectById(2L);
            roleMapper.updateById(role);
        } finally {
            sqlSession.rollback();
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteById() {
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取 RoleMapper 接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            //由于数据库数据 enable 都是 1，所以我们给其中一个角色的 enable 赋值为 0
            SysRole role = roleMapper.selectById(2L);
            roleMapper.deleteById(role.getId());
        } finally {
            sqlSession.rollback();
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdUseProvider() {
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取 RoleMapper 接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            //调用 selectById 方法，查询 id = 1 的角色
            SysRole role = roleMapper.selectByIdUseProvider(1l);
            System.out.println(role);
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }
}
