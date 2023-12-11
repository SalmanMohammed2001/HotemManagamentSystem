package bo.Custom.impl;

import bo.Custom.BookingDetailsBo;
import dao.Custom.BookingDetailsDao;
import dao.DaoFactory;
import dao.DaoType;
import dto.BookingDetailsDto;
import entity.BookingDetails;


import java.sql.SQLException;
import java.util.ArrayList;


public class BookingDetailsBoImpl implements BookingDetailsBo {

    private BookingDetailsDao detailsDao= DaoFactory.getInstance().getDao(DaoType.BOOKINGDETAILS);
    @Override
    public boolean saveBookingDetails(BookingDetailsDto b) throws SQLException {
        return detailsDao.save(new BookingDetails(
                b.getRoomId(),b.getBookingId(),b.getAvailability(),b.getPrice(),b.getDayCount()
        ));
    }

    @Override
    public ArrayList<BookingDetailsDto> updateBooking(String id) throws SQLException {
        ArrayList<BookingDetails> arrayList=detailsDao.updateBooking(id);
        ArrayList<BookingDetailsDto> dtoArrayList=new ArrayList<>();
        for(BookingDetails b:arrayList){
            dtoArrayList.add(new BookingDetailsDto(
                    b.getBookingId(),
                    b.getPrice(),
                    b.getDayCount()
            ));
        }
        return dtoArrayList;

    }
}
