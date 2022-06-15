package org.example.mapper;

import com.google.common.base.Charsets;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.example.WebException;
import org.example.enums.WebResultCode;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

@Component
public class BaseWebMapper {
    private static final Logger _logger = LogManager.getLogger(BaseWebMapper.class);

    @Autowired
    private SqlSessionTemplate _sqlSessionTemplate;

    @Autowired
    private DataSource _dataSource;

    public void excuteNonQuery(String sql) throws WebException{
        this.executeNonQuery(sql, null, false);
    }

    public void executeNonQuery(String sql, List<String> params) throws WebException{
        this.executeNonQuery(sql, params, false);
    }

    public void executeNonQuery(String sql, List<String> params, boolean addArray) throws WebException{
        Configuration config = _sqlSessionTemplate.getConfiguration();
        _logger.info("executeNonQuery:" + sql);
        PreparedStatement pst = null;
        SqlSession session = getSqlSession();
        try {
            Connection connection = session.getConnection();
            pst = connection.prepareStatement(sql);
            int idex = 1;
            if (params != null){
                if (addArray) {
                    Array array = connection.createArrayOf("VARCHAR", params.toArray());
                    pst.setArray(idex, array);
                }
                else {
                    for (String param : params)
                    {
                        pst.setString(idex++, param);
                    }
                }
            }
            pst.execute();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            _logger.error("executeNonQuery: Message:" + e.getMessage() + "sql:" + sql);
            throw new WebException(WebResultCode.EXEC_SQL_ERROR, "excuteNonQuery error:" + e.getMessage());
        }
        finally {
            if (pst != null)
            {
                try{
                    pst.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            closeSqlSession(session);
        }
    }

    public List<Map<String, String>> selectQuery(String sql, List<String> params) throws  WebException{
        Configuration config = _sqlSessionTemplate.getConfiguration();
        _logger.info("selectQuery:" + sql);
        PreparedStatement pst = null;
        SqlSession session = getSqlSession();
        try {
            Connection connection = session.getConnection();
            pst = connection.prepareStatement(sql);
            int idex = 1;
            for (String param : params) {
                pst.setString(idex++, param);
            }
            ResultSet resultSet = pst.executeQuery();
            return getResultMaps(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            _logger.error("excuteNonQuery:" + sql);
            throw new WebException(WebResultCode.EXEC_SQL_ERROR, e.getMessage());
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            closeSqlSession(session);
        }
    }

    private static List<Map<String, String>> getResultMaps(ResultSet resultSet)
            throws SQLException {
        List<Map<String, String>> list = new ArrayList<>();
        ResultSetMetaData md = resultSet.getMetaData();//获取键名
        int columnCount = md.getColumnCount();//获取列的数量
        while (resultSet.next()) {
            Map<String, String> rowData = new HashMap<>();//声明Map
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), resultSet.getString(i));//获取键名及值
            }
            list.add(rowData);
        }
        return list;
    }

    /**
     * 获取sqlSession
     *
     * @return
     */
    public SqlSession getSqlSession() {
        return SqlSessionUtils.getSqlSession(_sqlSessionTemplate.getSqlSessionFactory(),
                _sqlSessionTemplate.getExecutorType(), _sqlSessionTemplate.getPersistenceExceptionTranslator());
    }

    /**
     * 关闭sqlSession
     *
     * @param session
     */
    public void closeSqlSession(SqlSession session) {
        SqlSessionUtils.closeSqlSession(session, _sqlSessionTemplate.getSqlSessionFactory());
    }

    /**
     * 使用ScriptRunner执行SQL脚本
     */
    public boolean RunSqlScript(String filePath) {
        //通过数据源获取数据库链接
        Connection connection = DataSourceUtils.getConnection(_dataSource);
        //创建脚本执行器

        //设置读取文件格式
        Resources.setCharset(Charsets.UTF_8);
        try {
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            //获取资源文件的字符输入流
            Reader reader = new FileReader(filePath);
            scriptRunner.runScript(reader);
            reader.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            _logger.error("doExecuteSql 执行失败!");
            return false;
        }
    }
}
