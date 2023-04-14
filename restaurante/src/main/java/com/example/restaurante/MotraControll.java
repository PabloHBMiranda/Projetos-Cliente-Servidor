package com.example.restaurante;

import com.example.restaurante.dao.PegarPratosDAO;
import com.example.restaurante.model.PegarPratos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

import javafx.scene.image.ImageView;
import  javafx.scene.image.Image;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MotraControll implements Initializable {

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnDeslogar;

    @FXML
    private Button btnLimpar;

    @FXML
    private Label txtCategoria;

    @FXML
    private Label txtDescricao;

    @FXML
    private Label txtID;

    @FXML
    private Label txtNome;

    @FXML
    private Label txtValor;

    @FXML
    private ImageView imagemB;

    @FXML
    private ListView<String> lista = new ListView<String>();

    @FXML
    void clickBuscar(ActionEvent event) {
        if (lista.getItems().isEmpty()) {
            List<PegarPratos> listaPegarDados = new PegarPratosDAO().findByAll();
            for (PegarPratos a : listaPegarDados) {
                String dados = "COD - " + a.getId() + " | Nome do Prato: " + a.getNomeprato() + " | Culinária: " + a.getCategoria() + " | R$ " + a.getValor()
                        + "\nDescrição: " + a.getDescricao();
                lista.getItems().add(dados);
            }
            if(!lista.getItems().isEmpty()){
                btnLimpar.setDisable(false);
            } else{
                btnLimpar.setDisable(true);
            }

        } else{
            lista.getItems().clear();
            lista.refresh();
            List<PegarPratos> listaPegarDados = new PegarPratosDAO().findByAll();
            for (PegarPratos a : listaPegarDados) {
                String dados = "COD - " + a.getId() + " | Nome do Prato: " + a.getNomeprato() + " | Culinária: " + a.getCategoria() + " | R$ " + a.getValor()
                        + "\nDescrição: " + a.getDescricao();
                lista.getItems().add(dados);
            }
            if(!lista.getItems().isEmpty()){
                btnLimpar.setDisable(false);
            } else{
                btnLimpar.setDisable(true);
            }
        }
    }

    @FXML
    void clickDeslogar(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("LOGIN");
        alert.setContentText("Usuário Deslogado!");
        alert.showAndWait();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginpane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) btnDeslogar.getScene().getWindow();
        stage1.hide();
    }

    @FXML
    void clickLimpar(ActionEvent event) {
        lista.getItems().clear();
        lista.refresh();
        btnLimpar.setDisable(true);
        txtID.setVisible(false);
        txtValor.setVisible(false);
        txtDescricao.setVisible(false);
        txtCategoria.setVisible(false);
        txtNome.setVisible(false);
        imagemB.setVisible(false);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imagemB.setVisible(false);
        clickLista();
    }
    private void clickLista(){
        lista.setOnMouseClicked(evento -> {
            Image imagemBrasileira = null;
            Image imagemItaliana = null;
            Image imagemJaponesa = null;
            Image imagemMexicana = null;
            try{
                imagemBrasileira = new Image(new FileInputStream("src/main/resources/brasileira.png"));
                imagemItaliana = new Image(new FileInputStream("src/main/resources/italiana.png"));
                imagemJaponesa = new Image(new FileInputStream("src/main/resources/japonesa.png"));
                imagemMexicana = new Image(new FileInputStream("src/main/resources/mexicana.png"));
            } catch(FileNotFoundException ex){
                throw new RuntimeException(ex);
            }
            int selectedIndex = lista.getSelectionModel().getSelectedIndex();
            String conteudoSelecionado = lista.getSelectionModel().getSelectedItem();
            lista.getItems().clear();
            lista.refresh();
            List<PegarPratos> listaPegarDados = new PegarPratosDAO().findByAll();
            for (PegarPratos a : listaPegarDados) {
                String dados = "COD - " + a.getId() + " | Nome do Prato: " + a.getNomeprato() + " | Culinária: " + a.getCategoria() + " | R$ " + a.getValor()
                        + "\nDescrição: " + a.getDescricao();
                lista.getItems().add(dados);
            }
            if (selectedIndex != -1) {
                if(!lista.getItems().isEmpty() && !listaPegarDados.isEmpty()){
                    PegarPratos novoPrato = listaPegarDados.get(selectedIndex);
                    String ID = String.valueOf(novoPrato.getId());
                    if(conteudoSelecionado.contains(ID)){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("UEPA!");
                        alert.setHeaderText("LISTA ATUALIZADA");
                        alert.setContentText("Prato Selecionado");
                        alert.showAndWait();

                        if(novoPrato.getCategoria().equals("Brasileira")){
                            imagemB.setImage(imagemBrasileira);
                        } else if(novoPrato.getCategoria().equals("Mexicana")){
                            imagemB.setImage(imagemMexicana);
                        } else if(novoPrato.getCategoria().equals("Italiana")){
                            imagemB.setImage(imagemItaliana);
                        } else{
                            imagemB.setImage(imagemJaponesa);
                        }

                        imagemB.setVisible(true);
                        txtID.setVisible(true);
                        txtValor.setVisible(true);
                        txtDescricao.setVisible(true);
                        txtCategoria.setVisible(true);
                        txtNome.setVisible(true);
                        txtID.setText("COD: " + novoPrato.getId());
                        txtNome.setText("NOME PRATO: " + novoPrato.getNomeprato());
                        txtValor.setText("R$ " + novoPrato.getValor());
                        txtCategoria.setText("CATEGORIA: " + novoPrato.getCategoria());
                        txtDescricao.setText("DESCRIÇÃO: " + novoPrato.getDescricao());


                    } else{
                        System.out.println(novoPrato.getId());
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("OPS!");
                        alert.setHeaderText("PRATO REMOVIDO");
                        alert.showAndWait();
                    }
                } else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("OPS!");
                    alert.setHeaderText("OCORREU UM ERRO");
                    alert.setContentText("TENTE NOVAMENTE!");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("OPS!");
                alert.setHeaderText("OCORREU UM ERRO");
                alert.setContentText("TENTE NOVAMENTE!");
                alert.showAndWait();
            }
        });
    }

}
