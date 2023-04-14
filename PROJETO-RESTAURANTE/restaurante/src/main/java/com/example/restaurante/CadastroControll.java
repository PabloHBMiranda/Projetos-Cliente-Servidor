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

import java.security.NoSuchAlgorithmException;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.example.restaurante.model.Criptografia.gerarHash;

public class CadastroControll {

    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnFechar;

    @FXML
    private Button btnLimpar;

    @FXML
    private CheckBox checTermo;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Hyperlink link;

    @FXML
    void clickFechar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginpane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        Stage stage1 = (Stage) btnFechar.getScene().getWindow();
        stage1.hide();
    }

    @FXML
    void clickCadastrar(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        if(txtEmail.getText().isEmpty() || txtSenha.getText().isEmpty() || txtNome.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ALERTA");
            alert.setHeaderText("ERRO!");
            alert.setContentText("Não deixe nenhum campo vazio.");
            alert.showAndWait();
        } else if(!validarEmail(txtEmail.getText().toLowerCase())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ALERTA");
            alert.setHeaderText("E-mail inválido");
            alert.showAndWait();
        } else if(!checTermo.isSelected()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ALERTA");
            alert.setHeaderText("ATENÇÃO");
            alert.setContentText("É necessário confirmar os termos de uso");
            alert.showAndWait();
        }
        else{
            LoginDAO loginDAO = new LoginDAO();
            Pessoa pessoa = new Pessoa();
            boolean resposta = loginDAO.searchByEmail(txtEmail.getText().toLowerCase());
            if(!resposta && !validarEmail(txtNome.getText().toLowerCase()) && !validarEmail(txtSenha.getText().toLowerCase())){
                //Cadastra a nova pessoa no banco
                pessoa.setNome(txtNome.getText());
                pessoa.setEmail(txtEmail.getText().toLowerCase());
                pessoa.setSenha(gerarHash(txtSenha.getText()));
                Pessoa novaPessoa = loginDAO.create(pessoa);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SUCESSO!");
                alert.setHeaderText("Cadastro Efetuado com Sucesso!");
                alert.setContentText
                        ("Nome: " + novaPessoa.getNome()
                        +"\nE-mail: " + novaPessoa.getEmail());
                alert.showAndWait();

                //Abre a página de Login
                FXMLLoader loader = new FXMLLoader(getClass().getResource("loginpane.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                Stage stage1 = (Stage) btnFechar.getScene().getWindow();
                stage1.hide();
            } else {
                if(resposta) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("ALERTA");
                    alert.setHeaderText("ATENÇÃO");
                    alert.setContentText("O e-mail informado está sendo utilizado.");
                    alert.showAndWait();
                } else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ALERTA");
                    alert.setHeaderText("ATENÇÃO");
                    alert.setContentText("Insira um nome ou senha que seja diferente do seu endereço de e-mail.");
                    alert.showAndWait();
                }
            }
        }
    }

    @FXML
    void clickLimpar(ActionEvent event) {
        txtNome.setText("");
        txtSenha.setText("");
        txtEmail.setText("");
        checTermo.setSelected(false);
    }
    public boolean validarEmail(String email) {
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$";
        return email.matches(regex);
    }


    @FXML
    void clickLink(ActionEvent event) {
        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI("https://learn.microsoft.com/pt-br/legal/termsofuse"));
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }
    }

}
