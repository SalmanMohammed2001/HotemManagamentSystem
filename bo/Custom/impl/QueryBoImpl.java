package bo.Custom.impl;

import bo.Custom.QueryBo;
import dao.Custom.QueryDao;
import dao.DaoFactory;
import dao.DaoType;
import dto.CustomDto;
import entity.Custom;

import java.sql.SQLException;
import java.util.ArrayList;

public class QueryBoImpl implements QueryBo {

    QueryDao queryDao = DaoFactory.getInstance().getDao(DaoType.QUERY);

    @Override
    public ArrayList<CustomDto> loadItemJoinQuery(String id) throws SQLException {
        ArrayList<Custom> arrayList = queryDao.loadItemJoinQuery(id);
        ArrayList<CustomDto> dtoArrayList = new ArrayList<>();
        for (Custom c : arrayList) {
            dtoArrayList.add(new CustomDto(
                    c.getId(),
                    c.getUnitePrice(),
                    c.getQty()

            ));
        }
        return dtoArrayList;
    }

    @Override
    public ArrayList<CustomDto> loadBookingJoinQuery(String id) throws SQLException {
        ArrayList<Custom> arrayList = queryDao.loadBookingJoinQuery(id);
        ArrayList<CustomDto> dtoArrayList = new ArrayList<>();
        for (Custom c : arrayList) {
            dtoArrayList.add(new CustomDto(
                    c.getRoomId(),
                    c.getPrice(),
                    c.getAvailability(),
                    c.getDayCount()

            ));

        }
        return dtoArrayList;
    }
}
