package appserver.dao;

import appserver.model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends BaseDao {

    /**
     * 根据用户名查找用户，如果存在就返回 UserModel，否则返回 null
     */
    public UserModel getUserByUsername(String username) {
        String sql = "SELECT username, password FROM user WHERE username = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new UserModel(rs.getString("username"), rs.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps, rs);
        }

        return null;
    }

    // 后续可以继续增加 addUser / updateUser / deleteUser 方法
}
