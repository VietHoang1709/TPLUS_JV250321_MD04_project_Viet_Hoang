package business;

import entity.Admin;

import java.util.List;

public interface AdminBusiness {
    Admin login(String username, String password);
}
