package StudentSecondLevelCard;

import Common.Cookie;
import DAO.IstuDAO;
import FACTORY.DAOFactory;
import FirstLevelCard.WorkCard;
import VO.stu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class HomeWorkControl extends JPanel {
    private JTextField address;
    private JRadioButton accordingName;
    private JRadioButton accordingId;
    private ButtonGroup group;
    private JCheckBox fruitName;
    private JCheckBox fruitId;
    private JButton OK;
    private JLabel addressLabel;
    private JLabel accordingLabel;
    private JLabel fruitLabel;
    private JTextArea OutFruit;
    private JScrollPane jsp;//滚动条
    String output;
    private static HomeWorkControl instance;
    public static synchronized HomeWorkControl getInstance(){
        if(instance==null){
            instance=new HomeWorkControl();
        }
        return instance;
    }
    public static synchronized HomeWorkControl closeInstance(){
        if(instance!=null){
            instance=null;
        }
        return instance;
    }
    public static synchronized HomeWorkControl RestartInstance(){
        if(instance==null){
            instance=new HomeWorkControl();
        }else{
            instance=new HomeWorkControl();
        }
        return instance;
    }
    private HomeWorkControl(){
        setLayout(null);
        Color workbgcolor=new Color(240,240,240);
        setBackground(workbgcolor);
        addressLabel=new JLabel("请输入文件夹目录");
        addressLabel.setFont(new java.awt.Font("Dialog", 1, 20));
        addressLabel.setBounds(10,10,300,30);
        address=new JTextField();
        address.setBounds(10,50,260,30);
        accordingLabel=new JLabel("请选择对比依据");
        accordingLabel.setFont(new java.awt.Font("Dialog", 1, 20));
        accordingLabel.setBounds(10,100,300,30);
        accordingName =new JRadioButton("名称",true);
        accordingName.setBounds(10,150,200,30);
        accordingId=new JRadioButton("学号");
        accordingId.setBounds(10,190,200,30);
        group=new ButtonGroup();
        group.add(accordingName);
        group.add(accordingId);
        fruitLabel = new JLabel("请选择生成数据项");
        fruitLabel.setFont(new java.awt.Font("Dialog", 1, 20));
        fruitLabel.setBounds(10,230,300,30);
        fruitName=new JCheckBox("名称",true);
        fruitName.setBounds(10,280,200,30);
        fruitId=new JCheckBox("学号");
        fruitId.setBounds(10,320,200,30);
        OK=new JButton("开 始 比 对");
        OK.setFont(new java.awt.Font("Dialog", 1, 20));
        OK.setBounds(10,390,200,50);
        OutFruit=new JTextArea();
        jsp=new JScrollPane(OutFruit);
        jsp.setBounds(300,10,430,430);
        add(address);
        add(accordingName);
        add(accordingId);
        add(fruitName);
        add(fruitId);
        add(OK);
        add(addressLabel);
        add(accordingLabel);
        add(fruitLabel);
        //add(OutFruit);
        add(jsp);
        OK.setActionCommand("OK");
        OK.addActionListener(new ButtonClickListener());
    }
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("OK")) {
                        new Thread(() -> {
                            OutFruit.setText("比对中...");
                            ArrayList<String> outPrint = new ArrayList<>();
                            HashMap<String, Boolean> Judge = new HashMap<>();
                            String path = address.getText();
                            File file = new File(path);
                            if (file.isDirectory()) {
                                File[] files = file.listFiles();
                                ArrayList<stu> stuList = new ArrayList<stu>();
                                try {
                                    stuList = DAOFactory.getIstuDoDAO().findstudent(Cookie.getUserID(), Cookie.getChoiceClass());
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                if (accordingName.isSelected()) {
                                    for (stu s : stuList) {
                                        Judge.put(s.getStuName(), true);
                                    }
                                    for (int i = 0; i < files.length; i++) {
                                        String filename = files[i].getName();
                                        try {
                                            IstuDAO proxy = DAOFactory.getIstuDAO();
                                            int count = proxy.getCount(Cookie.getUserID(), Cookie.getChoiceClass());
                                            ArrayList<stu> ssl = DAOFactory.getIstuDAO().findstudentByName(Cookie.getUserID(), Cookie.getChoiceClass(), filename.substring(0, filename.lastIndexOf("-")), 0, count);
                                            for (stu ss : ssl) {
                                                Judge.replace(ss.getStuName(), false);
                                            }
                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                    for (stu s : stuList) {
                                        if (Judge.get(s.getStuName())) {
                                            String outmessage = "";
                                            if (fruitName.isSelected()) {
                                                outmessage = outmessage + s.getStuName();
                                            }
                                            if (fruitId.isSelected()) {
                                                outmessage = outmessage + s.getStuId();
                                            }
                                            outPrint.add(outmessage);
                                        }
                                    }
                                } else {
                                    for (stu s : stuList) {
                                        Judge.put(s.getStuId(), true);
                                    }
                                    for (int i = 0; i < files.length; i++) {
                                        String filename = files[i].getName();
                                        try {
                                            stu s = DAOFactory.getIstuDoDAO().findstudentById(Cookie.getUserID(), Cookie.getChoiceClass(), filename.substring(0, filename.lastIndexOf("-")));
                                            Judge.replace(s.getStuId(), false);
                                        } catch (Exception ex) {
                                            //ex.printStackTrace();
                                        }
                                    }
                                    for (stu s : stuList) {
                                        if (Judge.get(s.getStuId())) {
                                            String outmessage = "";
                                            if (fruitName.isSelected()) {
                                                outmessage = outmessage + s.getStuName();
                                            }
                                            if (fruitId.isSelected()) {
                                                outmessage = outmessage + s.getStuId();
                                            }
                                            outPrint.add(outmessage);
                                        }
                                    }
                                }
                                output = "";
                                for (int i = 0; i < outPrint.size(); i++) {
                                    output = output + outPrint.get(i) + "\n";
                                }
                                OutFruit.setText(output);
                            }else {
                                String Text = "找不到目标文件夹";
                                JOptionPane.showMessageDialog(null, Text, "警告", JOptionPane.WARNING_MESSAGE);
                                OutFruit.setText("找不到路径...");
                            }
                        }).start();
                }

            }
        }
    }
