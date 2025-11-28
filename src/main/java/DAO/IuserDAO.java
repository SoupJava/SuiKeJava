package DAO;

import VO.user;

import java.util.ArrayList;

public interface IuserDAO {
    // 用来显示学生表的数目
    public int getCount(String id) throws Exception;

    // 用来显示学生表
    public ArrayList<user> OutStu(String userid, int startindex, int recordnum) throws Exception;

    // 通过名称来查询学生表
    public user SecStuByName(String id, String classname) throws Exception;

    // 用来修改学生表信息
    public boolean UpdateStu(user u, String id, String aftername) throws Exception;

    // 用来添加学生表
    public boolean AddStu(String id, String classname, String begintime, String explain) throws Exception;

    // 用来删除学生表
    public boolean DelStu(String classname, String id) throws Exception;
}
