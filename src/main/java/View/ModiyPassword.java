package View;

import FACTORY.DAOFactory;
import VO.Users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModiyPassword {
    private static JPanel panel;
    private static JFrame frame;
    private static JButton OK;
    private static JPasswordField afterpasswordText;
    private static JPasswordField passwordText;
    private static JPasswordField passwordTextOK;
    private static JLabel one;
    private static JLabel two;
    private static JLabel three;
    private static Users U;

    public ModiyPassword(Users U) {
        ModiyPassword.U = U;
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        int width = 360;
        int height = 450;
        frame = new JFrame("修改密码");
        panel = new JPanel();
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // frame.setLayout(null);
        panel.setLayout(null);
        one = new JLabel("原  密  码:");
        one.setFont(new java.awt.Font("Dialog", 1, 20));
        one.setBounds(10, 70, 100, 25);
        panel.add(one);
        two = new JLabel("密       码:");
        two.setFont(new java.awt.Font("Dialog", 1, 20));
        two.setBounds(10, 120, 100, 25);
        panel.add(two);
        three = new JLabel("密码验证:");
        three.setFont(new java.awt.Font("Dialog", 1, 20));
        three.setBounds(10, 170, 100, 25);
        panel.add(three);
        afterpasswordText = new JPasswordField(20);
        afterpasswordText.setBounds(120, 70, 200, 30);
        panel.add(afterpasswordText);
        passwordText = new JPasswordField(20);
        passwordText.setBounds(120, 120, 200, 30);
        panel.add(passwordText);
        passwordTextOK = new JPasswordField(20);
        passwordTextOK.setBounds(120, 170, 200, 30);
        panel.add(passwordTextOK);
        OK = new JButton("确   定");
        OK.setFont(new java.awt.Font("Dialog", 1, 20));
        OK.setBounds(70, 320, 200, 50);
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
                String passone = new String(afterpasswordText.getPassword());
                String passtwo = new String(passwordText.getPassword());
                String passthree = new String(passwordTextOK.getPassword());
                if (passone.equals("") || passtwo.equals("") || passthree.equals("")) {
                    String Text = "请将所有信息输入完整";
                    JOptionPane.showMessageDialog(null, Text, "警告", JOptionPane.WARNING_MESSAGE);
                } else if (!passone.equals(U.getpassword())) {
                    String Text = "原密码输入错误";
                    JOptionPane.showMessageDialog(null, Text, "警告", JOptionPane.WARNING_MESSAGE);
                } else if (!passtwo.equals(passthree)) {
                    String Text = "两次密码不一致";
                    JOptionPane.showMessageDialog(null, Text, "警告", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        DAOFactory.getIUserDAO().ModiyPassword(U.getId(), passtwo);
                        frame.dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}
