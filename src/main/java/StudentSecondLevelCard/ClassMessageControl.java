package StudentSecondLevelCard;

import Common.Cookie;
import DAO.IstuDAO;
import FACTORY.DAOFactory;
import FirstLevelCard.StudentCard;
import VO.stu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ClassMessageControl extends JPanel {
    private int count;
    private int bad;
    private int female;
    private static JLabel first;
    private static JLabel second;
    private static JLabel thrid;
    private static JLabel fourth;
    private static JLabel fifth;
    private static JLabel sixth;
    private static ClassMessageControl instance;
//    public static synchronized ClassMessageControl getInstance(){
//        if(instance==null){
//            instance=new ClassMessageControl();
//        }
//        return instance;
//    }
//    public static synchronized ClassMessageControl closeInstance(){
//        if(instance!=null){
//            instance=null;
//        }
//        return instance;
//    }
//    public static synchronized ClassMessageControl RestartInstance(){
//        if(instance==null){
//            instance=new ClassMessageControl();
//        }else{
//            instance=new ClassMessageControl();
//        }
//        return instance;
//    }
    public ClassMessageControl() {
        Color workbgcolor=new Color(240,240,240);
        setBackground(workbgcolor);
        setLayout(null);
        ArrayList<stu> stuList = new ArrayList<stu>();
        try {
            IstuDAO proxy = DAOFactory.getIstuDAO();
            count = proxy.getCount(Cookie.getUserID(), Cookie.getChoiceClass());
            stuList = DAOFactory.getIstuDAO().findstudent(Cookie.getUserID(), Cookie.getChoiceClass(), 0, count);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for (stu u : stuList) {
            if (u.getallgrade() < 60) {
                bad++;
            }
            if (!u.getStuSex().equals("男")) {
                female++;
            }
        }
        String one, two, three, four, five, six;
        one = "课程名:" + Cookie.getChoiceClass();
        two = "共有学生" + count + "名";
        three = "其中及格人数为:" + (count - bad) + "人";
        four = "不及格人数为:" + bad + "人";
        five = "其中男生有" + (count - female) + "人";
        six = "女生有" + female + "人";
        first = new JLabel(one);
        first.setFont(new java.awt.Font("Dialog", 1, 30));
        first.setBounds(400, 50, 390, 25);
        add(first);
        second = new JLabel(two);
        second.setFont(new java.awt.Font("Dialog", 1, 30));
        second.setBounds(400, 110, 390, 25);
        add(second);
        thrid = new JLabel(three);
        thrid.setFont(new java.awt.Font("Dialog", 1, 30));
        thrid.setBounds(400, 170, 390, 25);
        add(thrid);
        fourth = new JLabel(four);
        fourth.setFont(new java.awt.Font("Dialog", 1, 30));
        fourth.setBounds(400, 230, 390, 25);
        add(fourth);
        fifth = new JLabel(five);
        fifth.setFont(new java.awt.Font("Dialog", 1, 30));
        fifth.setBounds(400, 290, 390, 25);
        add(fifth);
        sixth = new JLabel(six);
        sixth.setFont(new java.awt.Font("Dialog", 1, 30));
        sixth.setBounds(400, 350, 390, 25);
        add(sixth);
    }

    Font font = new Font("Times", Font.PLAIN, 16);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        double zgood = (double) (count - bad) / (double) count;
        double zbad = (double) bad / (double) count;
        double zmale = (double) (count - female) / (double) count;
        double zfemale = (double) female / (double) count;
        int radius = (int) (Math.min(getWidth() * 0.2, getHeight() * 0.2));
        String str;
        String str3 = String.format("%.3f", zgood);
        String str4 = String.format("%.3f", zbad);
        String str5 = String.format("%.3f", zmale);
        String str6 = String.format("%.3f", zfemale);
        g.setColor(Color.BLUE);
        g.fillArc(10, 10, radius * 2, radius * 2, 0, (int) (360 * zgood));

        g.setColor(Color.RED);
        g.fillArc(10, 10, radius * 2, radius * 2, (int) (360 * zgood - 1),
                (int) (361 - (int) 360 * zgood));

        g.setFont(font);
        g.setColor(Color.BLACK);
        str = "及格人数 -- " + String.format("%.2f",Double.parseDouble(str3) * 100) + "%";
        g.drawString(str, radius * 2 + 10, 60);

        g.setColor(Color.BLACK);
        str = "不及格人数 -- " + String.format("%.2f",Double.parseDouble(str4) * 100)+ "%";
        g.drawString(str, radius * 2, radius + 60);

        int radius2 = (int) (Math.min(getWidth() * 0.2, getHeight() * 0.2));
        String str2;
        g.setColor(Color.BLUE);
        g.fillArc(10, 40 + radius * 2, radius2 * 2, radius2 * 2, 0, (int) (360 * zfemale));

        g.setColor(Color.RED);
        g.fillArc(10, 40 + radius * 2, radius2 * 2, radius2 * 2, (int) (360 * zfemale - 1),
                (int) (361 - (int) 360 * zfemale));

        g.setFont(font);
        g.setColor(Color.BLACK);
        str2 = "女生人数 -- " +String.format("%.2f",Double.parseDouble(str6) * 100)  + "%";
        g.drawString(str2, radius2 * 2 + 10, 90 + radius2 * 2);

        g.setColor(Color.BLACK);
        str2 = "男生人数 -- " + String.format("%.2f",Double.parseDouble(str5) * 100) + "%";
        g.drawString(str2, radius2 * 2, radius + 90 + radius2 * 2);
    }
}
