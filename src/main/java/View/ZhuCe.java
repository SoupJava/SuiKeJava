package View;

import FACTORY.DAOFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZhuCe {
    private static JButton OK;
    private static JTextField userText;
    private static JPasswordField passwordText;
    private static JPasswordField passwordTextOK;
    private static JTextField username;
    private static JTextField telephone;
    private static JTextField mail;
    private static JPanel panel;
    private static JFrame frame;

    public ZhuCe() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        int width = 320;
        int height = 450;
        frame = new JFrame("注册账号");
        panel = new JPanel();
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // frame.setLayout(null);
        panel.setLayout(null);
        userText = new JTextField("账号...", 20);
        userText.setBounds(50, 20, 200, 30);
        panel.add(userText);
        passwordText = new JPasswordField("密码...", 20);
        passwordText.setBounds(50, 70, 200, 30);
        panel.add(passwordText);
        passwordTextOK = new JPasswordField("确认密码...", 20);
        passwordTextOK.setBounds(50, 120, 200, 30);
        panel.add(passwordTextOK);
        username = new JTextField("用户名...", 20);
        username.setBounds(50, 170, 200, 30);
        panel.add(username);
        telephone = new JTextField("电话...", 20);
        telephone.setBounds(50, 220, 200, 30);
        panel.add(telephone);
        mail = new JTextField("邮箱...", 20);
        mail.setBounds(50, 270, 200, 30);
        panel.add(mail);
        OK = new JButton("确   定");
        OK.setFont(new java.awt.Font("Dialog", 1, 20));
        OK.setBounds(50, 320, 200, 50);
        panel.add(OK);
        frame.setIconImage(new ImageIcon("src\\main\\java\\Image\\SK3.png").getImage());
        frame.add(panel);
        OK.setActionCommand("OK");
        OK.addActionListener(new ButtonClickListener());
        frame.setVisible(true);
        frame.setBounds((screenWidth - width) / 2, (screenHeight - height) / 2, width, height);
    }

    private static class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("OK")) {
                boolean next = true;
                String zhanghao = userText.getText();
                String mima = new String(passwordText.getPassword());
                String mimaOK = new String(passwordText.getPassword());
                String name = username.getText();
                String tel = telephone.getText();
                String Mail = mail.getText();
                if (zhanghao.equals("账号...") || zhanghao.equals("")) {
                    next = false;
                }
                if (mima.equals("密码...") || mima.equals("")) {
                    next = false;
                }
                if (mimaOK.equals("确认密码...") || mimaOK.equals("")) {
                    next = false;
                }
                if (name.equals("用户名...") || name.equals("")) {
                    next = false;
                }
                if (tel.equals("账号...") || tel.equals("")) {
                    next = false;
                }
                if (Mail.equals("邮箱...") || Mail.equals("")) {
                    next = false;
                }
                if (!mima.equals(mimaOK)) {
                    next = false;
                }
                if (next) {
                    try {
                        DAOFactory.getIUserDAO().AddUser(zhanghao, mima, name, tel, Mail);
                        frame.dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    String Text = "请将所有信息输入完整";
                    if (!mima.equals(mimaOK)) {
                        Text = "两次密码不一致";
                    }
                    JOptionPane.showMessageDialog(null, Text, "警告", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                System.exit(0);
            }
        }
    }
}
