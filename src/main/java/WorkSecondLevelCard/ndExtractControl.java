package WorkSecondLevelCard;

import Common.Cookie;
import FACTORY.DAOFactory;
import VO.stu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ndExtractControl extends JPanel {
    private static JButton easy;
    private static JButton putong;
    private static JButton yiban;
    private static JButton difficult;

    public ndExtractControl() {
        setLayout(null);
        Color workbgcolor=new Color(240,240,240);
        setBackground(workbgcolor);
        easy = new JButton("简单");
        easy.setFont(new java.awt.Font("Dialog", 1, 20));
        easy.setBounds(155, 55, 150, 150);
        putong = new JButton("普通");
        putong.setFont(new java.awt.Font("Dialog", 1, 20));
        putong.setBounds(465, 55, 150, 150);
        yiban = new JButton("较难");
        yiban.setFont(new java.awt.Font("Dialog", 1, 20));
        yiban.setBounds(155, 275, 150, 150);
        difficult = new JButton("困难");
        difficult.setFont(new java.awt.Font("Dialog", 1, 20));
        difficult.setBounds(465, 275, 150, 150);
        add(easy);
        add(putong);
        add(yiban);
        add(difficult);
        easy.setActionCommand("easy");
        putong.setActionCommand("putong");
        yiban.setActionCommand("yiban");
        difficult.setActionCommand("difficult");
        easy.addActionListener(new ButtonClickListener());
        putong.addActionListener(new ButtonClickListener());
        yiban.addActionListener(new ButtonClickListener());
        difficult.addActionListener(new ButtonClickListener());
    }

    private static class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("easy")) {
                ArrayList<stu> stuList = new ArrayList<stu>();
                String Markname = null;
                String MarkId = null;
                int twtime = 0;
                double allstuGrade = 0;
                int temp = 0;
                try {
                    int allstu = DAOFactory.getIstuDAO().getCount(Cookie.getUserID(), Cookie.getChoiceClass());
                    stuList = DAOFactory.getIstuDoDAO().findstudent(Cookie.getUserID(), Cookie.getChoiceClass());
                    if (stuList.size() == 0) {
                        String Text = "请添加学生";
                        JOptionPane.showMessageDialog(null, Text, "警告", JOptionPane.WARNING_MESSAGE);
                    } else {
                        for (int i = 0; i < stuList.size(); i++) {
                            allstuGrade += stuList.get(i).getallgrade();
                        }
                        for (int i = 0; i < stuList.size(); i++) {
                            double pi = DAO.IMPL.skWrok.TWwork(stuList.get(i).getallgrade(), stuList.get(i).getqqtime(),
                                    stuList.get(i).gettwtime(), -1, allstuGrade, allstu);
                            stuList.get(i).setMin(temp);
                            temp += (int) (pi * 100);
                            stuList.get(i).setPi(pi);
                        }
                        int num = DAOFactory.getIstuDoDAO().RandomNum(0, temp);
                        for (int i = 0; i < stuList.size(); i++) {
                            if (num >= stuList.get(i).getMin() && num <= stuList.get(i).getMax()) {
                                Markname = stuList.get(i).getStuName();
                                MarkId = stuList.get(i).getStuId();
                                twtime = stuList.get(i).gettwtime();
                                break;
                            }
                        }
                        new ndRecord(Markname, MarkId, twtime);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (command.equals("putong")) {
                ArrayList<stu> stuList = new ArrayList<stu>();
                String Markname = null;
                String MarkId = null;
                int twtime = 0;
                double allstuGrade = 0;
                int temp = 0;
                try {
                    int allstu = DAOFactory.getIstuDAO().getCount(Cookie.getUserID(), Cookie.getChoiceClass());
                    stuList = DAOFactory.getIstuDoDAO().findstudent(Cookie.getUserID(), Cookie.getChoiceClass());
                    if (stuList.size() == 0) {
                        String Text = "请添加学生";
                        JOptionPane.showMessageDialog(null, Text, "警告", JOptionPane.WARNING_MESSAGE);
                    } else {
                        for (int i = 0; i < stuList.size(); i++) {
                            allstuGrade += stuList.get(i).getallgrade();
                        }
                        for (int i = 0; i < stuList.size(); i++) {
                            double pi = DAO.IMPL.skWrok.TWwork(stuList.get(i).getallgrade(), stuList.get(i).getqqtime(),
                                    stuList.get(i).gettwtime(), -0.5, allstuGrade, allstu);
                            stuList.get(i).setMin(temp);
                            temp += (int) (pi * 100);
                            stuList.get(i).setPi(pi);
                        }
                        int num = DAOFactory.getIstuDoDAO().RandomNum(0, temp);
                        for (int i = 0; i < stuList.size(); i++) {
                            if (num >= stuList.get(i).getMin() && num <= stuList.get(i).getMax()) {
                                Markname = stuList.get(i).getStuName();
                                MarkId = stuList.get(i).getStuId();
                                twtime = stuList.get(i).gettwtime();
                                break;
                            }
                        }
                        new ndRecord(Markname, MarkId, twtime);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (command.equals("yiban")) {
                ArrayList<stu> stuList = new ArrayList<stu>();
                String Markname = null;
                String MarkId = null;
                int twtime = 0;
                double allstuGrade = 0;
                int temp = 0;
                try {
                    int allstu = DAOFactory.getIstuDAO().getCount(Cookie.getUserID(), Cookie.getChoiceClass());
                    stuList = DAOFactory.getIstuDoDAO().findstudent(Cookie.getUserID(), Cookie.getChoiceClass());
                    if (stuList.size() == 0) {
                        String Text = "请添加学生";
                        JOptionPane.showMessageDialog(null, Text, "警告", JOptionPane.WARNING_MESSAGE);
                    } else {
                        for (int i = 0; i < stuList.size(); i++) {
                            allstuGrade += stuList.get(i).getallgrade();
                        }
                        for (int i = 0; i < stuList.size(); i++) {
                            double pi = DAO.IMPL.skWrok.TWwork(stuList.get(i).getallgrade(), stuList.get(i).getqqtime(),
                                    stuList.get(i).gettwtime(), 0.5, allstuGrade, allstu);
                            stuList.get(i).setMin(temp);
                            temp += (int) (pi * 100);
                            stuList.get(i).setPi(pi);
                        }
                        int num = DAOFactory.getIstuDoDAO().RandomNum(0, temp);
                        for (int i = 0; i < stuList.size(); i++) {
                            if (num >= stuList.get(i).getMin() && num <= stuList.get(i).getMax()) {
                                Markname = stuList.get(i).getStuName();
                                MarkId = stuList.get(i).getStuId();
                                twtime = stuList.get(i).gettwtime();
                                break;
                            }
                        }
                        new ndRecord(Markname, MarkId, twtime);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (command.equals("difficult")) {
                ArrayList<stu> stuList = new ArrayList<stu>();
                String Markname = null;
                String MarkId = null;
                int twtime = 0;
                double allstuGrade = 0;
                int temp = 0;
                try {
                    int allstu = DAOFactory.getIstuDAO().getCount(Cookie.getUserID(), Cookie.getChoiceClass());
                    stuList = DAOFactory.getIstuDoDAO().findstudent(Cookie.getUserID(), Cookie.getChoiceClass());
                    if (stuList.size() == 0) {
                        String Text = "请添加学生";
                        JOptionPane.showMessageDialog(null, Text, "警告", JOptionPane.WARNING_MESSAGE);
                    } else {
                        for (int i = 0; i < stuList.size(); i++) {
                            allstuGrade += stuList.get(i).getallgrade();
                        }
                        for (int i = 0; i < stuList.size(); i++) {
                            double pi = DAO.IMPL.skWrok.TWwork(stuList.get(i).getallgrade(), stuList.get(i).getqqtime(),
                                    stuList.get(i).gettwtime(), 1, allstuGrade, allstu);
                            stuList.get(i).setMin(temp);
                            temp += (int) (pi * 100);
                            stuList.get(i).setPi(pi);
                        }
                        int num = DAOFactory.getIstuDoDAO().RandomNum(0, temp);
                        for (int i = 0; i < stuList.size(); i++) {
                            if (num >= stuList.get(i).getMin() && num <= stuList.get(i).getMax()) {
                                Markname = stuList.get(i).getStuName();
                                MarkId = stuList.get(i).getStuId();
                                twtime = stuList.get(i).gettwtime();
                                break;
                            }
                        }
                        new ndRecord(Markname, MarkId, twtime);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
