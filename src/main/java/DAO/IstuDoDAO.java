package DAO;

import VO.stu;

import java.util.ArrayList;

public interface IstuDoDAO {
    // 获取学生信息
    public ArrayList<stu> findstudent(String id, String classname) throws Exception;

    // 记录学生缺勤情况
    public boolean qqDoStudent(String id, String classname, String StuId, int kqtime, int qqtime, boolean ynqq)
            throws Exception;

    // 根据学号获取当前学生信息
    public stu findstudentById(String id, String classname, String StuId) throws Exception;

    // 根据产生随机数来抽取学生
    public String RandomSampling(String id, String classname, int num) throws Exception;

    // 根据学生ID获取学生总体得分评价
    public double GetStuGrade(String id, String classname, String StuId) throws Exception;

    // 设置该学生的得分评价和提问次数
    public boolean SetTWStu(String id, String classname, String StuId, double allgrade, int twtime) throws Exception;

    // 产生随机数字
    public int RandomNum(int min, int max) throws Exception;
}
