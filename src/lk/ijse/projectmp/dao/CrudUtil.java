package lk.ijse.projectmp.dao;

import lk.ijse.projectmp.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {
    private static PreparedStatement getPreparedStatement(String sql,Object...params) throws SQLException, ClassNotFoundException {
        PreparedStatement stm= DBConnection.getInstance().getConnection().prepareStatement(sql);

        for(int i=0; i< params.length; i++){
            stm.setObject(i+1,params[i]);
        }
        return stm;
    }

    public static boolean executeUpdate(String sql,Object...params) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(sql,params).executeUpdate()>0;
    }

    public static ResultSet executeQuery(String sql,Object...params) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(sql, params).executeQuery();
    }
}
