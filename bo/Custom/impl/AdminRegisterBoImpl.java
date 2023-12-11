package bo.Custom.impl;

import bo.Custom.AdminRegisterBo;
import dao.Custom.AdminRegisterDao;
import dao.DaoFactory;
import dao.DaoType;
import dto.AdminRegisterDto;
import entity.AdminRegister;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRegisterBoImpl implements AdminRegisterBo {

    private AdminRegisterDao adminRegisterDao= DaoFactory.getInstance().getDao(DaoType.ADMIN);


    @Override
    public boolean saveAdmin(AdminRegisterDto a) throws SQLException {
        return adminRegisterDao.save(
                new AdminRegister(a.getId(),a.getUserName(),a.getEmail(),a.getPassword())
        );
    }

    @Override
    public ResultSet autoGenerateIdAdmin() throws SQLException {
        return adminRegisterDao.autoGenerateId();
    }

    @Override
    public ResultSet loginAdmin(AdminRegisterDto a) throws SQLException {
        return adminRegisterDao.login(new AdminRegister(a.getUserName(),a.getPassword()));
    }
}
