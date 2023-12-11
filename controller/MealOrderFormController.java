package controller;

import bo.BoFactory;
import bo.BoType;
import bo.Custom.BookingBo;
import bo.Custom.MealOrderBo;
import bo.Custom.impl.MealOrderBoImpl;
import dao.Custom.BookingDao;
import dao.Custom.MealOrderDao;
import dao.Custom.MealOrderDetailsDao;
import dao.Custom.impl.BookingDaoImpl;
import dao.Custom.impl.MealOrderDetailsDaoImpl;
import dao.Custom.impl.MealOrderDaoImpl;
import dao.DaoFactory;
import dao.DaoType;
import db.DBConnection;
import dto.CustomerDto;
import dto.ItemDetailsDto;
import dto.MealDto;
import dto.OrderDto;
import entity.Customer;
import entity.ItemDetails;
import entity.Meal;
import entity.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.AddMealTm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

public class MealOrderFormController {
    public AnchorPane root;
    public ComboBox<String> cbmCustomerId;
    public TextField txtBookingId;
    public TextField BookingDate;
    public TextField txtName;
    public TextField txtCountry;
    public TextField txtEmail;
    public ComboBox<String> cmdMealId;
    public TextField txtMealName;
    public TextField txtUnitePrice;
    public TextField txtQtyOnHand;
    public TableView tblCart;
    public TableColumn colMealId;
    public TableColumn colMealName;
    public TableColumn colUnitePrice;
    public TableColumn colQTYOnHand;
    public TableColumn colQty;
    public TableColumn colTotal;
    public TableColumn colOption;
    public Label lblTotal;
    public TextField txtMobileNo;
    public TextField txtAge;
    public TextField txtGender;
    public TextField txtQty;
    public TextField txtOrderId;
    public TextField txtOrderDate;

  //  MealOrderDaoImpl mealOrderDao = new MealOrderDaoImpl();

  //  MealOrderDetailsDao mealOrderDetailsDao = new MealOrderDetailsDaoImpl();

 //   BookingDao bookingDao = new BookingDaoImpl();

   MealOrderDao mealOrderDao= DaoFactory.getInstance().getDao(DaoType.MEALORDER);

    MealOrderDetailsDao mealOrderDetailsDao=DaoFactory.getInstance().getDao(DaoType.MEALORDERDETAILS);

  //  BookingDao bookingDao=DaoFactory.getInstance().getDao(DaoType.BOOKING);
    BookingBo bookingBo= BoFactory.getInstance().getBo(BoType.BOOKING);
    MealOrderBo mealOrderBo=BoFactory.getInstance().getBo(BoType.MEALORDER);



    public void initialize() {

        loadDate();

        colMealId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMealName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUnitePrice.setCellValueFactory(new PropertyValueFactory<>("unitePrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadCustomerId();

        loadMealId();


        setOderId();


        cbmCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setCustomerDetails();
        });

        cmdMealId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setMealDetails();
        });
    }

    private void loadDate() {
        txtOrderDate.setText(new SimpleDateFormat("yyy-MM-dd").format(new Date()));

    }

    private void setMealDetails() {

        try {


            ArrayList<MealDto> arrayList = mealOrderBo.setMealDetails(cmdMealId.getValue());
            for (MealDto m : arrayList) {
                txtMealName.setText(m.getName());
                txtUnitePrice.setText(String.valueOf(m.getUnitePrice()));
                txtQtyOnHand.setText(String.valueOf(m.getQtyOnHand()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setCustomerDetails() {

        try {


            ArrayList<CustomerDto> arrayList = bookingBo.bookingSetCustomerDetails(cbmCustomerId.getValue());
            for (CustomerDto c : arrayList) {
                txtName.setText(c.getName());
                txtCountry.setText(c.getCountry());
                txtEmail.setText(c.getEmail());
                txtMobileNo.setText(c.getMobileNo());
                txtAge.setText(c.getAge());
                txtGender.setText(c.getGender());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadMealId() {
        try {

            ResultSet set = mealOrderBo.loadMealId();
            ArrayList<String> arrayList = new ArrayList<>();
            while (set.next()) {
                arrayList.add(set.getString(1));
            }
            ObservableList<String> obList = FXCollections.observableArrayList(arrayList);
            cmdMealId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void loadCustomerId() {

        try {


            ResultSet set = mealOrderBo.loadCustomerId();
            ArrayList<String> arrayList = new ArrayList<>();
            while (set.next()) {
                arrayList.add(set.getString(1));
            }
            ObservableList<String> obList = FXCollections.observableArrayList(arrayList);
            cbmCustomerId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnBackToHomeAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ReceptionistDashBoardForm.fxml"))));

    }

    private void setOderId() {


        try {


            ResultSet resultSet = mealOrderBo.OrderId();
            boolean next = resultSet.next();
            if (next) {
                String userId = resultSet.getString(1);

                userId = userId.substring(1, userId.length());
                int intId = Integer.parseInt(userId);

                intId++;

                if (intId < 10) {
                    txtOrderId.setText("O00" + intId);

                } else if (intId < 100) {
                    txtOrderId.setText("O0" + intId);

                } else
                    txtOrderId.setText("O" + intId);

            } else {
                txtOrderId.setText("O001");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private boolean checkQty(String id, int qty) {
        try {


            ResultSet set = mealOrderBo.checkQty(id);

            if (set.next()) {
                int tempqty = set.getInt(1);
                if (tempqty >= qty) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    ObservableList<AddMealTm> obList = FXCollections.observableArrayList();

    public void btnAddTocardOnAction(ActionEvent actionEvent) {
        if (!checkQty(cmdMealId.getValue(), Integer.parseInt(txtQty.getText()))) {
            new Alert(Alert.AlertType.WARNING, "Invalid entry").show();
            return;

        }


        double unitePrice = Double.parseDouble(txtUnitePrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        double total = unitePrice * qty;
        Button btn = new Button("Delete");


        int row = isAlerdyexists(cmdMealId.getValue());
        if (row == -1) {
            AddMealTm tm = new AddMealTm(cmdMealId.getValue(), txtMealName.getText(), unitePrice, qty, total, btn);
            obList.add(tm);
            tblCart.setItems(obList);
        } else {
            int tempQty = obList.get(row).getQty() + qty;
            double temTotal = unitePrice * tempQty;

            if (!checkQty(cmdMealId.getValue(), tempQty)) {
                new Alert(Alert.AlertType.WARNING, "Invalid entry").show();
                return;

            }
            obList.get(row).setQty(tempQty);
            obList.get(row).setTotal(temTotal);
            tblCart.refresh();
        }

        calculateTotal();
        clearFlied();

        btn.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want  to delete", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();

            if (buttonType.get() == ButtonType.YES) {
                for (AddMealTm tm : obList
                ) {
                    boolean remove = obList.remove(tm);
                    if (remove) {
                        new Alert(Alert.AlertType.INFORMATION, "Delted").show();
                        calculateTotal();
                        tblCart.refresh();
                        return;

                    } else {
                        new Alert(Alert.AlertType.WARNING, "try again").show();

                    }

                }

            }


        });
    }

    private int isAlerdyexists(String code) {
        for (int i = 0; i < obList.size(); i++) {
            if (obList.get(i).getId().equals(code)) {
                return i;
            }
        }
        return -1;
    }

    public void jesperReport() {
        String billPath = "E:\\GDSE\\New folder (2)\\System 2\\Report\\MealReport.jrxml";
        String sql = "select o.customer as customer,od.MealId as Meal,od.unitePrice as UnitePrice,od.qty as Qty,o.totalCost as TotalCost from `order`o inner join `Order Details` `od` on o.id = `od`.orderId where o.customer='" + cbmCustomerId.getValue() + "'";
        JasperDesign jasdi = null;
        String savePath = "E:\\GDSE\\New folder (2)\\System 2\\saveReport" + LocalDate.now().getYear() + LocalDate.now().getMonth().toString() + LocalDate.now().getDayOfMonth() + LocalTime.now().getHour() + LocalTime.now().getMinute() + LocalTime.now().getSecond() + ".pdf";
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("total", lblTotal.getText());
        try {
            jasdi = JRXmlLoader.load(billPath);
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jasdi.setQuery(newQuery);
            JasperReport js = JasperCompileManager.compileReport(jasdi);
            JasperPrint jp = JasperFillManager.fillReport(js, hm, DBConnection.getInstance().getConnection());
            JasperExportManager.exportReportToPdfFile(jp, savePath);
            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.show();
        } catch (JRException e) {
            e.printStackTrace();
        }
    }


    public void btnMealOrder(ActionEvent actionEvent) throws SQLException {
        if (obList.isEmpty()) return;
        ArrayList<ItemDetailsDto> details = new ArrayList<>();

        for (AddMealTm tm : obList
        ) {

            details.add(new ItemDetailsDto(tm.getId(), txtOrderId.getText(), tm.getUnitePrice(), tm.getQty()));

        }







        mealOrderBo.MealOrderSave(new OrderDto(
                txtOrderId.getText(),
                new Date(),
                cbmCustomerId.getValue(),
                Double.parseDouble(lblTotal.getText())
        ),details);
        jesperReport();
        clearAll();

    }

   /*     Connection con = null;
        try {

            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);


            boolean isOrderSaved = mealOrderDao.save(new Order(txtOrderId.getText(), new Date(), cbmCustomerId.getValue(),
                    Double.parseDouble(lblTotal.getText())));

            if (isOrderSaved) {
                boolean isAllUpdate = manageQty(details);
                if (isAllUpdate) {
                    con.commit();
                    new Alert(Alert.AlertType.INFORMATION, "Order places").show();
                    jesperReport();
                    clearAll();

                } else {
                    con.setAutoCommit(true);
                    con.rollback();
                    new Alert(Alert.AlertType.WARNING, "try again").show();

                }
            } else {
                con.setAutoCommit(true);
                con.rollback();
                new Alert(Alert.AlertType.WARNING, "tryy again").show();
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.setAutoCommit(true);
        }

    }

    private boolean manageQty(ArrayList<ItemDetails> details) {

        try {
            for (ItemDetails d : details
            ) {


                boolean isOrderDetailsSaved = mealOrderDetailsDao.save(new entity.ItemDetails(d.getId(), txtOrderId.getText(), d.getUnitePrice(), d.getQty()));
                if (isOrderDetailsSaved) {
                    boolean isQtyUpdate = update(d);
                    if (!isQtyUpdate) {
                        return false;

                    }

                } else {
                    return false;
                }


            }

        } catch (SQLException e) {
            e.printStackTrace();

        }


        return true;
    }

    private boolean update(ItemDetails d) {
        try {

            return mealOrderDetailsDao.update(new ItemDetails(d.getQty(), d.getId()));


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
*/

    public void calculateTotal() {
        double total = 0;
        for (AddMealTm tm : obList
        ) {

            total += tm.getTotal();
            lblTotal.setText(String.valueOf(total));

        }
    }

    private void clearFlied() {
        txtMealName.clear();
        txtUnitePrice.clear();
        txtQty.clear();
        txtQtyOnHand.clear();
    }

    private void clearAll() {
        obList.clear();
        calculateTotal();
        txtName.clear();
        txtCountry.clear();
        ;
        txtEmail.clear();
        ;
        txtMobileNo.clear();
        txtAge.clear();
        txtGender.clear();

        cmdMealId.setValue(null);
        cbmCustomerId.setValue(null);

        cbmCustomerId.requestFocus();

        clearFlied();

        setOderId();

    }
//    public void btnMealOrder(ActionEvent actionEvent) {
//    }
}
