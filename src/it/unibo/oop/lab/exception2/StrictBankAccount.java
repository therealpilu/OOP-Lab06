package it.unibo.oop.lab.exception2;

/**
 * Class modeling a BankAccount with strict policies: getting money is allowed
 * only with enough founds, and there are also a limited number of free ATM
 * transaction (this number is provided as a input in the constructor).
 * 
 */
public class StrictBankAccount implements BankAccount {

    private final int usrID;
    private double balance;
    private int totalTransactionCount;
    private int transactionsOnAtm;
    private final int maximumAllowedATMTransactions;
    private static final double ATM_TRANSACTION_FEE = 1;
    private static final double MANAGEMENT_FEE = 5;
    private static final double TRANSACTION_FEE = 0.1;

    /**
     * 
     * @param usrID
     *            user id
     * @param balance
     *            initial balance
     * @param maximumAllowedAtmTransactions
     *            max no of ATM transactions allowed
     */
    public StrictBankAccount(final int usrID, final double balance, final int maximumAllowedAtmTransactions) {
        this.usrID = usrID;
        this.balance = balance;
        this.maximumAllowedATMTransactions = maximumAllowedAtmTransactions;
        this.transactionsOnAtm = 0;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void deposit(final int usrID, final double amount) {
        if (checkUser(usrID)) {
            this.balance += amount;
            increaseTransactionsCount();
        } else {
        	throw new WrongAccountHolderException();
        }
    }

    /**
     * 
     * {@inheritDoc}
     * @throws NotEnoughFundsException 
     */
    public void withdraw(final int usrID, final double amount) throws NotEnoughFundsException {
        if (checkUser(usrID)) {
        	if (isWithdrawAllowed(amount)) {
        		this.balance -= amount;
        		increaseTransactionsCount();
        	} else {
        		throw new NotEnoughFundsException();
        	}
        } else {
        	throw new WrongAccountHolderException();
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void depositFromATM(final int usrID, final double amount) {
        if (isAtmTransactionAvailable()) {
        	this.deposit(usrID, amount - StrictBankAccount.ATM_TRANSACTION_FEE);
            transactionsOnAtm++;
        } else {
        	throw new TransactionsOverQuotaException();
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void withdrawFromATM(final int usrID, final double amount) throws NotEnoughFundsException {
        if (isAtmTransactionAvailable()) {
        	this.withdraw(usrID, amount + StrictBankAccount.ATM_TRANSACTION_FEE);
        	transactionsOnAtm++;
        } else {
        	throw new TransactionsOverQuotaException();
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public int getTransactionCount() {
        return totalTransactionCount;
    }

    /**
     * 
     * @param usrID
     *            id of the user related to these fees
     */
    public void computeManagementFees(final int usrID) {
        final double feeAmount = MANAGEMENT_FEE + (totalTransactionCount * StrictBankAccount.TRANSACTION_FEE);
        if (checkUser(usrID) && isWithdrawAllowed(feeAmount)) {
            balance -= MANAGEMENT_FEE + totalTransactionCount * StrictBankAccount.TRANSACTION_FEE;
            totalTransactionCount = 0;
        }
    }

    private boolean checkUser(final int id) {
        return this.usrID == id;
    }

    private boolean isWithdrawAllowed(final double amount) {
        return balance > amount;
    }

    private void increaseTransactionsCount() {
        totalTransactionCount++;
    }
    
    private boolean isAtmTransactionAvailable() {
    	return transactionsOnAtm < maximumAllowedATMTransactions;
    }
}
