package it.unibo.oop.lab.exception2;

/**
 * Models a generic bank account.
 * 
 */
public interface BankAccount {

    /**
     * @param usrID
     *            id of the user requesting this operation
     * @param amount
     *            amount to be withdrawn
     * 
     * @throws NotEnoughFundsException
     * 			  if the balance is not enough to withdraw
     */
    void withdraw(int usrID, double amount) throws NotEnoughFundsException;

    /**
     * 
     * @param usrID
     *            id of the user requesting this operation
     * @param amount
     *            amount to be credited
     */
    void deposit(int usrID, double amount);

    /**
     * 
     * @param usrID
     *            id of the user requesting this opera
     * @param amount
     *            amount to be deposited via ATM
     */
    void depositFromATM(int usrID, double amount);

    /**
     * 
     * @param usrID
     *            id of the user requesting this opera
     * @param amount
     *            amount to be withdrawn via AT
     * @throws NotEnoughFundsException 
     */
    void withdrawFromATM(int usrID, double amount) throws NotEnoughFundsException;

    /**
     * 
     * @return The current balance
     */
    double getBalance();

    /**
     * 
     * @return The total number of transaction for the account
     */
    int getTransactionCount();
}
