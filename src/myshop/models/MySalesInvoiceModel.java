package myshop.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MySalesInvoiceModel {
	
	 public Label serialNo;
	 public ComboBox<String> productName;
	 public Label availableQuantity;
	 public TextField quantity;
	 public TextField rate;
	 public TextField amount;
	 
	 
	 
	public MySalesInvoiceModel(ObservableList<String> prodNameList) {
		super();
		this.serialNo = new Label();
		this.availableQuantity=new Label();
		this.quantity=new TextField();
		this.rate = new TextField();
		this.amount=new TextField();
		
		
		
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
	public Label getAvailableQuantity() {
		return availableQuantity;
	}
	public void setAvailableQuantity(Label availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	public TextField getQuantity() {
		return quantity;
	}
	public void setQuantity(TextField quantity) {
		this.quantity = quantity;
	}
	public TextField getRate() {
		return rate;
	}
	public void setRate(TextField rate) {
		this.rate = rate;
	}
	public TextField getAmount() {
		return amount;
	}
	public void setAmount(TextField amount) {
		this.amount = amount;
	}
	
	
}
