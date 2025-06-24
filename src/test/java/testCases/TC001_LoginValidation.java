package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.IAssert;

import PageObjects.LoginPage;
import testBase.BaseClass;

public class TC001_LoginValidation extends BaseClass
{
	@Test
	public void validateLogin() throws InterruptedException
	{
		try {
			LoginPage lp=new LoginPage(driver);
			lp.clickLogin();
			lp.enterPhoneNumber("1234567890");
			lp.clickContiniueBtn();
			lp.enterOtp("1234");
			lp.btnVerifyOtpClick();
			Thread.sleep(3000);
			boolean check=lp.isLoginSuccessful();
			Assert.assertTrue(check);//if asserttrue==true-->tc pass else fail.
			
		}
		catch (Exception e) 
		{
			logger.info("TC001_LoginValidation tC fail");
			Assert.fail();
		}
	}
	
	
}
