package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {

    @FXML
    private Button sendButton;
    @FXML
    private ListView<String> contactsListView;
    @FXML
    private ListView<String> chatListView;
    @FXML
    private TextField messageTextField;
    private Client client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setContactList();
        try {
            client = new Client(chatListView);
            client.connect();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        this.sendButton.setOnAction(actionEvent -> {
            try {
                this.client.send(this.messageTextField.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.messageTextField.clear();
        });

    }

    private void setContactList() {
        this.contactsListView.getItems().add("Victor Alcantara");
    }

}
