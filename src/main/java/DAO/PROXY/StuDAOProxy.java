package DAO.PROXY;

import DAO.BaseDao;
import DAO.IMPL.StuDAOImpl;
import DAO.IstuDAO;
import DBC.DatabaseConnection;
import VO.stu;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;

public class StuDAOProxy implements IstuDAO {
    private BaseDao db;
    private SqlSession session;
    IstuDAO stuDao;
    public StuDAOProxy() {
        try {
            this.db = new BaseDao();
            this.session=this.db.getSession();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        stuDao=this.session.getMapper(IstuDAO.class);
    }

    @Override
    // 查看该表信息
    public ArrayList<stu> findstudent(String id, String classname, int startindex, int recordnum) throws Exception {
        ArrayList<stu> stuList;
        try {
            stuList = this.stuDao.findstudent(id, classname, startindex, recordnum);
        } catch (Exception e) {
            throw e;
        } finally {
            this.db.close(session);
        }
        return stuList;
    }

    @Override
    // 根据学号ID查询学生信息
    public stu findstudentByID(String id, String classname, String StuId) throws Exception {
        stu u = null;
        try {
            u =this.stuDao.findstudentByID(id, classname, StuId);
        } catch (Exception e) {
            throw e;
        } finally {
            this.db.close(session);
        }
        return u;
    }

    @Override
    // 根据学生姓名查询学生信息
    public ArrayList<stu> findstudentByName(String id, String classname, String name, int startindex, int recordnum)
            throws Exception {
        ArrayList<stu> stuList;
        try {
            stuList =this.stuDao.findstudentByName(id, classname, name, startindex, recordnum);
        } catch (Exception e) {
            throw e;
        } finally {
            this.db.close(session);
        }
        return stuList;
    }

    @Override
    // 获取所有学生信息的数目（分页用）
    public int getCount(String id, String classname) throws Exception {
        int count;
        try {
            count = this.stuDao.getCount(id, classname);
        } catch (Exception e) {
            throw e;
        } finally {
            this.db.close(session);
        }
        return count;
    }

    @Override
    // 根据学生名称获取产品信息的数目（分页用）
    public int getCountByName(String id, String classname, String name) throws Exception {
        int count;
        try {
            count = this.stuDao.getCountByName(id, classname, name);
        } catch (Exception e) {
            throw e;
        } finally {
            this.db.close(session);
        }

        return count;
    }

    @Override
    // 新增学生信息
    public boolean addstudent(String id, String classname, stu s) throws Exception {
        boolean flag = false;
        try {
            flag = this.stuDao.addstudent(id, classname, s);
        } catch (Exception e) {
            throw e;
        } finally {
            this.db.close(session);
        }
        return flag;
    }

    @Override
    // 修改学生信息
    public boolean updatestudent(String id, String classname, stu s, String afterId) throws Exception {
        boolean flag = false;
        try {
            flag = this.stuDao.updatestudent(id, classname, s, afterId);
        } catch (Exception e) {
            throw e;
        } finally {
            this.db.close(session);
        }
        return flag;
    }

    @Override
    // 删除学生信息
    public boolean deletestudent(String id, String classname, String StuId) throws Exception {
        boolean flag = false;
        try {
            flag = this.stuDao.deletestudent(id, classname, StuId);
        } catch (Exception e) {
            throw e;
        } finally {
            this.db.close(session);
        }
        return flag;
    }
}
