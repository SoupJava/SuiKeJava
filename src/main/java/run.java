//import Common.DLL;
import View.Login;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.FrameBorderStyle;

import javax.swing.*;
import java.awt.*;

public class run {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BeautyEyeLNFHelper.frameBorderStyle = FrameBorderStyle.generalNoTranslucencyShadow;
                    org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();//启用BeautyEyeLNF
                    UIManager.put("RootPane.setupButtonVisible",false);
                } catch (Exception e) {
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }
        });
            new Login();
        }
    }