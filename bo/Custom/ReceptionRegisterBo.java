package bo.Custom;

import dto.ReceptionistRegisterDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ReceptionRegisterBo {
    public boolean saveReception(ReceptionistRegisterDto r) throws SQLException;
    public ResultSet autoGenerateIdReception() throws SQLException;
    public ResultSet loginReception(ReceptionistRegisterDto r) throws SQLException;

}
