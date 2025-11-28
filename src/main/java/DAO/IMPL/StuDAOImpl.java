package DAO.IMPL;

import DAO.IstuDAO;
import VO.stu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StuDAOImpl implements IstuDAO {
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public StuDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public ArrayList<stu> findstudent(String id, String classname, int startindex, int recordnum) throws Exception {
        ArrayList<stu> stuList = new ArrayList<stu>();
        String path = id + "_" + classname;
        String sql = "SELECT * FROM skwork." + path + " limit ?,?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, startindex);
        this.pstmt.setInt(2, recordnum);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            stu s = new stu();
            s.setStuId(rs.getString("stu_id"));
            s.setStuName(rs.getString("stu_name"));
            s.setStuSex(rs.getString("sex"));
            s.settwtime(rs.getInt("tw_time"));
            s.setkqtime(rs.getInt("kq_time"));
            s.setqqtime(rs.getInt("qq_time"));
            s.setallgrade(rs.getDouble("allgrade"));
            stuList.add(s);
        }
        this.pstmt.close();
        return stuList;
    }

    @Override
    // 根据学号ID查询学生信息
    public stu findstudentByID(String id, String classname, String StuId) throws Exception {
        stu s = null;
        String path = id + "_" + classname;
        String sql = "SELECT * FROM skwork." + path + " where stu_id=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, StuId);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            s = new stu();
            s.setStuId(rs.getString("stu_id"));
            s.setStuName(rs.getString("stu_name"));
            s.setStuSex(rs.getString("sex"));
            s.settwtime(rs.getInt("tw_time"));
            s.setkqtime(rs.getInt("kq_time"));
            s.setqqtime(rs.getInt("qq_time"));
            s.setallgrade(rs.getDouble("allgrade"));
        }
        this.pstmt.close();
        return s;
    }

    @Override
    // 根据学生姓名查询学生信息
    public ArrayList<stu> findstudentByName(String id, String classname, String name, int startindex, int recordnum)
            throws Exception {
        ArrayList<stu> stuList = new ArrayList<stu>();
        String path = id + "_" + classname;
        String sql = "SELECT * FROM skwork." + path + " where stu_name like ? limit ?,?";
        this.pstmt = this.conn.prepareStatement(sql);
        pstmt.setString(1, "%"+name+"%");
        pstmt.setInt(2, startindex);
        pstmt.setInt(3, recordnum);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            stu s = new stu();
            s.setStuId(rs.getString("stu_id"));
            s.setStuName(rs.getString("stu_name"));
            s.setStuSex(rs.getString("sex"));
            s.settwtime(rs.getInt("tw_time"));
            s.setkqtime(rs.getInt("kq_time"));
            s.setqqtime(rs.getInt("qq_time"));
            s.setallgrade(rs.getDouble("allgrade"));
            stuList.add(s);
        }
        this.pstmt.close();
        return stuList;
    }

    @Override
    // 获取所有学生信息的数目（分页用）
    public int getCount(String id, String classname) throws Exception {
        String path = id + "_" + classname;
        String sql = "SELECT COUNT(*) FROM skwork." + path;
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        this.pstmt.close();
        return count;
    }

    @Override
    // 根据学生名称获取产品信息的数目（分页用）
    public int getCountByName(String id, String classname, String name) throws Exception {
        String path = id + "_" + classname;
        String sql = "SELECT COUNT(*) FROM skwork." + path + " WHERE stu_name=?";
        this.pstmt = this.conn.prepareStatement(sql);
        pstmt.setString(1, name);
        ResultSet rs = this.pstmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        this.pstmt.close();
        return count;
    }

    @Override
    // 新增学生信息
    public boolean addstudent(String id, String classname, stu s) throws Exception {
        boolean flag = false;
        String path = id + "_" + classname;
        String sql = "insert into skwork." + path
                + " (`stu_id`, `stu_name`, `sex`, `tw_time`, `kq_time`, `qq_time`, `allgrade`) VALUES (?,?,?,?,?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, s.getStuId());
        this.pstmt.setString(2, s.getStuName());
        this.pstmt.setString(3, s.getStuSex());
        this.pstmt.setInt(4, s.gettwtime());
        this.pstmt.setInt(5, s.getkqtime());
        this.pstmt.setInt(6, s.getqqtime());
        this.pstmt.setDouble(7, s.getallgrade());
        if (this.pstmt.executeUpdate() > 0) {
            flag = true;
            //System.out.println(this.pstmt.executeUpdate() + "条数据更改!");
        }
        this.pstmt.close();
        return flag;
    }

    @Override
    // 修改学生信息
    public boolean updatestudent(String id, String classname, stu s, String afterId) throws Exception {
        boolean flag = false;
        String path = id + "_" + classname;
        String sql = "update skwork." + path
                + " SET `stu_id` =?, `stu_name` =?, `sex` =?, `tw_time` =?, `kq_time` =?, `qq_time` =?, `allgrade` =? WHERE (`stu_id` =?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, s.getStuId());
        this.pstmt.setString(2, s.getStuName());
        this.pstmt.setString(3, s.getStuSex());
        this.pstmt.setInt(4, s.gettwtime());
        this.pstmt.setInt(5, s.getkqtime());
        this.pstmt.setInt(6, s.getqqtime());
        this.pstmt.setDouble(7, s.getallgrade());
        this.pstmt.setString(8, afterId);
        if (this.pstmt.executeUpdate() > 0) {
            flag = true;
            System.out.println(this.pstmt.executeUpdate() + "条数据更改!");
        }
        this.pstmt.close();
        return flag;
    }

    @Override
    // 删除学生信息
    public boolean deletestudent(String id, String classname, String StuId) throws Exception {
        boolean flag = false;
        String path = id + "_" + classname;
        String sql = "delete from " + path + " where stu_id=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, StuId);
        if (this.pstmt.executeUpdate() > 0) {
            flag = true;
            System.out.println(path + "数据删除成功!");
        }
        this.pstmt.close();
        return flag;
    }
}
