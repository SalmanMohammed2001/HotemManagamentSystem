package dao.Custom.impl;

import dao.CrudUtil;
import dao.Custom.EmployeeDao;
import db.DBConnection;
import entity.Employee;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean save(Employee e1) throws SQLException {
        return CrudUtil.execute("INSERT INTO Employee VALUES(?,?,?,?,?,?,?,?)",
               e1.getId(), e1.getEname(),e1.getIdNumber(),e1.getEmail(),e1.getJob(),e1.getNumber(),e1.getAge(),
                e1.getSalary());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return CrudUtil.execute("DELETE FROM Employee WHERE  eId =?",id);
    }

    @Override
    public boolean update(Employee e1) throws SQLException {
        return CrudUtil.execute("UPDATE Employee SET Ename =?,idNumber=?,email=?, job=?, number=?, age=?,salary=? where  eId =?",
                e1.getEname(),e1.getIdNumber(),e1.getEmail(),e1.getJob(),e1.getNumber(),e1.getAge(),
                e1.getSalary(),e1.getId());
    }

    @Override
    public ArrayList<Employee> searchEmployees(String searchText) throws SQLException {
        ResultSet set = CrudUtil.execute("SELECT * FROM Employee WHERE  Ename  like ? || idNumber like?",
                searchText,searchText);
        ArrayList<Employee>arrayList=new ArrayList<>();
        while (set.next()){
            arrayList.add(new Employee(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getString(5),
                    set.getString(6),
                    set.getString(7),
                    set.getDouble(8)
            ));
        }
        return arrayList;
    }

    @Override
    public ResultSet autoGenerateId() throws SQLException {
        ResultSet resultSet =CrudUtil.execute("select  eId   from Employee order by  eId   desc limit 1");
        return  resultSet;

    }
}
