package bo.Custom;

import dto.AdminRegisterDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface AdminRegisterBo {

    public boolean saveAdmin(AdminRegisterDto a) throws SQLException;
    public ResultSet autoGenerateIdAdmin() throws SQLException;
    public ResultSet loginAdmin(AdminRegisterDto a) throws SQLException;
}
