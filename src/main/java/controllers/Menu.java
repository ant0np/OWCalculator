package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import utils.Parser;
import utils.Validator;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu {

    private static final Logger logger = Logger.getLogger(Menu.class.getName());

    private FileChooser fileChooser;
    private File in;
    private File out;

    @FXML private Button calculate, show;
    @FXML private Button browse;
    @FXML private TextField selected, solved;
    @FXML private Label valid;

    @FXML
    public void initialize() {
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("(*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Select xml file");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + Parser.location));
        //selected.setFocusTraversable(true);
        show.setDisable(true);
        valid = new Label("");
    }

    @FXML
    public void evaluateResult() {

        if (in == null) {
            logger.log(Level.INFO, "Select xml file");
            valid = new Label("Select xml file");
            valid.setStyle("\n" +
                    "-fx-text-fill: rgb(208, 73, 57);" +
                    "visibility: visible");
            return;

        }

        if (Validator.validate(in)) {
            logger.log(Level.INFO, "Success");
            valid.setText("Success");
            valid.setStyle("\n" +
                    "-fx-text-fill: rgb(44, 171, 64);" +
                    "visibility: visible");

            Parser parser = new Parser();
            out = parser.evaluateResult(in);

            solved.requestFocus();

            if (out != null) {

                solved.setText(out.getName());
                show.setDisable(false);


            } else {

                logger.log(Level.SEVERE, "Error: out file is missing");
                valid.setText("Error: out file is missing");
                valid.setStyle("\n" +
                        "-fx-text-fill: rgb(208, 73, 57);" +
                        "visibility: visible");

            }


        } else {

            logger.log(Level.WARNING, "Error: No valid xml");
            valid.setText("Error: No valid xml");
            valid.setStyle("\n" +
                    "-fx-text-fill: rgb(208, 73, 57);" +
                    "visibility: visible");

        }
    }

    @FXML
    public void browseXml() {

        browse.setOnAction(action -> {

            selected.setText("");
            solved.setText("");
            show.setDisable(true);

            in = fileChooser.showOpenDialog(calculate.getScene().getWindow());

            if (in == null) {
                logger.log(Level.WARNING, "No input file");
                valid.setText("Select xml file");
                valid.setStyle("\n" +
                        "-fx-text-fill: rgb(208, 73, 57);" +
                        "visibility: visible");
                return;
            }

            selected.requestFocus();
            selected.setText(in.getName());
        });

    }

    @FXML
    public void showResultXml() {
        show.setOnAction(action -> {
            try {
                Desktop.getDesktop().open(out);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error: open result xml\n" + e.toString(), e);
                valid.setText("Error: open result xml");
                valid.setStyle("\n" +
                        "-fx-text-fill: rgb(208, 73, 57);" +
                        "visibility: visible");
            }
        });
    }

}
