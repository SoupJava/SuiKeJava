package Controler;

import Common.Cookie;
import DAO.IuserDAO;
import FACTORY.DAOFactory;
import VO.user;
import View.Menu;

import java.util.ArrayList;

public class UserCard_UserClassControl {
    private static int count;

    public static void setUserCard_UserClassControl() {
        ArrayList<user> userList = new ArrayList<user>();
        try {
            IuserDAO proxy = DAOFactory.getIuserDAO();
            count = proxy.getCount(Cookie.getUserID());
            //System.out.println(count);
            userList = DAOFactory.getIuserDAO().OutStu(Cookie.getUserID(),
                    0, count);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Menu.Getusercard().getuserclassControl().UpdateTable(userList, count);
    }
}
