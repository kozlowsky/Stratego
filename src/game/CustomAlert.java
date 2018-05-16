package game;

import javafx.scene.control.Alert;

public class CustomAlert extends Alert{

    public CustomAlert(Alert.AlertType alertType) {
        super(alertType);

    }

    public void throwAlert( String alertTitle, String alertHeader, String alertContent){
        super.setTitle(alertTitle);
        super.setHeaderText(alertHeader);
        super.setContentText(alertContent);
        this.showAndWait();
    }
}
