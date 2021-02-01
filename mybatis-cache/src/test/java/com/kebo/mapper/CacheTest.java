package com.kebo.mapper;

import com.kebo.po.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

/**
 * @description:
 * @Author: kb
 * @Date: 2021-02-01 20:11
 */
public class CacheTest extends BaseMapperTest{
    @Test
    public void testL1Cache(){
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        SysUser user1 = null;
        try {
            //获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //调用 selectById 方法，查询 id = 1 的用户
            user1 = userMapper.selectById(1l);
            System.out.println(user1);
            //对当前获取的对象重新赋值
            user1.setUserName("New Name");
            //再次查询获取 id 相同的用户
            SysUser user2 = userMapper.selectById(1l);
            System.out.println(user2);
            //虽然我们没有更新数据库，但是这个用户名和我们 user1 重新赋值的名字相同了
            Assert.assertEquals("New Name", user2.getUserName());
            System.out.println(user1==user2);
            //不仅如此，user2 和 user1 完全就是同一个实例
            Assert.assertEquals(user1, user2);

        } finally {
            //关闭当前的 sqlSession
            sqlSession.close();
        }
        System.out.println("开启新的 sqlSession");
        //开始另一个新的 session
        sqlSession = getSqlSession();
        try {
            //获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //调用 selectById 方法，查询 id = 1 的用户
            SysUser user2 = userMapper.selectById(1l);
            System.out.println(user2);
            //第二个 session 获取的用户名仍然是 admin
            Assert.assertNotEquals("New Name", user2.getUserName());
            //这里的 user2 和 前一个 session 查询的结果是两个不同的实例
            Assert.assertNotEquals(user1, user2);
            System.out.println(user2==user1);
            //执行删除操作
            userMapper.deleteById(2L);
            //获取 user3
            SysUser user3 = userMapper.selectById(1l);
            System.out.println(user3);
            //这里的 user2 和 user3 是两个不同的实例
            Assert.assertNotEquals(user2, user3);
            System.out.println(user2==user3);
        } finally {
            //关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testL2Cache(){
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        SysUser user1 = null;
        try {
            //获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //调用 selectById 方法，查询 id = 1 的用户
            user1 = userMapper.selectById(1l);
            System.out.println(user1);
            //对当前获取的对象重新赋值
            user1.setUserName("New Name");
            //再次查询获取 id 相同的用户
            SysUser user2 = userMapper.selectById(1l);
            System.out.println(user2);
            System.out.println(user1==user2);
            //虽然我们没有更新数据库，但是这个用户名和我们 user1 重新赋值的名字相同了
            Assert.assertEquals("New Name", user2.getUserName());
            //不仅如何，user2 和 user1 完全就是同一个实例
            System.out.println(user1);
            //Assert.assertNotEquals(user1, user2);
        } finally {
            //关闭当前的 sqlSession
            sqlSession.close();
        }
        System.out.println("开启新的 sqlSession");
        //开始另一个新的 session
        sqlSession = getSqlSession();
        try {
            //获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //调用 selectById 方法，查询 id = 1 的用户
            SysUser user2 = userMapper.selectById(1l);
            //第二个 session 获取的用户名仍然是 admin
            Assert.assertEquals("New Name", user2.getUserName());
            //这里的 user2 和 前一个 session 查询的结果是两个不同的实例
            Assert.assertNotEquals(user1, user2);
            //获取 user3
            SysUser user3 = userMapper.selectById(1l);
            //这里的 user2 和 user3 是两个不同的实例
            Assert.assertNotEquals(user2, user3);
        } finally {
            //关闭 sqlSession
            sqlSession.close();
        }
    }
}
