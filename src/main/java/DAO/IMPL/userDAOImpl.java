package DAO.IMPL;

import DAO.IuserDAO;
import VO.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class userDAOImpl implements IuserDAO {
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public userDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int getCount(String id) throws Exception {
        String sql = "Select count(*) from skwork." + id;
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        this.pstmt.close();
        return count;
    }

    @Override
    public ArrayList<user> OutStu(String userid, int startindex, int recordnum) throws Exception {
        ArrayList<user> userList = new ArrayList<user>();
        String sql = "SELECT * FROM skwork." + userid + " limit ?,?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, startindex);
        this.pstmt.setInt(2, recordnum);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            user u = new user();
            u.setclassname(rs.getString("classname"));
            // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // u.setbegintime(sdf.format(rs.getDate("begintime")));
            u.setbegintime(rs.getString("begintime"));
            u.setexplain(rs.getString("explain"));
            userList.add(u);
        }
        this.pstmt.close();
        return userList;
    }

    @Override
    public user SecStuByName(String id, String classname) throws Exception {
        user u = null;
        String sql = "SELECT * FROM skwork." + id + " where classname=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, classname);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            u = new user();
            u.setclassname(rs.getString("classname"));
            // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // u.setbegintime(sdf.format(rs.getDate("begintime")));
            u.setbegintime(rs.getString("begintime"));
            u.setexplain(rs.getString("explain"));
        }
        this.pstmt.close();
        return u;
    }

    @Override
    public boolean UpdateStu(user u, String id, String aftername) throws Exception {
        boolean flag = false;
        String path1 = id + "_" + u.getclassname();
        String path2 = id + "_" + aftername;
        String sql = "ALTER  TABLE skwork." + path2 + " RENAME TO skwork." + path1;
        // String sql2 = "update skwork."+u.getclassname()+" set
        // classname=?,begintime=?,explain=?where classname=?";
        String sql2 = "UPDATE `skwork`." + id
                + " SET `classname` = ?, `begintime` = ?, `explain` = ? WHERE (`classname` = ?)";
        this.pstmt = this.conn.prepareStatement(sql2);
        pstmt.setString(1, u.getclassname());
        // DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        // Date date = df.parse(u.getbegintime());
        // this.pstmt.setDate(2, new java.sql.Date(date.getTime()));
        this.pstmt.setString(2, u.getbegintime());
        this.pstmt.setString(3, u.getexplain());
        this.pstmt.setString(4, aftername);
        PreparedStatement change = conn.prepareStatement(sql);
        if (this.pstmt.executeUpdate() > 0 && change.executeUpdate() > 0) {
            flag = true;
            System.out.println(this.pstmt.executeUpdate() + "条数据更改!\n");
            System.out.println(change.executeUpdate() + "条数据更改!\n");
        }
        this.pstmt.close();
        change.close();
        return flag;
    }

    @Override
    public boolean AddStu(String id, String classname, String begintime, String explain) throws Exception {
        boolean flag = false;
        String path1 = "skwork." + id + "_" + classname;
        String path2 = "skwork." + id;
        String sql = "INSERT INTO " + path2 + " (`classname`, `begintime`, `explain`) VALUES (?,?,?)";
        String sql2 = "CREATE TABLE " + path1 + " (\r\n" + "  `stu_id` VARCHAR(15) NOT NULL,\r\n"
                + "  `stu_name` VARCHAR(45) NOT NULL,\r\n" + "  `sex` VARCHAR(2) NOT NULL,\r\n"
                + "  `tw_time` INT NOT NULL,\r\n" + "  `kq_time` INT NOT NULL,\r\n" + "  `qq_time` INT NOT NULL,\r\n"
                + "  `allgrade` DOUBLE(7,2) NOT NULL,\r\n" + "  PRIMARY KEY (`stu_id`),\r\n"
                + "  UNIQUE INDEX `stu_id_UNIQUE` (`stu_id` ASC) VISIBLE);\r\n" + "";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, classname);
        // DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        // Date date = df.parse(begintime);
        // this.pstmt.setDate(2, new java.sql.Date(date.getTime()));
        this.pstmt.setString(2, begintime);
        this.pstmt.setString(3, explain);
        // System.out.println("!!!");
        PreparedStatement create = conn.prepareStatement(sql2);
        if (this.pstmt.executeUpdate() > 0 && create.executeUpdate() > 0) {
            flag = true;
            System.out.println(this.pstmt.executeUpdate() + "条数据更改!\n");
            System.out.println(create.executeUpdate() + "条数据更改!\n");
        }
        this.pstmt.close();
        create.close();
        return flag;
    }

    @Override
    public boolean DelStu(String classname, String id) throws Exception {
        boolean flag = false;
        String path = "skwork." + id + "_" + classname;
        String path2 = "skwork." + id;
        String sql = "drop table " + path;
        String sql2 = "DELETE FROM " + path2 + " WHERE classname=?";
        this.pstmt = this.conn.prepareStatement(sql2);
        this.pstmt.setString(1, classname);
        PreparedStatement del = conn.prepareStatement(sql);
        if (this.pstmt.executeUpdate() > 0 && del.executeUpdate() > 0) {
            flag = true;
            System.out.println(path + "数据表删除成功!");
        }
        this.pstmt.close();
        del.close();
        return flag;
    }

}