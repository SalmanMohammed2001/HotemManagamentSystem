package dao.Custom;

import dao.CrudDao;
import entity.AdminRegister;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface AdminRegisterDao extends CrudDao<AdminRegister,String> {

    ResultSet autoGenerateId() throws SQLException;
    ResultSet login(AdminRegister a) throws SQLException;
}
