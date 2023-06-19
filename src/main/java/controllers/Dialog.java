package controllers;

import javafx.scene.control.Alert;

public class Dialog {

    public static void successfulInfoWindow(String title, String headerText){
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle(title);
        info.setHeaderText(headerText);
        info.setContentText(null);
        info.showAndWait();
    }

    public static void errorWindow(String title, String headerText){
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle(title);
        error.setHeaderText(headerText);
        error.setContentText(null);

        error.show();
    }
}
