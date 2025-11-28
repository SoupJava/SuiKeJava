package DBC;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private Connection conn = null;
    public DatabaseConnection() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // conn = DriverManager.getConnection(url);
            conn = DriverManager.getConnection(
                    "数据库地址",
                    "用户名",
                    "密码");
        } catch (Exception e) {
            String Text = "请检查是否连接网络或者防火墙设置";
            JOptionPane.showMessageDialog(null, Text, "警告", JOptionPane.WARNING_MESSAGE);
            throw e;
        }
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void close() throws Exception {
        if (this.conn != null) {
            try {
                this.conn.close();
            } catch (Exception e) {
                throw e;
            }
        }
    }

}
