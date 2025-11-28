package FACTORY;

import DAO.IUsersDAO;
import DAO.IstuDAO;
import DAO.IstuDoDAO;
import DAO.IuserDAO;
import DAO.PROXY.StuDAOProxy;
import DAO.PROXY.StuDoDAOProxy;
import DAO.PROXY.UsersDAOProxy;
import DAO.PROXY.userDAOProxy;

public class DAOFactory {
    public static IUsersDAO getIUserDAO() {
        return new UsersDAOProxy();
    }

    public static IuserDAO getIuserDAO() {
        return new userDAOProxy();
    }

    public static IstuDAO getIstuDAO() {
        return new StuDAOProxy();
    }

    public static IstuDoDAO getIstuDoDAO() {
        return new StuDoDAOProxy();
    }
}
