package dao;

import dao.Custom.impl.*;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {

        return daoFactory == null ? (daoFactory = new DaoFactory()) : daoFactory;
    }


    public <T> T getDao(DaoType type) {
        switch (type) {
            case CUSTOMER:
                return (T) new CustomerDaoImpl();
            case EMPLOYEE:
                return (T) new EmployeeDaoImpl();
            case MEAL:
                return (T) new MelDaoImpl();
            case ROOM:
                return (T) new RoomDaoImpl();
            case ADMIN:
                return (T) new AdminRegisterDaoImpl();
            case QUERY:
                return (T) new QueryDaoImpl();
            case BOOKING:
                return (T) new BookingDaoImpl();
            case BOOKINGDETAILS:
                return (T) new BookingDetailsDaoImpl();
            case MEALORDER:
                return (T) new MealOrderDaoImpl();

            case MEALORDERDETAILS:
                return (T) new MealOrderDetailsDaoImpl();

            case RECEPTIONIST:
                return (T) new ReceptionRegisterDaoImpl();

            default:
                return null;

        }

    }
}
