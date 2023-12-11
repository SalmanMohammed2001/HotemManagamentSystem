package bo.Custom.impl;

import bo.Custom.MelBo;
import dao.Custom.MelDao;
import dao.DaoFactory;
import dao.DaoType;
import dto.MealDto;
import entity.Meal;

import java.sql.SQLException;
import java.util.ArrayList;

public class MelBoImpl implements MelBo {

    private MelDao dao= DaoFactory.getInstance().getDao(DaoType.MEAL);
    @Override
    public boolean saveMel(MealDto dto) throws SQLException {
        return dao.save(
                new Meal(dto.getId(),dto.getName(),dto.getUnitePrice(),dto.getQtyOnHand())
        );
    }

    @Override
    public boolean updateMel(MealDto dto) throws SQLException {
        return dao.update(
                new Meal(dto.getId(),dto.getName(),dto.getUnitePrice(),dto.getQtyOnHand())
        );
    }

    @Override
    public boolean deleteMel(String id) throws SQLException {
        return dao.delete(id);
    }

    @Override
    public ArrayList<MealDto> searchMel(String searchText) throws SQLException {
        ArrayList<Meal>entity=dao.searchMeals(searchText);
        ArrayList<MealDto> arrayList=new ArrayList<>();
        for (Meal m:entity
        ) {
            arrayList.add(
                    new MealDto(m.getId(),m.getName(),m.getUnitePrice(),m.getQtyOnHand())
            );

        }
        return arrayList;
    }
}
