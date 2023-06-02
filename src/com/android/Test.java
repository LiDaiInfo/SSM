package com.android;

import com.android.bean.User;
import com.android.controller.UserController;
import com.android.mapper.UserMapper;
import com.android.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
public class Test {



    @org.junit.Test
    public void test() throws IOException {

        //mybatis框架  使用

       /* InputStream is = Resources.getResourceAsStream("config/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper com.android.mapper = sqlSession.getMapper(UserMapper.class);
        User user = com.android.mapper.selectUser(1);
        System.out.println(user);*/

        //spring框架      使用

//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/applicationContext.xml");
//        UserController userController = (UserController) applicationContext.getBean("userController");
//
//            userController.abc1();


    }


    @Autowired
    private UserService userService;

    @org.junit.Test
    public void abc2(){

//        User user = userService.selectUser(2);
//
//        System.out.println("信息:"+user);

      //  YunDaSignUtil.getSign();

    }


    


}
