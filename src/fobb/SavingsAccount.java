package fobb;

/**
 * SavingsAccount.java
 * A subclass of Account that represents a savings account with custom
 * interest calculation and withdrawal rules.
 * 
 * @author [Goh Pei Yi]
 */
public class SavingsAccount extends Account {
    
    // Unique attributes for SavingsAccount
    private double interestRate;      // Annual interest rate (e.g., 0.03 for 3%)
    private double minimumBalance;    // Minimum balance required
    private double withdrawalLimit;   // Maximum withdrawal amount per transaction
    private int monthsActive;         // Number of months account has been active
    
    /**
     * Constructor for SavingsAccount
     * @param accountNumber Unique account identifier
     * @param accountHolder Name of account holder
     * @param initialBalance Initial account balance
     * @param interestRate Annual interest rate (e.g., 0.03 for 3%)
     * @param minimumBalance Minimum required balance
     * @param withdrawalLimit Maximum withdrawal per transaction
     */
    public SavingsAccount(String accountNumber, String accountHolder, double initialBalance,
                         double interestRate, double minimumBalance, double withdrawalLimit) {
        // Call parent constructor
        super(accountNumber, accountHolder, initialBalance);
        
        // Initialize SavingsAccount specific attributes
        this.interestRate = interestRate;
        this.minimumBalance = minimumBalance;
        this.withdrawalLimit = withdrawalLimit;
        this.monthsActive = 0;  // New account starts at 0 months
    }
    
    /**
     * Overridden withdraw method with savings account rules
     * 1. Cannot withdraw below minimum balance
     * 2. Cannot exceed withdrawal limit per transaction
     * @param amount The amount to withdraw
     * @return true if withdrawal successful, false otherwise
     */
    @Override
    public boolean withdraw(double amount) {
        // Rule 1: Check if withdrawal would go below minimum balance
        if (getBalance() - amount < minimumBalance) {
            System.out.println("Withdrawal denied: Would go below minimum balance of $" + 
                              String.format("%.2f", minimumBalance));
            return false;
        }
        
        // Rule 2: Check if amount exceeds withdrawal limit
        if (amount > withdrawalLimit) {
            System.out.println("Withdrawal denied: Amount $" + String.format("%.2f", amount) + 
                             " exceeds withdrawal limit of $" + String.format("%.2f", withdrawalLimit));
            return false;
        }
        
        // If all rules pass, perform the withdrawal using parent method
        return super.withdraw(amount);
    }
    
    /**
     * Overridden calculateInterest method
     * Implements compound interest calculation for savings account
     * Prints interest information (matching parent class style)
     */
    @Override
    public void calculateInterest() {
        // Compound interest calculation
        double monthlyRate = interestRate / 12;  // Monthly interest rate
        double compoundFactor = Math.pow(1 + monthlyRate, monthsActive);
        double interest = getBalance() * (compoundFactor - 1);
        
        // Display interest information (matching Ammar's style)
        if (monthsActive > 0) {
            System.out.println("Savings Account Interest: $" + String.format("%.2f", interest) + 
                             " (Rate: " + String.format("%.2f", interestRate * 100) + 
                             "%, Months: " + monthsActive + ")");
        } else {
            System.out.println("No interest yet - account is new.");
        }
    }
    
    /**
     * Method to get calculated interest amount (separate from display)
     * @return The calculated interest amount
     */
    public double getInterestAmount() {
        double monthlyRate = interestRate / 12;
        double compoundFactor = Math.pow(1 + monthlyRate, monthsActive);
        return getBalance() * (compoundFactor - 1);
    }
    
    /**
     * Method to apply interest to the account (specific to savings accounts)
     * Adds calculated interest to balance and resets months counter
     */
    public void applyInterest() {
        double interest = getInterestAmount();
        if (interest > 0) {
            deposit(interest);  // Add interest to balance using parent's deposit method
            
            System.out.println("Interest applied: $" + String.format("%.2f", interest) +
                             " | New balance: $" + String.format("%.2f", getBalance()));
            
            // Reset months counter after applying interest
            monthsActive = 0;
        }
    }
    
    /**
     * Method to increment months active (simulates time passing)
     * Should be called monthly by the banking system
     */
    public void incrementMonth() {
        monthsActive++;
        System.out.println("Month incremented. Account active for " + monthsActive + " months.");
    }
    
    /**
     * Method to change interest rate (specific to savings accounts)
     * @param newRate The new annual interest rate
     */
    public void updateInterestRate(double newRate) {
        this.interestRate = newRate;
        System.out.println("Interest rate updated to: " + String.format("%.2f", newRate * 100) + "%");
    }
    
    // Getter methods for SavingsAccount attributes
    public double getInterestRate() {
        return interestRate;
    }
    
    public double getMinimumBalance() {
        return minimumBalance;
    }
    
    public double getWithdrawalLimit() {
        return withdrawalLimit;
    }
    
    public int getMonthsActive() {
        return monthsActive;
    }
    
    /**
     * Display enhanced account info including savings-specific details
     * Extends parent's displayAccountInfo method
     */
    public void displaySavingsAccountInfo() {
        displayAccountInfo();  // Call parent's display method
        System.out.println("Account Type: Savings Account");
        System.out.println("Interest Rate: " + String.format("%.2f", interestRate * 100) + "%");
        System.out.println("Minimum Balance: $" + String.format("%.2f", minimumBalance));
        System.out.println("Withdrawal Limit: $" + String.format("%.2f", withdrawalLimit));
        System.out.println("Months Active: " + monthsActive);
        System.out.println("---------------------------");
    }
}
