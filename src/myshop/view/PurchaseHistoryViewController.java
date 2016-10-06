package myshop.view;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.util.converter.LocalDateStringConverter;
import myshop.Main;
import myshop.dbhandler.DBQuries;
import myshop.models.PurchaseHistoryModel;



public class PurchaseHistoryViewController implements Initializable {

	@FXML
	public DatePicker datePicker;
	@FXML 
	public DatePicker datePicker1;
	@FXML
	public Button searchButton;
	@FXML 
	public  TableColumn<PurchaseHistoryModel,String> dateColumn;
	@FXML 
	public  TableColumn<PurchaseHistoryModel,String> billNoColumn;
	@FXML 
	public TableColumn<PurchaseHistoryModel,String> supplierIdColumn;
	@FXML 
	public  TableColumn<PurchaseHistoryModel,String> supplierNameColumn;
	@FXML
	public TableColumn<PurchaseHistoryModel,String> billedAmountColumn;
	@FXML
	public  TableView<PurchaseHistoryModel> table;
	
	public static String clickedBillNo,clickedName,clickedId,clickedDate,clickedAmount;
	
	public PurchaseHistoryModel model;
	
	public static ObservableList<PurchaseHistoryModel> list;
	
	String columnOfBill,columnOfpartyName,columnOfpartyId,columnOfAmount,columnOfDate;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		
		//System.out.println();
		

		/*table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		   
			
			System.out.println(" Obs is ");
			System.out.println("Selected Bill is "+newSelection.billNo +"Selected Name is "+newSelection.name+"Selected ID Is "+newSelection.id);
			clickedBillNo=newSelection.billNo;
			clickedName=newSelection.name;
			clickedId=newSelection.id;
			clickedDate=newSelection.date;
			clickedAmount=newSelection.amount;

			
			try {
				Main.showPurchaseBillDetail();
			} catch (Exception e) {
				//e.printStackTrace();
			}
			
		});*/
		
		DBQuries dbQueries = new DBQuries();
		ResultSet rset;
		if(Main.isPurchaseClicked)
			rset = dbQueries.getPurchaseHistoryWithDate();
		else
		{
			rset = dbQueries.getSalesHistoryWithDate();
		}
			
		if(Main.isPurchaseClicked)
		{
			columnOfBill="bill_No";
			columnOfpartyId="supplier_Id";
			columnOfpartyName="supplier_Name";
			columnOfAmount="Amount";
			columnOfDate="purchase_Date";
		}
		else
		{
			columnOfBill="reciept_No";
			columnOfpartyId="customer_Id";
			columnOfpartyName="customer_Name";
			columnOfAmount="amount";
			columnOfDate="sale_Date";
		}

		setDataForTable(rset);
	}

	@FXML
	void searchButtonPressed()
	{
		
		LocalDate currDate = datePicker.getValue();
		LocalDate currDate1= datePicker1.getValue();
		if(currDate == null)
		{
			return;
		}
		//if(currDate !=null && currDate1!=null)
		//{
		
		table.setEditable(true);
		int year= currDate.getYear();
		int month=currDate.getMonthValue();
		int date =currDate.getDayOfMonth();
		LocalDate locald = LocalDate.of(year, month,date);
		Date myDate = Date.valueOf(locald);
		int year1=0;
		int month1=0;
		int date1=0 ;
		LocalDate locald1=null ;
		Date myDate1 =null;
		if(currDate1!=null)
		{
		 year1= currDate1.getYear();
		 month1=currDate1.getMonthValue();
		 date1 =currDate1.getDayOfMonth();
		 locald1 = LocalDate.of(year1, month1,date1);
		 myDate1 = Date.valueOf(locald1);
		}
		//String dateFormat = date+"/"+month+"/"+year;
		/*String dateFormat = year+"-"+month+"-"+date;
		Date myDate;
		//System.out.println(dateFormat);
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			 myDate = (Date) formatter.parse(dateFormat);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			myDate=null;
			e.printStackTrace();
		}*/
		
		DBQuries dbQueries = new DBQuries();
		ResultSet rset;
		if(Main.isPurchaseClicked)
		{
			if(currDate1==null)
			rset = dbQueries.getPurchaseHistoryWithDate(myDate);
			else
			{
				rset = dbQueries.getPurchaseHistoryWithDate(myDate,myDate1);
			}
			
		}
		else
		{
			if(currDate1==null)
				rset = dbQueries.getSalesHistoryWithDate(myDate);
				else 
				{
					rset = dbQueries.getSalesHistoryWithDate(myDate,myDate1);
				}
				
		}
		
		setDataForTable(rset);
		//}
	}
	
	void setDataForTable(ResultSet rset)
	{
		list = FXCollections.observableArrayList();
		
		try {
			while(rset.next())
			{
				//System.out.println("in WHile");
				if(!list.isEmpty())
				{
					//System.out.println("in if");
					boolean found=false;
					
					for(PurchaseHistoryModel model : list)
					{
						if(model.billNo.equals(rset.getString(columnOfBill)) && model.name.equals(rset.getString(columnOfpartyName)) && model.id.equals(rset.getString(columnOfpartyId)))
						{
							Float amountFromdb = rset.getFloat(columnOfAmount);
							String amountFromList = model.amount;
							Float totalAmount = Float.parseFloat(amountFromList)+amountFromdb;
							model.amount=totalAmount+"";
							found=true;
						}
					}
					if(!found)
					{
						Date dates= rset.getDate(columnOfDate);
						LocalDate localD = dates.toLocalDate();
						int year= localD.getYear();
						int month=localD.getMonthValue();
						int date =localD.getDayOfMonth();
						list.add(new PurchaseHistoryModel(date+"/"+month+"/"+year,rset.getString(columnOfBill),rset.getString(columnOfpartyName),rset.getString(columnOfpartyId),rset.getFloat(columnOfAmount)+""));
					    found=false;
					}
					
				}
				else
				{
					//System.out.println("in else");
					Date dates= rset.getDate(columnOfDate);
					LocalDate localD = dates.toLocalDate();
					int year= localD.getYear();
					int month=localD.getMonthValue();
					int date =localD.getDayOfMonth();
					list.add(new PurchaseHistoryModel(date+"/"+month+"/"+year,rset.getString(columnOfBill),rset.getString(columnOfpartyName),rset.getString(columnOfpartyId),rset.getFloat(columnOfAmount)+""));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(Main.isSalesClicked)
		{
		supplierIdColumn.setText("Customer ID");
		supplierNameColumn.setText("Customer Name");
		billedAmountColumn.setText("Invoice Amount");
		billNoColumn.setText("Reciept No");
		}
		
		  	 dateColumn.setCellValueFactory(new PropertyValueFactory<PurchaseHistoryModel,String>("date"));
	         billNoColumn.setCellValueFactory(new PropertyValueFactory<PurchaseHistoryModel,String>("billNo"));
	         supplierIdColumn.setCellValueFactory(new PropertyValueFactory<PurchaseHistoryModel,String>("Id"));
	         supplierNameColumn.setCellValueFactory(new PropertyValueFactory<PurchaseHistoryModel,String>("name"));
		     billedAmountColumn.setCellValueFactory(new PropertyValueFactory<PurchaseHistoryModel,String>("amount"));
		
		     table.setItems(list);
		     
		     
		     table.setRowFactory(tv -> {
		    	    TableRow<PurchaseHistoryModel> row = new TableRow<>();
		    	    row.setOnMouseClicked(event -> {
		    	        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
		    	             && event.getClickCount() == 2) {

		    	        	PurchaseHistoryModel clickedRow = row.getItem();
		    	        	
		    	        	clickedBillNo=clickedRow.billNo;
		    				clickedName=clickedRow.name;
		    				clickedId=clickedRow.id;
		    				clickedDate=clickedRow.date;
		    				clickedAmount=clickedRow.amount;

		    				
		    				try {
		    					Main.showPurchaseBillDetail();
		    				} catch (Exception e) {
		    					//e.printStackTrace();
		    				}
		    	          
		    	        }
		    	        if(! row.isEmpty() &&event.getButton() == MouseButton.SECONDARY)
		    	        {
		    	        	
		    	        }
		    	        if(event.getButton() == MouseButton.PRIMARY&& event.getClickCount() == 1){
		    	        	
		    	        }
		    	    });
		    	    return row ;
		    	});
	}
	
	
}
