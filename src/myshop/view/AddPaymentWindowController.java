package myshop.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class AddPaymentWindowController implements Initializable {
	
	@FXML
	private DatePicker datePicker;
	@FXML
	private CheckBox suuplierCheckBox;
	@FXML
	private CheckBox customerCheckBox;
	@FXML
	private RadioButton bankRadioButton;
	@FXML
	private RadioButton cashRadioButton;
	@FXML
	private Label dealerLabel;
	@FXML 
	private ComboBox dealerComboBox;
	@FXML
	private TextField amountTextField;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	void addPressed()
	{
		
	}


}
