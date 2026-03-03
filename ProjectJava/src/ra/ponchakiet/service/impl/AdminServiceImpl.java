package ra.ponchakiet.service.impl;

import org.mindrot.jbcrypt.BCrypt;
import ra.ponchakiet.dao.IAdminDao;
import ra.ponchakiet.dao.impl.AdminDaoImpl;
import ra.ponchakiet.model.Admin;
import ra.ponchakiet.service.IAdminService;

public class AdminServiceImpl implements IAdminService {
    private static IAdminDao adminDao = new AdminDaoImpl();
    @Override
    public Admin login(String username, String password) {
        Admin admin = adminDao.findAdminByUsername(username);
        if(admin!=null && BCrypt.checkpw(password,admin.getPassword())){
            return admin;
        }
        return null;
    }
}
