package com.example.restaurante;

import com.example.restaurante.dao.LoginDAO;
import com.example.restaurante.model.Pessoa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static com.example.restaurante.model.Criptografia.gerarHash;


public class LoginControll {

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnLogin;

    @FXML
    private CheckBox notRobot;

    @FXML
    private Label txtTopo;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtUser;

    @FXML
    void clickCadastro(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cadastropane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) btnCadastrar.getScene().getWindow();
        stage1.hide();
    }

    @FXML
    void clickLogar(ActionEvent event) throws NoSuchAlgorithmException, IOException {

        if(txtUser.getText().isEmpty() || txtSenha.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ALERTA");
            alert.setHeaderText("ERRO!");
            alert.setContentText("Informe o e-mail e a senha");
            alert.showAndWait();
        } else if(! validarEmail(txtUser.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ALERTA");
            alert.setHeaderText("ERRO!");
            alert.setContentText("Formato de e-mail inválido.");
            alert.showAndWait();
        } else{
            LoginDAO loginDAO = new LoginDAO();
            Pessoa pessoa = new Pessoa();
            boolean resposta = loginDAO.searchByEmail(txtUser.getText().toLowerCase());
            if(resposta){
                pessoa = loginDAO.findByEmail(txtUser.getText().toLowerCase());
                if(gerarHash(txtSenha.getText()).equals(pessoa.getSenha())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("LOGIN");
                    alert.setContentText("LOGIN REALIZADO COM SUCESSO!");
                    alert.showAndWait();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("mostrapratos.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                    Stage stage1 = (Stage) btnCadastrar.getScene().getWindow();
                    stage1.hide();
                } else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ALERTA");
                    alert.setHeaderText("ERRO!");
                    alert.setContentText("Email ou Senha Inválido(s)");
                    alert.showAndWait();
                }
            }
        }
    }

    public boolean validarEmail(String email) {
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$";
        return email.matches(regex);
    }

}