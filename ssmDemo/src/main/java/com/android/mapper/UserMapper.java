package com.android.mapper;

import com.android.bean.User;

import java.util.List;

public interface UserMapper {

    //添加用户信息
    public void saveUser(User user);
    //删除用户信息
    public void deleteUserById(Integer id);
    //更改用户信息
    public void UpdateUser(User user);
    //查询单个用户信息
    public User selectUser(Integer id);
    //查询所有用户信息
    public List<User> selectAllUser(User user);
    //查询用户信息根据账号和密码
    public User selectUserByNameAndPwd(User user);

}
