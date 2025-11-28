package FirstLevelCard;

import Controler.UserCard_UserMessageControl;
import StudentSecondLevelCard.ClassMessageControl;
import StudentSecondLevelCard.ClassStudentControl;
import StudentSecondLevelCard.HomeWorkControl;
import UserSecondLevelCard.UserClassControl;
import UserSecondLevelCard.UserMessageControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserCard extends JPanel {
    private static JButton messageControl;
    private static JButton MyClass;
    private static JPanel UserDoPanel;
    private static CardLayout UserCardLayout;
    private static UserMessageControl usermessageControl;
    private static UserClassControl userclassControl;
    private static UserCard instance;
    public static synchronized UserCard getInstance(){
        if(instance==null){
            instance=new UserCard();
        }
        return instance;
    }
    public static synchronized UserCard closeInstance(){
        if(instance!=null){
            instance=null;
        }
        return instance;
    }
    public static synchronized UserCard RestartInstance(){
        instance=new UserCard();
        return instance;
    }

    private UserCard() {
        setLayout(null);
        setBackground(Color.WHITE);
        messageControl = new JButton();
        MyClass = new JButton();
        UserDoPanel = new JPanel();
        UserCardLayout = new CardLayout();
        UserDoPanel.setLayout(UserCardLayout);
        UserDoPanel.setBackground(Color.WHITE);
        messageControl = new JButton("信息管理");
        messageControl.setFont(new java.awt.Font("Dialog", 1, 15));
        messageControl.setBounds(10, 100, 130, 60);
        messageControl.addActionListener(new UserMenuListener());
        messageControl.setActionCommand("message");
        MyClass = new JButton("我的课表");
        MyClass.setFont(new java.awt.Font("Dialog", 1, 15));
        MyClass.setBounds(10, 20, 130, 60);
        MyClass.addActionListener(new UserMenuListener());
        MyClass.setActionCommand("class");
        add(MyClass);
        add(messageControl);
        UserAddCard(UserDoPanel);
        UserDoPanel.setBounds(150, 0, 770, 520);
        add(UserDoPanel);
    }

    private void UserAddCard(JPanel userDoPanel) {
        userclassControl = new UserClassControl();
        userDoPanel.add(userclassControl, "userclassControl");
        usermessageControl = new UserMessageControl();
        userDoPanel.add(usermessageControl, "usermessageControl");
    }

    public UserMessageControl getusermessageControl() {
        return usermessageControl;
    }

    public UserClassControl getuserclassControl() {
        return userclassControl;
    }

    private class UserMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("message")) {
                // System.out.println("第一张卡片");
                UserCard_UserMessageControl.setUserCard_UserMessageControl();
                UserCardLayout.show(UserDoPanel, "usermessageControl");
            } else if (command.equals("class")) {
                // UserCard_UserClassControl.setUserCard_UserClassControl();
                userclassControl = new UserClassControl();
                UserDoPanel.add(userclassControl, "userclassControl");
                UserCardLayout.show(UserDoPanel, "userclassControl");
            }
        }
    }
}
