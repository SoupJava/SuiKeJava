package DAO.IMPL;

import DAO.IUsersDAO;
import VO.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsersDAOImpl implements IUsersDAO {
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public UsersDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean findLogin(Users user) throws Exception {
        boolean flag = false;
        String sql = "SELECT name FROM skwork.users WHERE id=? AND password=?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, user.getId());
        this.pstmt.setString(2, user.getpassword());
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            user.setname(rs.getString(1));
            flag = true;
        }
        this.pstmt.close();
        return flag;
    }

    @Override
    public void AddUser(String id, String password, String name, String tel, String mail) throws Exception

    {
        String sql1 = "insert into skwork.users(id,password,name,tel,mail) values(?,?,?,?,?)";
        String sql2 = "CREATE TABLE `skwork`.`" + id + "` (\r\n" + "  `classname` VARCHAR(40) NOT NULL,\r\n"
                + "  `begintime` VARCHAR(40) NOT NULL,\r\n" + "  `explain` VARCHAR(100) NOT NULL,\r\n"
                + "  PRIMARY KEY (`classname`),\r\n"
                + "  UNIQUE INDEX `classname_UNIQUE` (`classname` ASC) VISIBLE);\r\n" + "";
        this.pstmt = this.conn.prepareStatement(sql1);
        this.pstmt.setString(1, id);
        this.pstmt.setString(2, password);
        this.pstmt.setString(3, name);
        this.pstmt.setString(4, tel);
        this.pstmt.setString(5, mail);
        PreparedStatement create = conn.prepareStatement(sql2);
        int rowcount = this.pstmt.executeUpdate();
        int rowcount2 = create.executeUpdate();
        System.out.println(rowcount+"行执行操作了");
        System.out.println(rowcount2+"行执行操作了");
        this.pstmt.close();
        create.close();
    }

    @Override
    public void ModUser(String name, String tel, String mail, String id) throws Exception

    {
        String sql = "UPDATE `skwork`.`users` SET `name` = ?, `tel` = ?, `mail` = ? WHERE (`id` = ?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, name);
        this.pstmt.setString(2, tel);
        this.pstmt.setString(3, mail);
        this.pstmt.setString(4, id);
        int rowcount = this.pstmt.executeUpdate();
        System.out.println(rowcount+"行执行操作了");
        this.pstmt.close();
    }

    @Override
    public Users SelectUser(String id) throws Exception {
        Users u = null;
        String sql = "select * from skwork.users where id=?";
        this.pstmt = this.conn.prepareStatement(sql);
        pstmt.setString(1, id);
        ResultSet rs = this.pstmt.executeQuery();
        while (rs.next()) {
            u = new Users();
            u.setId(rs.getString("id"));
            u.setpassword(rs.getString("password"));
            u.setname(rs.getString("name"));
            u.settel(rs.getString("tel"));
            u.setmail(rs.getString("mail"));
        }
        this.pstmt.close();
        return u;
    }

    @Override
    public void ModiyPassword(String id, String Password) throws Exception {
        String sql = "UPDATE `skwork`.`users` SET `password` = ? WHERE (`id` = ?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, Password);
        this.pstmt.setString(2, id);
        int rowcount = this.pstmt.executeUpdate();
        System.out.println(rowcount+"行执行操作了");
        this.pstmt.close();
    }
}