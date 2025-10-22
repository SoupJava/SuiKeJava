package FirstLevelCard;

import StudentSecondLevelCard.ClassMessageControl;
import StudentSecondLevelCard.ClassStudentControl;
import StudentSecondLevelCard.HomeWorkControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class StudentCard extends JPanel {
    private static JButton ClassMessage;
    private static JButton StudentControl;
    private static JButton HomeworkControl;
    private static JPanel ClassDoPanel;
    private static CardLayout classCardLayout;
    private static ClassMessageControl classmessagecontrol;
    private static ClassStudentControl classstudentcontrol;
    private static HomeWorkControl homeworkcontrol;
    private static StudentCard instance;
    public static synchronized StudentCard getInstance(){
        if(instance==null){
            instance=new StudentCard();
        }
        return instance;
    }
    public static synchronized StudentCard closeInstance(){
        if(instance!=null){
            instance=null;
        }
        return instance;
    }
    public static synchronized StudentCard RestartInstance(){
            instance=new StudentCard();
//            ClassMessageControl.RestartInstance();
//            ClassStudentControl.RestartInstance();
            HomeWorkControl.RestartInstance();
        return instance;
    }
    private StudentCard() {
        setLayout(null);
        setBackground(Color.WHITE);
        ClassMessage = new JButton();
        StudentControl = new JButton();
        HomeworkControl=new JButton();
        ClassDoPanel = new JPanel();
        classCardLayout = new CardLayout();
        ClassDoPanel.setLayout(classCardLayout);
        ClassDoPanel.setBackground(Color.WHITE);
        ClassMessage = new JButton("课程信息");
        ClassMessage.setFont(new java.awt.Font("Dialog", 1, 15));
        ClassMessage.setBounds(10, 20, 130, 60);
        ClassMessage.addActionListener(new ClassMenuListener());
        ClassMessage.setActionCommand("message");
        StudentControl = new JButton("学生信息");
        StudentControl.setFont(new java.awt.Font("Dialog", 1, 15));
        StudentControl.setBounds(10, 100, 130, 60);
        StudentControl.addActionListener(new ClassMenuListener());
        StudentControl.setActionCommand("class");
        HomeworkControl = new JButton("作业管理");
        HomeworkControl.setFont(new java.awt.Font("Dialog", 1, 15));
        HomeworkControl.setBounds(10, 180, 130, 60);
        HomeworkControl.addActionListener(new ClassMenuListener());
        HomeworkControl.setActionCommand("HomeWork");
        add(ClassMessage);
        add(StudentControl);
        add(HomeworkControl);
        ClassAddCard(ClassDoPanel);
        ClassDoPanel.setBounds(150, 0, 770, 520);
        add(ClassDoPanel);
    }

    private void ClassAddCard(JPanel ClassDoPanel) {
        classmessagecontrol = new ClassMessageControl();
        ClassDoPanel.add(classmessagecontrol, "classmessageControl");
        classstudentcontrol =new ClassStudentControl();
        ClassDoPanel.add(classstudentcontrol, "classstudentControl");
        homeworkcontrol=HomeWorkControl.getInstance();
        ClassDoPanel.add(homeworkcontrol,"homeworkControl");
    }

    public static ClassMessageControl getclassmessagecontrol() {
        return classmessagecontrol;
    }

    public static ClassStudentControl getclassstudentcontrol() {
        return classstudentcontrol;
    }

    private class ClassMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("message")) {
                classmessagecontrol =new ClassMessageControl();
                ClassDoPanel.add(classmessagecontrol, "classmessageControl");
                classCardLayout.show(ClassDoPanel, "classmessageControl");
            } else if (command.equals("class")) {
                classstudentcontrol =new ClassStudentControl();
                ClassDoPanel.add(classstudentcontrol, "classstudentControl");
                classCardLayout.show(ClassDoPanel, "classstudentControl");
            }else if (command.equals("HomeWork")) {
                homeworkcontrol = HomeWorkControl.getInstance();
                ClassDoPanel.add(homeworkcontrol, "homeworkControl");
                classCardLayout.show(ClassDoPanel, "homeworkControl");
            }
        }
    }
}
