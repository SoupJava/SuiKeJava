package DAO;

import VO.Users;

public interface IUsersDAO {
    // 用来进行登录验证
    public boolean findLogin(Users user) throws Exception;

    // 用来注册账号
    public void AddUser(String id, String password, String name, String tel, String mail) throws Exception;

    // 用来修改账号信息
    public void ModUser(String name, String tel, String mail, String id) throws Exception;

    // 用来获取所有账号信息
    public Users SelectUser(String id) throws Exception;

    // 用来修改密码
    public void ModiyPassword(String id, String Password) throws Exception;
}
