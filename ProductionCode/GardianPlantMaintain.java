import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GardianPlantMaintain {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//global variable but helps to program be cleaner that this stage.

    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to the Garden Maintenance Program!");

    System.out.print("Enter the filename to load the garden data (CSV format): ");
    String filename = scanner.nextLine();

    PlantClass[] garden = new PlantClass[100];
    int plantCount = loadGardenData(filename, garden);

    boolean running = true;
    while (running) {
        displayMenu();
        int choice = getValidIntegerInput(scanner); // Use the new method
        scanner.nextLine(); // Consume newline

        if (choice == 1) {
            plantCount = addNewPlant(scanner, garden, plantCount);
        } else if (choice == 2) {
            waterAllPlants(garden, plantCount);
        } else if (choice == 3) {
            searchForPlants(scanner, garden, plantCount);
        } else if (choice == 4) {
            saveGardenData(filename, garden, plantCount);
            running = false;
        } else if (choice == 5) {
            running = false;
        } else {
            System.out.println("Invalid option, please try again.");
        }
    }
    scanner.close();
}

    
//this method is used to validate integer inputs such as the pot number.
private static int getValidIntegerInput(Scanner scanner) {
   while (true) { // Infinite loop that will return once a valid integer is entered.
       try {
            return scanner.nextInt(); // Attempt to read an integer
       } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number for your choice.");
            scanner.nextLine(); // Clear the buffer
            // No continue needed; the loop will just iterate again
       }
    }
}

 
//Got the idea from stake over flow, when searching for general java i/o practice.
 private static int loadGardenData(String filename, PlantClass[] garden) {
    int count = 0;
    File file = new File(filename);
    boolean fileExists = file.exists();

    if (!fileExists) {
        System.out.println("The file " + filename + " does not exist.");
    } else {
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",");
                if (data.length == 4) {
                    garden[count++] = new PlantClass(data[0], Integer.parseInt(data[1]), data[2], data[3]);
                }
            }
        } catch (FileNotFoundException e) { // This catch might be redundant given the file existence check
            System.out.println("Failed to open the file: " + filename);
        }
    }
    return count; // Single return statement
}


// used to enter new plants to the be added to the Csv file.
 private static int addNewPlant(Scanner scanner, PlantClass[] garden, int plantCount) {
     System.out.println("Enter details for the new plant:");
     System.out.print("Plant name: ");
     String name = scanner.nextLine();
     System.out.print("Pot number: ");
     int potNumber = scanner.nextInt();
     scanner.nextLine(); // Consume newline
     System.out.print("Purpose(medicinal/culinary/decorative/madilinala): ");
     String purpose = scanner.nextLine();

     String wateredDate = "";
     boolean validDate = false;
     while (!validDate) {
         System.out.print("Last watered date (yyyy-MM-dd): ");
         wateredDate = scanner.nextLine();
         validDate = isValidDate(wateredDate);
         if (!validDate) {
             System.out.println("Invalid date format. Please try again using the yyyy-MM-dd format.");
          }
     }

     garden[plantCount++] = new PlantClass(name, potNumber, purpose, wateredDate);
     System.out.println("Plant added.");
     return plantCount;
 }
    
// used to check if all the plants where watered or not.
 private static void waterAllPlants(PlantClass[] garden, int plantCount) {
     String currentDate = dateFormat.format(new Date());
     for (int i = 0; i < plantCount; i++) {
         garden[i].setLastWateredDate(currentDate);
     }
     System.out.println("All plants have been watered.");
 }

 //used to search for plants in the csv file.
 private static void searchForPlants(Scanner scanner, PlantClass[] garden, int plantCount) {
     System.out.print("Enter plant name to search for: ");
     String name = scanner.nextLine();
     boolean found = false;
     for (int i = 0; i < plantCount; i++) {
         if (garden[i].getPlantName().equalsIgnoreCase(name)) {
             System.out.println(garden[i].toStringCsv());
             found = true;
          }
     }
        if (!found) {
            System.out.println("No plant found with the name " + name);
        }
 }

 //to save data to the csv file. if the user enter a file that doesn't exist it will give an error message.
 private static void saveGardenData(String filename, PlantClass[] garden, int plantCount) {
    try (PrintWriter writer = new PrintWriter(filename)) {
        for (int i = 0; i < plantCount; i++) {
            writer.println(garden[i].toStringCsv());
        }
        System.out.println("Garden data saved to " + filename);
    } catch (FileNotFoundException e){
        System.out.println("Error saving the file: " + e.getMessage());
    }
}

//this method is used to display the menu to the user.
 private static void displayMenu() {
     System.out.println("\nWhat would you like to do?");
     System.out.println("1. Add a new plant");
     System.out.println("2. Water all plants");
     System.out.println("3. Search for plants");
     System.out.println("4. Save and exit");
     System.out.println("5. Exit without saving");
     System.out.print("Select an option: ");
 }
	
// Find another way for this question. there can only be one return per Method.
 private static boolean isValidDate(String dateStr) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    sdf.setLenient(false); // controls how strict the parsing of date strings should be.
    boolean isValid;
    try {
        sdf.parse(dateStr);
        isValid = true; 
    } catch (ParseException e) {
        isValid = false; 
    }
    return isValid; 
}

}

