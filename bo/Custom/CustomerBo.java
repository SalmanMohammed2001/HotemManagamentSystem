package bo.Custom;

import dto.CustomerDto;
import entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBo {

    public boolean saveCustomer(CustomerDto dto) throws SQLException;
    public boolean updateCustomer(CustomerDto dto) throws SQLException;
    public boolean deleteCustomer(String id) throws SQLException;
    public ArrayList<CustomerDto> searchCustomer(String searchText) throws SQLException;
    public ResultSet autoGenerateIdCustomer() throws SQLException;


}
