package com.devmate.parcialr1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Optional;

public class UniversityRegistrationApplication extends Application {
    @Override
    public void start(Stage stage) {

        //Nombres de los Label
        Label nameLabel = new Label("Nombre");
        TextField nameText = new TextField();

        // Label de fecha de nacimiento
        Label dobLabel = new Label("Nacimiento");
        DatePicker datePicker = new DatePicker();

        // Label de Genero
        Label genderLabel = new Label("Género");
        ToggleGroup groupGender = new ToggleGroup();
        RadioButton maleRadio = new RadioButton("Hombre");
        maleRadio.setToggleGroup(groupGender);
        RadioButton femaleRadio = new RadioButton("Mujer");
        femaleRadio.setToggleGroup(groupGender);

        // Label de los lenguajes de programación
        Label technologiesLabel = new Label("¿Lenguajes de programación que maneje?");
        CheckBox javaCheckBox = new CheckBox("Java");
        CheckBox dotnetCheckBox = new CheckBox("Python");

        // Label de formación academica
        Label educationLabel = new Label("Educación/Nivel Academico");
        ListView<String> eduList = new ListView<>();
        ObservableList<String> data = FXCollections.observableArrayList();
        data.addAll("Licenciatura", "Bachillerato", "Maestría", "Doctorado");
        eduList.setItems(data);
        eduList.setPrefSize(100, 100);

        // Label de su localidad
        Label locationLabel = new Label("Localidad");
        ChoiceBox<String> locationChoiceBox = new ChoiceBox<>();
        locationChoiceBox.getItems().addAll(
                "Jutiapa", "Quesada", "Moyuta", "Agua Blanca", "Otro departamento"
        );

        Button buttonSelectTime = new Button("Seleccionar Horario");
        Button buttonRegister = new Button("Registrar");
        Button buttonClear = new Button("Limpiar");

        Label selectedTimeLabel = new Label("Horario seleccionado:");
        buttonSelectTime.setOnAction(event -> {
            ObservableList<String> timeOptions = FXCollections.observableArrayList(
                    "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM"
            );

            ChoiceDialog<String> timeDialog = new ChoiceDialog<>("9:00 AM", timeOptions);
            timeDialog.setTitle("Seleccionar Horario");
            timeDialog.setHeaderText("Seleccione un horario de citación:");
            timeDialog.setContentText("Horario:");

            Optional<String> result = timeDialog.showAndWait();
            result.ifPresent(selectedTime -> {
                System.out.println("Horario seleccionado: " + selectedTime);
                selectedTimeLabel.setText("Horario seleccionado: " + selectedTime); // Actualizar el Label
            });
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(500, 300);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameText, 1, 0);

        gridPane.add(dobLabel, 0, 1);
        gridPane.add(datePicker, 1, 1);

        gridPane.add(genderLabel, 0, 2);
        gridPane.add(maleRadio, 1, 2);
        gridPane.add(femaleRadio, 2, 2);

        gridPane.add(technologiesLabel, 0, 3);
        gridPane.add(javaCheckBox, 1, 3);
        gridPane.add(dotnetCheckBox, 2, 3);

        gridPane.add(educationLabel, 0, 4);
        gridPane.add(eduList, 1, 4);

        gridPane.add(locationLabel, 0, 5);
        gridPane.add(locationChoiceBox, 1, 5);

        gridPane.add(buttonSelectTime, 0, 6);
        GridPane.setColumnSpan(buttonSelectTime, 3);

        gridPane.add(selectedTimeLabel, 1, 6);
        GridPane.setColumnSpan(selectedTimeLabel, 3);

        gridPane.add(buttonClear, 0, 8);
        gridPane.add(buttonRegister, 1, 8);

        // Styling nodes
        buttonRegister.setStyle("-fx-background-color:darkslateblue; -fx-text-fill: white;");
        buttonClear.setStyle("-fx-background-color:darkred; -fx-text-fill: white;");
        nameLabel.setStyle("-fx-font:normal bold 15px 'serif' ");
        dobLabel.setStyle("-fx-font:normal bold 15px 'serif' ");
        genderLabel.setStyle("-fx-font:normal bold 15px 'serif' ");
        technologiesLabel.setStyle("-fx-font:normal bold 15px 'serif' ");
        educationLabel.setStyle("-fx-font:normal bold 15px 'serif' ");
        locationLabel.setStyle("-fx-font:normal bold 15px 'serif' ");

        gridPane.setStyle("-fx-background-color:#ffffff;");

// estas líneas aseguran que todos los campos y selecciones
// en el formulario se restablezcan a sus valores iniciales cuando
// el usuario presiona el botón limpiar

        buttonClear.setOnAction(event -> {
            nameText.clear();
            datePicker.setValue(null);
            groupGender.selectToggle(null);
            javaCheckBox.setSelected(false);
            dotnetCheckBox.setSelected(false);
            eduList.getSelectionModel().clearSelection();
            locationChoiceBox.getSelectionModel().clearSelection();
            selectedTimeLabel.setText("Horario seleccionado:");
        });

        //Esto imprime las respuestas seleccionadas
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
            String educacion = eduList.getSelectionModel().getSelectedItem();
            String localidad = locationChoiceBox.getSelectionModel().getSelectedItem();

            System.out.println("Registro:\n" +
                    "Nombre: " + nombre + "\n" +
                    "Nacimiento: " + nacimiento + "\n" +
                    "Género: " + genero + "\n" +
                    "Tecnologías: " + tecnologias + "\n" +
                    "Educación: " + educacion + "\n" +
                    "Localidad: " + localidad);
        });

        Scene scene = new Scene(gridPane);
        stage.setTitle("Formulario de Inscripción Universitaria");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
