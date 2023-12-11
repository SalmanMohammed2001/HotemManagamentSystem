package dao.Custom;

import dao.SuperDao;
import entity.Custom;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDao extends SuperDao {
    public ArrayList<Custom>loadItemJoinQuery(String id) throws SQLException;
    public ArrayList<Custom>loadBookingJoinQuery(String id) throws SQLException;

}
