package com.android.service;

import com.android.bean.User;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UserService {

    //添加用户信息
    @Transactional(readOnly = false)
    public void saveUser(User user);
    //删除用户信息
    @Transactional(readOnly = false)
    public void deleteUserById(Integer[] ids);
    //更改用户信息
    @Transactional(readOnly = false)
    public void UpdateUser(User user);
    //查询单个用户信息
    public User selectUser(Integer id);
    //查询所有用户信息
    public PageInfo<User> selectAllUser(int pageNum, int pageSize,User user);
    //查询用户信息根据账号和密码
    public User login(User user);

}
