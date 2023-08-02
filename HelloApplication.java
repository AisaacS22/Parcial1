package com.devmate.parcialr1;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //Label for name
        Label nameLabel=new Label("Nombre");

        //Text Field for Name
        TextField nameText=new TextField();

        //Label for date of birth
        Label dobLabel=new Label("Nacimiento");

        //date picker to choose date
        DatePicker datePicker=new DatePicker();

        //Label for gender
        Label genderLabel=new Label("Género");

        //Toggle group of radio button
        ToggleGroup groupGender=new ToggleGroup();
        RadioButton maleRadio=new RadioButton("Hombre");
        maleRadio.setToggleGroup(groupGender);
        RadioButton femaleRadio=new RadioButton("Mujer");
        femaleRadio.setToggleGroup(groupGender);

        //Label for technologies known
        Label technologiesLabel=new Label("Tecnologías Conocidas");

        //Check box for education
        CheckBox javaCheckBox=new CheckBox("Java");
        CheckBox dotnetCheckBox=new CheckBox("Python");

        //Label for education
        Label educationLabel=new Label("Educación");

        //list view for educational qualification
        ListView eduList=new ListView();
        ObservableList<String> data= FXCollections.observableArrayList();
        data.addAll("Licenciatura","Bachillerato","Maestría","Doctorado");
        eduList.setItems(data);
        eduList.setPrefSize(100,100);

        //Label for Location
        Label locationLabel=new Label("Localidad");

        //Choice box for location
        ChoiceBox<String> locationChoiceBox=new ChoiceBox<>();
        locationChoiceBox.getItems().addAll(
                "Jutiapa","Quesada","Pajarita","Moyuta","Agua Blanca"
        );

        //Label for register
        Button buttonRegister=new Button("Registrar");
        Button buttonClear=new Button("Limpiar");

        //Crating a Grid Pane
        GridPane gridPane=new GridPane();

        //Setting size for pane
        gridPane.setMinSize(500,300);

        //Setting the padding
        gridPane.setPadding(new Insets(10,10,10,10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        //Setting the grid alignment
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
        gridPane.add(nameLabel,0,0);
        gridPane.add(nameText,1,0);

        gridPane.add(dobLabel,0,1);
        gridPane.add(datePicker,1,1);

        gridPane.add(genderLabel,0,2);
        gridPane.add(maleRadio,1,2);
        gridPane.add(femaleRadio,2,2);

        gridPane.add(technologiesLabel,0,3);
        gridPane.add(javaCheckBox,1,3);
        gridPane.add(dotnetCheckBox,2,3);

        gridPane.add(educationLabel,0,4);
        gridPane.add(eduList,1,4);

        gridPane.add(locationLabel,0,5);
        gridPane.add(locationChoiceBox,1,5);

        gridPane.add(buttonClear,0,7);
        gridPane.add(buttonRegister,1,7);

        //Styling nodes
        buttonRegister.setStyle(
                "-fx-background-color:darkslateblue; -fx-text-fill: white;"
        );
        buttonClear.setStyle(
                "-fx-background-color:darkred; -fx-text-fill: white;"
        );
        nameLabel.setStyle("-fx-font:normal bold 15px 'serif' ");
        dobLabel.setStyle("-fx-font:normal bold 15px 'serif' ");
        genderLabel.setStyle("-fx-font:normal bold 15px 'serif' ");
        technologiesLabel.setStyle("-fx-font:normal bold 15px 'serif' ");
        educationLabel.setStyle("-fx-font:normal bold 15px 'serif' ");
        locationLabel.setStyle("-fx-font:normal bold 15px 'serif' ");

        //Setting the background color
        gridPane.setStyle("-fx-background-color:#9ad3cb;");

        //Adding action for Clear button
        buttonClear.setOnAction(event -> {
            nameText.clear();
            datePicker.setValue(null);
            groupGender.selectToggle(null);
            javaCheckBox.setSelected(false);
            dotnetCheckBox.setSelected(false);
            eduList.getSelectionModel().clearSelection();
            locationChoiceBox.getSelectionModel().clearSelection();
        });

        //Adding action for Register button
        buttonRegister.setOnAction(event -> {
            String nombre = nameText.getText();
            String nacimiento = datePicker.getValue().toString();
            String genero = maleRadio.isSelected() ? "Hombre" : "Mujer";
            String tecnologias = "";
            if (javaCheckBox.isSelected()) {
                tecnologias += "Java ";
            }
            if (dotnetCheckBox.isSelected()) {
                tecnologias += "Python ";
            }
            String educacion = (String) eduList.getSelectionModel().getSelectedItem();
            String localidad = locationChoiceBox.getSelectionModel().getSelectedItem();

            // Aquí podrías realizar alguna acción con los datos, como almacenarlos en una base de datos.
            System.out.println("Registro:\n" +
                    "Nombre: " + nombre + "\n" +
                    "Nacimiento: " + nacimiento + "\n" +
                    "Género: " + genero + "\n" +
                    "Tecnologías: " + tecnologias + "\n" +
                    "Educación: " + educacion + "\n" +
                    "Localidad: " + localidad);
        });

        //Creating a scene object
        Scene scene=new Scene(gridPane);

        //Setting the title of stage
        stage.setTitle("Formulario de Registro");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
