package rflint2740ex3i;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.DefaultListModel;

public class DriverExamObjMapper {
	private String fileName;
	private Scanner inputFile;
	private File file;
	
	public DriverExamObjMapper(String fileName) {
		super();
		this.fileName = fileName;
	}
public boolean openInputFile() {
	boolean fileOpened = false;
	 // Open the file. (Copied from Ex3D)
    try {
    	File file = new File(this.fileName);
		fileOpened = file.exists();
			
		
		if (fileOpened) {
			// Open the file.
		   this.inputFile = new Scanner(file);
		}
    }
    catch (IOException e) {}
	
	return  fileOpened;
}


public void closeInputFile(){
	if (this.inputFile != null) this.inputFile.close();
}

public DriverExam getAllDriverExam(){
	DriverExam exam = null;
	DefaultListModel<String> driverExamCollection = new DefaultListModel<String>();
	 if (this.openInputFile())
	 {
		 while (this.inputFile.hasNext()){
			 String answer = inputFile.nextLine();
		driverExamCollection.addElement(answer);
	 }
		 exam = new DriverExam(driverExamCollection);
	 }
	 
	 this.closeInputFile();
	return exam;
}
@SuppressWarnings("unused")
private DriverExam getNextDriverExam() {
	// TODO Auto-generated method stub
	return null;
}
public File getFile() {
	return file;
}
public void setFile(File file) {
	this.file = file;
}


}


