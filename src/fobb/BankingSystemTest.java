package fobb;

/**
 * TestSavingsAccount.java
 * Test program to demonstrate SavingsAccount functionality
 * Compatible with Ammar's Account base class
 */
public class TestSavingsAccount {
    public static void main(String[] args) {
        System.out.println("=== SAVINGS ACCOUNT TEST ===\n");
        
        // Create a savings account
        SavingsAccount savings = new SavingsAccount(
            "SAV123456",              // Account number (8-10 digits as per Ammar's validation)
            "John Doe",               // Account holder
            5000.00,                  // Initial balance
            0.03,                     // 3% annual interest rate
            1000.00,                  // Minimum balance required
            2000.00                   // Withdrawal limit per transaction
        );
        
        System.out.println("=== 1. INITIAL ACCOUNT INFO ===");
        savings.displaySavingsAccountInfo();
        
        System.out.println("\n=== 2. TESTING DEPOSIT ===");
        savings.deposit(500.00);
        System.out.println("Current balance: $" + String.format("%.2f", savings.getBalance()));
        
        System.out.println("\n=== 3. TESTING WITHDRAWAL RULES ===");
        
        // Test 1: Try to withdraw more than limit
        System.out.println("\nTest 1 - Withdraw $2500 (limit is $2000):");
        boolean result1 = savings.withdraw(2500.00);
        System.out.println("Withdrawal successful? " + result1);
        
        // Test 2: Try to withdraw below minimum (would trigger both rules)
        System.out.println("\nTest 2 - Withdraw $4500 (would go below $1000 minimum):");
        boolean result2 = savings.withdraw(4500.00);
        System.out.println("Withdrawal successful? " + result2);
        
        // Test 3: Valid withdrawal
        System.out.println("\nTest 3 - Withdraw $1000 (valid - within limits):");
        boolean result3 = savings.withdraw(1000.00);
        System.out.println("Withdrawal successful? " + result3);
        System.out.println("Balance after withdrawal: $" + 
                          String.format("%.2f", savings.getBalance()));
        
        System.out.println("\n=== 4. TESTING INTEREST CALCULATION ===");
        // Simulate 6 months passing
        System.out.println("\nSimulating 6 months of account activity...");
        for (int i = 0; i < 6; i++) {
            savings.incrementMonth();
        }
        
        // Calculate and display interest
        System.out.println("\nCalculating interest...");
        savings.calculateInterest();
        
        // Apply the interest to the account
        System.out.println("\nApplying interest to account...");
        savings.applyInterest();
        
        System.out.println("\n=== 5. TESTING INTEREST RATE UPDATE ===");
        savings.updateInterestRate(0.035); // Update to 3.5%
        
        System.out.println("\n=== 6. FINAL ACCOUNT INFO ===");
        savings.displaySavingsAccountInfo();
        
        System.out.println("\n=== 7. TESTING ACCOUNT INFO METHODS ===");
        System.out.println("\nUsing parent's displayAccountInfo():");
        savings.displayAccountInfo();
        
        System.out.println("\n=== TEST COMPLETE ===");
    }
}
