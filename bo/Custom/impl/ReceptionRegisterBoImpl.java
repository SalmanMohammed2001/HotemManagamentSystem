package bo.Custom.impl;

import bo.Custom.ReceptionRegisterBo;
import dao.Custom.ReceptionRegisterDao;
import dao.DaoFactory;
import dao.DaoType;
import dto.ReceptionistRegisterDto;
import entity.ReceptionistRegister;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceptionRegisterBoImpl implements ReceptionRegisterBo {

    ReceptionRegisterDao registerDao= DaoFactory.getInstance().getDao(DaoType.RECEPTIONIST);
    @Override
    public boolean saveReception(ReceptionistRegisterDto r) throws SQLException {
        return registerDao.save(new ReceptionistRegister(r.getId(),r.getUserName(),r.getEmail(),r.getPassword()));
    }

    @Override
    public ResultSet autoGenerateIdReception() throws SQLException {
        return registerDao.autoGenerateId();
    }

    @Override
    public ResultSet loginReception(ReceptionistRegisterDto r) throws SQLException {
        return registerDao.loginForm(new ReceptionistRegister(r.getUserName(),r.getPassword()));
    }
}
