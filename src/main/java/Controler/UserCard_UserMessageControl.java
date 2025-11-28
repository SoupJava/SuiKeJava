package Controler;

import Common.Cookie;
import FACTORY.DAOFactory;
import VO.Users;
import View.Menu;

public class UserCard_UserMessageControl {
    public static void setUserCard_UserMessageControl() {
        Users UserList = null;
        try {
            UserList = DAOFactory.getIUserDAO().SelectUser(Cookie.getUserID());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Menu.Getusercard().getusermessageControl().UpdateTable(UserList.getname(), UserList.gettel(),
                UserList.getmail());
    }
}
