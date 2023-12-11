package bo.Custom.impl;

import bo.Custom.CustomerBo;
import dao.Custom.CustomerDao;
import dao.DaoFactory;
import dao.DaoType;
import dto.CustomerDto;
import entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBoImpl implements CustomerBo {

    private CustomerDao dao= DaoFactory.getInstance().getDao(DaoType.CUSTOMER);
    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException {
        return dao.save(
                new Customer(
                        dto.getId(),dto.getName(),dto.getCountry(),dto.getEmail(),dto.getMobileNo(),
                        dto.getAge(),dto.getGender()
                )
        );
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException {
        return dao.update(
                new Customer(
                        dto.getId(),dto.getName(),dto.getCountry(),dto.getEmail(),dto.getMobileNo(),
                        dto.getAge(),dto.getGender()
                )
        );

    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException {
        return dao.delete(id);
    }

    @Override
    public ArrayList<CustomerDto> searchCustomer(String searchText) throws SQLException {
        ArrayList<Customer>entity=dao.searchCustomers(searchText);
        ArrayList<CustomerDto>arrayList=new ArrayList<>();
        for (Customer c:entity
             ) {
            arrayList.add(
                    new CustomerDto(c.getId(),c.getName(),c.getCountry(),c.getEmail(),c.getMobileNo(),
                            c.getAge(),c.getGender())
            );

        }
        return arrayList;
    }

    @Override
    public ResultSet autoGenerateIdCustomer() throws SQLException {
        return dao.autoGenerateId();
    }
}
