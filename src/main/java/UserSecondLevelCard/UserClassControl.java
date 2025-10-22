package UserSecondLevelCard;

import Common.Cookie;
import Controler.UserCard_UserClassControl;
import DAO.IuserDAO;
import FACTORY.DAOFactory;
import FirstLevelCard.StudentCard;
import VO.user;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserClassControl extends JPanel {
    private int count;
    private static JTable table;
    private JButton addButton;
    private JButton removeButton;
    private JButton modifyButton;
    private JButton queryButton;
    private JButton selectButton;
    private static JTextField queryField;

    public UserClassControl() {
        setLayout(null);
        ArrayList<user> userList = new ArrayList<user>();
        try {
            IuserDAO proxy = DAOFactory.getIuserDAO();
            count = proxy.getCount(Cookie.getUserID());
            System.out.println(count);
            userList = DAOFactory.getIuserDAO().OutStu(Cookie.getUserID(),
                    0, count);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        createTable(userList, count);
        addButton = new JButton("增 加");
        addButton.setBounds(10, 370, 100, 30);
        removeButton = new JButton("删 除");
        removeButton.setBounds(130, 370, 100, 30);
        modifyButton = new JButton("修 改");
        modifyButton.setBounds(250, 370, 100, 30);
        selectButton = new JButton("选 择");
        selectButton.setBounds(370, 370, 100, 30);
        queryButton = new JButton("查询(课程名)");
        queryButton.setBounds(10, 430, 120, 30);
        queryField = new JTextField();
        queryField.setBounds(150, 430, 200, 30);
        add(addButton);
        add(removeButton);
        add(modifyButton);
        add(selectButton);
        add(queryButton);
        add(queryField);
        addButton.setActionCommand("add");
        removeButton.setActionCommand("remove");
        modifyButton.setActionCommand("modify");
        selectButton.setActionCommand("select");
        queryButton.setActionCommand("query");
        addButton.addActionListener(new ButtonClickListener());
        removeButton.addActionListener(new ButtonClickListener());
        modifyButton.addActionListener(new ButtonClickListener());
        selectButton.addActionListener(new ButtonClickListener());
        queryButton.addActionListener(new ButtonClickListener());
    }

    private void createTable(ArrayList<user> userList, int count) {
        Color workbgcolor=new Color(240,240,240);
        setBackground(workbgcolor);
        String[] columnNames = { "课程名称", "创建时间", "课程描述" };
        String[][] rowData = new String[count][3];
        int i = 0;
        for (user u : userList) {
            rowData[i][0] = u.getclassname();
            rowData[i][1] = u.getbegintime();
            int num = u.getexplain().length();
            if (num >= 15) {
                num = 15;
            }
            rowData[i][2] = u.getexplain().substring(0, num) + "...";
            i++;
        }
        DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames);// 创建指定表格列名和表格数据的表格模型
        table = new JTable(tableModel) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大小
        head.setFont(new Font("楷体", Font.PLAIN, 18));// 设置表格字体
        JScrollPane scrollPane = new JScrollPane(table);// 创建显示表格的滚动面板
        scrollPane.setViewportView(table);
        scrollPane.setBounds(10, 10, 750, 350);
        add(scrollPane);
    }

    private static class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("add")) {
                new AddClass();
            } else if (command.equals("remove")) {
                int count = table.getSelectedRow();// 获取你选中的行号（记录）
                String getname = table.getValueAt(count, 0).toString();// 读取你获取行号的某一列的值（也就是字段）
                // System.out.println(getname);
                try {
                    DAOFactory.getIuserDAO().DelStu(getname, Cookie.getUserID());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                UserCard_UserClassControl.setUserCard_UserClassControl();
            } else if (command.equals("modify")) {
                int count = table.getSelectedRow();
                String getname = table.getValueAt(count, 0).toString();
                new modiyClass(getname);
            } else if (command.equals("query")) {
                String queryname = queryField.getText();
                if (queryname.equals("")) {
                    String Text = "请输入课程名";
                    JOptionPane.showMessageDialog(null, Text, "警告", JOptionPane.WARNING_MESSAGE);
                } else
                    new queryClass(queryname);
            } else if (command.equals("select")) {
                int count = table.getSelectedRow();
                String getname = table.getValueAt(count, 0).toString();
                Cookie.setChoiceClass(getname);
                try {
                    int allnumber = DAOFactory.getIstuDAO().getCount(Cookie.getUserID(), Cookie.getChoiceClass());
                    // System.out.println(allnumber);
                    ArrayList<Double> mark = new ArrayList<Double>();
                    for (int i = 0; i < allnumber; i++) {
                        mark.add(10.0);
                    }
                    Cookie.setMark(mark);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                StudentCard.RestartInstance();
                String Text = "课程名: '" + getname + "' 选择成功！";
                JOptionPane.showMessageDialog(null, Text, "提示", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    public void UpdateTable(ArrayList<user> userList, int count) {
        String[] columnNames = { "课程名称", "创建时间", "课程描述" };
        String[][] rowData = new String[count][3];
        int i = 0;
        for (user u : userList) {
            rowData[i][0] = u.getclassname();
            rowData[i][1] = u.getbegintime();
            int num = u.getexplain().length();
            if (num >= 15) {
                num = 15;
            }
            rowData[i][2] = u.getexplain().substring(0, num) + "...";
            i++;
        }
        DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames);// 创建指定表格列名和表格数据的表格模型
        table.setModel(tableModel);
    }
}
