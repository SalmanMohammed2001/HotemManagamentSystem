package dao.Custom.impl;

import dao.CrudUtil;
import dao.Custom.QueryDao;
import entity.Custom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDaoImpl implements QueryDao {
    @Override
    public ArrayList<Custom> loadItemJoinQuery(String id) throws SQLException {
        ArrayList<Custom> arrayList=new ArrayList<>();
        ResultSet set= CrudUtil.execute("select o.id,d.MealId,d.orderId,d.unitePrice,d.qty from `Order` o INNER JOIN `Order Details` d ON o.id=d.orderId AND o.id=? ",id);
        while (set.next()){
            arrayList.add(new Custom(
                    set.getString(2),  //2
            set.getDouble(4),  //4
            set.getInt(5)   //5
            ));

        }
        return arrayList;
    }

    @Override
    public ArrayList<Custom> loadBookingJoinQuery(String id) throws SQLException {
        ArrayList<Custom> arrayList=new ArrayList();
        ResultSet set=CrudUtil.execute("select b.bookingId,d.RoomId,d.bookingId,d.price,d.availability,d. dayCount from Booking b INNER JOIN " +
                "bookingDetails d ON b.bookingId=d.bookingId AND b.bookingId=?",id);
        while (set.next()){
            arrayList.add(new Custom(
                    set.getString(2),
                    set.getDouble(4),
                    set.getString(5),
                    set.getInt(6)


            ));

        }
        return arrayList;
    }
}
