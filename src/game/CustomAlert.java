package game;

import javafx.scene.control.Alert;

public class CustomAlert extends Alert{

    CustomAlert(AlertType alertType) {
        super(alertType);

    }

    void throwAlert(String alertTitle, String alertHeader, String alertContent){
        super.setTitle(alertTitle);
        super.setHeaderText(alertHeader);
        super.setContentText(alertContent);
        this.showAndWait();
    }
}
