package com.csye6225.spring2019.filter;

import com.csye6225.spring2019.entity.Account;
import com.google.common.base.Strings;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* Verifier Tester.
* 
* @author <Authors name> 
* @since <pre>Jan 28, 2019</pre> 
* @version 1.0 
*/ 
public class VerifierTest {

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: isVerified(HttpServletRequest request, HttpServletResponse response) 
* 
*/ 
@Test
public void testIsVerifiedForRequestResponse() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: isVerified(String authHeader) 
* 
*/ 
@Test
public void testIsVerifiedAuthHeader() throws Exception { 
//TODO: Test goes here...
    String auth = "Basic eHhzOjEyMw==";
    Account account = Verifier.isVerified(auth);
    Assert.assertTrue(account!=null);
    Assert.assertFalse(Strings.isNullOrEmpty(account.getEmailAddress())|| Strings.isNullOrEmpty(account.getPassword()));
    System.out.println(account.getEmailAddress()+":" +account.getPassword() );
    Account account1 = Verifier.isVerified("Basic eHhzOjEyMw==");
    Account account2 = Verifier.isVerified("Basics eHhzOjEyMw==");
    Assert.assertTrue(account2==null);
    Assert.assertTrue(account.getEmailAddress().equals(account1.getEmailAddress()));
    Assert.assertTrue(account.getPassword().equals(account1.getPassword()));



} 


} 
