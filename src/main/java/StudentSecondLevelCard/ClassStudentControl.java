package StudentSecondLevelCard;

import Common.Cookie;
import Controler.StudentCard_ClassStudentControl;
import DAO.IstuDAO;
import FACTORY.DAOFactory;
import VO.stu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClassStudentControl extends JPanel {
    private int count;
    private static JTable table;
    private JButton addButton;
    private JButton removeButton;
    private JButton modifyButton;
    private JButton queryButtonById;
    private static JTextField queryFieldById;
//    private static ClassStudentControl instance;
//    public static synchronized ClassStudentControl getInstance(){
//        if(instance==null){
//            instance=new ClassStudentControl();
//        }
//        return instance;
//    }
//    public static synchronized ClassStudentControl closeInstance(){
//        if(instance!=null){
//            instance=null;
//        }
//        return instance;
//    }
//    public static synchronized ClassStudentControl RestartInstance(){
//        if(instance==null){
//            instance=new ClassStudentControl();
//        }else{
//            instance=new ClassStudentControl();
//        }
//        return instance;
//    }
    public ClassStudentControl() {
        setLayout(null);
        ArrayList<stu> stuList = new ArrayList<stu>();
        try {
            IstuDAO proxy = DAOFactory.getIstuDAO();
            count = proxy.getCount(Cookie.getUserID(), Cookie.getChoiceClass());
            stuList = DAOFactory.getIstuDAO().findstudent(Cookie.getUserID(), Cookie.getChoiceClass(), 0, count);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        createTable(stuList, count);
        addButton = new JButton("增 加");
        addButton.setBounds(10, 370, 100, 30);
        removeButton = new JButton("删 除");
        removeButton.setBounds(130, 370, 100, 30);
        modifyButton = new JButton("修 改");
        modifyButton.setBounds(250, 370, 100, 30);
        queryButtonById = new JButton("查 询(ID)");
        queryButtonById.setBounds(10, 430, 100, 30);
        queryFieldById = new JTextField();
        queryFieldById.setBounds(130, 430, 150, 30);
        add(addButton);
        add(removeButton);
        add(modifyButton);
        add(queryButtonById);
        add(queryFieldById);
        addButton.setActionCommand("add");
        removeButton.setActionCommand("remove");
        modifyButton.setActionCommand("modify");
        queryButtonById.setActionCommand("queryI");
        addButton.addActionListener(new ButtonClickListener());
        removeButton.addActionListener(new ButtonClickListener());
        modifyButton.addActionListener(new ButtonClickListener());
        queryButtonById.addActionListener(new ButtonClickListener());
    }

    private void createTable(ArrayList<stu> stuList, int count) {
        Color workbgcolor=new Color(240,240,240);
        setBackground(workbgcolor);
        String[] columnNames = { "学号", "姓名", "性别", "总体得分评价" };
        String[][] rowData = new String[count][4];
        int i = 0;
        for (stu u : stuList) {
            rowData[i][0] = u.getStuId();
            rowData[i][1] = u.getStuName();
            rowData[i][2] = u.getStuSex();
            rowData[i][3] = String.format("%.2f", u.getallgrade());
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
                new AddStudent();
            } else if (command.equals("remove")) {
                int count = table.getSelectedRow();// 获取你选中的行号（记录）
                String getId = table.getValueAt(count, 0).toString();// 读取你获取行号的某一列的值（也就是字段）
                // System.out.println(getname);
                try {
                    DAOFactory.getIstuDAO().deletestudent(Cookie.getUserID(), Cookie.getChoiceClass(), getId);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                StudentCard_ClassStudentControl.setStudentCard_ClassStudentControl();
            } else if (command.equals("modify")) {
                int count = table.getSelectedRow();
                String getID = table.getValueAt(count, 0).toString();
                new modiyStudent(getID);
            } else if (command.equals("queryI")) {
                String queryid = queryFieldById.getText();
                if (queryid.equals("")) {
                    String Text = "请输入学生学号";
                    JOptionPane.showMessageDialog(null, Text, "警告", JOptionPane.WARNING_MESSAGE);
                } else
                    new queryStudent(queryid);
            }
        }
    }

    public void UpdateTable(ArrayList<stu> stuList, int count) {
        String[] columnNames = { "学号", "姓名", "性别", "总体得分评价" };
        String[][] rowData = new String[count][4];
        int i = 0;
        for (stu u : stuList) {
            rowData[i][0] = u.getStuId();
            rowData[i][1] = u.getStuName();
            rowData[i][2] = u.getStuSex();
            rowData[i][3] = String.format("%.2f", u.getallgrade());
            i++;
        }
        DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames);// 创建指定表格列名和表格数据的表格模型
        table.setModel(tableModel);
    }
}
