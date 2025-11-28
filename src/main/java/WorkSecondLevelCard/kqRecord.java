package WorkSecondLevelCard;

import Common.Cookie;
import Common.speekName;
import FACTORY.DAOFactory;
import VO.stu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class kqRecord {
    private static JButton ZQ;
    private static JButton QQ;
    private static JLabel Name;
    private static JFrame frame;
    private static JPanel panel;
    private static String StuId;

    public kqRecord(String StuId, String StuName) {
        new speekName(StuName).start();
        kqRecord.StuId = StuId;
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        int width = 420;
        int height = 300;
        frame = new JFrame("考勤抽取");
        panel = new JPanel();
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel.setLayout(null);
        ZQ = new JButton("出   勤");
        ZQ.setFont(new java.awt.Font("Dialog", 1, 20));
        ZQ.setBounds(50, 200, 150, 40);
        panel.add(ZQ);
        QQ = new JButton("缺   勤");
        QQ.setFont(new java.awt.Font("Dialog", 1, 20));
        QQ.setBounds(220, 200, 150, 40);
        panel.add(QQ);
        Name = new JLabel(StuName, JLabel.CENTER);
        Name.setFont(new java.awt.Font("Dialog", 1, 40));
        Name.setBounds(0, 10, 420, 200);
        // Name.setVerticalTextPosition(JLabel.TOP);
        Name.setHorizontalTextPosition(JLabel.CENTER);
        panel.add(Name);
        frame.setIconImage(new ImageIcon("src\\Image\\SK3.png").getImage());
        frame.add(panel);
        frame.setVisible(true);
        frame.setBounds((screenWidth - width) / 2, (screenHeight - height) / 2, width, height);
        ZQ.setActionCommand("ZQ");
        QQ.setActionCommand("QQ");
        ZQ.addActionListener(new ButtonClickListener());
        QQ.addActionListener(new ButtonClickListener());
    }

    private static class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("ZQ")) {
                stu s = new stu();
                try {
                    s = DAOFactory.getIstuDoDAO().findstudentById(Cookie.getUserID(), Cookie.getChoiceClass(), StuId);
                    DAOFactory.getIstuDoDAO().qqDoStudent(Cookie.getUserID(), Cookie.getChoiceClass(), StuId,
                            s.getkqtime(), s.getqqtime(), true);
                    frame.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (command.equals("QQ")) {
                stu s = new stu();
                try {
                    s = DAOFactory.getIstuDoDAO().findstudentById(Cookie.getUserID(), Cookie.getChoiceClass(), StuId);
                    DAOFactory.getIstuDoDAO().qqDoStudent(Cookie.getUserID(), Cookie.getChoiceClass(), StuId,
                            s.getkqtime(), s.getqqtime(), false);
                    frame.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
