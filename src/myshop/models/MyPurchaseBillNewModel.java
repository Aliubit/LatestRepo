package myshop.models;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MyPurchaseBillNewModel {

	public Label serialNo;
	//public String productId;
	public ComboBox<String> productName;
	public TextField productQuantity;
	public TextField productRate;
	public TextField productAmount;
	
	public MyPurchaseBillNewModel(ObservableList<String> prodNameList){
		super();
		this.serialNo = new Label();
		this.productQuantity=new TextField();
		this.productRate=new TextField();
		this.productAmount=new TextField();
		
		ComboBox<String> comboBox = new ComboBox<>();
		comboBox.setEditable(true);
        comboBox.setItems(prodNameList);
		this.productName = comboBox;
		new AutoCompleteComboBoxListener<>(comboBox);
	}

	public Label getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Label serialNo) {
		this.serialNo = serialNo;
	}

	public ComboBox<String> getProductName() {
		return productName;
	}

	public void setProductName(ComboBox<String> productName) {
		this.productName = productName;
	}

	public TextField getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(TextField productQuantity) {
		this.productQuantity = productQuantity;
	}

	public TextField getProductRate() {
		return productRate;
	}

	public void setProductRate(TextField productRate) {
		this.productRate = productRate;
	}

	public TextField getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(TextField productAmount) {
		this.productAmount = productAmount;
	}
	
	
	
	
}
