public class PlantClass {
    private String plantName;
    private int potNumber;
    private String purpose;
    private String lastWateredDate;

    // Default constructor
    public PlantClass() {
        this.plantName = "";
        this.potNumber = 1;
        this.purpose = "Decorative";
        this.lastWateredDate = "Not watered yet";
    }

    // Parameterized constructor
    public PlantClass(String plantName, int potNumber, String purpose, String lastWateredDate) {
        setPlantName(plantName);
        setPotNumber(potNumber);
        setPurpose(purpose);
        setLastWateredDate(lastWateredDate);
    }

    // Copy constructor
    public PlantClass(PlantClass other) {
        this.plantName = other.plantName;
        this.potNumber = other.potNumber;
        this.purpose = other.purpose;
        this.lastWateredDate = other.lastWateredDate;
    }

    // Accessors (getters)
    public String getPlantName() {
        return plantName;
    }

    public int getPotNumber() {
        return potNumber;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getLastWateredDate() {
        return lastWateredDate;
    }

    // Mutators (setters) with validation
    public void setPlantName(String plantName) {
        if (!isValidPlantName(plantName)) {
            throw new IllegalArgumentException("Invalid plant name.");
        }
        this.plantName = plantName;
    }

    public void setPotNumber(int potNumber) {
        if (!isValidPotNumber(potNumber)) {
            throw new IllegalArgumentException("Invalid pot number.");
        }
        this.potNumber = potNumber;
    }

    public void setPurpose(String purpose) {
        if (!isValidPurpose(purpose)) {
            throw new IllegalArgumentException("Invalid purpose.");
        }
        this.purpose = purpose;
    }

    public void setLastWateredDate(String lastWateredDate) {
        if (!isValidDate(lastWateredDate)) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd format.");
        }
        this.lastWateredDate = lastWateredDate;
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

    private static boolean isValidDate(String dateStr) {
        if (dateStr == null || dateStr.length() != 10) {
            return false;
        }

        if (dateStr.charAt(4) != '-' || dateStr.charAt(7) != '-') {
            return false;
        }

        try {
            int year = Integer.parseInt(dateStr.substring(0, 4));
            int month = Integer.parseInt(dateStr.substring(5, 7));
            int day = Integer.parseInt(dateStr.substring(8, 10));

            if (year < 1000 || year > 9999) {
                return false;
            }
            if (month < 1 || month > 12) {
                return false;
            }
            if (day < 1 || day > 31) {
                return false;
            }

            // Additional checks for days in each month could be added here

        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public String toStringCsv() {
        return plantName + "," + potNumber + "," + purpose + "," + lastWateredDate;
    }
}

