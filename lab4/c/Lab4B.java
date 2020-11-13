package c;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import a.Billing;
import a.Reception;
import a.Dispatch;
import a.Lab;
import a.LabTest;
import b.BioChemLab;
import b.BioChemTest;
import b.PathLab;
import b.PathLabTest;

public class Lab4B {

	static BioChemLab bioChemLab = new BioChemLab();
	static PathLab pathLab = new PathLab();

	public static BioChemLab getBioChemLab(){
		return bioChemLab;
	}

	public static PathLab getPathLab(){
		return pathLab;
	}


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n,totalBill=0;
		String test,patientName;
		ArrayList<String> bills = new ArrayList<String>();
		ArrayList<String> reports = new ArrayList<String>();
		ArrayList<BioChemTest> bioChemTestlist = new ArrayList<BioChemTest>();
		ArrayList<PathLabTest> pathTestlist = new ArrayList<PathLabTest>();
		String[] strArr = new String[2];

		// create instance of Reception, Billing, Dispatch, BioChemLab, PathLab
		Reception reception = new Reception();
		Billing bill = new Billing();
		Dispatch dispatch = new Dispatch();
		// static BioChemLab bioChemLab = new BioChemLab();
		// static PathLab pathLab = new PathLab();

		// read in the input for test requests, and send to Reception (addRequest)
		n = scanner.nextInt();
		for(int i=0; i<n; i++){
			test = scanner.next();
			patientName = "patientHasNoName";
			reception.addRequest(test,patientName,bill);
		}

		// get all bills from Billing and print out the total for the day
		bills = bill.getBills();
		for(int i=0; i<bills.size(); i++){
			strArr = bills.get(i).split(" ",2);
			totalBill += Integer.parseInt(strArr[1]);
		}
		System.out.println("Total billed: " + totalBill);
		System.out.println("Reports:");

		// get all reports from Dispatch and print out one line at a time
		// Each line will have: Patient ID, Test ID, result string
		reports = dispatch.getAllResults();
		for(int i=0; i<bills.size(); i++){
			System.out.println(reports.get(i));
		}

		// get list of tests from each lab, and print out total tests per lab
		bioChemTestlist = BioChemLab.getBioChemTestList();
		pathTestlist = PathLab.getPathTestList();
		System.out.println("Total tests:");
		System.out.println("BioChem: " + bioChemTestlist.size());
		System.out.println("Path: " + pathTestlist.size());

	}

}

// Move to different packages and files
// Many return statements have default values (to allow them to be compiled.
// Replace with the correct statements.


// Similarly, define a PathLabTest, PathLab etc
