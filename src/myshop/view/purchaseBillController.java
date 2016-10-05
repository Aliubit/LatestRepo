package myshop.view;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import myshop.Main;
import myshop.dbhandler.ConnectToDB;
import myshop.dbhandler.DBQuries;
import myshop.models.AutoCompleteComboBoxListener;
import myshop.models.MyPurchaseBillNewModel;
import myshop.models.MySalesInvoiceModel;

public class purchaseBillController implements Initializable{

	@FXML
	private DatePicker datePicker;
	@FXML
	private TextField billNoTextField;
	@FXML
	private ComboBox<String> supplierNameComboBox;
	@FXML
	private ComboBox<String> prodNameComboBox;
	@FXML
	private TextField quantityTextField;
	@FXML
	private TextField rateTextField;
	@FXML
	private TextField amountTextField;
	@FXML
	private Button addButton;
	
	
	public ObservableList<String> supplierNameList;
	public ObservableList<String> productNameList;
	
	ContextMenu cm;
	
	
	public  TableView<MyPurchaseBillNewModel> table = new TableView();

	public  TableColumn serialNoColumn;
	 
	public  TableColumn productColumn;
	 	 
	public  TableColumn quantityColumn;
	
	public TableColumn rateColumn;
	
	public TableColumn amountColumn;
	@FXML
	public VBox vbox;
	@FXML
	private GridPane gridPane;

	private ObservableList<MyPurchaseBillNewModel> tableData;
	
	int rowCounter,result;

	String text;

	Scene scene;
	boolean check;
	
	@FXML
	public void addFiveMoreRowsPressed()
	{
		int localCOunter=rowCounter;
        for(int i = rowCounter ; i < rowCounter+5 ; i++)
        {
     	   MyPurchaseBillNewModel model = new MyPurchaseBillNewModel(productNameList);
     	  // model.productName.setItems(productNameList);
     	   model.getSerialNo().setText((i+1)+"");
     	   tableData.add(model);
     	   localCOunter++;
        }
	rowCounter=localCOunter;
	
	
	table.setItems(tableData);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rowCounter = 0;
		DBQuries query = new DBQuries();
		ResultSet rset = query.getAllProducts();
		ResultSet rset1 = query.getAllSuppliersName();
		
		
		text= new String("");
		supplierNameList = FXCollections.observableArrayList();
		productNameList = FXCollections.observableArrayList();
		
		try{
			while(rset.next()){
				productNameList.add(rset.getString("product_Id")+"_"+rset.getString("product_Name"));
			}
			
			while(rset1.next()){
				supplierNameList.add(rset1.getString("name")+"_"+rset1.getString("mobile_No_1"));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		//prodNameComboBox.setItems(productNameList);
		supplierNameComboBox.setItems(supplierNameList);
		new AutoCompleteComboBoxListener<>(supplierNameComboBox);
 
		tableData = FXCollections.observableArrayList();
		
		table.setEditable(true);
		  
		 
	    serialNoColumn = new TableColumn("Serial No");
	    serialNoColumn.setMinWidth(20);
	    serialNoColumn.setCellValueFactory(new PropertyValueFactory<MyPurchaseBillNewModel, Label>("serialNo"));
	        
        
       // ObservableList<String> cbValues = FXCollections.observableArrayList("1", "2", "3", "4", "5");
        productColumn = new TableColumn("Product Column");
        productColumn.setMinWidth(100);
        productColumn.setCellValueFactory(new PropertyValueFactory<MyPurchaseBillNewModel, ComboBox>("productName"));
        
        quantityColumn = new TableColumn("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<MyPurchaseBillNewModel, TextField>("productQuantity"));
        

        rateColumn = new TableColumn("Rate");
        rateColumn.setMinWidth(100);
        rateColumn.setCellValueFactory(new PropertyValueFactory<MyPurchaseBillNewModel, TextField>("productRate"));
       
        amountColumn = new TableColumn("Amount");
        amountColumn.setMinWidth(100);
        amountColumn.setCellValueFactory(new PropertyValueFactory<MyPurchaseBillNewModel, TextField>("productAmount"));

        for(int i=0;i<5;i++)
        {
        	MyPurchaseBillNewModel model = new MyPurchaseBillNewModel(productNameList);
     	   //model.productName.setItems(productNameList);
     	   model.getSerialNo().setText((i+1)+"");
     	   tableData.add(model);
     	   rowCounter++;
        }
    
	    table.setMaxHeight(200);
	    table.setItems(tableData);
	 
	    table.getColumns().addAll(serialNoColumn, productColumn,quantityColumn,rateColumn,amountColumn);
	        
	 

	    for(MyPurchaseBillNewModel model : tableData)
        {
        	
        model.productQuantity.setOnKeyReleased(e-> {
        TextField typedQtyTextField = (TextField) e.getSource();
        for(MyPurchaseBillNewModel model2 : tableData)
        {
      	  
      	  if(model2.productQuantity.equals(typedQtyTextField))
      	  {
      		  try {
					keyPressed(quantityTextField, rateTextField, amountTextField);
      			  //quantityLeft(typedQtyTextField,model2.rate,model2.amount,model2.availableQuantity);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
      		  break;
      	  }
      	  
        }
        });
        
        model.productRate.setOnKeyReleased(e-> {
            TextField typedQtyTextField = (TextField) e.getSource();
            for(MyPurchaseBillNewModel model2 : tableData)
            {
          	  
          	  if(model2.productRate.equals(typedQtyTextField))
          	  {
          		  try {
          			  keyPressed(quantityTextField, rateTextField, amountTextField);
						//quantityLeft(model2.quantity,model2.rate,model2.amount,model2.availableQuantity);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
          		  break;
          	  }
          	  
            }
            });
        }

	}
	
	public void handle( KeyEvent event ) {
		if(prodNameComboBox.isFocused())
		{
		//System.out.println("Aagaya Text is "+text);
	//	System.out.println("you typed "+event.getCharacter());
        if( event.getCode() == KeyCode.BACK_SPACE)
        {
            text = text.substring( 0, text.length() - 1 );
        }
        else
        {
        	 text += event.getCharacter();
        }
        for( String item: productNameList ) {
        	 if( item.startsWith(text)) 
        		 {
        		 prodNameComboBox.setValue(item);
        		 break;
        		 }
           // if( item.startsWith( text ) ) prodNameComboBox.setEffect( item );
        }
		}
		else
		{
			text="";
		}
        
       
    }
	
	//////////////////////////// Controlling Methods //////////////////////////////////////
	
	@FXML
	private void addButonPressed(){
		//if(areAllFieldsSelected() ){
		for(MyPurchaseBillNewModel model : tableData){
			
			if(datePicker.getValue() != null && billNoTextField.getText().length() > 0 && supplierNameComboBox.getValue() != null
					&& model.productName.getValue() != null && model.productQuantity.getText().length() > 0 && model.productRate.getText().length() > 0 && model.productAmount.getText().length() > 0){
			
		LocalDate currDate = datePicker.getValue();
		
		int year= currDate.getYear();
		int month=currDate.getMonthValue();
		int date =currDate.getDayOfMonth();
		
		String dateFormat = date+"/"+month+"/"+year;
		String billNo = billNoTextField.getText();
		String quantity = model.productQuantity.getText();
		String rate = model.productRate.getText();
		String amount = model.productAmount.getText();
		String supplier = supplierNameComboBox.getValue();
		String products = model.productName.getValue();
		String[] str =supplier.split("_");
		String[] str1 =products.split("_");
		
		DBQuries query = new DBQuries();
		result = query.insertIntoPurchaseHistory(dateFormat, str[0], str[1], billNo, str1[1], str1[0],Integer.parseInt(quantity) , Float.parseFloat(rate),Float.parseFloat(amount));
		}
		}
		
		for(MyPurchaseBillNewModel model : tableData){
		if(result == 1){
			check = true;
			//Main.successDialogBox();
			datePicker.getEditor().setText("");
			billNoTextField.setText("");
			supplierNameComboBox.setValue("");
			model.productName.setValue("");
			model.productQuantity.setText("");
			model.productRate.setText("");
			model.productAmount.setText("");
			//model.availableQuantity.setText("");
			//System.out.println("Which error yarrrrrr");
		}
		}
		if(check == true){
			Main.successDialogBox();
		}
		else{
			Main.faillureDialogBox("Check is not true");
		}
		
}
	
	
	/*
	public boolean areAllFieldsSelected(){
			return datePicker.getValue()!=null && billNoTextField.getText().length()>0 && supplierNameComboBox.getValue()!=null 
				   && prodNameComboBox.getValue()!=null && quantityTextField.getText().length()>0 && rateTextField.getText().length()>0 && amountTextField.getText().length()>0;
	}
	*/
	@FXML
	public void mouseDraggedOnAmountTextField(){
		Float rate = Float.parseFloat(rateTextField.getText());
		int quantity= Integer.parseInt(quantityTextField.getText());
		Float amount = rate * quantity;
		amountTextField.setText(amount+"");
	}
	
	
	public void keyPressed(TextField qty,TextField ratefield,TextField Amount){
		quantityTextField=qty;
		rateTextField=ratefield;
		amountTextField=Amount;
		if(quantityTextField.getText().length() > 0 && rateTextField.getText().length() > 0){
			Float rate = Float.parseFloat(rateTextField.getText());
			int quantity= Integer.parseInt(quantityTextField.getText());
			Float amount = rate * quantity;
			amountTextField.setText(amount+"");
		}
		else
			amountTextField.setText(0+"");
	}

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
	
	@FXML
	public void  cancelButtonPressed(){
		//Main.promptDialogBox("Are you sure you want to cancel this window");
		scene.getWindow().hide();
	}
}
