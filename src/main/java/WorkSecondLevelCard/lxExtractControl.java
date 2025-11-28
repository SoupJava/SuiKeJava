package WorkSecondLevelCard;

import Common.Cookie;
import FACTORY.DAOFactory;
import VO.stu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class lxExtractControl extends JPanel{
    private static JButton OK;
    public lxExtractControl(){
        setLayout(null);
        Color workbgcolor=new Color(240,240,240);
        setBackground(workbgcolor);
        OK = new JButton("开  始  本  轮  抽  取");
        OK.setFont(new java.awt.Font("Dialog", 1, 20));
        OK.setBounds(245, 200, 250, 100);
        add(OK);
        OK.setActionCommand("OK");
        OK.addActionListener(new ButtonClickListener());
    }
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("OK")) {
                ArrayList<stu> stuList = new ArrayList<stu>();
                try {
                    stuList = DAOFactory.getIstuDoDAO().findstudent(Cookie.getUserID(), Cookie.getChoiceClass());
                    Random r = new Random();
                    ArrayList<StuName> sn=new ArrayList<StuName>();
                    for(stu s:stuList){
                        sn.add(new StuName(s.getStuName(), r.nextInt()));
                    }
                    Collections.sort(sn,new Comparator<StuName>() {
                        @Override
                        public int compare(StuName S2, StuName S1) {
                            return S1.getIndex() - S2.getIndex();
                        }
                    });
                    new lxWork(sn);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    class StuName{
        private String name;
        private int index;
        public StuName(String name,int index){
            this.name=name;
            this.index=index;
        }
        public void setName(String name){
            this.name=name;
        }
        public void setIndex(int index){
            this.index=index;
        }
        public String getName(){
            return name;
        }
        public int getIndex(){
            return index;
        }
    }
}
