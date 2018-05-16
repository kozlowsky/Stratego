package game;

import javafx.scene.control.Alert;

public class CustomAlert extends Alert{

    public CustomAlert(Alert.AlertType alertType, String alertTitle, String alertHeader, String alertContent) {
        super(alertType);
        super.setTitle(alertTitle);
        super.setHeaderText(alertHeader);
        super.setContentText(alertContent);
    }
}
