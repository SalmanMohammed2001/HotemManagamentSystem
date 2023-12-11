package dao.Custom;

import dao.CrudDao;
import entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDao extends CrudDao<Customer,String> {
    public ArrayList<Customer> searchCustomers(String searchText) throws SQLException;
    ResultSet autoGenerateId() throws SQLException;
}
