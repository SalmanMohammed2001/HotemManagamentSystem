package dao.Custom.impl;

import dao.CrudUtil;
import dao.Custom.ReceptionRegisterDao;
import entity.AdminRegister;
import entity.ReceptionistRegister;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceptionRegisterDaoImpl implements ReceptionRegisterDao {
    @Override
    public boolean save(ReceptionistRegister a) throws SQLException {
        return CrudUtil.execute("insert into ReceptionistRegister VALUES (?,?,?,?)",a.getId(),a.getUserName(),a.getEmail(),a.getPassword());
    }

    @Override
    public boolean delete(String searchText) throws SQLException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(ReceptionistRegister r) throws SQLException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public ResultSet autoGenerateId() throws SQLException {
        ResultSet set=CrudUtil.execute("select id from ReceptionistRegister order by id desc limit 1");
        return set;
    }

    @Override
    public ResultSet loginForm(ReceptionistRegister r) throws SQLException {
        ResultSet set=CrudUtil.execute("select * from ReceptionistRegister where userName=? and password=?",r.getUserName(),r.getPassword());
        return set;
    }


}
