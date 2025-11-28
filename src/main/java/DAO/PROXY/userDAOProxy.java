package DAO.PROXY;

import DAO.BaseDao;
import DAO.IuserDAO;
import VO.user;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;

public class userDAOProxy implements IuserDAO {
    private BaseDao db;
    private SqlSession session;
    IuserDAO userDao;

    public userDAOProxy() {
        try {
            this.db = new BaseDao();
            this.session=this.db.getSession();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        userDao=this.session.getMapper(IuserDAO.class);
    }

    @Override
    public int getCount(String id) throws Exception {
        int count;
        try {
            count = this.userDao.getCount(id);
        } catch (Exception e) {
            throw e;
        } finally {
            this.db.close(session);
        }
        return count;
    }

    @Override
    public ArrayList<user> OutStu(String userid, int startindex, int recordnum) throws Exception {
        ArrayList<user> userList;
        try {
            userList = this.userDao.OutStu(userid, startindex, recordnum);
        } catch (Exception ex) {
            throw ex;
        } finally {
            this.db.close(session);
        }
        return userList;
    }

    @Override
    public user SecStuByName(String id, String classname) throws Exception {
        user u = null;
        try {
            u = this.userDao.SecStuByName(id, classname);
        } catch (Exception ex) {
            throw ex;
        } finally {
            this.db.close(session);
        }
        return u;
    }

    @Override
    public boolean UpdateStu(user u, String id, String aftername) throws Exception {
        boolean flag = false;
        try {
            flag = this.userDao.UpdateStu(u, id, aftername);
        } catch (Exception ex) {
            throw ex;
        } finally {
            this.db.close(session);
        }
        return flag;
    }

    @Override
    public boolean AddStu(String id, String classname, String begintime, String explain) throws Exception {
        boolean flag = false;
        try {
            flag = this.userDao.AddStu(id, classname, begintime, explain);
        } catch (Exception ex) {
            throw ex;
        } finally {
            this.db.close(session);
        }
        return flag;
    }

    @Override
    public boolean DelStu(String classname, String id) throws Exception {
        boolean flag = false;
        try {
            flag = this.userDao.DelStu(classname, id);
        } catch (Exception ex) {
            throw ex;
        } finally {
            this.db.close(session);
        }
        return flag;
    }
}
