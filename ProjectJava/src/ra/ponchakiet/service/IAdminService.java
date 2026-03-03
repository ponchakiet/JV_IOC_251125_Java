package ra.ponchakiet.service;

import ra.ponchakiet.model.Admin;

public interface IAdminService {
    Admin login (String username, String password);
}
