package com.example.pratos;

import com.example.pratos.dao.PratosDAO;
import com.example.pratos.model.Pratos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PratoController {


    @FXML
    public void initialize() {
        cmbCateg.getItems().removeAll(cmbCateg.getItems());
        cmbCateg.getItems().addAll("<Selecione>", "Japonesa", "Brasileira", "Italiana", "Mexicana");
        cmbCateg.getSelectionModel().select("<Selecione>");

        txtValor.textProperty().addListener((observable, oldValue, newValue) -> {
            // Remove quaisquer caracteres que não sejam números ou vírgulas
            String filtered = newValue.replaceAll("[^\\d.]", "");
            txtValor.setText(filtered);
        });
    }

    @FXML
    private Button btnRg;

    @FXML
    private ComboBox<String> cmbCateg;

    @FXML
    private TextField txtDescri;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtValor;

    @FXML
    void clickRg(ActionEvent event) {
        if (txtNome.getText().isEmpty() || txtValor.getText().isEmpty()
                || txtDescri.getText().isEmpty()
                || cmbCateg.getSelectionModel().getSelectedItem().equals("<Selecione>")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ALERTA");
            alert.setHeaderText("ERRO!");
            alert.setContentText("Preencha todos os dados.");
            alert.showAndWait();
        } else if (!isValidNumber(txtValor.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ALERTA");
            alert.setHeaderText("ERRO!");
            alert.setContentText("Valor inválido");
            alert.showAndWait();
        } else {
            PratosDAO pratosDAO = new PratosDAO();
            Pratos pratos = new Pratos();
            pratos.setCategoria(cmbCateg.getSelectionModel().getSelectedItem());
            pratos.setDescricao(txtDescri.getText());
            pratos.setNomeprato(txtNome.getText());
            pratos.setValor(Float.parseFloat(txtValor.getText()));
            Pratos novoPrato = pratosDAO.create(pratos);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCESSO!");
            alert.setHeaderText("Cadastro Efetuado com Sucesso!");
            alert.setContentText
                    ("Código do Prato: >>  " + novoPrato.getId()
                        +"\nNome: " + novoPrato.getNomeprato()
                    + "\nCategoria: " + novoPrato.getCategoria()
                    + "\nValor: R$ " + novoPrato.getValor()
                    + "\nDescrição: " + novoPrato.getDescricao());
            alert.showAndWait();
            txtValor.setText("");
            txtNome.setText("");
            txtDescri.setText("");
            cmbCateg.getSelectionModel().select("<Selecione>");
        }
    }

    private boolean isValidNumber(String text) {
        // Verifica se há mais de uma vírgula no texto
        if (text.indexOf(".") != text.lastIndexOf(".")) {
            return false;
        }

        if (text.startsWith(".") || text.endsWith(".")) {
            return false;
        }

        return true;
    }

}
