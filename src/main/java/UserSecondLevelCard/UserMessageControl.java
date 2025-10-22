package UserSecondLevelCard;

import Common.Cookie;
import FACTORY.DAOFactory;
import VO.Users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMessageControl extends JPanel {
    private JButton MessageKeep;
    private JTextField username;
    private JTextField telephone;
    private JTextField mail;
    private String name;
    private String tel;
    private String Mail;

    public UserMessageControl() {
        setLayout(null);
        Color workbgcolor=new Color(240,240,240);
        setBackground(workbgcolor);
        Users UserList = null;
        try {
            UserList = DAOFactory.getIUserDAO().SelectUser(Cookie.getUserID());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        String Uname = UserList.getname();
        String Utel = UserList.gettel();
        String Umail = UserList.getmail();
        JLabel userLabel = new JLabel("账 号:");
        userLabel.setFont(new java.awt.Font("Dialog", 1, 20));
        userLabel.setBounds(190, 20, 80, 25);
        add(userLabel);
        JLabel userID = new JLabel(Cookie.getUserID());
        userID.setFont(new java.awt.Font("Dialog", 1, 20));
        userID.setBounds(270, 20, 300, 25);
        add(userID);
        JLabel userName = new JLabel("用户名:");
        userName.setFont(new java.awt.Font("Dialog", 1, 20));
        userName.setBounds(190, 120, 80, 25);
        add(userName);
        // username.setFont(new java.awt.Font("Dialog", 1, 20));
        username = new JTextField(Uname, 20);
        username.setBounds(270, 120, 300, 40);
        add(username);
        JLabel usertel = new JLabel("手机号:");
        usertel.setFont(new java.awt.Font("Dialog", 1, 20));
        usertel.setBounds(190, 220, 80, 25);
        add(usertel);
        telephone = new JTextField(Utel, 20);
        telephone.setBounds(270, 220, 300, 40);
        add(telephone);
        JLabel usermail = new JLabel("邮箱号:");
        usermail.setFont(new java.awt.Font("Dialog", 1, 20));
        usermail.setBounds(190, 320, 80, 25);
        add(usermail);
        mail = new JTextField(Umail, 20);
        mail.setBounds(270, 320, 300, 40);
        add(mail);
        MessageKeep = new JButton("保存");
        MessageKeep.setFont(new java.awt.Font("Dialog", 1, 20));
        MessageKeep.setBounds(190, 400, 380, 40);
        add(MessageKeep);
        MessageKeep.setActionCommand("OK");
        MessageKeep.addActionListener(new ButtonClickListener());
        name = username.getText();
        tel = telephone.getText();
        Mail = mail.getText();
    }

    public void UpdateTable(String name, String tel, String Mail) {
        username.setText(name);
        telephone.setText(tel);
        mail.setText(Mail);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("OK")) {
                boolean next = true;
                name = username.getText();
                tel = telephone.getText();
                Mail = mail.getText();
                if (name.equals("")) {
                    next = false;
                }
                if (tel.equals("")) {
                    next = false;
                }
                if (Mail.equals("")) {
                    next = false;
                }
                if (next) {
                    try {
                        DAOFactory.getIUserDAO().ModUser(name, tel, Mail, Cookie.getUserID());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    String Text = "请将所有信息输入完整";
                    JOptionPane.showMessageDialog(null, Text, "警告", JOptionPane.WARNING_MESSAGE);
                    // username.setText(name);
                    // telephone.setText(tel);
                    // mail.setText(Mail);
                    // System.out.println(mail.getText());
                }
            }
        }
    }
}
