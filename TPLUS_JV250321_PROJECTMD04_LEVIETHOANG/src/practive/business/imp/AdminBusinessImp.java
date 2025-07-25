package business.imp;

import business.AdminBusiness;
import dao.AdminDAO;
import dao.imp.AdminDaoImp;
import entity.Admin;

import java.util.List;

public class AdminBusinessImp implements AdminBusiness {
    private AdminDAO adminDAO;

    public AdminBusinessImp() {
        adminDAO = new AdminDaoImp();
    }

    @Override
    public Admin login(String username, String password) {
        return adminDAO.loginAdmin(username, password);
    }
}
