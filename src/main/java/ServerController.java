import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {

    @FXML
    private TextArea txtArea;

    @FXML
    private TextField txtMessage;

    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    String message="";
    public void initialize() {
        new Thread(()->{
            try {
                txtArea.appendText("Server Started !");
                ServerSocket serverSocket = new ServerSocket(3008);
                Socket localSocket = serverSocket.accept();

                dataInputStream = new DataInputStream(localSocket.getInputStream());
                dataOutputStream = new DataOutputStream(localSocket.getOutputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

                String message = "";

                while (!message.equals("end")) {
                    message = dataInputStream.readUTF();
                    txtArea.appendText( "\nClient : " + message);

                }

                dataInputStream.close();
                dataOutputStream.close();
                bufferedReader.close();
                localSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }}).start();

    }
    public void btnSendOnAction(ActionEvent event) throws IOException {
        String reply = txtMessage.getText();
        dataOutputStream.writeUTF(reply.trim());
        dataOutputStream.flush();
        txtMessage.setText(" ");
    }
}

