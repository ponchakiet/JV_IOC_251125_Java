package ra.ponchakiet.dao;

import ra.ponchakiet.model.Admin;

public interface IAdminDao {
    Admin findAdminByUsername(String username);
}
