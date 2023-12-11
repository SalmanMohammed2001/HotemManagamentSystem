package dao;

import java.sql.SQLException;

public interface CrudDao <T,ID> extends SuperDao{
    public boolean save(T t) throws SQLException;
    public boolean delete(ID id) throws SQLException;
    public boolean update(T t) throws SQLException;

}
