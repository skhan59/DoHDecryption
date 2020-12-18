import java.io.*;  
import java.util.Scanner;  
public class CSVSplit 
{  
	public static void main(String args[])  
    {  	
	String fileName = "1DOH.csv"; //change fileName

    
	FileInputStream fileIn=null; //takes input from csv
	FileOutputStream fileOut =null;
	DataInputStream dataIn=null;
	DataOutputStream dataOut =null; //writes out data from csv 
	
	String line;
	try //
	{  
	    fileIn = new FileInputStream(fileName); 
	    dataIn = new DataInputStream(fileIn);  
	    int x=0; //number of output files
	    
	    fileOut = new FileOutputStream("1DOHSplitFile"+(x+1)+".csv");  //creates new file called "splitfileX.csv)
	    dataOut = new DataOutputStream(fileOut);  
	    
	    while((line=dataIn.readLine())!=null)
	    {
		String words[]=line.split(","); 
		String strTime=words[1].substring(1, words[1].length()-1); //getting time values from the proper column
		double time=Double.parseDouble(strTime);
		
		if(time<=50*(x+1)) //if time extracted from line is in the range of (0-50, 50.0001-100, etc)
		{
		    dataOut.writeBytes(line+"\n"); //write the current line and add new line character
		}
		else //oherwise make new file 
		{
		    dataOut.close();
		    fileOut.close();
		    x++; //counter
		    fileOut = new FileOutputStream("outputfile"+(x+1)+".csv");
		    dataOut = new DataOutputStream(fileOut); 
		}
	    }
	    System.out.println("File splitted. Check your folder"); //command+spacebar to find more easily
	}
	catch (FileNotFoundException e) //catches for the earlier "try"
	{  
	    System.err.println("File Not Exception: " + e.getMessage());  
	} 
	catch (IOException e)
	{  	    
	    System.err.println("IOException: " + e.getMessage());  
	}  
	catch (Exception e)	{
	    System.err.println("Exception: " + e.getMessage());  
	}
	}
}