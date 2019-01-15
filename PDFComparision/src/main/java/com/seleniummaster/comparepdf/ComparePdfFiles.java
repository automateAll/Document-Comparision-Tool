//package com.seleniummaster.comparepdf;
//import com.beust.testng.TestNG;
//import com.google.diffmatchpatch.*;
//import com.google.diffmatchpatch.Diff_Match_Patch.Diff;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.PrintWriter;
//import java.net.URL;
//import java.util.LinkedList;
//import java.util.concurrent.TimeUnit;  
//
//import org.apache.pdfbox.pdfparser.PDFParser;  
//import org.apache.pdfbox.util.PDFTextStripper;  
//import org.openqa.selenium.WebDriver;  
//import org.openqa.selenium.firefox.FirefoxDriver;  
//import org.testng.Assert;
//import org.testng.Reporter;
//import org.testng.TestListenerAdapter;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;  
//import org.testng.annotations.Test;  
//  
//public class ComparePdfFiles {  
//   
// WebDriver driver;  
//   
////  @BeforeTest  
////  public void setUpDriver() {  
//////   driver = new FirefoxDriver();  
//////   Reporter.log("Web Driver Setup is complete"); 
////	  System.out.println("Before Test Annotation.........");
////     }  
//
//
// 
//@Test  
//  public void CompareTextInTwoPdfs() throws Exception
//  {
//    Diff_Match_Patch compare= new Diff_Match_Patch();
//    
//    String text1=ReadBaseTemplate();
//    String text2=ReadModifiedTemplate();
//    //System.out.print(compare.diff_main(text1,text2)); 
//    
//    LinkedList<Diff> output = compare.diff_main(text1,text2);
//    compare.diff_cleanupSemantic(output);
//    String s = compare.diff_prettyHtml(output);
//    PrintWriter out = null;
//    try {  out = new PrintWriter( "C:\\Users\\ABHATTI\\Desktop\\report.html" );
//        out.println( s );
//    }
//    catch (IOException e){
//    	throw e;
//    }
//    
//    finally
//    {
//        
//            if ( out != null)
//            out.close();
//    }
//        
//    
//        
//  }
//  public String ReadBaseTemplate() throws IOException{  
//  //driver.get("http://www.seleniummaster.com/sitecontent/images/Selenium_Master_Test_Case_Base_Template.pdf");  
//  //driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//	    String filepath = "C:\\Users\\ABHATTI\\Documents\\PAD\\PAD Document Commital Process\\Documents\\PAFR.pdf";
//	    File file = new File(filepath);
//  //URL url = new URL(driver.getCurrentUrl());   
//  //BufferedInputStream fileToParse=new BufferedInputStream(url.openStream());  
//   //parse()  --  This will parse the stream and populate the COSDocument object.   
//  //COSDocument object --  This is the in-memory representation of the PDF document  
//  PDFParser parser = new PDFParser(new FileInputStream(file));
//  parser.parse();  
//  //getPDDocument() -- This will get the PD document that was parsed. When you are done with this document 
//  //you must call    close() on it to release resources  
//  //PDFTextStripper() -- This class will take a pdf document and strip out all of the text and ignore the formatting and           such.  
//  String output=new PDFTextStripper().getText(parser.getPDDocument());  
//  //System.out.println(output);  
//  Assert.assertTrue(output.length()>0,"Text is extracted successfully");
//  parser.getPDDocument().close();   
//  Reporter.log("base template reading is done");  
//
//  return output;
//  } 
//  
//  public String ReadModifiedTemplate() throws IOException{  
//	  	String filepath1 = "C:\\Users\\ABHATTI\\Documents\\PAD\\PAD Document Commital Process\\Documents 2\\PAFR2.pdf";
//	    File file1 = new File(filepath1);
////  URL url = new URL(driver.getCurrentUrl());   
////  BufferedInputStream fileToParse=new BufferedInputStream(url.openStream());  
//   //parse()  --  This will parse the stream and populate the COSDocument object.   
//  //COSDocument object --  This is the in-memory representation of the PDF document  
//  PDFParser parser = new PDFParser(new FileInputStream(file1));  
//  parser.parse();  
//  //getPDDocument() -- This will get the PD document that was parsed. When you are done with this document 
//  //you must call    close() on it to release resources  
//  //PDFTextStripper() -- This class will take a pdf document and strip out all of the text and ignore the formatting and           such.  
//  String output1=new PDFTextStripper().getText(parser.getPDDocument());  
//  //System.out.println(output1);  
//  Assert.assertTrue(output1.length()>0,"Text is extracted successfully");
//  parser.getPDDocument().close();   
//  Reporter.log("modified template reading is done");  
//
//  return output1;
//  }
//  
////  @AfterTest
////  public void teardown()
////  {
////    System.out.println("Execution Done");
////  }
//}  