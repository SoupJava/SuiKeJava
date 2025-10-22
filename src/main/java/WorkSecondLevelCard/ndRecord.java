package WorkSecondLevelCard;

import Common.Cookie;
import Common.speekName;
import FACTORY.DAOFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ndRecord {
    private static JButton OK;
    private static JTextField grade;
    private static JLabel Name;
    private static JPanel panel;
    private static JFrame frame;
    private static String StuId;
    private static int twtime;

    public ndRecord(String MarkName, String MarkId, int twtime) {
        new speekName(MarkName).start();
        ndRecord.StuId = MarkId;
        ndRecord.twtime = twtime;
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
        Name = new JLabel(MarkName, JLabel.CENTER);
        Name.setFont(new java.awt.Font("Dialog", 1, 40));
        Name.setBounds(0, 00, 420, 200);
        Name.setHorizontalTextPosition(JLabel.CENTER);
        panel.add(Name);
        OK = new JButton("确  定");
        OK.setFont(new java.awt.Font("Dialog", 1, 20));
        OK.setBounds(50, 200, 150, 40);
        panel.add(OK);
        grade = new JTextField("分数...", 20);
        grade.setBounds(220, 200, 150, 30);
        panel.add(grade);
        frame.setIconImage(new ImageIcon("src\\Image\\SK3.png").getImage());
        frame.add(panel);
        frame.setVisible(true);
        frame.setBounds((screenWidth - width) / 2, (screenHeight - height) / 2, width, height);
        OK.setActionCommand("OK");
        OK.addActionListener(new ButtonClickListener());
    }

    private static class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("OK")) {
                if(grade.getText().equals("分数...")||Integer.parseInt(grade.getText())>100||Integer.parseInt(grade.getText())<0){
                    String Text = "请输入0-100以内的分数";
                    JOptionPane.showMessageDialog(null, Text, "警告", JOptionPane.WARNING_MESSAGE);
                }else {
                    Double Grade = Double.parseDouble(grade.getText());
                    try {
                        double allgrade = DAOFactory.getIstuDoDAO().GetStuGrade(Cookie.getUserID(), Cookie.getChoiceClass(),
                                StuId);
                        allgrade = 0.8 * allgrade + (1 - 0.8) * Grade;
                        twtime = twtime + 1;
                        DAOFactory.getIstuDoDAO().SetTWStu(Cookie.getUserID(), Cookie.getChoiceClass(), StuId, allgrade,
                                twtime);
                        frame.dispose();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}
