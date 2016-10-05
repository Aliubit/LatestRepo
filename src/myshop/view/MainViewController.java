package myshop.view;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import myshop.Main;

public class MainViewController implements Initializable{
	
	
	////////////////////// left pane labels
	
	@FXML
	private Label addNewProductLabel;
	@FXML
	private Label addNewCustomerOrSellerLabel;
	@FXML
	private Label viewCustomersLabel;
	@FXML
	private Label viewSuppliersLabel;
	@FXML
	private Label viewAllProducts;
	///////////////////////////////////////
	
	//////////////////////  right pane Labels
	@FXML
	private Label accountPayableViewLabel;
	@FXML
	private Label accountReceivableViewLabel;
	@FXML
	private Label purchaseHistoryLabel;
	@FXML
	private Label salesHistoryLabel;
	@FXML 
	private Label cashBookLabel;
	@FXML
	private Label inventoryLabel;
	/////////////////////////////////////////
	
	@FXML
	private Label cuurDateLabel;
	
	////////////////////////////top////////////////////////////
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		cuurDateLabel.setText(date.toString());
		System.out.println(date.toString());
		
	}
	
	////////////////////////////Left //////////////////////////
	@FXML
	private void openInventoryLabel() throws IOException{
		Main.showInventoryScene();
	}
	@FXML 
	private void goAddNewProdLabel() throws IOException{
		Main.showAddProdWindow();
	}
	@FXML
	private void goAddDealerNCustomerLabel() throws IOException{
		Main.showNewDealerWindow();
	}	
	////////////////////////////Right //////////////////////////}
		
	@FXML
	public void openPurchaseHistory() throws IOException{
		Main.showPurchaseHistoryScene();
	}
	@FXML
	public void openSalesHistory() throws IOException{
		Main.showSalesHistoryScene();
	}
	@FXML
	private void openAccountPayable() throws IOException{
		Main.showAcountPaybleScene();
	}
	@FXML
	private void openAccountRecievable() throws IOException{
		Main.showAcountRecievableScene();
	}
	////////////////////////////bottom //////////////////////////
	@FXML
	private void goHomeBtn() throws IOException{
		Main.showMainItems();
	}
	
	///////////////////////////*********/////////////////////////
	
	////////////////////////// hovering Methods for Right Pane/////////////////
			@FXML	
			public void onHoverAccPayableLabel(){
					accountPayableViewLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: midnightblue;");
					accountPayableViewLabel.setEffect(new Glow());
					accountPayableViewLabel.setMaxWidth(250);
					accountPayableViewLabel.setWrapText(true);
				}
			@FXML	
			public void onHoverAccReceivableLabel(){
					accountReceivableViewLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: midnightblue;");
					accountReceivableViewLabel.setEffect(new Glow());
					accountReceivableViewLabel.setMaxWidth(250);
					accountReceivableViewLabel.setWrapText(true);
				}
			
			@FXML	
			public void onHoverPurchaseHistoryLabel(){
					purchaseHistoryLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: midnightblue;");
					purchaseHistoryLabel.setEffect(new Glow());
					purchaseHistoryLabel.setMaxWidth(250);
					purchaseHistoryLabel.setWrapText(true);
				}
			
			@FXML	
			public void onHoverSalesHistoryLabel(){
					salesHistoryLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: midnightblue;");
					salesHistoryLabel.setEffect(new Glow());
					salesHistoryLabel.setMaxWidth(250);
					salesHistoryLabel.setWrapText(true);
				}
			
			@FXML	
			public void onHoverCashBookLabel(){
					cashBookLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: midnightblue;");
					cashBookLabel.setEffect(new Glow());
					cashBookLabel.setMaxWidth(250);
					cashBookLabel.setWrapText(true);
				}
			
			@FXML	
			public void onHoverInventotyLabel(){
					inventoryLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: midnightblue;");
					inventoryLabel.setEffect(new Glow());
					inventoryLabel.setMaxWidth(250);
					inventoryLabel.setWrapText(true);
				}
				
			//////////////////////////////////*******************/////////////////////////////////
			
			////////////////////////////////////// for Left Pane Hovering Labels//////////////////
			
			@FXML	
			public void onHoverAddNewProductLabel(){
					addNewProductLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: midnightblue;");
					addNewProductLabel.setEffect(new Glow());
					addNewProductLabel.setMaxWidth(250);
					addNewProductLabel.setWrapText(true);
				}
			
			@FXML	
			public void onHoverAddNewCustomerOrSellerLabel(){
					addNewCustomerOrSellerLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: midnightblue;");
					addNewCustomerOrSellerLabel.setEffect(new Glow());
					addNewCustomerOrSellerLabel.setMaxWidth(250);
					addNewCustomerOrSellerLabel.setWrapText(true);
				}
			
			@FXML	
			public void onHoverViewSellerLabel(){
					viewSuppliersLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: midnightblue;");
					viewSuppliersLabel.setEffect(new Glow());
					viewSuppliersLabel.setMaxWidth(250);
					viewSuppliersLabel.setWrapText(true);
				}
			
			@FXML	
			public void onHoverViewCustomerLabel(){
					viewCustomersLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: midnightblue;");
					viewCustomersLabel.setEffect(new Glow());
					viewCustomersLabel.setMaxWidth(250);
					viewCustomersLabel.setWrapText(true);
				}
			
			@FXML	
			public void onHoverViewAllProductsLabel(){
					viewAllProducts.setStyle("-fx-font-size: 15px; -fx-text-fill: midnightblue;");
					viewAllProducts.setEffect(new Glow());
					viewAllProducts.setMaxWidth(250);
					viewAllProducts.setWrapText(true);
				}
		
			//////////////////////////////////*******************/////////////////////////////////
			
			///////////////////////// Right Pane closing Hover Label//////////////////////////////
	
	
			@FXML	
			public void onCloseHoveraccpayableLabel(){
				accountPayableViewLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: black;");
				accountPayableViewLabel.setEffect(null);
			}
			
			@FXML	
			public void onCloseHoveraccReceivableLabel(){
				accountReceivableViewLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: black;");
				accountReceivableViewLabel.setEffect(null);
			}
			
			@FXML	
			public void onCloseHoverPurchaseHistoryLabel(){
				purchaseHistoryLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: black;");
				purchaseHistoryLabel.setEffect(null);
			}
			
			@FXML	
			public void onCloseHoverSalesHistoryLabel(){
				salesHistoryLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: black;");
				salesHistoryLabel.setEffect(null);
			}
			
			@FXML	
			public void onCloseHoverCashBookLabel(){
				cashBookLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: black;");
				cashBookLabel.setEffect(null);
			}
			
			@FXML	
			public void onCloseHoverinventoryLabel(){
				inventoryLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: black;");
				inventoryLabel.setEffect(null);
			}
			
			//////////////////////////////******************//////////////////////////////
			
			///////////////////////// Left Pane Hover Label//////////////////////////////
			@FXML	
			public void onCloseHoverAddNewProductLabel(){
				addNewProductLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: black;");
				addNewProductLabel.setEffect(null);
			}
			
			@FXML	
			public void onCloseHoverAddNewCustomerOrSellerLabel(){
				addNewCustomerOrSellerLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: black;");
				addNewCustomerOrSellerLabel.setEffect(null);
				
			}
			
			@FXML	
			public void onCloseHoverViewCustomerLabel(){
				viewCustomersLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: black;");
				viewCustomersLabel.setEffect(null);
			}
			
			@FXML	
			public void onCloseHoverViewSupplierLabel(){
				viewSuppliersLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: black;");
				viewSuppliersLabel.setEffect(null);
			}
			
			@FXML	
			public void onCloseHoverViewAllProductLabel(){
				viewAllProducts.setStyle("-fx-font-size: 11px; -fx-text-fill: black;");
				viewAllProducts.setEffect(null);
			}
		
			
			
		
			
			//////////////////////////////******************//////////////////////////////
		
		
	
		
}
