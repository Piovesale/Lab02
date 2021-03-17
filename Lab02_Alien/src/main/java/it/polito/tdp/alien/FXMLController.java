package it.polito.tdp.alien;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	Dictionary d = new Dictionary();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtParola;

    @FXML
    private TextArea txtShow;

    @FXML
    void doReset(ActionEvent event) {

    }

    @FXML
    void doTranslate(ActionEvent event) {
    	String[] parole = txtParola.getText().split(" ");
    	
    	if(parole.length==1 && parole[0].contains("?"))
    		{
    		String alien = txtParola.getText();
    		String ris = d.similarTransalate(alien);
    		
    		txtShow.setText("Possibili corrispondenze sono : "+ris);
    		return;
    		}
    	
    	txtShow.setText(null);
    	
    	Pattern pattern = Pattern.compile("[^A-Za-z]");
    	
    	for(int i=0; i<parole.length ; i++)
    		{
    		Matcher matcher = pattern.matcher(parole[i]);
    		boolean isStringContainsSpecialCharacter = matcher.find();
    		 if(isStringContainsSpecialCharacter)
    		 	{
    			 txtShow.setText("Errore nel formato di stringa");
    			 return;
    		 	}
    			 
    		}
    	
    	if (parole.length==2)
    		{
    		d.addWord(parole[0].toLowerCase(), parole[1].toLowerCase());
    		txtShow.setText("Parola : '" + parole[0] + "' aggiunta con traduzione '" + parole[1] + "'");
    		}
    	
    	if(parole.length==1)
    		{
    		txtShow.setText("Traduzioni trovate per la parola '" + parole[0] + "' : " + d.translateWord(parole[0].toLowerCase()));
    		}
    	
    	
    	
    }

    @FXML
    void initialize() {
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtShow != null : "fx:id=\"txtShow\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
