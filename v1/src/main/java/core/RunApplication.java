package core;

import cn.hutool.db.DbUtil;
import cn.hutool.db.meta.Table;
import core.DbTool.DbUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunApplication {
   /* public static Map<String,List<String>> getFullColumns(String tableName) {
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


        System.out.println("- "+tableName);
        System.out.println();
        System.out.println("|字段|类型|空|默认|注释|");
        System.out.println("|:----    |:-------    |:--- |-- -|------      |");
        for (int i = 0 ; i < columnFields.size() ; i++){
            System.out.println("|" + columnFields.get(i) + "|" + columntypes.get(i) + "|" + columnNulls.get(i) + "|" + columnDefaults.get(i) + "|" + columnComments.get(i) + "|");
        }
        System.out.println();
        System.out.println();
        System.out.println();

        return map;
    }*/
    public static void main(String[] args) {
       /* List<String> tableNames = getTableNames();
        int i =  0 ;
        for (String tableName : tableNames) {
            System.out.print(i++);
            if (i > 50){
                break;
            }else {
                getFullColumns(tableName) ;
            }
        }*/
        List<String> tableNames = DbUtils.getTableNames();
        int j = 0 ;
       /* for (String tableName : tableNames) {
            Map<String, List<String>> fullColumns = DbUtils.getFullColumns(tableName);
            System.out.println(j++ + "- "+tableName);
            System.out.println();
            System.out.println("|字段|类型|空|默认|注释|");
            System.out.println("|:----    |:-------    |:--- |-- -|------      |");
            for (int i = 0 ; i <  fullColumns.get("Field").size(); i++){
                System.out.println("|" + fullColumns.get("Field").get(i) + "|" + fullColumns.get("Type").get(i) + "|" + fullColumns.get("Null").get(i) + "|" + fullColumns.get("Default").get(i)+ "|" + fullColumns.get("Comment").get(i) + "|");
            }
            System.out.println();
            System.out.println();
            System.out.println();

        }*/
        Map<String, List<String>> advert = DbUtils.getFullColumns("advert");
        while (true){
            System.out.println("1");
        }
    }
}
