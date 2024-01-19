import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientController {


    @FXML
    private TextArea txtArea;
    @FXML
    private TextField txtMessage;

    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;

    public void initialize() {
        new Thread(()->{
            try {
                Socket remoteSocket  = new Socket("localhost",3008);
                dataOutputStream = new DataOutputStream(remoteSocket.getOutputStream());

                dataInputStream = new DataInputStream(remoteSocket.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

                String message = "";
                while (!message.equals("end")) {
                    message=dataInputStream.readUTF();
                    txtArea.appendText("\nServer: "+message);

                }

                dataInputStream.close();
                dataOutputStream.close();
                bufferedReader.close();
                remoteSocket.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            } }).start();

    }
    public void btnSendOnAction(ActionEvent event) throws IOException {
        dataOutputStream.writeUTF(txtMessage.getText().trim());
        dataOutputStream.flush();
        txtMessage.setText(" ");
    }



}
