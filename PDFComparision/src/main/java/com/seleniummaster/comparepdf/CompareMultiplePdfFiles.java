package com.seleniummaster.comparepdf;
import com.beust.testng.TestNG;
import com.google.diffmatchpatch.*;
import com.google.diffmatchpatch.Diff_Match_Patch.Diff;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;  

import org.apache.pdfbox.pdfparser.PDFParser;  
import org.apache.pdfbox.util.PDFTextStripper;  
import org.openqa.selenium.WebDriver;  
import org.openqa.selenium.firefox.FirefoxDriver;  
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;  
import org.testng.annotations.Test;  

public class CompareMultiplePdfFiles {  

	WebDriver driver;  

	//  @BeforeTest  
	//  public void setUpDriver() {  
	////   driver = new FirefoxDriver();  
	////   Reporter.log("Web Driver Setup is complete"); 
	//	  System.out.println("Before Test Annotation.........");
	//     }  



	@Test  
	public void CompareTextInTwoPdfs() throws Exception
	{
		Diff_Match_Patch compare= new Diff_Match_Patch();

		String directoryPath = "C:\\Users\\ABHATTI\\Documents\\Reggie Automation files\\Documents Base files";
		File directory = new File(directoryPath);
		File[] filesInDir = directory.listFiles();//list all files in directory

		String directoryPath1 = "C:\\Users\\ABHATTI\\Documents\\Reggie Automation files\\Documents Changed files";
		File directory1 = new File(directoryPath1);
		File[] filesInDir1 = directory1.listFiles();//list all files in directory

//		for(File file: filesInDir) {  
//			if(!file.isDirectory()){ //read the file if not directory
//				BufferedReader reader = new BufferedReader (new FileReader(file));
//				System.out.println("Start reading file : "+file);				
//				String text1=ReadBaseTemplate(reader,file);// Call to the base document parser method...
//				for(File file1: filesInDir1) {  
//					if(!file1.isDirectory()){ //read the file if not directory
//						BufferedReader reader1 = new BufferedReader (new FileReader(file1));
//						System.out.println("Start reading file : "+file1);
//						String text2=ReadModifiedTemplate(reader1,file1);	
//															
//					// Call to the new document parser method...
//					//System.out.print(compare.diff_main(text1,text2)); 	
//					LinkedList<Diff> output = compare.diff_main(text1,text2);
//					compare.diff_cleanupSemantic(output);
//					String s = compare.diff_prettyHtml(output);
//					PrintWriter out = createHTMLReport(s);					
//					continue;
//					}
//					
//				}
//			
//			}
//			
//		}
		System.out.println(filesInDir.length);
		
		for(int i =0; i<filesInDir.length; i++){
			BufferedReader reader = new BufferedReader (new FileReader(filesInDir[i]));
			System.out.println("Start reading file : "+filesInDir[i].getName().toString());				
			String text1=ReadBaseTemplate(reader,filesInDir[i]);// Call to the base document parser method...
			BufferedReader reader1 = new BufferedReader (new FileReader(filesInDir[i]));
			System.out.println("Start reading file : "+filesInDir1[i].getName().toString());				
			String text2=ReadModifiedTemplate(reader1,filesInDir1[i]);
			LinkedList<Diff> output = compare.diff_main(text1,text2);
			compare.diff_cleanupSemantic(output);
			String s = compare.diff_prettyHtml(output);
//			PrintWriter out = createHTMLReport(s,i);
//			out.print(s);
			PrintWriter out = null;
			try {  
				out = new PrintWriter( "C:\\Users\\ABHATTI\\Desktop\\report"+i+".html");
				out.println( s );
							}
			catch (IOException e){
				throw e;
			}

			finally
			{

				if ( out != null)
					out.close();
			}
			
		}
		
	}
				
				
				
				public String ReadBaseTemplate(BufferedReader reader, File file) throws IOException{  

					//		String directoryPath = "C:\\Users\\ABHATTI\\Documents\\Reggie Automation files\\Documents Base files";
					//		File directory = new File(directoryPath);
					//		File[] filesInDir = directory.listFiles();//list all files in directory
					String output = null;
					//		for(File file: filesInDir) {  
					//			if(!file.isDirectory()){ //read the file if not directory
					//				BufferedReader reader = 
					//						new BufferedReader (new FileReader(file));
					//				System.out.println("Start reading file : "+file);
					PDFParser parser = new PDFParser(new FileInputStream(file));
					parser.parse();  
					output = new PDFTextStripper().getText(parser.getPDDocument());
					System.out.println(output);
					Assert.assertTrue(output.length()>0,"Text is extracted successfully");
					parser.getPDDocument().close();   
					Reporter.log("base template reading is done");  
					reader.close();
					System.out.println("End reading file : "+file);					

					//String line = null;
					//			      while((line=reader.readLine())!= null){
					//			         System.out.println(line);
					//			         
					//			      }	   
			
			
			return output;

			//getPDDocument() -- This will get the PD document that was parsed. When you are done with this document 
			//you must call    close() on it to release resources  
			//PDFTextStripper() -- This class will take a pdf document and strip out all of the text and ignore the formatting and           such.  

		} 

		public String ReadModifiedTemplate(BufferedReader reader, File file) throws IOException{  
//			String directoryPath = "C:\\Users\\ABHATTI\\Documents\\Reggie Automation files\\Documents Changed files";
//			File directory = new File(directoryPath);
//			File[] filesInDir = directory.listFiles();//list all files in directory
			String output = null;
//			for(File file: filesInDir) {  
//				if(!file.isDirectory()){ //read the file if not directory
//					BufferedReader reader = 
//							new BufferedReader (new FileReader(file));
//					System.out.println("Start reading file : "+file);
					PDFParser parser = new PDFParser(new FileInputStream(file));
					parser.parse();  
					output = new PDFTextStripper().getText(parser.getPDDocument());  
					System.out.println(output);
					Assert.assertTrue(output.length()>0,"Text is extracted successfully");
					parser.getPDDocument().close();   
					Reporter.log("base template reading is done");  
					reader.close();
					System.out.println("End reading file : "+file);					

					//String line = null;
					//			      while((line=reader.readLine())!= null){
					//			         System.out.println(line);
					//			         
					//			      }		      

				
			
			return output;

			//getPDDocument() -- This will get the PD document that was parsed. When you are done with this document 
			//you must call    close() on it to release resources  
			//PDFTextStripper() -- This class will take a pdf document and strip out all of the text and ignore the formatting and
		}
		
//		public PrintWriter createHTMLReport(String s,int i) throws IOException{
//			
//			PrintWriter out = null;
//			try {  
//				out = new PrintWriter( "C:\\Users\\ABHATTI\\Desktop\\report"+i+".html");
//				//out.println( s );
//							}
//			catch (IOException e){
//				throw e;
//			}
//
//			finally
//			{
//
//				if ( out != null)
//					out.close();
//			}
//			return out;
//		}
		

		//  @AfterTest
		//  public void teardown()
		//  {
		//    System.out.println("Execution Done");
		//  }
	}  