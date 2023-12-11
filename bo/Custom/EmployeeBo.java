package bo.Custom;

import dto.CustomerDto;
import dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBo {
    public boolean saveEmployee(EmployeeDto dto) throws SQLException;
    public boolean updateEmployee(EmployeeDto  dto) throws SQLException;
    public boolean deleteEmployee(String id) throws SQLException;
    public ArrayList<EmployeeDto> searchEmployee(String searchText) throws SQLException;
}
