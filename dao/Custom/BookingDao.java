package dao.Custom;

import dao.CrudDao;
import entity.Booking;
import entity.Customer;
import entity.Room;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public interface BookingDao extends CrudDao<Booking,String> {

    public ArrayList<Room>setRoomDetails(String id) throws SQLException;
    public ArrayList<Customer> setCustomerDetails(String id) throws SQLException;
    public ResultSet loadRoomId() throws SQLException;
    public ResultSet loadCustomerId() throws SQLException;
    public ResultSet bookingId() throws SQLException;
    public ArrayList<Booking>loadAllBooking() throws SQLException, ParseException;
}
