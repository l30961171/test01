package com.xp.util.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
   /**
    * 发邮件的身份验证器
    * @author Benjamin
    * @version 2014.3.11
    *
    */
public class MyAuthenticator extends Authenticator{   
  String userName=null;   
  String password=null;   
      
  public MyAuthenticator(){   
  	
  }   
  public MyAuthenticator(String username, String password) {    
      this.userName = username;    
      this.password = password;    
  }  
  protected PasswordAuthentication getPasswordAuthentication(){   
      return new PasswordAuthentication(userName, password);   
  }   
}   