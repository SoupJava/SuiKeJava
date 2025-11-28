package WorkSecondLevelCard;

import Common.Cookie;
import FACTORY.DAOFactory;
import VO.stu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class kqAddRecord {
    private static JTextField StuId;
    private static JButton OK;
    private static JPanel panel;
    private static JFrame frame;

    public kqAddRecord() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        int width = 320;
        int height = 200;
        frame = new JFrame("添加缺勤");
        panel = new JPanel();
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // frame.setLayout(null);
        panel.setLayout(null);
        StuId = new JTextField("请输入学号...", 20);
        StuId.setBounds(50, 40, 220, 30);
        panel.add(StuId);
        OK = new JButton("确   定");
        OK.setFont(new java.awt.Font("Dialog", 1, 20));
        OK.setBounds(50, 120, 200, 40);
        panel.add(OK);
        OK.setActionCommand("OK");
        OK.addActionListener(new ButtonClickListener());
        frame.setIconImage(new ImageIcon("src\\Image\\SK3.png").getImage());
        frame.add(panel);
        frame.setVisible(true);
        frame.setBounds((screenWidth - width) / 2, (screenHeight - height) / 2, width, height);
    }

    private static class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("OK")) {
                String Id = StuId.getText();
                if (Id.equals("") || Id.equals("请输入学号...")) {
                    String Text = "请输入学号";
                    JOptionPane.showMessageDialog(null, Text, "警告", JOptionPane.WARNING_MESSAGE);
                } else {
                    stu s = new stu();
                    try {
                        s = DAOFactory.getIstuDoDAO().findstudentById(Cookie.getUserID(), Cookie.getChoiceClass(), Id);
                        DAOFactory.getIstuDoDAO().qqDoStudent(Cookie.getUserID(), Cookie.getChoiceClass(), Id,
                                s.getkqtime(), s.getqqtime(), true);
                        frame.dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}
