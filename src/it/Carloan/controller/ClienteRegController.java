package it.Carloan.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import it.Carloan.database.Crud;
import it.Carloan.controller.FrontController.FrontController;
import it.Carloan.controller.model.Cliente;

public class ClienteRegController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtMail;

    @FXML
    private Button btnReg;

    @FXML
    private TextField txtNome;

    @FXML
    private PasswordField txtPass2;

    @FXML
    private TextField txtCognome;

    @FXML
    private PasswordField txtPass1;
    
    @FXML
    private Hyperlink hylGoClienteLogin;
    
    @FXML
    void RegistraCliente(ActionEvent event) throws SQLException {
    	
    	// verifico che tutti i campi siano compilati
    	if(txtMail.getText().equals("")){
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERRORE");
    		alert.setHeaderText(null);
    		alert.setContentText("Errore: Il campo E-Mail NON può essere vuoto!");
    		alert.showAndWait();
    		}
    	else if(txtPass1.getText().equals("")){
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERRORE");
    		alert.setHeaderText(null);
    		alert.setContentText("Errore: Il campo Password NON può essere vuoto!");
    		alert.showAndWait();
    		}
    	else if(txtPass2.getText().equals("")){
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERRORE");
    		alert.setHeaderText(null);
    		alert.setContentText("Errore: Il campo Verifica Password NON può essere vuoto!");
    		alert.showAndWait();
    		}
    	else if(txtNome.getText().equals("")){
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERRORE");
    		alert.setHeaderText(null);
    		alert.setContentText("Errore: Il campo Nome NON può essere vuoto!");
    		alert.showAndWait();
    		}
    	else if(txtCognome.getText().equals("")){
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERRORE");
    		alert.setHeaderText(null);
    		alert.setContentText("Errore: Il campo Cognome NON può essere vuoto!");
    		alert.showAndWait();
    		}
    	else if(txtTelefono.getText().equals("")){
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERRORE");
    		alert.setHeaderText(null);
    		alert.setContentText("Errore: Il campo Telefono NON può essere vuoto!");
    		alert.showAndWait();
    		}
    	
    	else if (txtPass1.getText().equals(txtPass2.getText()) && (!txtPass1.getText().equals("")) && (!txtPass2.getText().equals(""))) {
    		System.out.println("OK!\n"+txtPass1.getText()+" - "+txtPass2.getText()+
    						   " Le password Corrispondono\n");
    		
    		try {
    	
    			Crud query = new Crud();
    	
    			Cliente nuovo = new Cliente(txtMail.getText(), txtPass1.getText(), txtNome.getText(), txtCognome.getText(), txtTelefono.getText());
    	
    			query.insertCliente(nuovo);
    			
    			Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Registrazione Cliente");
        		alert.setHeaderText(null);
        		alert.setContentText("Congratulazioni, ti sei registrato con successo.\nClicca per andare alla Home Cliente");
        		alert.showAndWait();
    			
        		FrontController.mailUtente = txtMail.getText();
		    	   
		    	   Stage stage = new Stage();
		    	   try {
		    			FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("view/ClienteHome.fxml"));
			    	
		    			Scene homecliente = new Scene(loader.load());
		    			stage.setScene(homecliente);
			    	
		    			stage.show();
			    	
		    			((Node)event.getSource()).getScene().getWindow().hide();
		    		} catch (Exception e) {
		    								System.out.println("Si e' verificato un errore imprevisto");
		    								e.printStackTrace();
		    								}
    			
    			} catch (SQLException e) {
    						System.out.println("Codice Errore: "+e.getErrorCode()+"\nMessaggio Errore: "+e.getMessage());
    									}
    		}
    	else {
    		System.out.println("Password diverse!\n"+txtPass1.getText()+" - "+txtPass2.getText());
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("ERRORE");
    		alert.setHeaderText(null);
    		alert.setContentText("Errore: Le password inserite NON corrispondono!");
    		alert.showAndWait();
    	}
    }

    @FXML
    void backToClienteLogin(ActionEvent event) {
    	Stage stage = new Stage();
    	try {
	    	FXMLLoader loader = new FXMLLoader(HomeController.class.getResource("view/ClienteLogin.fxml"));
	    	Scene homescena = new Scene(loader.load());
	    	stage.setScene(homescena);
	    	stage.show();
	    	((Node)event.getSource()).getScene().getWindow().hide();
    	} catch (Exception e) {
    		System.out.println("Si e' verificato un errore imprevisto");
        	e.printStackTrace();
    	}
    }
    
    @FXML
    void initialize() {
        assert txtTelefono != null : "fx:id=\"txtTelefono\" was not injected: check your FXML file 'ClienteReg.fxml'.";
        assert txtMail != null : "fx:id=\"txtMail\" was not injected: check your FXML file 'ClienteReg.fxml'.";
        assert btnReg != null : "fx:id=\"btnReg\" was not injected: check your FXML file 'ClienteReg.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'ClienteReg.fxml'.";
        assert txtPass2 != null : "fx:id=\"txtPass2\" was not injected: check your FXML file 'ClienteReg.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'ClienteReg.fxml'.";
        assert txtPass1 != null : "fx:id=\"txtPass1\" was not injected: check your FXML file 'ClienteReg.fxml'.";

    }
}
