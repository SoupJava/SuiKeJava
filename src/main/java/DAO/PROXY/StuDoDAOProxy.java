package DAO.PROXY;

import DAO.BaseDao;
import DAO.IstuDoDAO;
import VO.stu;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;

public class StuDoDAOProxy implements IstuDoDAO {
    private BaseDao db;
    private SqlSession session;
    IstuDoDAO stuDoDao;

    public StuDoDAOProxy() {
        try {
            this.db = new BaseDao();
            this.session=this.db.getSession();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        stuDoDao=this.session.getMapper(IstuDoDAO.class);
    }

    @Override
    public ArrayList<stu> findstudent(String id, String classname) throws Exception {
        ArrayList<stu> stuList;
        try {
            stuList = this.stuDoDao.findstudent(id, classname);
        } catch (Exception e) {
            throw e;
        } finally {
            this.db.close(session);
        }
        return stuList;
    }

    @Override
    public boolean qqDoStudent(String id, String classname, String StuId, int kqtime, int qqtime, boolean ynqq)
            throws Exception {
        boolean flag = false;
        try {
            flag = this.stuDoDao.qqDoStudent(id, classname, StuId, kqtime, qqtime, ynqq);
        } catch (Exception e) {
            throw e;
        } finally {
            this.db.close(session);
        }
        return flag;
    }

    @Override
    public stu findstudentById(String id, String classname, String StuId) throws Exception {
        stu u = null;
        try {
            u = this.stuDoDao.findstudentById(id, classname, StuId);
        } catch (Exception e) {
            throw e;
        } finally {
            this.db.close(session);
        }
        return u;
    }

    @Override
    public String RandomSampling(String id, String classname, int num) throws Exception {
        String name;
        try {
            name = this.stuDoDao.RandomSampling(id, classname, num);
        } catch (Exception e) {
            throw e;
        } finally {
            this.db.close(session);
        }
        return name;
    }

    @Override
    public double GetStuGrade(String id, String classname, String StuId) throws Exception {
        double grade;
        try {
            grade = this.stuDoDao.GetStuGrade(id, classname, StuId);
        } catch (Exception e) {
            throw e;
        } finally {
            this.db.close(session);
        }
        return grade;
    }

    @Override
    public boolean SetTWStu(String id, String classname, String StuId, double allgrade, int twtime) throws Exception {
        boolean flag = false;
        try {
            flag = this.stuDoDao.SetTWStu(id, classname, StuId, allgrade, twtime);
        } catch (Exception e) {
            throw e;
        } finally {
            this.db.close(session);
        }
        return flag;
    }

    @Override
    public int RandomNum(int min, int max) throws Exception {
        int num = 0;
        try {
            num = this.stuDoDao.RandomNum(min, max);
        } catch (Exception e) {
            throw e;
        } finally {
            this.db.close(session);
        }
        return num;
    }
}
