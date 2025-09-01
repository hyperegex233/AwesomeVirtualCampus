package appserver.dao;

import appserver.model.UserModel;

import java.util.HashMap;
import java.util.Map;

public class UserDao {

    // 模拟数据库里的用户名和密码
    private static final Map<String, String> USER_DB = new HashMap<>();
    static {
        USER_DB.put("admin", "123456");
        USER_DB.put("user1", "password");
    }

    /**
     * 根据用户名查找用户，如果存在就返回 UserModel，否则返回 null
     */
    public UserModel getUserByUsername(String username) {
        String password = USER_DB.get(username);
        if (password != null) {
            return new UserModel(username, password);
        } else {
            return null;
        }
    }
}

