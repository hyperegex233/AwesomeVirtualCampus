package appserver.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {

    private static HikariDataSource dataSource;

    static {
        // HikariCP 配置
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/vcamppus?useSSL=false&serverTimezone=UTC");
        config.setUsername("root");
        config.setPassword("123456");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setIdleTimeout(30000);
        config.setConnectionTimeout(30000);
        config.setMaxLifetime(1800000);

        dataSource = new HikariDataSource(config);
    }

    /** 获取连接 */
    protected Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /** 关闭 ResultSet、PreparedStatement、Connection */
    protected void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException ignored) {}
        try {
            if (ps != null) ps.close();
        } catch (SQLException ignored) {}
        try {
            if (conn != null) conn.close();
        } catch (SQLException ignored) {}
    }

    /** 关闭连接池 */
    public static void closeDataSource() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
