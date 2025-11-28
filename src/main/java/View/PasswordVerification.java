package View;

import FACTORY.DAOFactory;
import VO.Users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordVerification {
    private static JButton OK;
    private static JTextField userText;
    private static JTextField username;
    private static JTextField telephone;
    private static JTextField mail;
    private static JPanel panel;
    private static JFrame frame;

    public PasswordVerification() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        int width = 320;
        int height = 450;
        frame = new JFrame("忘记密码");
        panel = new JPanel();
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // frame.setLayout(null);
        panel.setLayout(null);
        userText = new JTextField("账号...", 20);
        userText.setBounds(50, 20, 200, 30);
        panel.add(userText);
        username = new JTextField("用户名...", 20);
        username.setBounds(50, 80, 200, 30);
        panel.add(username);
        telephone = new JTextField("电话...", 20);
        telephone.setBounds(50, 140, 200, 30);
        panel.add(telephone);
        mail = new JTextField("邮箱...", 20);
        mail.setBounds(50, 200, 200, 30);
        panel.add(mail);
        OK = new JButton("确   定");
        OK.setFont(new java.awt.Font("Dialog", 1, 20));
        OK.setBounds(50, 320, 200, 50);
        panel.add(OK);
        frame.add(panel);
        OK.setActionCommand("OK");
        OK.addActionListener(new ButtonClickListener());
        frame.setIconImage(new ImageIcon("src\\main\\java\\Image\\SK3.png").getImage());
        frame.setVisible(true);
        frame.setBounds((screenWidth - width) / 2, (screenHeight - height) / 2, width, height);
    }

    private static class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("OK")) {
                boolean next = true;
                String zhanghao = userText.getText();
                String name = username.getText();
                String tel = telephone.getText();
                String Mail = mail.getText();
                if (zhanghao.equals("账号...") || zhanghao.equals("")) {
                    next = false;
                }
                if (name.equals("用户名...") || name.equals("")) {
                    next = false;
                }
                if (tel.equals("电话...") || tel.equals("")) {
                    next = false;
                }
                if (Mail.equals("邮箱...") || Mail.equals("")) {
                    next = false;
                }
                if (next) {
                    try {
                        Users u = new Users();
                        u = DAOFactory.getIUserDAO().SelectUser(zhanghao);
                        boolean nextnext = true;
                        if (u.getname().equals(null) || !u.getname().equals(name)) {
                            nextnext = false;
                        }
                        if (u.gettel().equals(null) || !u.gettel().equals(tel)) {
                            nextnext = false;
                        }
                        if (u.getmail().equals(null) || !u.getmail().equals(Mail)) {
                            nextnext = false;
                        }
                        if (nextnext) {
                            new ModiyPassword(u);
                            frame.dispose();
                        } else {
                            String Text = "信息不符,验证失败";
                            JOptionPane.showMessageDialog(null, Text, "警告", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    String Text = "请将所有信息输入完整";
                    JOptionPane.showMessageDialog(null, Text, "警告", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                System.exit(0);
            }
        }
    }
}
