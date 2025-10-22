package DAO;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class BaseDao {
    private static SqlSessionFactory sqlSessionFactory;
    private static String CONFIG_PATH="mybatis.config.xml" ;
    protected SqlSession sqlsession;

    private synchronized void init() throws IOException {
        if(sqlSessionFactory == null){
            InputStream inputStream= Resources.getResourceAsStream(CONFIG_PATH);
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        }
    }
    public SqlSession getSession() throws IOException {
        init();
        return sqlSessionFactory.openSession();
    }
    public void close(SqlSession sqlSession){
        sqlSession.close();
    }
}
