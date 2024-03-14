import java.util.*;
import java.time.*;

public class PlantClass {
    private String plantName;
    private int potNumber;
    private String purpose;
    private LocalDate lastWateredDate;

    // Default constructor
    public PlantClass() {
        this.plantName = "";
        this.potNumber = 0;
        this.purpose = "";
        this.lastWateredDate = LocalDate.now(); // Initialize with current date
    }

    // Parameterized constructor
    public PlantClass(String plantName, int potNumber, String purpose, LocalDate lastWateredDate) {
        this.plantName = plantName;
        this.potNumber = potNumber;
        this.purpose = purpose;
        this.lastWateredDate = lastWateredDate;
    }

    // Copy constructor
    public PlantClass(PlantClass otherPlant) {
        this.plantName = otherPlant.plantName;
        this.potNumber = otherPlant.potNumber;
        this.purpose = otherPlant.purpose;
        this.lastWateredDate = otherPlant.lastWateredDate;
    }

    // Getter methods
    public String getPlantName() {
        return plantName;
    }

    public int getPotNumber() {
        return potNumber;
    }

    public String getPurpose() {
        return purpose;
    }

    public LocalDate getLastWateredDate() {
        return lastWateredDate;
    }

    // Setter methods
    public void setPlantName(String newPlantName) {
        if (isValidPlantName(newPlantName)) {
            plantName = newPlantName;
        } else {
            throw new IllegalArgumentException("Enter a valid plant name.");
        }
    }

    public void setPotNumber(int newPotNumber) {
        if (isValidPotNumber(newPotNumber)) {
            potNumber = newPotNumber;
        } else {
            throw new IllegalArgumentException("Enter a valid pot number.");
        }
    }

    public void setPurpose(String newPurpose) {
        if (isValidPurpose(newPurpose)) {
            purpose = newPurpose;
        } else {
            throw new IllegalArgumentException("Enter a valid purpose.");
        }
    }

    public void setLastWateredDate(LocalDate newLastWateredDate) {
        if (isValidDate(newLastWateredDate)) {
            lastWateredDate = newLastWateredDate;
        } else {
            throw new IllegalArgumentException("Enter a valid date.");
        }
    }

    // Validation methods
    private static boolean isValidPlantName(String plantName) {
        return plantName != null && !plantName.trim().isEmpty();
    }

    private static boolean isValidPotNumber(int potNumber) {
        return potNumber > 0;
    }

    private static boolean isValidPurpose(String purpose) {
        return purpose.equalsIgnoreCase("Medicinal") || purpose.equalsIgnoreCase("Culinary") || purpose.equalsIgnoreCase("Decorative");
    }

    private static boolean isValidDate(LocalDate date) {
        return date != null && !date.isAfter(LocalDate.now());
    }

    // Method for validating plant details
    public static PlantClass validatePlantDetails(Scanner scanner, int plantIndex) {
        System.out.println("Enter the details for Plant " + (plantIndex + 1) + ": ");

        String plantName = validatePlantName(scanner);
        int potNumber = validatePotNumber(scanner);
        String purpose = validatePurpose(scanner);
        LocalDate lastWateredDate = validateDateWatered(scanner);

        return new PlantClass(plantName, potNumber, purpose, lastWateredDate);
    }

    // Validation helper methods for plant details
    private static String validatePlantName(Scanner scanner) {
        String plantName;
        do {
            System.out.println("Enter the plant name: ");
            plantName = scanner.nextLine().trim();// is used trim to remove spacing.
        } while (!isValidPlantName(plantName));
        return plantName;
    }

    private static int validatePotNumber(Scanner scanner) {
        int potNumber;
        do {
            try {
                System.out.println("Enter the pot number: ");
                potNumber = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer for the pot number.");
                scanner.nextLine(); // Clear the input buffer
                potNumber = -1; // Set to invalid value to continue loop
            }
        } while (!isValidPotNumber(potNumber));
        return potNumber;
    }

    private static String validatePurpose(Scanner scanner) {
        String purpose;
        do {
            System.out.println("Enter the purpose (Medicinal, Culinary, or Decorative): ");
            purpose = scanner.nextLine().trim();
        } while (!isValidPurpose(purpose));
        return purpose;
    }

    private static LocalDate validateDateWatered(Scanner scanner) {
    LocalDate lastWateredDate;
    do {
        System.out.println("Enter the date watered (yyyy-MM-dd): ");
        String dateString = scanner.nextLine().trim();
        try {
            lastWateredDate = LocalDate.parse(dateString);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format."+e.getMessage());
            lastWateredDate = null; // Set to null to continue loop
        }
    } while (!isValidDate(lastWateredDate));
    return lastWateredDate;
}
	//this is used to edit the csv file.
	public String toeditCsv(){
		return PlantName+","+pitNumber+","+purpose+","+lastWaterDate;
	}
  
    public String toCsv() {
        return "Plant name: " + plantName + " pot number: " + potNumber + " purpose: " + purpose + " Last date watered: " + lastWateredDate;
    }
}

