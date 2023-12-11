package bo.Custom.impl;

import bo.Custom.EmployeeBo;
import dao.Custom.EmployeeDao;
import dao.DaoFactory;
import dao.DaoType;
import dto.EmployeeDto;
import entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBoImpl implements EmployeeBo {

    private EmployeeDao dao= DaoFactory.getInstance().getDao(DaoType.EMPLOYEE);
    @Override
    public boolean saveEmployee(EmployeeDto dto) throws SQLException {
        return dao.save(
                new Employee(
                        dto.getId(),dto.getEname(),dto.getIdNumber(),dto.getEmail(),dto.getJob(),
                        dto.getNumber(),dto.getAge(),dto.getSalary()
                )
        );
    }

    @Override
    public boolean updateEmployee(EmployeeDto dto) throws SQLException {
        return dao.update(
                new Employee(
                        dto.getId(),dto.getEname(),dto.getIdNumber(),dto.getEmail(),dto.getJob(),
                        dto.getNumber(),dto.getAge(),dto.getSalary()
                )
        );
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException {
        return dao.delete(id);
    }

    @Override
    public ArrayList<EmployeeDto> searchEmployee(String searchText) throws SQLException {
        ArrayList<Employee>entity=dao.searchEmployees(searchText);
        ArrayList<EmployeeDto> arrayList=new ArrayList<>();
        for (Employee e:entity
             ) {
            arrayList.add(
                    new EmployeeDto(e.getId(),e.getEname(),e.getIdNumber(),e.getEmail(),e.getJob(),
                    e.getNumber(),e.getAge(),e.getSalary())
            );

        }
        return arrayList;
    }
}
