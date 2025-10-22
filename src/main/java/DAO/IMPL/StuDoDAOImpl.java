package DAO.IMPL;

import DAO.IstuDoDAO;
import VO.stu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StuDoDAOImpl implements IstuDoDAO {
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public StuDoDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public ArrayList<stu> findstudent(String id, String classname) throws Exception {
        ArrayList<stu> stuList = new ArrayList<stu>();
        String path = id + "_" + classname;
        String sql = "SELECT * FROM skwork." + path;
        this.pstmt = this.conn.prepareStatement(sql);
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
    public boolean qqDoStudent(String id, String classname, String StuId, int kqtime, int qqtime, boolean ynqq)
            throws Exception {
        boolean flag = false;
        String path = id + "_" + classname;
        String sql = "update skwork." + path + " SET `kq_time` =?, `qq_time` =? WHERE (`stu_id` =?)";
        if (ynqq) {
            kqtime++;
            qqtime++;
        } else {
            kqtime++;
        }
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, kqtime);
        this.pstmt.setInt(2, qqtime);
        this.pstmt.setString(3, StuId);
        if (this.pstmt.executeUpdate() > 0) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    @Override
    public stu findstudentById(String id, String classname, String StuId) throws Exception {
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
    public String RandomSampling(String id, String classname, int num) throws Exception {
        String name = null;
        String path = id + "_" + classname;
        String sql = "SELECT stu_name FROM skwork." + path + " limit ?,1";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, num);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            name = rs.getString("stu_name");
        }
        this.pstmt.close();
        return name;
    }

    @Override
    public double GetStuGrade(String id, String classname, String StuId) throws Exception {
        double grade = 0;
        String path = id + "_" + classname;
        String sql = "select allgrade from skwork." + path + " where stu_id=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, StuId);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            grade = rs.getDouble("allgrade");
        }
        this.pstmt.close();
        return grade;
    }

    @Override
    public boolean SetTWStu(String id, String classname, String StuId, double allgrade, int twtime) throws Exception {
        boolean flag = false;
        String path = id + "_" + classname;
        String sql = "UPDATE `skwork`." + path + " SET `tw_time` =?,`allgrade` =? WHERE (stu_id = ?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, twtime);
        this.pstmt.setDouble(2, allgrade);
        this.pstmt.setString(3, StuId);
        if (this.pstmt.executeUpdate() > 0) {
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    @Override
    public int RandomNum(int min, int max) throws Exception {
        int num = (int) (min + Math.random() * (max - min + min));
        return num;
    }
}
