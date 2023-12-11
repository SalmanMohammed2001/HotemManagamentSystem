package dao.Custom.impl;

import dao.CrudUtil;
import dao.Custom.CustomerDao;
import db.DBConnection;
import entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(Customer c) throws SQLException {
        return CrudUtil.execute("INSERT INTO Customer VALUES(?,?,?,?,?,?,?)",
                c.getId(),c.getName(),c.getCountry(),c.getEmail(),c.getMobileNo(),c.getAge(),c.getGender());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM Customer WHERE id=?",id);
    }

    @Override
    public boolean update(Customer c) throws SQLException {
        return CrudUtil.execute("UPDATE Customer SET name=?,country=?,email=?,mobileNo=?,age=?,gender=? where id=?",
                c.getName(),c.getCountry(),c.getEmail(),c.getMobileNo(),c.getAge(),c.getGender(),c.getId());
    }

    @Override
    public ArrayList<Customer> searchCustomers(String searchText) throws SQLException {
        ResultSet set = CrudUtil.execute("SELECT * FROM Customer WHERE id like ?|| name like ? || email like?",
                searchText,searchText,searchText);
        ArrayList<Customer>arrayList=new ArrayList<>();
        while (set.next()){
            arrayList.add(new Customer(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getString(5),
                    set.getString(6),
                    set.getString(7)
            ));
        }
        return arrayList;
    }

    @Override
    public ResultSet autoGenerateId() throws SQLException {


        ResultSet resultSet = CrudUtil.execute("select  id   from Customer order by  id   desc limit 1");
        return resultSet;

    }
}
