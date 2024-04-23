/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class UserDao extends BaseDao {

    PreparedStatement pstm;
    ResultSet rs;

    public boolean checkUser(String username, String password) {
        try {
            String strSelect = "select * from Users "
                    + "where username=? "
                    + "and password=?";
            pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, username);
            pstm.setString(2, password);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("checkUser:" + e.getMessage());
        }
        return false;
    }

    public String getRoleNameByUsername(String username) {
        String roleName = null;
        try {
            String sql = "SELECT Quyen.RoleName\n"
                    + "FROM Users\n"
                    + "JOIN Quyen_Users ON Users.UserID = Quyen_Users.UserID\n"
                    + "JOIN Quyen ON Quyen_Users.RoleID = Quyen.RoleID\n"
                    + "WHERE Users.Username = ?;";
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, username);
            rs = pstm.executeQuery();
            if (rs.next()) {
                roleName = rs.getString("RoleName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleName;
    }

    public static void main(String[] args) {
        UserDao u = new UserDao();
        u.checkUser("admin", "123");

    }

    public String getPosisionByUsername(String username) {
        String sql = "SELECT NhanVien.Chucvu FROM Users "
                + "JOIN NhanVien ON Users.UserID = NhanVien.UserID "
                + "WHERE Users.Username = ?";
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Chucvu");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }
    public String getFullNameByUsername(String username) {
        String sql = "SELECT fullName FROM users WHERE username = ?";
        try ( PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("fullName");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }
    
}
