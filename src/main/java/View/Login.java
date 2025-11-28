package View;

import Common.Cookie;
import FACTORY.DAOFactory;
import VO.Users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private static CardLayout Card;
    // private static FlowLayout layout;
    private static JPanel panel;
    private static JPanel magine;
    private static JLabel magcard1;
    private static JLabel magcard2;
    private static JLabel magcard3;
    private static ImageIcon icon1;
    private static ImageIcon icon2;
    private static ImageIcon icon3;
    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton loginButton;
    private static JButton zhuceButton;
    private static JButton modiyButton;
    private static JFrame frame;

    public Login() {

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        int width = 460;
        int height = 360;
        frame = new JFrame("随课-SuiKe登录界面");
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.WHITE);
        Card = new CardLayout();
        panel = new JPanel();
        magine = new JPanel();
        magcard1 = new JLabel();
        magcard2 = new JLabel();
        magcard3 = new JLabel();
        icon1 = new ImageIcon("src\\main\\java\\Image\\R-D.png");
        icon2 = new ImageIcon("src\\main\\java\\Image\\R-E.png");
        icon3 = new ImageIcon("src\\main\\java\\Image\\R-F.png");
        frame.setIconImage(new ImageIcon("src\\main\\java\\Image\\SK3.png").getImage());
        icon1.setImage(icon1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        icon2.setImage(icon2.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        icon3.setImage(icon3.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        magcard1 = new JLabel(icon1, SwingConstants.CENTER);
        magcard2 = new JLabel(icon2, SwingConstants.CENTER);
        magcard3 = new JLabel(icon3, SwingConstants.CENTER);
        magine.setLayout(Card);
        magine.add("1", magcard1);
        magine.add("2", magcard2);
        magine.add("3", magcard3);
        // magine.addMouseListener(this);
        new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Card.next(magine);
            }
        }).start();
        frame.setLayout(null);
        panel.setBounds(0, 150, 460, 190);
        magine.setBounds(0, 0, 460, 140);
        frame.add(magine);
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);
        frame.setBounds((screenWidth - width) / 2, (screenHeight - height) / 2, width, height);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);
        userLabel = new JLabel("账号:");
        userLabel.setFont(new java.awt.Font("Dialog", 1, 20));
        userLabel.setBounds(75, 0, 80, 25);
        panel.add(userLabel);
        userText = new JTextField(20);
        userText.setBounds(135, 0, 240, 30);
        panel.add(userText);
        passwordLabel = new JLabel("密码:");
        passwordLabel.setFont(new java.awt.Font("Dialog", 1, 20));
        passwordLabel.setBounds(75, 40, 80, 25);
        panel.add(passwordLabel);
        passwordText = new JPasswordField(20);
        passwordText.setBounds(135, 40, 240, 30);
        panel.add(passwordText);
        loginButton = new JButton("登      录");
        loginButton.setFont(new java.awt.Font("Dialog", 1, 20));
        loginButton.setBounds(75, 80, 300, 50);

        panel.add(loginButton);
        zhuceButton = new JButton("注册账号");
        zhuceButton.setFont(new java.awt.Font("Dialog", 1, 10));
        zhuceButton.setBounds(10, 140, 80, 20);
        panel.add(zhuceButton);
        modiyButton = new JButton("忘记密码");
        modiyButton.setFont(new java.awt.Font("Dialog", 1, 10));
        modiyButton.setBounds(360, 140, 80, 20);
        panel.add(modiyButton);
        loginButton.setActionCommand("login");
        zhuceButton.setActionCommand("zhuce");
        modiyButton.setActionCommand("modiy");
        loginButton.addActionListener(new ButtonClickListener());
        zhuceButton.addActionListener(new ButtonClickListener());
        modiyButton.addActionListener(new ButtonClickListener());
    }

    private static class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("login")) {
                System.out.println("获得响应");
                String zhanghao = userText.getText();
                String mima = new String(passwordText.getPassword());
                Users user = new Users();
                user.setId(zhanghao);
                user.setpassword(mima);
                try {
                    if (DAOFactory.getIUserDAO().findLogin(user)) {
                        System.out.println("登录成功");
                        Cookie.setUserID(zhanghao);
                        Users u = new Users();
                        u = DAOFactory.getIUserDAO().SelectUser(zhanghao);
                        frame.dispose();
                        new Menu(u.getname());
                    } else {
                        JOptionPane.showMessageDialog(null, "账号密码错误", "警告", JOptionPane.WARNING_MESSAGE);
                        System.out.println("登录失败");
                    }
                } catch (Exception ex) {
                    //ex.printStackTrace();
                }
            } else if (command.equals("zhuce")) {
                new ZhuCe();
            } else if (command.equals("modiy")) {
                new PasswordVerification();
            }
        }

    }
    // public static void setWindow(boolean control) {
    // frame.setVisible(control);
    // }
}