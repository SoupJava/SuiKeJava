package DAO.PROXY;

import DAO.BaseDao;
import DAO.IMPL.UsersDAOImpl;
import DAO.IUsersDAO;
import DAO.IuserDAO;
import DBC.DatabaseConnection;
import VO.Users;
import org.apache.ibatis.session.SqlSession;

public class UsersDAOProxy implements IUsersDAO {
    private BaseDao db;
    private SqlSession session;
    IUsersDAO usersDao;

    public UsersDAOProxy() {
        try {
            this.db = new BaseDao();
            this.session=this.db.getSession();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        usersDao=this.session.getMapper(IUsersDAO.class);
    }

    @Override
    public boolean findLogin(Users user) throws Exception {
        boolean flag = false;
        try {
            flag = this.usersDao.findLogin(user); // 调用真实主题，完成操作
        } catch (Exception e) {
            throw e;
        } finally {
            this.db.close(session);
        }
        return flag;
    }

    @Override
    public void AddUser(String id, String password, String name, String tel, String mail) throws Exception {
        try {
            this.usersDao.AddUser(id, password, name, tel, mail);
        } catch (Exception e) {
            throw e;
        } finally {
            this.db.close(session);
        }
    }

    @Override
    public void ModUser(String name, String tel, String mail, String id) throws Exception {
        try {
            this.usersDao.ModUser(name, tel, mail, id);
        } catch (Exception e) {
            throw e;
        } finally {
            this.db.close(session);
        }
    }

    @Override
    public Users SelectUser(String id) throws Exception {
        Users UserList = null;
        try {
            UserList = this.usersDao.SelectUser(id);
        } catch (Exception e) {
            throw e;
        } finally {
            this.db.close(session);
        }
        return UserList;
    }

    @Override
    public void ModiyPassword(String id, String Password) throws Exception {
        try {
            this.usersDao.ModiyPassword(id, Password);
        } catch (Exception e) {
            throw e;
        } finally {
            this.db.close(session);
        }
    }
}
