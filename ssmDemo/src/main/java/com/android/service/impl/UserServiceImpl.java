package com.android.service.impl;

import com.android.bean.User;
import com.android.mapper.UserMapper;
import com.android.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void saveUser(User user) {
         userMapper.saveUser(user);
    }

    @Override
    public void deleteUserById(Integer[] ids) {

        for (int i=0;i<ids.length;i++) {
            System.out.println("要删除的下标索引:"+ids[i]);
             userMapper.deleteUserById(ids[i]);
        }


    }

    @Override
    public void UpdateUser(User user) {
         userMapper.UpdateUser(user);
    }

    @Override
    public User selectUser(Integer id) {

        System.out.println("业务层方法执行啦.........");

        return userMapper.selectUser(id);
    }

    //分页查询

    @Override
    public PageInfo<User> selectAllUser(int pageNum,int pageSize,User user) {

        //当前页  每页显示的条数
        PageHelper.startPage(pageNum,pageSize);
        List<User> list = userMapper.selectAllUser(user);
                //因为要获取到分页的信息  所有要返回pageInfo对象
        return new PageInfo<User>(list);
    }

    @Override
    public User login(User user) {
        return userMapper.selectUserByNameAndPwd(user);
    }
}
