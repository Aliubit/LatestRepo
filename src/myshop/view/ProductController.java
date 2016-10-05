package myshop.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import myshop.Main;
import myshop.dbhandler.DBQuries;

public class ProductController implements Initializable{

	@FXML
	private TextField prodNameTextField;
	@FXML 
	private TextField prodIdTextField;
	@FXML 
	private Button cancelBtn;
	
	
	Scene scene;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	
		/*cancelBtn.setOnKeyPressed(
					event ->{
						System.out.println("mera kaam");
					}
				);*/
		
		
		
	/*		cancelBtn.setOnKeyPressed(new EventHandler<KeyEvent>() {
				public void handle(KeyEvent ke){
					if(ke.getCode().equals(KeyCode.ESCAPE)){
						System.out.println("key pressed is "+ke.getCode());
						scene.getWindow().hide();
						//addDialogStage.close();
					}
				}
			});	*/	
		}
	
	@FXML
	public void addButtonPressed(){
		
		String prodname = prodNameTextField.getText();
		String prodId =  prodIdTextField.getText();
		
		if(prodname.length() >0 && prodId.length() > 0){
			DBQuries query = new DBQuries();
			int result = query.insertIntoProducts(prodId, prodname);
			if(result==1)
			{
				Main.successDialogBox();
				prodNameTextField.setText("");
				prodIdTextField.setText("");
			}
			else
				Main.faillureDialogBox("Item with same name and id already exists");
		}
		
		else
		{
			Main.promptDialogBox("Please fill all the required fields");
		}
		/////Nahi HOrha yR
		/*if(ButtonType.YES.equals(ButtonData.YES)){
			System.out.println("Ok Pressed");
		}
		else if(ButtonType.CANCEL != null && ButtonData.CANCEL_CLOSE != null){
			System.out.println("Cancel Pressed");
		}*/
	}
	
/*	public void faillureDialogBox(String message){
		Alert fail= new Alert(AlertType.INFORMATION);
        fail.setHeaderText("failure");
        fail.setContentText(message);
        fail.showAndWait();
	}
	
	public void successDialogBox(){
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText("Succes");
        alert.setContentText("Record Added !");
        alert.showAndWait();
    
	}
*/
	public void setScene(Scene scene) { 
		this.scene = scene; 
		scene.setOnKeyPressed(
				event->{
					switch(event.getCode()){
					case ESCAPE:
						cancelButtonPressed();
					default:
						break;
					}
				}
			);
	}
	

/*
	public void keyboardListener(){
		
		scene.);
	}*/

	
	@FXML
	public void cancelButtonPressed(){
		
	
		
		scene.getWindow().hide();
		
	}


		
	
	
}
