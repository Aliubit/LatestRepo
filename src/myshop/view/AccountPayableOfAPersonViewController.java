package myshop.view;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import myshop.Main;
import myshop.dbhandler.DBQuries;
import myshop.models.MyInventoryModel;
import myshop.models.PurchaseHistoryModel;
import myshop.models.SupplierLeisureModel;


public class AccountPayableOfAPersonViewController implements Initializable {

@FXML
public Label supplierNameLabel;
@FXML
public Label supplierIDLabel;
@FXML
public TableColumn<SupplierLeisureModel,String> dateColumn;
@FXML
public TableColumn<SupplierLeisureModel,String> particularColumn;
@FXML
public TableColumn<SupplierLeisureModel,String> debitColumn;
@FXML
public TableColumn<SupplierLeisureModel,String> creditColumn;
@FXML
public TableColumn<SupplierLeisureModel,String> balanceColumn;
@FXML
public TableView<SupplierLeisureModel> table;

public ObservableList<SupplierLeisureModel> finalList;

public ObservableList<PurchaseHistoryModel> purchaseList;

public ObservableList<PurchaseHistoryModel> amountPaidList;



///

String columnOfBill,columnOfpartyName,columnOfpartyId,columnOfAmount,columnOfDate;
///
@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	
	////Trying Avain
	
	if(Main.isPayableClicked)
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
	
	////
	
	this.supplierIDLabel.setText(AccountPayableViewController.supplierID);
	this.supplierNameLabel.setText(AccountPayableViewController.supplierName);
	
	DBQuries db = new DBQuries();
	ResultSet rset = db.getPurchaseHistoryWithSupplierNameAndId(AccountPayableViewController.supplierName, AccountPayableViewController.supplierID);
	
	/// Setting Data For Purchase Histroy
	purchaseList = FXCollections.observableArrayList();
	try {
		while(rset.next())
		{
			if(!purchaseList.isEmpty())
			{
				//System.out.println("in if");
				boolean found=false;
				
				for(PurchaseHistoryModel model : purchaseList)
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
					PurchaseHistoryModel e = new PurchaseHistoryModel(date+"/"+month+"/"+year,rset.getString(columnOfBill),rset.getString(columnOfpartyName),rset.getString(columnOfpartyId),rset.getFloat(columnOfAmount)+"");
					e.originalDate=dates;
					purchaseList.add(e);
				    found=false;
				}
				
			}
			else
			{
				//System.out.println("in else");
				/*Date dates= rset.getDate(columnOfDate);
				LocalDate localD = dates.toLocalDate();
				int year= localD.getYear();
				int month=localD.getMonthValue();
				int date =localD.getDayOfMonth();
				list.add(new PurchaseHistoryModel(date+"/"+month+"/"+year,rset.getString(columnOfBill),rset.getString(columnOfpartyName),rset.getString(columnOfpartyId),rset.getFloat(columnOfAmount)+""));
			old*/
				Date dates= rset.getDate(columnOfDate);
				
				LocalDate localD = dates.toLocalDate();
				int year= localD.getYear();
				int month=localD.getMonthValue();
				int date =localD.getDayOfMonth();
				PurchaseHistoryModel e = new PurchaseHistoryModel(date+"/"+month+"/"+year,rset.getString(columnOfBill),rset.getString(columnOfpartyName),rset.getString(columnOfpartyId),rset.getFloat(columnOfAmount)+"");
				e.originalDate=dates;
				purchaseList.add(e);
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//// End Setting data for purchase Histroy
	
	//// Start Editing Amount Paid 
	
	amountPaidList = FXCollections.observableArrayList();
	
	
	
	
	
	ResultSet rset1 = db.getAmountPaidToSuppliers(AccountPayableViewController.supplierName, AccountPayableViewController.supplierID);
	
	/// Setting Data For Purchase Histroy
	amountPaidList = FXCollections.observableArrayList();
	try {
		while(rset1.next())
		{
		
			Date dates= rset1.getDate("date");
			
			LocalDate localD = dates.toLocalDate();
			int year= localD.getYear();
			int month=localD.getMonthValue();
			int date =localD.getDayOfMonth();
			PurchaseHistoryModel e = new PurchaseHistoryModel(date+"/"+month+"/"+year,rset1.getString("type"),rset1.getString("supplier_Name"),rset1.getString("supplier_Id"),rset1.getFloat("amount")+"");
			e.originalDate=dates;
			amountPaidList.add(e);
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finalList= FXCollections.observableArrayList();
	int sizeOfPurchaseList=purchaseList.size(),j=0;
	int sizeOfAmountPaidList=amountPaidList.size(),k=0;
	/// now Merge Both Lists into FinalList
	for(int i=0;i<purchaseList.size()+amountPaidList.size();i++)
	{
		if(j<sizeOfAmountPaidList)
		{
			PurchaseHistoryModel pmodel = purchaseList.get(j);
			
			SupplierLeisureModel smodel= new SupplierLeisureModel(pmodel.date, pmodel.billNo, pmodel.amount, "0", pmodel.amount);
		smodel.originalDate=pmodel.originalDate;
			finalList.add(smodel);
		j++;
		}
		if(k<sizeOfAmountPaidList)
		{
			PurchaseHistoryModel amodel = amountPaidList.get(k);
			SupplierLeisureModel smodel= new SupplierLeisureModel(amodel.date, amodel.billNo, "0", amodel.amount, amodel.amount);
			smodel.originalDate=amodel.originalDate;
			finalList.add(smodel);
			k++;
		}
	}
	
	
	
	
	 dateColumn.setCellValueFactory(new PropertyValueFactory<SupplierLeisureModel,String>("stringDate"));
	 particularColumn.setCellValueFactory(new PropertyValueFactory<SupplierLeisureModel,String>("particular"));
	 debitColumn.setCellValueFactory(new PropertyValueFactory<SupplierLeisureModel,String>("debit"));
	 creditColumn.setCellValueFactory(new PropertyValueFactory<SupplierLeisureModel,String>("credit"));
	 balanceColumn.setCellValueFactory(new PropertyValueFactory<SupplierLeisureModel,String>("balance"));
		
	 
	 table.setItems(finalList);
	 ///
	
	
	
	
	//// End Editing Amount Paid
}



 
  /*
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
		} 
		catch (Exception e) {
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
	
  */











}
