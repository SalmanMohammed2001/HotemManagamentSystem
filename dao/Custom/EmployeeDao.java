package dao.Custom;

import dao.CrudDao;
import entity.Customer;
import entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDao extends CrudDao<Employee,String>{
    public ArrayList<Employee> searchEmployees(String searchText) throws SQLException;
    public ResultSet autoGenerateId() throws SQLException;

}
