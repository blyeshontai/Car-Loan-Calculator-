import java.util.Scanner;

class CarLoan {
    private double loanAmount;
    private double interestRate; // annual interest rate in percentage
    private int loanLengthYears;
    private double downPayment;

    public CarLoan(double loanAmount, double interestRate, int loanLengthYears, double downPayment) {
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.loanLengthYears = loanLengthYears;
        this.downPayment = downPayment;
    }

    public double calculateMonthlyPayment() {
        double principal = loanAmount - downPayment;

        if (principal <= 0) return 0;

        int months = loanLengthYears * 12;
        double monthlyInterestRate = interestRate / 12 / 100;

        // Monthly payment using compound interest formula
        return (principal * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -months));
    }

    public double totalPayment() {
        return calculateMonthlyPayment() * loanLengthYears * 12;
    }

    public double totalInterest() {
        return totalPayment() - (loanAmount - downPayment);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Car Loan Payment Calculator ===");

        System.out.print("Enter car loan amount: $");
        double loanAmount = scanner.nextDouble();

        System.out.print("Enter annual interest rate (e.g., 5 for 5%): ");
        double interestRate = scanner.nextDouble();

        System.out.print("Enter loan length in years: ");
        int loanLength = scanner.nextInt();

        System.out.print("Enter down payment: $");
        double downPayment = scanner.nextDouble();

        if (loanAmount <= 0 || interestRate < 0 || loanLength <= 0 || downPayment < 0) {
            System.out.println("Error: Please enter valid positive numbers.");
            return;
        }

        CarLoan loan = new CarLoan(loanAmount, interestRate, loanLength, downPayment);

        double monthlyPayment = loan.calculateMonthlyPayment();

        if (monthlyPayment == 0) {
            System.out.println("The car can be paid in full with the down payment.");
        } else {
            System.out.printf("Monthly Payment: $%.2f%n", monthlyPayment);
            System.out.printf("Total Payment over %d years: $%.2f%n", loanLength, loan.totalPayment());
            System.out.printf("Total Interest Paid: $%.2f%n", loan.totalInterest());
        }
    }
}
