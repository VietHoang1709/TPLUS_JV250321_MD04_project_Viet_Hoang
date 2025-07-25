package dao;

import entity.Admin;

import java.util.List;

public interface AdminDAO {
    Admin loginAdmin(String username, String password);
}
