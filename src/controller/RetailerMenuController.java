package controller;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.ViewManager;


/**
 * @author 1914-杨雨田-20195462
 * @create 2020-07-22 2:03
 */
public class RetailerMenuController {

    @FXML
    private BorderPane rootLayout;
    @FXML
    private FontAwesomeIconView exitButton;

    @FXML
    private JFXButton nyOrderButton;

    @FXML
    private JFXButton waitForTenderingButton;

    @FXML
    private JFXButton teneredButton;

    @FXML
    void close(MouseEvent event) {
        Stage currentStage = (Stage) exitButton.getScene().getWindow();
        currentStage.close();
    }


    @FXML
    void tendered(ActionEvent event) {

    }

    @FXML
    void waitForTendering(ActionEvent event) {

    }

}

