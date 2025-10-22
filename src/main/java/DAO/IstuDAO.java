package DAO;

import VO.stu;

import java.util.ArrayList;

public interface IstuDAO {

    // 查看该表信息
    public ArrayList<stu> findstudent(String id,String classname,int startindex, int recordnum) throws Exception;

    // 根据学号ID查询学生信息
    public stu findstudentByID(String id,String classname,String StuId) throws Exception;

    // 根据学生姓名查询学生信息
    public ArrayList<stu> findstudentByName(String id,String classname,String name, int startindex, int recordnum) throws Exception;

    // 获取所有学生信息的数目（分页用）
    public int getCount(String id,String classname) throws Exception;

    // 根据学生名称获取产品信息的数目（分页用）
    public int getCountByName(String id,String classname,String name) throws Exception;
    // 新增学生信息
    public boolean addstudent(String id,String classname,stu s) throws Exception;

    // 修改学生信息
    public boolean updatestudent(String id,String classname,stu s,String afterId) throws Exception;

    // 删除学生信息
    public boolean deletestudent(String id,String classname,String StuId) throws Exception;
}
