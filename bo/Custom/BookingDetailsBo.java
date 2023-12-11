package bo.Custom;

import dto.BookingDetailsDto;
import entity.BookingDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookingDetailsBo {
    public boolean saveBookingDetails(BookingDetailsDto b) throws SQLException;
    public ArrayList<BookingDetailsDto> updateBooking(String id) throws SQLException;
}
