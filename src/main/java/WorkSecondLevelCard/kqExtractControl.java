package WorkSecondLevelCard;

import Common.Cookie;
import FACTORY.DAOFactory;
import VO.stu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class kqExtractControl extends JPanel {
    private static JButton OK;
    private static JButton ADD;

    public kqExtractControl() {
        setLayout(null);
        Color workbgcolor=new Color(240,240,240);
        setBackground(workbgcolor);
        OK = new JButton("开  始  抽  取");
        OK.setFont(new java.awt.Font("Dialog", 1, 20));
        OK.setBounds(285, 120, 200, 100);
        ADD = new JButton("手  动  添  加");
        ADD.setFont(new java.awt.Font("Dialog", 1, 20));
        ADD.setBounds(285, 320, 200, 100);
        add(OK);
        add(ADD);
        OK.setActionCommand("OK");
        ADD.setActionCommand("ADD");
        OK.addActionListener(new ButtonClickListener());
        ADD.addActionListener(new ButtonClickListener());
    }

    private static class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("OK")) {
                ArrayList<stu> stuList = new ArrayList<stu>();
                int temp = 0;
                String Markname = null;
                String MarkId = null;
                try {
                    stuList = DAOFactory.getIstuDoDAO().findstudent(Cookie.getUserID(), Cookie.getChoiceClass());
                    ArrayList<Double> mark = Cookie.getMark();
                    for (int i = 0; i < mark.size(); i++) {
                        double pi = DAO.IMPL.skWrok.KQwork(stuList.get(i).getkqtime(), stuList.get(i).getqqtime(),
                                stuList.get(i).gettwtime());
                        stuList.get(i).setMin(temp);
                        temp += (int) (pi * mark.get(i));
                        stuList.get(i).setPi(pi, mark.get(i));
                    }
                    int num = DAOFactory.getIstuDoDAO().RandomNum(0, temp);
                    for (int i = 0; i < mark.size(); i++) {
                        if (num >= stuList.get(i).getMin() && num <= stuList.get(i).getMax()) {
                            Markname = stuList.get(i).getStuName();
                            MarkId = stuList.get(i).getStuId();
                            mark.set(i, mark.get(i) / 10);
                            break;
                        }
                    }
                    Cookie.setMark(mark);
                    new kqRecord(MarkId, Markname);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (command.equals("ADD")) {
                new kqAddRecord();
            }
        }
    }
}
