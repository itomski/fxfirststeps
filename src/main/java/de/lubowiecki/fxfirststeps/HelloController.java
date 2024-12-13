package de.lubowiecki.fxfirststeps;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

// Initializable definiert eine Methode die automatisch ausgeführt wird
public class HelloController implements Initializable {

    @FXML
    private Label output;

    @FXML
    private TextField input;

    private List<String> eintraege = new ArrayList<>();

    private static final String FILE = System.getProperty("user.home") + "/einkaufsliste.ser";

    @FXML
    protected void showInput() {
        eintraege.add(input.getText());
        saveToFile();
        input.setText("");
        print();
    }

    private void print() {
        StringBuilder sb = new StringBuilder();
        for(String line : eintraege)
            sb.append(line).append("\n");

        output.setText(sb.toString());
    }

    private void saveToFile() {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(eintraege);
        }
        catch(IOException e) {
            // TODO: Ausgabe des Fehlers
        }
    }

    private void loadFromFile() {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
           eintraege = (List<String>)in.readObject();
        }
        catch(Exception e) {
            // TODO: Ausgabe des Fehlers
            eintraege = new ArrayList<>();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadFromFile();
        print();
    }
}