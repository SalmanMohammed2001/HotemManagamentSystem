package controller;

import bo.BoFactory;
import bo.BoType;
import bo.Custom.MelBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Custom.impl.MelDaoImpl;
import db.DBConnection;
import dto.MealDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.Regex;
import util.TextFields;
import view.tm.MealTm;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class AddMealsFormController {
    public AnchorPane root;
    public JFXTextField txtMelaId;
    public JFXTextField txtMealName;
    public JFXTextField txtUnitePrice;
    public JFXTextField txtQTYOnHand;
    public JFXButton saveMeal;
    public TextField txtSearch;

    public TableColumn colMealId;
    public TableColumn colName;
    public TableColumn colUnitePrice;
    public TableColumn colQtyOnHand;
    public TableColumn colOption;
    public TableView<MealTm> tbMeals;

    private String searchMeal="";

  //  private MelDao melDao = DaoFactory.getInstance().getDao(DaoType.MEAL);
    private MelBo melBo= BoFactory.getInstance().getBo(BoType.MEAL);

    public void initialize(){
        colMealId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUnitePrice.setCellValueFactory(new PropertyValueFactory<>("unitePrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        autoGenerateId();

        searchMeal(searchMeal);

        tbMeals.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(null!=newValue){
                setMealDetails(newValue);
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            if (null!=newValue){
                searchMeal=newValue;
                searchMeal(searchMeal);
            }
        });

    }

    private void setMealDetails(MealTm tm) {
        txtMelaId.setText(tm.getId());
        txtMealName.setText(tm.getName());
        txtUnitePrice.setText(String.valueOf(tm.getUnitePrice()));
        txtQTYOnHand.setText(String.valueOf(tm.getQtyOnHand()));
        saveMeal.setText("Update Meal");
    }

    public void btnBackToHomeAction(ActionEvent actionEvent) throws IOException {
        Stage stage= (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdminDashBoardForm.fxml"))));

    }

    public void btnSaveMeal(ActionEvent actionEvent) {
        if(txtMealName.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING,"invalid MelName");
            alert.show();
            return;
        }

        if(txtQTYOnHand.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING,"invalid QTYOnHand");
            alert.show();
            return;


            }


        if(txtUnitePrice.getText().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.WARNING,"invalid unitePrice");
            alert.show();
            return;

        }


        boolean salary = Regex.isTextFieldValid(TextFields.DOUBLE,txtUnitePrice.getText());
        if (!salary) {
            new Alert(Alert.AlertType.WARNING, "Invalid UnitePrice").show();
            return;
        }

        boolean qty = Regex.isTextFieldValid(TextFields.INTEGER,txtQTYOnHand.getText());
        if (!qty) {
            new Alert(Alert.AlertType.WARNING, "Invalid qty").show();
            return;
        }



        if(saveMeal.getText().equalsIgnoreCase("save Meal")){

            try {

                boolean isAdd= melBo.saveMel(
                        new MealDto(
                                txtMelaId.getText(),txtMealName.getText(),Double.parseDouble(txtUnitePrice.getText()),
                                Integer.parseInt(txtQTYOnHand.getText())

                        )
                );


                if(isAdd){
                    searchMeal(searchMeal);
                    ClearFiled();
                    new Alert(Alert.AlertType.INFORMATION,"Meal Added").show();
                }else {
                    new Alert(Alert.AlertType.INFORMATION,"Try again").show();

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else {

            try {
             boolean isUpdate = melBo.updateMel(
                       new MealDto(
                               txtMelaId.getText(),txtMealName.getText(),Double.parseDouble(txtUnitePrice.getText()),
                               Integer.parseInt(txtQTYOnHand.getText())

                       )
               );
                if(isUpdate){
                    searchMeal(searchMeal);
                    ClearFiled();
                    new Alert(Alert.AlertType.INFORMATION,"Meal update").show();
                }else {
                    new Alert(Alert.AlertType.INFORMATION,"Try again").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private void searchMeal(String text) {
        String searchText="%"+text+"%";

        try {
            ObservableList<MealTm>obList= FXCollections.observableArrayList();
            ArrayList<MealDto>arrayList= melBo.searchMel(searchText);

            for (MealDto m:arrayList){
                Button btn=new Button("Delete");
                MealTm tm=new MealTm(
                        m.getId(),
                        m.getName(),
                        m.getUnitePrice(),
                        m.getQtyOnHand()
                        ,btn
                );obList.add(tm);
                btn.setOnAction(event -> {

                    try {

                        if(melBo.deleteMel(tm.getId())){
                           searchMeal(searchText);
                            ClearFiled();
                            new Alert(Alert.AlertType.INFORMATION,"Meal delete").show();
                        }else {
                            new Alert(Alert.AlertType.WARNING,"try Again").show();

                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }tbMeals.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void autoGenerateId(){
        Connection connection = DBConnection.getInstance().getConnection();

        try {
          /*  Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select  id   from Meal order by  id   desc limit 1");
*/

            MelDaoImpl melDao=new MelDaoImpl();
           ResultSet resultSet= melDao.autoGenerateId();


            boolean next1 = resultSet.next();

            if(next1){
                String userId = resultSet.getString(1);

                userId= userId.substring(1,userId.length());
                int intId = Integer.parseInt(userId);

                intId++;

                if(intId<10){
                    txtMelaId.setText("M00"+intId);

                }else if(intId<100){
                    txtMelaId.setText("M0"+intId);

                }else
                    txtMelaId.setText("M"+intId);



            }else{
                txtMelaId.setText("M001");


            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }



    private void ClearFiled() {
        txtUnitePrice.clear();
        txtMealName.clear();
        txtMelaId.clear();
        txtQTYOnHand.clear();
        autoGenerateId();
    }

    public void btnAddMeal(ActionEvent actionEvent) {
        saveMeal.setText("save Meal");



    }

    public void txtUnitePriceKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.DOUBLE,txtUnitePrice);
        System.out.println(Regex.isTextFieldValid(TextFields.DOUBLE,txtUnitePrice.getText()));
    }

    public void txtQTYOnHandKeyReleasedOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(TextFields.INTEGER,txtQTYOnHand);
        System.out.println(Regex.isTextFieldValid(TextFields.INTEGER,txtQTYOnHand.getText()));
    }
}
