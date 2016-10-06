package myshop.view;

import java.net.URL;
import java.sql.Date;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import myshop.Main;
import myshop.dbhandler.DBQuries;
import myshop.models.AutoCompleteComboBoxListener;
import myshop.models.MySalesInvoiceModel;

public class SalesInvoiceController implements Initializable{

	@FXML
	public BorderPane borderPane;
	ContextMenu cm;
	@FXML
	private DatePicker datePicker;
	@FXML
	private TextField recieptNoTextField;
	@FXML
	private ComboBox<String> customerNameComboBox;
	@FXML
	private ComboBox<String> prodNameComboBox;
	
	
	public  TableView<MySalesInvoiceModel> table = new TableView();

	public  TableColumn serialNoColumn;
	 
	public  TableColumn productColumn;
	 
	public TableColumn availableQuntityColumn;
	 
	public  TableColumn quantityColumn;
	
	public TableColumn rateColumn;
	
	public TableColumn amountColumn;
	@FXML
	public VBox vbox;
	@FXML
	private GridPane gridPane;

	int rowCounter;
	
	@FXML
	private TextField quantityTextField;
	@FXML
	private TextField rateTextField;
	@FXML
	private TextField amountTextField;
	@FXML
	private  Label availableQuantityLabel;
	@FXML
	private Button addButton;
	@FXML
	private Button cancelButton;
	private ObservableList<String> customerNameList;
	private ObservableList<String> productNameList;
	
	private ObservableList<MySalesInvoiceModel> tableData;
	
	String ds;
	
	int result;
	
	boolean check;
	
	Scene scene;
	DBQuries query = new DBQuries();

	@FXML
	public void add5MoreRowsPressed()
	{
		int localCOunter=rowCounter;
        for(int i=rowCounter;i<rowCounter+5;i++)
        {
     	   MySalesInvoiceModel model = new MySalesInvoiceModel(productNameList);
     	  // model.productName.setItems(productNameList);
     	   model.getSerialNo().setText((i+1)+"");
     	   tableData.add(model);
     	   localCOunter++;
        }
	rowCounter=localCOunter;
	
	
	table.setItems(tableData);
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		rowCounter=0;
		////Queries
		ResultSet rset1 = query.getAllProductsToSell();
		
		ResultSet rset2 = query.getAllCustomersName();
		
		customerNameList = FXCollections.observableArrayList();
		productNameList = FXCollections.observableArrayList();
		try {
			while(rset1.next()){
				productNameList.add(rset1.getString("product_Id")+"_"+rset1.getString("product_Name"));
				//System.out.println(rset1.getInt("quantity"));
			}
			
			while(rset2.next()){
				customerNameList.add(rset2.getString("name")+"_"+rset2.getString("mobile_No_1"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//prodNameComboBox.setItems(productNameList);
		
		customerNameComboBox.setItems(customerNameList);
		new AutoCompleteComboBoxListener<>(customerNameComboBox);
		////End Queries
		
		
		
		tableData =FXCollections.observableArrayList();
		
		
		table.setEditable(true);
	  
	 
        serialNoColumn = new TableColumn("Serial No");
        serialNoColumn.setMinWidth(20);
        serialNoColumn.setCellValueFactory(new PropertyValueFactory<MySalesInvoiceModel, Label>("serialNo"));
        
        
        ObservableList<String> cbValues = FXCollections.observableArrayList("1", "2", "3", "4", "5");
        productColumn = new TableColumn("Product Column");
        productColumn.setMinWidth(100);
        productColumn.setCellValueFactory(new PropertyValueFactory<MySalesInvoiceModel, ComboBox>("productName"));
        
        availableQuntityColumn = new TableColumn("Available Qty");
        availableQuntityColumn.setMinWidth(100);
        availableQuntityColumn.setCellValueFactory(new PropertyValueFactory<MySalesInvoiceModel, Label>("availableQuantity"));
      
        quantityColumn = new TableColumn("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<MySalesInvoiceModel, TextField>("quantity"));
        

        rateColumn = new TableColumn("Rate");
        rateColumn.setMinWidth(100);
        rateColumn.setCellValueFactory(new PropertyValueFactory<MySalesInvoiceModel, TextField>("rate"));
       
        amountColumn = new TableColumn("Amount");
        amountColumn.setMinWidth(100);
        amountColumn.setCellValueFactory(new PropertyValueFactory<MySalesInvoiceModel, TextField>("amount"));
   
           for(int i=0;i<5;i++)
           {
        	   MySalesInvoiceModel model = new MySalesInvoiceModel(productNameList);
        	   //model.productName.setItems(productNameList);
        	   model.getSerialNo().setText((i+1)+"");
        	   tableData.add(model);
        	   rowCounter++;
           }
       
	        table.setMaxHeight(200);
	        table.setItems(tableData);
	 
	       table.getColumns().addAll(serialNoColumn, productColumn,availableQuntityColumn,quantityColumn,rateColumn,amountColumn);
	        
	        
	        for(MySalesInvoiceModel model : tableData)
	        {
	        	
	        	//Listener on selection of product combobox
              model.productName.setOnAction(e -> {
              
            	  @SuppressWarnings("unchecked")
				ComboBox<String> clickedComboBox = (ComboBox<String>)e.getSource();
            	  
            	  for(MySalesInvoiceModel model1 : tableData)
            	  {
            		  if(model1.productName.equals(clickedComboBox))
            		  {
            			 // System.out.println("Milgya bhai");
            			  try {
							productSelected(clickedComboBox, model.getAvailableQuantity());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
            			  break;
            		  }
            	  }
            	  
            	  
              });
              
              
              
              model.quantity.setOnKeyReleased(e-> {
              TextField typedQtyTextField = (TextField) e.getSource();
              for(MySalesInvoiceModel model2 : tableData)
              {
            	  
            	  if(model2.quantity.equals(typedQtyTextField))
            	  {
            		  try {
						quantityLeft(typedQtyTextField,model2.rate,model2.amount,model2.availableQuantity);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            		  break;
            	  }
            	  
              }
              });
              
              model.rate.setOnKeyReleased(e-> {
                  TextField typedQtyTextField = (TextField) e.getSource();
                  for(MySalesInvoiceModel model2 : tableData)
                  {
                	  
                	  if(model2.rate.equals(typedQtyTextField))
                	  {
                		  try {
    						quantityLeft(model2.quantity,model2.rate,model2.amount,model2.availableQuantity);
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
		
	/////////////////////////// Controlling Methods //////////////////////////////////////
	/*
	 * 
	 * datePicker.getValue()!=null && recieptNoTextField.getText().length()>0 && customerNameComboBox.getValue()!=null 
				   && prodNameComboBox.getValue()!=null && quantityTextField.getText().length()>0 && rateTextField.getText().length()>0 && amountTextField.getText().length()>0;
	
	 * 	
	 */
	@FXML
	public void addButonPressed(){
		
		for(MySalesInvoiceModel model : tableData){
	
			if(datePicker.getValue() != null && recieptNoTextField.getText().length() > 0 && customerNameComboBox.getValue() != null
					&& model.productName.getValue() != null && model.quantity.getText().length() > 0 && model.rate.getText().length() > 0 && model.amount.getText().length() > 0){
				
			//if(areAllFieldsSelected()){
				/*if(!(Integer.parseInt(availableQuantityLabel.getText())>=0))
				{
					Main.faillureDialogBox("You do not have sufficient stock ");
					return;
				}
				*/
				LocalDate currDate = datePicker.getValue();
				
				int year= currDate.getYear();
				int month=currDate.getMonthValue();
				int date =currDate.getDayOfMonth();
				
				////
				LocalDate locald = LocalDate.of(year, month,date);
				Date myDate = Date.valueOf(locald); // Magic happens here!
				//r.setDateOfBirth(date);
				
				////
				/*String dateFormat = year+"-"+month+"-"+date;
				Date myDate;
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				try {
					 myDate = (Date) formatter.parse(dateFormat);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					myDate=null;
				}*/
				////////////
			//	String dateString = datePicker.getValue();
				
				////////////
				String recieptNo = recieptNoTextField.getText();
				String quantity = model.quantity.getText();
				String rate = model.rate.getText();
				String amount = model.amount.getText();
				String customer = customerNameComboBox.getValue();
				String products = model.productName.getValue();
				String[] str =customer.split("_");
				String[] str1 =products.split("_");
				
				DBQuries query1 = new DBQuries();
				result = query1.insertIntoSalesHistory(myDate, str[0], str[1], recieptNo, str1[1], str1[0],Integer.parseInt(quantity) , Float.parseFloat(rate),Float.parseFloat(amount));
				
		}/*
		else{
			Main.faillureDialogBox("Please fill all the required fields");
		}*/
		}
		
		for(MySalesInvoiceModel model : tableData){
		if(result == 1){
			check = true;
			//Main.successDialogBox();
			datePicker.getEditor().setText("");
			recieptNoTextField.setText("");
			customerNameComboBox.setValue("");
			model.productName.setValue("");
			model.quantity.setText("");
			model.rate.setText("");
			model.amount.setText("");
			model.availableQuantity.setText("");
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
	
	@FXML
	public void cancelButtonPressed(){
		scene.getWindow().hide();
	}
		

	public void productSelected(ComboBox<String> prodCmbo,Label avqty) throws SQLException{ 
		
		prodNameComboBox=prodCmbo;
		availableQuantityLabel=avqty;
		
		//// Available Quantitty k label p action laga wa H uski wajah se error aarha H shyd resolve krwana H Ali se
		if(prodNameComboBox.getValue().length() >0)
		{
		String str = prodNameComboBox.getValue();
		String[] str1 = str.split("_");
		String product_Id = str1[0];
		String product_Name = str1[1];
		ResultSet rset=query.getAllQuantityAvailable(product_Id, product_Name);
		ds=null;
		while(rset.next()){
			 ds = rset.getString("quantity");
		}
		availableQuantityLabel.setText(ds);
		
		}
	}
	
	public  void quantityLeft(TextField qty,TextField rate,TextField Amount,Label avqty) throws SQLException{
		quantityTextField=qty;
		availableQuantityLabel=avqty;
		keyPressed(qty,rate,Amount);
		
		if(quantityTextField.getText().length() >0)	{
			//System.out.println("im qty left" );
			
			//String n1 = availableQuantityLabel.getText();
			String n1=ds;
			int pn1 = Integer.parseInt(n1);
		
			String n2 = quantityTextField.getText();
			int pn2 = Integer.parseInt(n2);
			int res = pn1-pn2;
			String str =Integer.toString(res);
			
			//System.out.println(pn1  +" "+pn2 );
			
			availableQuantityLabel.setText(str);
		}
		else
			availableQuantityLabel.setText(ds);
	}
	/*
	private boolean areAllFieldsSelected() {
		return datePicker.getValue()!=null && recieptNoTextField.getText().length()>0 && customerNameComboBox.getValue()!=null 
				   && prodNameComboBox.getValue()!=null && quantityTextField.getText().length()>0 && rateTextField.getText().length()>0 && amountTextField.getText().length()>0;
	}
	*/
	///////////////////////zafar
	
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

	
	////////////////////////////

	

	
	
}


