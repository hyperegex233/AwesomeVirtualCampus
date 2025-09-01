package appserver.service;

import appserver.dao.UserDao;
import appserver.model.UserModel;

public class UserService {
    private final UserDao userDao = new UserDao();

    public boolean login(UserModel user) {
        UserModel storedUser = userDao.getUserByUsername(user.getUsername());
        if (storedUser == null) return false; // 用户不存在
        return storedUser.getPassword().equals(user.getPassword());
    }
}
