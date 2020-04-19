/*
    Name: Keerthana Madhavan
    Program: Assignment 8
 
 */

import java.io.*;
import java.util.Scanner;

public class TestTool {

	public static void main(String[] args) {
		String classname = args[0];
		String textfile = args[1];
		
		BufferedReader in;
		BufferedWriter out;
		
		
		char[] b = new char[100];
    
		int num = 1;
		
		Scanner scan;
		String txtans = null;
		String txtinput = null;
		String progAns = " ";
		
		
		
		try {
			scan =  new Scanner(new FileInputStream(textfile));
			
			
			Process process = Runtime.getRuntime().exec("java "+classname);
			
			
			in = new BufferedReader(new InputStreamReader(process.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
			
			
			
			
			while(scan.hasNext()) {
				txtinput = scan.nextLine();

				if(txtinput.contains("#")) {
					num++;
					process = Runtime.getRuntime().exec("java "+classname);
					in = new BufferedReader(new InputStreamReader(process.getInputStream()));
					out = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

				}
				else if(txtinput.contains("*")) {
					txtans = scan.nextLine();
					
					progAns = in.readLine();
					
					
					if(progAns.equals(txtans)) {
						System.out.println("test case "+num+" : pass");
					}
					else {
						System.out.println("test case "+num+" : fail");
					}
					        
				}
				else{
					out.write(txtinput);
					out.newLine();
					out.flush();	
				}
			}
			process.destroy();
			if (in != null)
				in.close();
			if (out != null)
				out.close();
			
			
		} catch (Exception e1) {
			System.out.println("Nope: "+e1);
			e1.printStackTrace();
		}
		
		

	}

}
