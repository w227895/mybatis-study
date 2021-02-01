package com.kebo.mapper;

import com.kebo.po.SysRole;
import com.kebo.po.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @Author: kb
 * @Date: 2021-01-31 16:35
 */
public class UserMapperTest extends BaseMapperTest {

    @Test
    public void testSelectByUser(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //只查询用户名时
            SysUser query = new SysUser();
            query.setUserName("ad");
            List<SysUser> userList = userMapper.selectByUser(query);
            System.out.println(userList);
            //只查询用户邮箱时
            query = new SysUser();
            query.setUserEmail("test@mybatis.tk");
            userList = userMapper.selectByUser(query);
            System.out.println(userList);
            //当同时查询用户名和邮箱时
            query = new SysUser();
            query.setUserName("ad");
            query.setUserEmail("test@mybatis.tk");
            userList = userMapper.selectByUser(query);
            //由于没有同时符合这两个条件的用户，查询结果数为 0
            System.out.println(userList);
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByIdSelective(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //从数据库查询 1 个 user 对象
            SysUser user = new SysUser();
            //更新 id = 1 的用户
            user.setId(1L);
            //修改邮箱
            user.setUserEmail("test@mybatis.tk");
            //将新建的对象插入数据库中，特别注意，这里的返回值 result 是执行的 SQL 影响的行数
            int result = userMapper.updateByIdSelective(user);
            System.out.println(result);
        } finally {
            //为了不影响数据库中的数据导致其他测试失败，这里选择回滚
            sqlSession.rollback();
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testInsert2(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //创建一个 user 对象
            SysUser user = new SysUser();
            user.setUserName("test-selective");
            user.setUserPassword("123456");
            user.setUserInfo("test info");
            user.setUserEmail("24918@qq.com");
            user.setCreateTime(new Date());
            //插入数据库
            int result = userMapper.insert2(user);
            System.out.println(result);
        } finally {
            sqlSession.rollback();
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdOrUserName(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //只查询用户名时
            SysUser query = new SysUser();
            query.setId(1L);
            query.setUserName("test");
            SysUser user = userMapper.selectByIdOrUserName(query);
            System.out.println(user);
            //当没有 id 时
            query.setId(null);
            user = userMapper.selectByIdOrUserName(query);
            System.out.println(user);
            //当 id 和 name 都为空时
            query.setUserName(null);
            user = userMapper.selectByIdOrUserName(query);
            System.out.println(user);
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }
}
