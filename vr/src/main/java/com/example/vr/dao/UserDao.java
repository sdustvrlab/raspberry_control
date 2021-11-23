package com.example.vr.dao;


import com.example.vr.bean.User;
import com.example.vr.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    com.example.vr.bean.User user=null;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean RegisterUser(String username,String password)
    {
        int update = 0;
        try {
            con = JDBCUtil.getConnection();
            String sql = "insert into t_user(username,password) values(?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1,username );
            ps.setString(2,password);
            update = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release(con, ps);
        }
        return update == 1;
    }


    public User LoginUser(String username, String password)
    {
        try {
            con = JDBCUtil.getConnection();
            String sql = "select * from t_user where username=?and password=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if (rs.next())
            {
                user = new com.example.vr.bean.User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getNString("username"));
                user.setPassword(rs.getNString("password"));
                user.setAdmin(rs.getInt("admin"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtil.release(con,ps,rs);
        }
        return user;
    }
}
