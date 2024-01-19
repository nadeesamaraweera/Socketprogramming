package lk.ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;



    public class ServerInitializer extends Application {
        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage stage) throws Exception {
            stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/Server_Form.fxml"))));
            stage.centerOnScreen();

            stage.show();
        }
    }

