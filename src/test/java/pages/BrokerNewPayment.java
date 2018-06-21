package pages;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;

public class BrokerNewPayment extends TestBase
{
	String  payment_status = "Verified";
	String payment_statuss = "Unmatched:";
	String load;
	String    scheduledamount;
	public static float totalamounnt;
	String schedule;
	String scheduleamt;
	
	@FindBy(xpath = "//a[text()='New Payment']")
	private WebElement lnk_newpayment;

	@FindBy(xpath = "//div[@class='total ng-scope notlast col-sm-6']//child::span[1]")
	private WebElement tab_shedulepayment;

	@FindBy(xpath = "//div[@class='total ng-scope']//child::span[1]")
	private WebElement creditvalue;

	@FindBy(id = "CarrierEmail")
	private WebElement field_CarrierEmail;
	
	@FindBy(id = "CompanyName")
	private WebElement field_PayTo;
	
	@FindBy(id = "CarrierDOT")
	private WebElement field_CarrierDOT;
	
	@FindBy(id = "PaymentDate")
	private WebElement field_ScheduleDate;

	@FindBy(id = "PaymentAmount")
	private WebElement field_PaymentAmount;
	
	@FindBy(id = "InvoiceNumber")
	private WebElement field_InvoiceNum;

	@FindBy(id = "LoadNumber")
	private WebElement field_LoadID;
	
	@FindBy(id = "InvoiceDate")
	private WebElement field_InvoiceRecd;
	
	@FindBy(id = "Memo")
	private WebElement field_Memo;
	
	@FindBy(id = "AdvancePayment")
	private WebElement checkbox_AdvancePayment;
	
	@FindBy(xpath = "//input[@value='Schedule']")
	private WebElement button_schedule;
	
	@FindBy(id = "OriginCountry")
	private WebElement dropdown_OriginCountry;
	
	@FindBy(id = "OriginCity")
	private WebElement field_OriginCity;
	
	@FindBy(id = "OriginProvinceId")
	private WebElement dropdown_OriginState;
	
	@FindBy(id = "OriginZipCode")
	private WebElement field_OriginZip;
	
	@FindBy(id = "DestinationCountry")
	private WebElement dropdown_DestinationCountry;
	
	@FindBy(id = "DestinationCity")
	private WebElement field_DestinationCity;
	
	@FindBy(id = "DestinationProvinceId")
	private WebElement dropdown_DestinationState;
	
	@FindBy(id = "DestinationZipCode")
	private WebElement field_DestinationZip;
	
	@FindBy(id = "TrailerTypeId")
	private WebElement dropdown_TrailerType;
	
	@FindBy(id = "Miles")
	private WebElement field_Miles;
	
	@FindBy(id = "PickupDate")
	private WebElement field_PickupDate;
	
	@FindBy(id = "DeliveryDate")
	private WebElement field_DeliveryDate;
	
	@FindBy(id = "Commodity")
	private WebElement field_Commodity;
	
	@FindBy(id = "Length")
	private WebElement field_Length;
	
	@FindBy(id = "Width")
	private WebElement field_Width;
	
	@FindBy(id = "Height")
	private WebElement field_Height;
	
	@FindBy(id = "Weight")
	private WebElement field_Weight;
	
	@FindBy(id = "NumberOfStops")
	private WebElement field_NumberOfStops;
	
	@FindBy(id = "FuelSurcharge")
	private WebElement field_FuelSurcharge;
	
	@FindBy(id="UserName")
	private WebElement field_username;
	
	@FindBy(id="Password")
	private WebElement field_password;
	
	@FindBy(xpath = "//input[@value='Login']")
	private WebElement button_login;
	
	@FindBy(id = "searchKeyword")
	private WebElement field_searchbox;
	
	@FindBy(xpath = "//input[@value='Search']")
	private WebElement btn_search;
	
	@FindBy(xpath = "//button[@type='button'][text()='OK']")
	private WebElement popup_ok;
	
//	@FindBy(xpath = "//div[@class='col-md-10 content']//child::span[1]")
//	private WebElement paymentstatus;
	
	@FindBy(xpath = "//div[contains(@class,'carrierPayment ')]//child::span[@class='ng-scope']")
	private WebElement paymentstatus;
	
	@FindBy(xpath = "//*[@id=\"angularScope\"]/div[2]/div/div[3]/div/div/div[1]/div/div[4]/div/div/div[3]/div/div[1]/div/div[5]/div/i")
	private WebElement row;
	
	@FindBy(xpath = ".//*[@id='searchKeyword']//following::div[@class='col-md-2 content ng-binding'][1]")
	private WebElement amount;
	
	@FindBy(xpath = "//div[@class='carrierPayment ng-scope']//child::div[5]/div/span")
	private static WebElement invoicenumber;
	
	@FindBy(xpath = "//div[@class='carrierPayment ng-scope']//child::div[6]/div/span")
	private static WebElement loadidd;
	
	@FindBy(xpath = "//*[text()='remove_circle']")
	private WebElement btn_cancelpayment;
	
	@FindBy(xpath = "//a[text()='Logoff']")
	private WebElement btn_logout;
	
	@FindBy(xpath="//*[@class='carrierPayment ng-scope']/div/div[5]/div")
	List< WebElement> List_payment;
	
	@FindBy(xpath="//*[text()[contains(., 'mode_edit')]]")
	private WebElement editPaymentIcon;

	
	/*-------PageFactory---------*/
	
	public BrokerNewPayment()
	{
		PageFactory.initElements(driver, this);
	}
	
	/*-------New Payment---------*/
	public void newPayment() throws InterruptedException
	{
		lnk_newpayment.click();
		//Thread.sleep(2000);
	}
	
	/*-------Carrier email---------*/
	public String  carrierEmail(String cemail) throws InterruptedException
	{
		field_CarrierEmail.click();
		Thread.sleep(1000);
		field_CarrierEmail.sendKeys(cemail);
		return cemail;
		//Thread.sleep(1000);
	}
	
	/*-------Amount---------*/
	public String amount(String amt)  
	{
		field_PaymentAmount.sendKeys(amt);
		return amt;
	}
	
	/*-------Invoice number---------*/
	public String invoiceNumber(String invoiceno)  
	{
		field_InvoiceNum.sendKeys(invoiceno);	
		return invoiceno;
	}

	/*-------load id---------*/
	public String loadId(String loadid)  
	{
		field_LoadID.sendKeys(loadid);
		return loadid;
	}
	
	/*-------advance checkbox---------*/
	public void advanceCheckbox()
	{
		checkbox_AdvancePayment.click();
	}
	
	/*-------schedule payment---------*/
	public void clickShedulePayment() throws InterruptedException
	{
		button_schedule.click();
		Thread.sleep(2000);
	}
	
	/*-------schedule payment tab---------*/
	public void clickShedulePaymenttab()
	{
		tab_shedulepayment.click();
	}
	/*-------Search Carrier---------*/
	public void searchCarrier(String cemail)
	{
		field_searchbox.sendKeys(cemail);
	}
	
	/*-------Search Invoice---------*/
	public void searchInvoice(String invoiceNum)
	{
		field_searchbox.sendKeys(invoiceNum);
	}
	
	/*-------Click Edit Icon---------*/
	public void clickEditIcon()
	{
		editPaymentIcon.click();
	}
	
	/*-------Edit Icon Enabled---------*/
	public Boolean isEditIconEnabled()
	{
		return editPaymentIcon.isEnabled();
	}
	
	/*-------Search button---------*/
	public void clickSearchButton() throws InterruptedException
	{
		btn_search.click();
		//Thread.sleep(2000);
	}
	/*-------verify payment---------*/
	public String verifyPaymentStatus()
	{
		String actualpaystatus = paymentstatus.getText();
		return actualpaystatus;

	}
	/*-------verify amount---------*/
	public float verifyscheduledPayment() throws InterruptedException
	{
	
		Thread.sleep(1000);
		paymentstatus.click();
		String scheduledamount = amount.getText();
		 schedule= scheduledamount.replaceAll("\\$", "");
		scheduleamt =  schedule.replaceAll(",", "");
		totalamounnt = Float.parseFloat(scheduleamt);
		System.out.println(totalamounnt);
		System.out.println(scheduledamount);
		return totalamounnt;			
	}
	
		/*-------verify invoicenumber---------*/
   public void verifyInvoiceNumber(String invoicenum, String amt) throws InterruptedException
   {
	   Thread.sleep(1000);
	   List<WebElement>invoicenumcount = List_payment;
	   
	   //System.out.println(invoicenumcount);
	   System.out.println("Total Sceduled Payments:"+invoicenumcount.size());
	   
	   for(int i=0; i<invoicenumcount.size();i++)
	   {

    	 String invoice = invoicenumcount.get(i).getText();

    	 if(invoice.contains(invoicenum))
    	 {

           System.out.println(invoice);
           invoicenumcount.get(i).click();
           Thread.sleep(1000);

           scheduledamount = amount.getText();

           if(scheduledamount.contains(amt))
           {
                  System.out.println("Amount is :"+ ""+ scheduledamount);
           }

           break;
         }
    	 
	   }
   }
		

		
		/*-------verify loadid---------*/		
	public String verifyLoadId()
	{
		String  load = loadidd.getText();
		System.out.println(load);
		return load;
	}	
	
	public void logout()
	{
		btn_logout.click();
	}

	//GETTERS AND SETTERS
	public WebElement getField_CarrierEmail() {
		return field_CarrierEmail;
	}

	public void setField_CarrierEmail(String carrierEmail) {
		this.field_CarrierEmail.clear();
		this.field_CarrierEmail.sendKeys(carrierEmail);
	}

	public WebElement getField_PayTo() {
		return field_PayTo;
	}

	public void setField_PayTo(String payTo) {
		this.field_PayTo.clear();
		this.field_PayTo.sendKeys(payTo);
	}

	public WebElement getField_CarrierDOT() {
		return field_CarrierDOT;
	}

	public void setField_CarrierDOT(String carrierDOT) {
		this.field_CarrierDOT.clear();
		this.field_CarrierDOT.sendKeys(carrierDOT);
	}

	public WebElement getField_ScheduleDate() {
		return field_ScheduleDate;
	}

	public void setField_ScheduleDate(String scheduleDate) {
		this.field_ScheduleDate.clear();
		this.field_ScheduleDate.click();
		this.field_ScheduleDate.sendKeys(scheduleDate);
		this.field_ScheduleDate.sendKeys(Keys.TAB);
	}

	public WebElement getField_PaymentAmount() {
		return field_PaymentAmount;
	}

	public void setField_PaymentAmount(String paymentAmount) {
		this.field_PaymentAmount.clear();
		this.field_PaymentAmount.sendKeys(paymentAmount);
	}

	public WebElement getField_InvoiceNum() {
		return field_InvoiceNum;
	}

	public void setField_InvoiceNum(String invoiceNum) {
		this.field_InvoiceNum.clear();
		this.field_InvoiceNum.sendKeys(invoiceNum);
	}

	public WebElement getField_LoadID() {
		return field_LoadID;
	}

	public void setField_LoadID(String loadID) {
		this.field_LoadID.clear();
		this.field_LoadID.sendKeys(loadID);
	}

	public WebElement getField_InvoiceRecd() {
		return field_InvoiceRecd;
	}

	public void setField_InvoiceRecd(String invoiceRecd) {
		this.field_InvoiceRecd.clear();
		this.field_InvoiceRecd.click();
		this.field_InvoiceRecd.sendKeys(invoiceRecd);
		this.field_InvoiceRecd.sendKeys(Keys.TAB);
	}

	public WebElement getField_Memo() {
		return field_Memo;
	}

	public void setField_Memo(String memo) {
		this.field_Memo.clear();
		this.field_Memo.sendKeys(memo);
	}

	public WebElement getCheckbox_AdvancePayment() {
		return checkbox_AdvancePayment;
	}

	public void setCheckbox_AdvancePayment(String advancePayment) {
		
		if(advancePayment.equalsIgnoreCase("true") && !this.checkbox_AdvancePayment.isSelected())
		{
			this.checkbox_AdvancePayment.click();
		}
		else if(advancePayment.equalsIgnoreCase("false") && this.checkbox_AdvancePayment.isSelected())
		{
			this.checkbox_AdvancePayment.click();
		}	
	}

	public WebElement getDropdown_OriginCountry() {
		return dropdown_OriginCountry;
	}

	public void setDropdown_OriginCountry(String originCountry) {
		this.dropdown_OriginCountry.sendKeys(originCountry);
	}

	public WebElement getField_OriginCity() {
		return field_OriginCity;
	}

	public void setField_OriginCity(String originCity) {
		this.field_OriginCity.clear();
		this.field_OriginCity.sendKeys(originCity);
	}

	public WebElement getDropdown_OriginState() {
		return dropdown_OriginState;
	}

	public void setDropdown_OriginState(String originState) {
		this.dropdown_OriginState.sendKeys(originState);
	}

	public WebElement getField_OriginZip() {
		return field_OriginZip;
	}

	public void setField_OriginZip(String originZip) {
		this.field_OriginZip.clear();
		this.field_OriginZip.sendKeys(originZip);
	}

	public WebElement getDropdown_DestinationCountry() {
		return dropdown_DestinationCountry;
	}

	public void setDropdown_DestinationCountry(String destinationCountry) {
		this.dropdown_DestinationCountry.sendKeys(destinationCountry);
	}

	public WebElement getField_DestinationCity() {
		return field_DestinationCity;
	}

	public void setField_DestinationCity(String destinationCity) {
		this.field_DestinationCity.clear();
		this.field_DestinationCity.sendKeys(destinationCity);
	}

	public WebElement getDropdown_DestinationState() {
		return dropdown_DestinationState;
	}

	public void setDropdown_DestinationState(String destinationState) {
		this.dropdown_DestinationState.sendKeys(destinationState);
	}

	public WebElement getField_DestinationZip() {
		return field_DestinationZip;
	}

	public void setField_DestinationZip(String destinationZip) {
		this.field_DestinationZip.clear();
		this.field_DestinationZip.sendKeys(destinationZip);
	}

	public WebElement getDropdown_TrailerType() {
		return dropdown_TrailerType;
	}

	public void setDropdown_TrailerType(String trailerType) {
		this.dropdown_TrailerType.sendKeys(trailerType);
	}

	public WebElement getField_Miles() {
		return field_Miles;
	}

	public void setField_Miles(String miles) {
		this.field_Miles.clear();
		this.field_Miles.sendKeys(miles);
	}

	public WebElement getField_PickupDate() {
		return field_PickupDate;
	}

	public void setField_PickupDate(String pickupDate) {
		this.field_PickupDate.clear();
		this.field_PickupDate.click();
		this.field_PickupDate.sendKeys(pickupDate);
		this.field_PickupDate.sendKeys(Keys.TAB);
	}

	public WebElement getField_DeliveryDate() {
		return field_DeliveryDate;
	}

	public void setField_DeliveryDate(String deliveryDate) {
		this.field_DeliveryDate.clear();
		this.field_DeliveryDate.click();
		this.field_DeliveryDate.sendKeys(deliveryDate);
		this.field_DeliveryDate.sendKeys(Keys.TAB);
	}

	public WebElement getField_Commodity() {
		return field_Commodity;
	}

	public void setField_Commodity(String commodity) {
		this.field_Commodity.clear();
		this.field_Commodity.sendKeys(commodity);
	}

	public WebElement getField_Length() {
		return field_Length;
	}

	public void setField_Length(String length) {
		this.field_Length.clear();
		this.field_Length.sendKeys(length);
	}

	public WebElement getField_Width() {
		return field_Width;
	}

	public void setField_Width(String width) {
		this.field_Width.clear();
		this.field_Width.sendKeys(width);
	}

	public WebElement getField_Height() {
		return field_Height;
	}

	public void setField_Height(String height) {
		this.field_Height.clear();
		this.field_Height.sendKeys(height);
	}

	public WebElement getField_Weight() {
		return field_Weight;
	}

	public void setField_Weight(String weight) {
		this.field_Weight.clear();
		this.field_Weight.sendKeys(weight);
	}

	public WebElement getField_NumberOfStops() {
		return field_NumberOfStops;
	}

	public void setField_NumberOfStops(String numberOfStops) {
		this.field_NumberOfStops.clear();
		this.field_NumberOfStops.sendKeys(numberOfStops);
	}

	public WebElement getField_FuelSurcharge() {
		return field_FuelSurcharge;
	}

	public void setField_FuelSurcharge(String fuelSurcharge) {
		this.field_FuelSurcharge.clear();
		this.field_FuelSurcharge.sendKeys(fuelSurcharge);
	}

	
}
