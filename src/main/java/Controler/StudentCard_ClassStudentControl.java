package Controler;

import Common.Cookie;
import DAO.IstuDAO;
import FACTORY.DAOFactory;
import FirstLevelCard.StudentCard;
import VO.stu;

import java.util.ArrayList;

public class StudentCard_ClassStudentControl {
    private static int count;

    public static void setStudentCard_ClassStudentControl() {
        ArrayList<stu> stuList = new ArrayList<stu>();
        try {
            IstuDAO proxy = DAOFactory.getIstuDAO();
            count = proxy.getCount(Cookie.getUserID(), Cookie.getChoiceClass());
            stuList = DAOFactory.getIstuDAO().findstudent(Cookie.getUserID(), Cookie.getChoiceClass(), 0, count);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        StudentCard.getclassstudentcontrol().UpdateTable(stuList, count);
    }
}
