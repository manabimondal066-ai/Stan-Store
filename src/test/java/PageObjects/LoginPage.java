package PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{   
	public LoginPage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath="(//span[.='LogIn'])[1]")
	WebElement btnLogin;
	
	@FindBy(xpath="//input[@type='text']")
	WebElement txtPhonenumber;
	

	@FindBy(xpath="//span[.='Continue']/span")
	WebElement btnContinue;
	
	@FindBy(xpath="//input[contains(@class,'mantine-Input-input mantine-TextInput-input') and @inputmode='numeric']")
	List<WebElement> otp4boxes;
	
	@FindBy(xpath="//span[.='Verify OTP']")
	WebElement btnVerifyOtp;
	
	@FindBy(xpath="//div[.='🎉 Welcome Back!']")
	WebElement msgLoginSuccessful;
	
	public void clickLogin()
	{
		btnLogin.click();
	}
	public void enterPhoneNumber(String Ph)
	{
		txtPhonenumber.sendKeys(Ph);
	}
	public void clickContiniueBtn()
	{
		btnContinue.click();
	}
	
	public void enterOtp(String otp)
	{
		for (int i = 0; i < otp.length(); i++) {
			otp4boxes.get(i).clear();  // optional: clear any prefilled value
			otp4boxes.get(i).sendKeys(String.valueOf(otp.charAt(i)));
        }
	}
	public void btnVerifyOtpClick()
	{
		btnVerifyOtp.click();
	}
	public boolean isLoginSuccessful()
	{
		return msgLoginSuccessful.isDisplayed();
		
	}


}
