package cinema;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int numrerOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numrerOfSeats = scanner.nextInt();

        int purchasedTickets = 0; //купленых билетов
        int totalIncome = 0;

        if (numrerOfSeats * numrerOfRows <= 60) {
            totalIncome = numrerOfSeats * numrerOfRows * 10;
        }
        else {
            int firstHalf = numrerOfRows / 2;
            totalIncome = (firstHalf * numrerOfSeats * 10) + ((numrerOfRows - firstHalf) * numrerOfSeats * 8);
        }

        int[] stat = {0};

        char[][] cinemaSeats = new char[numrerOfRows][numrerOfSeats + 1];

        for (int k = 0; k < numrerOfRows; k++) {
            int pointK = k + 1;
            cinemaSeats[k][0] = (char) (pointK + 48);
            for (int j = 1; j <= numrerOfSeats; j++)
                cinemaSeats[k][j] = 'S';
        }

        boolean loop = true;

        while (loop) {
            System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");

            int menuNumber = scanner.nextInt();

            switch (menuNumber){
                case 1: {
                    showTheSeats(cinemaSeats, numrerOfRows, numrerOfSeats);
                    break;
                }
                case 2: {
                    buyATicket(cinemaSeats, numrerOfRows, numrerOfSeats, stat);
                    showTheSeats(cinemaSeats, numrerOfRows, numrerOfSeats);
                    purchasedTickets++;
                    break;
                }
                case 3: {
                    printStatistics(purchasedTickets, totalIncome, stat, numrerOfSeats, numrerOfRows);
                    break;
                }
                case 0: {
                    loop = false;
                    break;
                }
            }
        }
    }
    public static void showTheSeats(char[][] cinemaSeats, int numrerOfRows, int numrerOfSeats) {
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i <= numrerOfSeats; i++)
            System.out.print(" " + i);
        System.out.println();

        for (int k = 0; k < numrerOfRows; k++) {
            for (int j = 0; j <= numrerOfSeats; j++)
                System.out.print(cinemaSeats[k][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
    public static void buyATicket(char[][] cinemaSeats, int numrerOfRows, int numrerOfSeats, int[] stat ) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int rowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNumber = scanner.nextInt();

        System.out.println();

        while (rowNumber > numrerOfRows | rowNumber < 0 | seatNumber < 0 | seatNumber > numrerOfSeats){
            System.out.println("Wrong input!\n");
            System.out.println("Enter a row number:");
            rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNumber = scanner.nextInt();
        }

        while (cinemaSeats[rowNumber - 1][seatNumber] == 'B'){
            System.out.println("That ticket has already been purchased!\n");
            System.out.println("Enter a row number:");
            rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNumber = scanner.nextInt();
        }

        if (numrerOfSeats * numrerOfRows <= 60) {
            System.out.println("Ticket price: $" + 10);
            stat [0] += 10;
        }
        else {
            int firstHalf = numrerOfRows / 2;
            if (firstHalf >= rowNumber) {
                System.out.println("Ticket price: $" + 10);
                stat [0] += 10;
            } else {
                System.out.println("Ticket price: $" + 8);
                stat [0] += 8;
            }
        }
        cinemaSeats[rowNumber - 1][seatNumber] = 'B';
    }

    public static void printStatistics(int purchasedTickets, int totalIncome, int[] stat, int numrerOfSeats, int numrerOfRows){
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        double persent, p, n, m;
        p = (double) purchasedTickets;
        n = (double) numrerOfSeats;
        m = (double) numrerOfRows;
        persent = p * 100 / (n * m);
        System.out.println(persent);
        String str = String.format("Percentage: %.2f", persent);
        System.out.println(str + "%");
        System.out.println("Current income: $" + stat[0]);
        System.out.println("Total income: $" + totalIncome);
        System.out.println();
    }
}