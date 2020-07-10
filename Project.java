import java.util.Scanner; // to take input for the exam grades
import java.util.ArrayList; // to allow for dynamic arrays to store input grades

public class Project {
    public static void main(String[] args) {

        /* Creating instructions for the user, which can optionally be printed */

        System.out.println("\nWelccome to the grade entry application. Please type \"info\" for what this application does, or \"start\" to enter grades.");

        // The program details are saved into a String variable. To allow it to be displayed on multiple lines, new lines and string concatination is used.
        String program_details = "\nThis application will allow you to select the number of grades you wish to input.\n" +
            "You will always have the option to input 999 to exit your input early.\n" +
            "You will then see all the grades you have input, and asked to confirm if they are correct, or else start the program over to input again.\n" +
            "You will then see the average grade of the quizzes entered, as well the rounded grade.\n" +
            "Finally, you will see the letter grade for the average, based on the following categories of grades:\n" +
            "A = 90-100, " + "B = 80-89, " + "C = 70-79, " + "D = 60-69, " + "F = 0-59.\n";

        Scanner user_select = new Scanner(System.in); // Creates a scanner for user to give their confirmation
    
        while(true) {
            String user_ans = user_select.nextLine();
            if (user_ans.equals("Start")|| user_ans.equals("start")){
                break;
            }
            else if (user_ans.equals("Info")|| user_ans.equals("info"))
            {
                System.out.println(program_details);
                break;
            }
            else {
                System.out.println("Please type info or start");
                continue;
            }
        }

        /* Code input from the user for the specified number of inputs, or until 999 is entered to exit */
        
        System.out.println("How many grades to enter?");
        Scanner num_of_grades = new Scanner(System.in); // Creates a scanner for how many grades to input.
        
        // Error check to confirm input is an integer
        while (!num_of_grades.hasNextInt()) {
            System.out.println("Please insert a whole number");
            num_of_grades.next();
        }

        int grade_count = num_of_grades.nextInt();
        
        
        ArrayList < Integer > grades = new ArrayList < Integer > (); // Create an ArrayList object to store the grades.

        Scanner gradeinput = new Scanner(System.in); // Creates a scanner to input the grades.
        System.out.println("Enter Grades (Press Enter after each). Enter 999 to quit early:"); // informs user of instructions of grade input.
        
        for (int i = 0; i != grade_count; i++) {
            // Error check to confirm input is an integer
            while (!gradeinput.hasNextInt()) {
                System.out.println("Please insert a whole number");
                gradeinput.next();
            }
            // Saves input into a variable
            int current_input = gradeinput.nextInt();

            // Firstly, checks if the input was the exit condition.
            if (current_input == 999) {
                break;
            }
            // Secondly, confirm if the input is within 0 to 100. If not, display error, decrement count, and start loop over.
            if (current_input > 100 || current_input < 0) {
                System.out.println("Please enter a grade between 0 and 100");
                i -= 1;
                continue;
            }
            // Any value that made it here is a grade to be added to the current list of grades.
            else {
                grades.add(current_input);
            }
        }


        // Since it is valid to enter 0 grades, the following prompts won't make sense if this is case. If so, it is best to exit the program.
        if (grades.size() == 0) {
            System.out.println("\nNo grades were input. The program will exit\n");
            System.exit(1);
        }

        // Prints entered grades.
        System.out.println("\nInput Successful. Total grades entered: " + grades.size() +".");


        /* Confirming the entry of Grades*/

        System.out.println("\nReview and confirm the entered grades:");

        // This loop prints the entered grades by iterating over the legnth of the ArrayList and printing the i-th index each time.
        for (int i = 0; i < grades.size(); i++) {
            System.out.println((i + 1) + ". " + grades.get(i));
        }
        System.out.println("\nIs this correct? Y/N");

        Scanner user_confirm = new Scanner(System.in); // Creates a scanner for user to give their confirmation
        
        while(true) {
            String user_ans = user_confirm.nextLine();
            if (user_ans.equals("Y")|| user_ans.equals("y")){
                break;
            }
            else if (user_ans.equals("N")|| user_ans.equals("n"))
            {
                System.out.println("\nPlease re-run program to enter grades correctly. The program will exit.\n");
                System.exit(2);
            }
            else {
                System.out.println("Please enter Y or N");
                continue;
            }
        }


        /* Computing the average grade, rounded and unrounded*/

        double total = 0; // will tally grade total for average

        // This loop iterates over the array list, adding all values together, and then divides it by the legnth of the list for the average.
        for (int i = 0; i < grades.size(); i++) {
            int current_grade = grades.get(i);
            total += current_grade;
        }

        double avg = total / grades.size();
        System.out.println("\nThe average of the grades is " + avg);

        int rounded_avg = (int) Math.round(avg); // rounds average and casts it to int.
        System.out.println("\nAfter rounding: " + rounded_avg);


        char avg_as_letter = '?'; // a default value just to initialize var.

        // if statements to check value. The else will take care of anything between 0 and 59:

        if (100 >= rounded_avg || rounded_avg >= 90) {
            avg_as_letter = 'A';
        }
        if (89 >= rounded_avg) {
            avg_as_letter = 'B';
        }
        if (79 >= rounded_avg) {
            avg_as_letter = 'C';
        }
        if (69 >= rounded_avg) {
            avg_as_letter = 'D';
        } 
        if (59 >= rounded_avg) {
            avg_as_letter = 'F';
        }

        System.out.println("\nThe letter grade average is " + avg_as_letter + ".\n");

        // close Scanners
        user_select.close();
        num_of_grades.close();
        gradeinput.close();
        user_confirm.close();
    }

}