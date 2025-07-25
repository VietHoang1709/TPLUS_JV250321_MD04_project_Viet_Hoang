package dao.imp;

import dao.AdminDAO;
import entity.Admin;
import util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImp implements AdminDAO {
    @Override
    public Admin loginAdmin(String username, String password) {
        Connection conn = null;
        CallableStatement callSt = null;
        Admin admin = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_admin(?,?)}");
            callSt.setString(1, username);
            callSt.setString(2, password);
            ResultSet rs = callSt.executeQuery();
            if(rs.next()){
                admin = new Admin();
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return admin;
    }
}
