package com.kebo.mapper;

import com.kebo.po.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

/**
 * @description:
 * @Author: kb
 * @Date: 2021-01-31 16:35
 */
public class UserMapperTest extends BaseMapperTest {

    @Test
    public void testSelectUserAndRoleById(){
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //特别注意，在我们测试数据中，id = 1L 的用户有两个角色
            //由于后面覆盖前面的，因此只能得到最后一个角色
            //我们这里使用只有一个角色的用户（id = 1001L）
            //可以用 selectUserAndRoleById2 替换进行测试
            SysUser user = userMapper.selectUserAndRoleById(1001L);
            System.out.println(user);
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleById2(){
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //特别注意，在我们测试数据中，id = 1L 的用户有两个角色
            //由于后面覆盖前面的，因此只能得到最后一个角色
            //我们这里使用只有一个角色的用户（id = 1001L）
            //可以用 selectUserAndRoleById2 替换进行测试
            SysUser user = userMapper.selectUserAndRoleById2(1001L);
            System.out.println(user);
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleByIdSelect(){
        //获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            //特别注意，在我们测试数据中，id = 1L 的用户有两个角色
            //由于后面覆盖前面的，因此只能得到最后一个角色
            //我们这里使用只有一个角色的用户（id = 1001L）
            SysUser user = userMapper.selectUserAndRoleByIdSelect(1001L);
            //user 不为空
            Assert.assertNotNull(user);
            //user.role 也不为空
            System.out.println("调用 user.equals(null)");
            user.equals(null);
            System.out.println("调用 user.getRole()");
            Assert.assertNotNull(user.getSysRole());
        } finally {
            //不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }
}
