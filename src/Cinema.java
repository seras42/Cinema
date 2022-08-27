import java.text.DecimalFormat;
import java.util.Scanner;

public class Cinema {

    public static int rows;
    public static int seats;
    public static int totalSeats;
    public static char[][] seatsArray;
    public static int[] seatsNum;
    public static Scanner scanner = new Scanner(System.in);
    public static int rowSelected;
    public static int seatSelected;
    public static boolean run = true;
    public static int tickets = 0;
    public static int currentIncome;
    public static int totalIncome;

    public static int firstHalfRowSeats;
    public static int secondHalfRowSeats;

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();
        totalSeats = rows * seats;
        seatsArray = new char[rows][seats];
        seatsNum = new int[seats];

        createSeats();
        calculateTotalIncome();

        while (run) {
            chooseTask();
        }
    }

    public static void showSeats() {
        //DisplaysSeats
        System.out.println("Cinema:");
        System.out.print("  ");
        //Displayers seats numbers
        for (int k = 0; k < seats; k++) {
            System.out.print(seatsNum[k] + " ");
        }
        System.out.println();
        //prints out seats array
        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < seats; j++) {
                System.out.print(" " + seatsArray[i][j]);
            }
            System.out.println();
        }
        System.out.println();

    }

    public static void createSeats() {
        //Fills the char with "S"
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                seatsArray[i][j] = 'S';
            }
        }
        //fills the seats string
        for (int i = 1; i - 1 < seats; i++) {
            seatsNum[i - 1] = i;
        }
    }

    public static void chooseTask() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        int input = scanner.nextInt();
        if (input == 1) {
            showSeats();
        } else if (input == 2) {
            buyTicket();
        } else if (input == 3) {
            statistics();
        } else {
            run = false;
        }

    }

    public static void buyTicket() {

        boolean run = true;
        while (run) {
            boolean badInput = true;
            while (badInput) {
                System.out.println("Enter a row number:");
                rowSelected = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                seatSelected = scanner.nextInt();
                if (rowSelected > rows || seatSelected > seats || rowSelected <= 0 || seatSelected <= 0) {
                    System.out.println("Wrong input!");
                    System.out.println();
                } else {
                    badInput = false;
                }
            }


            //changes S to B in selected seat location
            if (seatsArray[rowSelected - 1][seatSelected - 1] != 'B') {
                System.out.println();
                seatsArray[rowSelected - 1][seatSelected - 1] = 'B';
                run = false;
                tickets++;

                //price calculation depending on the rows
                Boolean calculatePrice = true;
                if (totalSeats <= 60) {
                    int priceWhole = totalSeats * 10;
                    calculatePrice = false;
                    System.out.println("Ticket price: $10");
                    System.out.println();
                    currentIncome += 10;
                }
                if (calculatePrice) {
                    double firstHalfRow = rows / 2d;
                    int secondHalfRow = rows / 2;
                    int firstHalfRowSeats = (int) (firstHalfRow * seats);
                    int secondHalfRowSeats = secondHalfRow * seats;
                    //if num of rows is even number
                    if ((firstHalfRow / 0.5d) % 2 == 0.0) {
                        if (rowSelected <= secondHalfRow) {
                            System.out.println("Ticket price: Second half row $10");
                            System.out.println();
                            currentIncome += 10;
                        } else {
                            System.out.println("Ticket price: First half row $8");
                            System.out.println();
                            currentIncome += 8;
                        }

                    }
                    // if num of rows is not even number
                    if ((firstHalfRow / 0.5d) % 2 != 0.0) {
                        firstHalfRow = firstHalfRow - 0.5;
                        secondHalfRow = (int) (firstHalfRow) + 1;
                        firstHalfRowSeats = (int) (firstHalfRow * seats);
                        secondHalfRowSeats = secondHalfRow * seats;
                        if (rowSelected < secondHalfRow) {
                            System.out.println("Ticket price: $10");
                            System.out.println();
                            currentIncome += 10;
                        } else {
                            System.out.println("Ticket price: $8");
                            System.out.println();
                            currentIncome += 8;
                        }

                    }

                }
            } else {
                System.out.println("That ticket has already been purchased!");
                System.out.println();
            }
        }
    }

    public static void statistics() {
        System.out.println("Number of purchased tickets: " + tickets);
        DecimalFormat formatter = new DecimalFormat("#0.00");
        //calculates the % of tickets bought
        double percentange = 100d / totalSeats * tickets;
        System.out.format("Percentage: " + formatter.format(percentange));
        System.out.print("%");
        System.out.println();
        //System.out.println("Percentage: " + percentange + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
        System.out.println();
    }

    public static void calculateTotalIncome() {
        //price calculation depending on the rows
        Boolean calculatePrice = true;
        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
            calculatePrice = false;
        }
        if (calculatePrice) {
            double firstHalfRow = rows / 2d;
            int secondHalfRow = rows / 2;
            firstHalfRowSeats = (int) (firstHalfRow * seats);
            secondHalfRowSeats = secondHalfRow * seats;
            // if num of rows is not even number
            if ((firstHalfRow / 0.5d) % 2 != 0.0) {
                firstHalfRow = firstHalfRow - 0.5;
                secondHalfRow = (int) (firstHalfRow) + 1;
                firstHalfRowSeats = (int) (firstHalfRow * seats);
                secondHalfRowSeats = secondHalfRow * seats;
            }
            totalIncome = firstHalfRowSeats * 10 + secondHalfRowSeats * 8;
        }
    }

}

