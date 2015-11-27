package in.co.sunrays.ocha.test;

import in.co.sunrays.ocha.exception.ApplicationException;
import in.co.sunrays.util.EmailMessage;
import in.co.sunrays.util.EmailUtility;

public class EmailUtilityTest
{
	public static void main(String[] args) 
	{
		
		testTextEmail();
		
	}
	
	public static void testHTMLEmail()
	{
		try
		{
			EmailMessage msg = new EmailMessage();
			msg.setTo("sunrayssunilbook@gmail.com");
			msg.setSubject("Test : testHTMLEmail");
			msg.setMessage("<h1>Hello World</h1>");
			msg.setMessageType(EmailMessage.HTML_MSG);
			EmailUtility.sendMail(msg);
			
		}
		catch (ApplicationException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void testTextEmail()
	{
		
		try
		{
			EmailMessage msg = new EmailMessage();
		
		msg.setTo("anil.vish91@gmail.com");
		msg.setSubject("Test : testTestEmail");
		msg.setMessage("<h1>Hello World</h1>");
		msg.setMessageType(EmailMessage.TEXT_MSG);
		EmailUtility.sendMail(msg);
		}
		catch(ApplicationException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void testEmailTORecipient()
	{
		try
		{
			EmailMessage msg = new EmailMessage();
			msg.setTo("sunrayssunilbook@gmail.com");
			msg.setSubject("Test : testTestEmailTORecipient");
			msg.setMessage("<h1>Hello World</h1>");
			msg.setMessageType(EmailMessage.HTML_MSG);
			EmailUtility.sendMail(msg);
		}
		catch(ApplicationException e)
		{
			e.printStackTrace();
			
		}
	}
	
	
	
	public  static void testEmailCCRecipient()
	{
		try 
		{
			EmailMessage msg = new EmailMessage();
			msg.setTo("sunrayssunilbook@gmail.com");
			msg.setSubject("Test : testTestEmailCCRecipient");
			msg.setMessage("<h1>Hello World</h1>");
			msg.setMessageType(EmailMessage.TEXT_MSG);
			EmailUtility.sendMail(msg);	
		}
		catch(ApplicationException e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void testEmailBCCRecipient()
	{
		try
		{
			EmailMessage msg = new EmailMessage();
			msg.setTo("sunrayssunilbook@gmail.com");
			msg.setSubject("Test : testTestEmailBCCRecipient");
			msg.setMessage("<h1>Hello World</h1>");
			msg.setMessageType(EmailMessage.HTML_MSG);
			EmailUtility.sendMail(msg);
		}
		catch(ApplicationException e)
		{
			e.printStackTrace();
			
		}
	}
	
	
	
	
	public static void testEmailMultipleTORecipient()
	{
		try 
		{
			EmailMessage msg = new EmailMessage();
			msg.setTo("vipinchandore@gmail.com,vipin.chandore@nenosystems.com");
	        msg.setSubject("Test : testEmailMultipleTORecipient");
	        msg.setMessage("<h1>Hello world</h1>");
	        msg.setMessageType(EmailMessage.HTML_MSG);
	        EmailUtility.sendMail(msg); 
			
		}
		catch(ApplicationException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void testEmailMultipleCCRecipient() 
	{
        try 
        {
            EmailMessage msg = new EmailMessage();
            msg.setCc("vipinchandore@gmail.com,vipin.chandore@nenosystems.com");
            msg.setSubject("Test : testEmailMultipleCCRecipient");
            msg.setMessage("<h1>Hello world</h1>");
            msg.setMessageType(EmailMessage.HTML_MSG);
            EmailUtility.sendMail(msg);
        }
        
        catch (ApplicationException e) 
        {
            e.printStackTrace();
        }
    }
 
	
	 public static void testEmailMultipleBCCRecipient()
	 {
	        try 
	        {
	            EmailMessage msg = new EmailMessage();
	            msg.setBcc("vipinchandore@gmail.com,vipin.chandore@nenosystems.com");
	            msg.setSubject("Test : testEmailMultipleBCCRecipient");
	            msg.setMessage("<h1>Hello world</h1>");
	            msg.setMessageType(EmailMessage.HTML_MSG);
	            EmailUtility.sendMail(msg);
	        }
	        catch (ApplicationException e) 
	        {
	            e.printStackTrace();
	        }
	    } 

}
