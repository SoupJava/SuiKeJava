package FirstLevelCard;

import WorkSecondLevelCard.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkCard extends JPanel {
    private static JButton sjExtract;
    private static JButton sxExtract;
    private static JButton kqExtract;
    private static JButton ndExtract;
    private static JButton lxExtract;
    private static JPanel WorkDoPanel;
    private static CardLayout workCardLayout;
    private static sjExtractControl sjextractcontrol;
    private static sxExtractControl sxextractcontrol;
    private static kqExtractControl kqextractcontrol;
    private static ndExtractControl ndextractcontrol;

    private static lxExtractControl lxextractcontrol;
    private static WorkCard instance;
    public static synchronized WorkCard getInstance(){
        if(instance==null){
            instance=new WorkCard();
        }
        return instance;
    }
    public static synchronized WorkCard closeInstance(){
        if(instance!=null){
            instance=null;
        }
        return instance;
    }
    public static synchronized WorkCard RestartInstance(){
        instance=new WorkCard();
        return instance;
    }

    private WorkCard() {
        setLayout(null);
        setBackground(Color.WHITE);
        sjExtract = new JButton();
        sxExtract = new JButton();
        kqExtract = new JButton();
        ndExtract = new JButton();
        WorkDoPanel = new JPanel();
        workCardLayout = new CardLayout();
        WorkDoPanel.setLayout(workCardLayout);
        WorkDoPanel.setBackground(Color.WHITE);
        sjExtract = new JButton("随机抽取");
        sjExtract.setFont(new java.awt.Font("Dialog", 1, 15));
        sjExtract.setBounds(10, 20, 130, 60);
        sjExtract.addActionListener(new WorkMenuListener());
        sjExtract.setActionCommand("sjwork");
        sxExtract = new JButton("顺序抽取");
        sxExtract.setFont(new java.awt.Font("Dialog", 1, 15));
        sxExtract.setBounds(10, 100, 130, 60);
        sxExtract.addActionListener(new WorkMenuListener());
        sxExtract.setActionCommand("sxwork");
        kqExtract = new JButton("考勤抽取");
        kqExtract.setFont(new java.awt.Font("Dialog", 1, 15));
        kqExtract.setBounds(10, 180, 130, 60);
        kqExtract.addActionListener(new WorkMenuListener());
        kqExtract.setActionCommand("kqwork");
        ndExtract = new JButton("难度抽取");
        ndExtract.setFont(new java.awt.Font("Dialog", 1, 15));
        ndExtract.setBounds(10, 260, 130, 60);
        ndExtract.addActionListener(new WorkMenuListener());
        ndExtract.setActionCommand("ndwork");
        lxExtract = new JButton("乱序抽取");
        lxExtract.setFont(new java.awt.Font("Dialog", 1, 15));
        lxExtract.setBounds(10, 340, 130, 60);
        lxExtract.addActionListener(new WorkMenuListener());
        lxExtract.setActionCommand("lxwork");
        add(sjExtract);
        add(sxExtract);
        add(kqExtract);
        add(ndExtract);
        add(lxExtract);
        WorkAddCard(WorkDoPanel);
        WorkDoPanel.setBounds(150, 0, 770, 520);
        add(WorkDoPanel);
    }

    private void WorkAddCard(JPanel WorkDoPanel) {
        sjextractcontrol = new sjExtractControl();
        WorkDoPanel.add(sjextractcontrol, "sjextractControl");
        sxextractcontrol = new sxExtractControl();
        WorkDoPanel.add(sxextractcontrol, "sxextractControl");
        kqextractcontrol = new kqExtractControl();
        WorkDoPanel.add(kqextractcontrol, "kqextractControl");
        ndextractcontrol = new ndExtractControl();
        WorkDoPanel.add(ndextractcontrol, "ndextractControl");
        lxextractcontrol = new lxExtractControl();
        WorkDoPanel.add(lxextractcontrol, "lxextractControl");
    }

    public static sjExtractControl getsjextractcontrol() {
        return sjextractcontrol;
    }

    public static sxExtractControl getsxextractcontrol() {
        return sxextractcontrol;
    }

    public static kqExtractControl getkqextractcontrol() {
        return kqextractcontrol;
    }

    public static ndExtractControl getndextractcontrol() {
        return ndextractcontrol;
    }

    public static lxExtractControl getlxextractcontrol() {
        return lxextractcontrol;
    }
    private class WorkMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("sjwork")) {
                sjextractcontrol = new sjExtractControl();
                WorkDoPanel.add(sjextractcontrol, "sjextractControl");
                workCardLayout.show(WorkDoPanel, "sjextractControl");
            } else if (command.equals("sxwork")) {
                sxextractcontrol = new sxExtractControl();
                WorkDoPanel.add(sxextractcontrol, "sxextractControl");
                workCardLayout.show(WorkDoPanel, "sxextractControl");
            } else if (command.equals("kqwork")) {
                kqextractcontrol = new kqExtractControl();
                WorkDoPanel.add(kqextractcontrol, "kqextractControl");
                workCardLayout.show(WorkDoPanel, "kqextractControl");
            } else if (command.equals("ndwork")) {
                ndextractcontrol = new ndExtractControl();
                WorkDoPanel.add(ndextractcontrol, "ndextractControl");
                workCardLayout.show(WorkDoPanel, "ndextractControl");
            } else if (command.equals("lxwork")) {
                lxextractcontrol = new lxExtractControl();
                WorkDoPanel.add(lxextractcontrol, "lxextractControl");
                workCardLayout.show(WorkDoPanel, "lxextractControl");
            }
        }
    }
}
