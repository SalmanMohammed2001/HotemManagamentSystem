package dao.Custom;

import dao.CrudDao;
import entity.BookingDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingDetailsDao extends CrudDao<BookingDetails,String> {
    public ArrayList<BookingDetails> updateBooking(String id) throws SQLException;

}
