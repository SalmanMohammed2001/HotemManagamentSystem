package dao.Custom.impl;

import dao.CrudUtil;
import dao.Custom.BookingDao;
import entity.Booking;
import entity.Customer;
import entity.Room;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BookingDaoImpl implements BookingDao {
    @Override
    public boolean save(Booking b) throws SQLException {
        return CrudUtil.execute("insert into Booking values(?,?,?,?) ",
                        b.getBookingId(),b.getDate(),b.getCustomer(),b.getTotalCost());
    }



    @Override
    public boolean delete(String searchText) throws SQLException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }


    @Override
    public boolean update(Booking booking) throws SQLException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

//    @Override
//    public ResultSet setItemDetails(String id) throws SQLException {
//        ResultSet resultSet= CrudUtil.execute("select * from Room where id=?",id);
//        return resultSet;
//    }

    @Override
    public ArrayList<Room> setRoomDetails(String id) throws SQLException {
        ArrayList<Room> rooms=new ArrayList<>();
        ResultSet resultSet= CrudUtil.execute("select * from Room where id=?",id);
        while (resultSet.next()){
           rooms.add(new Room(
                   resultSet.getString(2),
                   resultSet.getString(3),
                   resultSet.getString(4),
                   resultSet.getDouble(5)

           ));
        }
        return rooms;

    }

    @Override
    public ArrayList<Customer> setCustomerDetails(String id) throws SQLException {
        ArrayList<Customer> customerArrayList=new ArrayList<>();
        ResultSet resultSet=CrudUtil.execute("select * from Customer where id=?",id);
        while (resultSet.next()){
            customerArrayList.add(new Customer(
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)

            ));
        }
        return customerArrayList;

    }

    @Override
    public ResultSet loadRoomId() throws SQLException {

        ResultSet set = CrudUtil.execute("select * from Room where availability='Available'");
        return set;
    }

    @Override
    public ResultSet loadCustomerId() throws SQLException {
        ResultSet set = CrudUtil.execute("select * from Customer");
        return set;
    }

    @Override
    public ResultSet bookingId() throws SQLException {

        ResultSet resultSet = CrudUtil.execute("select  bookingId   from booking order by  bookingId   desc limit 1");
        return resultSet;



    }

    @Override
    public ArrayList<Booking> loadAllBooking() throws SQLException, ParseException {
        ArrayList<Booking> arrayList=new ArrayList<>();
        ResultSet set=CrudUtil.execute("select * from Booking");
        SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-mm-dd");
        while (set.next()){
            arrayList.add(new Booking(
                    set.getString(1),
                    formatter1.parse(set.getString(2)),
                    set.getString(3),
                    set.getDouble(4)
            ));
        }
        return arrayList;
    }


}
