package dao.Custom;

import dao.CrudDao;
import entity.AdminRegister;
import entity.ReceptionistRegister;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ReceptionRegisterDao extends CrudDao<ReceptionistRegister,String> {
    public ResultSet autoGenerateId() throws SQLException;
    public ResultSet loginForm(ReceptionistRegister r) throws SQLException;
}
