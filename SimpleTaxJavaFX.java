// Dan Lee

import javafx.application.Application;
import javafx.geometry.*; 
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;  
import javafx.stage.Stage;
import javafx.event.*; 
import javafx.scene.text.*;

public class SimpleTaxJavaFX extends Application 
{
	private TextField first = new TextField(); 
	private TextField last = new TextField(); 
	private TextField salary = new TextField(); 
	private TextField ssn = new TextField(); 
	private TextField empId = new TextField(); 
	private TextField dob = new TextField();
	public Label error = new Label();  
	public Label title = new Label("Simple Tax Form\nPlease complete the fields below");
	
	public static void main(String[] args)
   	{
      	Application.launch(args);
   	}
   
	@Override
	public void start(Stage primaryStage)
	{
		VBox labels = new VBox(); 
    	GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
    	labels.setAlignment(Pos.CENTER);
    	pane.setPadding(new Insets(10, 5, 5, 5)); 
    	labels.setPadding(new Insets(5, 5, 5, 5)); 
    	pane.setHgap(45.5);
    	pane.setVgap(20.5); 
      
      	title.setStyle("-fx-font-weight: bold; -fx-font-size:20;"); 
      	error.setStyle("-fx-text-fill: red;"); 
      
      	labels.getChildren().add(title);
      	labels.getChildren().add(error);  
      
      	pane.add(new Label("First Name: "), 0, 0);
      	pane.add(first, 1, 0); 
      	pane.add(new Label("Last Name: "), 0, 1); 
      	pane.add(last, 1, 1); 
      	pane.add(new Label("Salary: "), 0, 2); 
      	pane.add(salary, 1, 2);  
      	pane.add(new Label("Social Security Number: "), 0, 3); 
      	pane.add(ssn, 1, 3); 
      	pane.add(new Label("Employer ID: "), 0, 4); 
      	pane.add(empId, 1, 4); 
      	pane.add(new Label("Date of Birth: "), 0, 5); 
      	pane.add(dob, 1, 5); 
      
      	Button btnSubmit = new Button("Submit");
      	pane.add(btnSubmit,1,6); 
      	GridPane.setHalignment(btnSubmit, HPos.RIGHT); 
            
      	Scene scene = new Scene(new VBox(labels,pane), 450, 525);  
      	primaryStage.setTitle("Sign up form"); // Set the stage title
      	primaryStage.setScene(scene); // Place the scene in the stage
      	primaryStage.show(); // Display the stage
      
      	btnSubmit.setOnAction(new EventHandler<ActionEvent>()
      	{
        	   @Override
         	public void handle(ActionEvent e)
         	{
            	String fName = first.getText(); 
            	String lName = last.getText(); 
            	String inSalary = salary.getText(); 
            	String inSSN = ssn.getText(); 
            	String inEmpId = empId.getText(); 
            	String inDob = dob.getText(); 
               
               //initializing error's text to empty
            	error.setText("");
            	
            	// Call the validation methods here.
               checkDob(inDob);
               checkEmpId(inEmpId);
               checkSSN(inSSN);
               checkSalary(inSalary);
               checkLast(lName);
               checkFirst(fName);
               
            	if (error.getText() == "")
            	{
               		primaryStage.hide();
               		resultsPage();
            	}
           
         	}    
		   }); 
	}
   
//validate first
   public void checkFirst(String fName) {
      if(fName.matches("")) {
         error.setText("First name required");
      }
      
      
      else if(fName.length() < 2 || fName.matches(".*\\d+.*")) {
         error.setText("First name invalid");
      }
   }

//validate last
   public void checkLast(String lName) {
      if(lName.matches("")) {
         error.setText("Last name required");
      }
      
      
      else if(lName.length() < 2 || lName.matches(".*\\d+.*")) {
         error.setText("Last name invalid");
      }
   }
    
//validate salary
   public void checkSalary(String inSalary) {
      String capLetters = ".*[A-Z].*";
      String lowLetters = ".*[a-z].*";
      String numbers = ".*[0-9].*";
   
      if(inSalary.matches("")) {
         error.setText("Salary required");
      }
      
      
      //if salary doesn't contain numbers
      else if(!inSalary.matches(numbers)) {
         error.setText("Required salary range: 0 - 999999");
      }
      
      
      else if( (inSalary.matches(capLetters) || inSalary.matches(lowLetters)) 
       && inSalary.matches(numbers) ) {
         error.setText("Salary must contain only numbers");
      }
      
      else if(Double.parseDouble(inSalary) < 0 || Double.parseDouble(inSalary) > 999999) {
         error.setText("Required salary range: 0 - 999999");        
      }
   }
   
//validate ssn
   public void checkSSN(String inSSN) {
      String ssnPattern = "\\d{3}\\-?\\d{2}\\-?\\d{4}";
      
      if(inSSN.matches("")) {
         error.setText("SSN required");
      }
      
      else if(!inSSN.matches(ssnPattern)) {
         error.setText("SSN format: xx-xxx-xxx");
      }      
   }
//validate empId
   public void checkEmpId(String inEmpId) {
      String capLetters = ".*[A-Z].*";
      String lowLetters = ".*[a-z].*";
      
      if(inEmpId.matches("")) {
         error.setText("Employee Id required");
      }
      
      else if(inEmpId.matches(capLetters) || inEmpId.matches(lowLetters)) {
         error.setText("SSN should only contain numbers...");
      }
      
      else if(inEmpId.length() < 6) {
         error.setText("Employee Id: Minimum 6 digits");
      }
   }
   
//validate dob
   public void checkDob(String inDob) {
      String dobPattern = "([1-9]|1[0-2])\\/?([1-9]|1[0-9]|2[0-9]|3[0-1])\\/?(19|20)\\d{2}";
      
      if(inDob.matches("")) {
         error.setText("Date of Birth required");
      }
      else if(!inDob.matches(dobPattern)) {
         error.setText("Date of Birth format: xx/xx/xxxx or x/x/xxxx");
      }
   
   }

	public void resultsPage() 
   	{
      	Stage resultsStage = new Stage(); 
      	VBox results = new VBox(); 
      	results.setAlignment(Pos.CENTER);
      	results.setPadding(new Insets(10, 5, 5, 5));
      
      	Label congrats = new Label("Congrats!");
      	Label display = new Label("All the fields have been validated!");
      	congrats.setStyle("-fx-font-weight: bold; -fx-font-size:20; -fx-text-fill:blue;");
      	display.setStyle("-fx-font-weight: bold; -fx-font-size:20; -fx-text-fill:blue;");
      	results.getChildren().add(congrats); 
      	results.getChildren().add(display);
      
      	Scene scene = new Scene(results, 400, 400);  
      	resultsStage.setTitle("Results Page"); 
      	resultsStage.setScene(scene);
      	resultsStage.show(); 
   	}
}