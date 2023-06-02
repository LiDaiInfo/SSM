package com.android.service;

import com.android.bean.User;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface UserService {

    //添加用户信息
    @Transactional(readOnly = false)
    public boolean saveUser(User user);
    //删除用户信息
    @Transactional(readOnly = false)
    public boolean deleteUserById(Integer id);
    //更改用户信息
    @Transactional(readOnly = false)
    public boolean UpdateUser(User user);
    //查询单个用户信息
    public User selectUser(Integer id);
    //查询所有用户信息
    public PageInfo<User> selectAllUser(int pageNum, int pageSize);
    //查询用户信息根据账号和密码
    public User login(String name,String pwd);

}
