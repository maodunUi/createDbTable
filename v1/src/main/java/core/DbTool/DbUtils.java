package core.DbTool;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbUtils {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://39.107.247.82:3306/youqi?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT&autoReconnect=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "&mjfpTLRoN8xTkWLEO$TgZZA#VLRCm";

    private static final String SQL = "SELECT * FROM ";// 数据库操作

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
        }
        return conn;
    }

    /**
     * 关闭数据库连接
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }

    /**
     * 获取数据库下的所有表名
     */
    public static List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        Connection conn = getConnection();
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData db = conn.getMetaData();
            //从元数据中获取到所有的表名
            rs = db.getTables(null, null, null, new String[] { "TABLE" });
            while(rs.next()) {
                tableNames.add(rs.getString(3));
            }
        } catch (SQLException e) {
        } finally {
            try {
                rs.close();
                closeConnection(conn);
            } catch (SQLException e) {
            }
        }
        return tableNames;
    }

    /**
     * 获取表中所有字段名称
     * @param tableName 表名
     * @return
     */
    public static List<String> getColumnNames(String tableName) {
        List<String> columnNames = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnNames.add(rsmd.getColumnName(i + 1));
            }
        } catch (SQLException e) {
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                }
            }
        }
        return columnNames;
    }

    /**
     * 获取表中所有字段类型
     * @param tableName
     * @return
     */
    public static List<String> getColumnTypes(String tableName) {
        List<String> columnTypes = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();

            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnTypes.add(rsmd.getColumnTypeName(i + 1));
            }
        } catch (SQLException e) {
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                }
            }
        }
        return columnTypes;
    }

    //通过表名得到一些列的信息
    public static Map<String,List<String>> getFullColumns(String tableName) {
        Map<String,List<String>> map = new HashMap<>() ;
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        List<String> columnFields = new ArrayList<>();//列名集合 Field
        List<String> columntypes = new ArrayList<>();//列名类型集合 Type
        List<String> columnNulls = new ArrayList<>();//列名是否为空集合 Null
        List<String> columnDefaults = new ArrayList<>();//列名默认集合 Default
        List<String> columnComments = new ArrayList<>();//列名注释集合 Comment
        List<String> columnCollations = new ArrayList<>();//列名字符集集合 Collation
        List<String> columnKeys = new ArrayList<>();//列名主键信息集合 key
        List<String> columnExtras = new ArrayList<>();//列名额外信息集合 Extra
        ResultSet rs = null;
        try {
            pStemt = conn.prepareStatement(tableSql);
            rs = pStemt.executeQuery("show full columns from " + tableName);
            while (rs.next()) {
                columnFields.add(rs.getString("Field")) ;
                columntypes.add(rs.getString("Type")) ;
                columnNulls.add(rs.getString("Null")) ;
                columnDefaults.add(rs.getString("Default")) ;
                columnComments.add(rs.getString("Comment"));
                columnCollations.add(rs.getString("Collation"));
                columnKeys.add(rs.getString("key"));
                columnExtras.add(rs.getString("Extra"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    closeConnection(conn);
                } catch (SQLException e) {
                }
            }
        }
        map.put("Field",columnFields) ;
        map.put("Type",columntypes) ;
        map.put("Null",columnNulls) ;
        map.put("Default",columnDefaults) ;
        map.put("Comment",columnComments) ;
        map.put("Collation",columnCollations) ;
        map.put("key",columnKeys) ;
        map.put("Extra",columnExtras) ;
        return map;
    }
}
