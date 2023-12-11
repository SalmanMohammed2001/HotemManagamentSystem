package dao.Custom.impl;

import dao.CrudUtil;
import dao.Custom.AdminRegisterDao;
import db.DBConnection;
import entity.AdminRegister;

import java.sql.*;

public class AdminRegisterDaoImpl implements AdminRegisterDao {
    @Override
    public boolean save(AdminRegister admin) throws SQLException {
        return CrudUtil.execute("insert into AdminRegister VALUES (?,?,?,?)",
                admin.getId(), admin.getUserName(), admin.getEmail(), admin.getPassword());
    }

    @Override
    public boolean delete(String searchText) throws SQLException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(AdminRegister adminRegister) throws SQLException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public ResultSet autoGenerateId() throws SQLException {

        ResultSet resultSet = CrudUtil.execute("SELECT id FROM AdminRegister ORDER BY id DESC LIMIT 1");

        return resultSet;


    }

    @Override
    public ResultSet login(AdminRegister a) throws SQLException {

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM AdminRegister WHERE userName=? AND password=?",a.getUserName(),a.getPassword());
        return resultSet;

    }
}
