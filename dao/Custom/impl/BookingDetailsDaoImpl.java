package dao.Custom.impl;

import dao.CrudUtil;
import db.DBConnection;
import entity.BookingDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingDetailsDaoImpl implements dao.Custom.BookingDetailsDao {
    @Override
    public boolean save(BookingDetails b) throws SQLException {
   /*     String sql=;
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1,d.getRoomId());
        statement.setString(2,txtBookingId.getText());
        statement.setDouble(3,d.getPrice());
        statement.setString(4,d.getAvailability());
        statement.setInt(5,d.getDayCount());
        int isBookingDetaisSaved = statement.executeUpdate();*/

        return CrudUtil.execute("insert into bookingDetails values(?,?,?,?,?)",b.getRoomId(),b.getBookingId(),b.getPrice(),b.getAvailability(),b.getDayCount());
    }

    @Override
    public boolean delete(String searchText) throws SQLException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(BookingDetails b) throws SQLException {
        return CrudUtil.execute("update Room set availability='Occupied' where id=?",b.getRoomId());
    }

    @Override
    public ArrayList<BookingDetails> updateBooking(String id) throws SQLException {
        ArrayList<BookingDetails> arrayList=new ArrayList<>();
        ResultSet set = CrudUtil.execute("select * from bookingDetails where  RoomId =?",id);
        while (set.next()){
           arrayList.add(new BookingDetails(
                   set.getString(2),

                   set.getDouble(3),
                   set.getInt(5)

           )) ;
        }
        return arrayList;
    }
}
