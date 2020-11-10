package it.unibo.oop.lab.exception2;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * JUnit test to test
 * {@link StrictBankAccount}.
 * 
 */
public final class TestStrictBankAccount {
	
	private static final int INITIAL_AMOUNT = 10_000;

    /**
     * Used to test Exceptions on {@link StrictBankAccount}.
     */
    @Test
    public void testBankOperations() {
        /*
         * 1) Creare due StrictBankAccountImpl assegnati a due AccountHolder a
         * scelta, con ammontare iniziale pari a 10000 e nMaxATMTransactions=10
         * 
         * 2) Effetture un numero di operazioni a piacere per verificare che le
         * eccezioni create vengano lanciate soltanto quando opportuno, cioè in
         * presenza di un id utente errato, oppure al superamento del numero di
         * operazioni ATM gratuite.
         */
    	
    	final AccountHolder usr1 = new AccountHolder("Mario", "Rossi", 1);
        final AccountHolder usr2 = new AccountHolder("Luigi", "Bianchi", 2);
        final StrictBankAccount b1 = new StrictBankAccount(usr1.getUserID(), INITIAL_AMOUNT, 10);
        final StrictBankAccount b2 = new StrictBankAccount(usr2.getUserID(), INITIAL_AMOUNT, 10);
    	
    	try {
    		b1.deposit(4, 900);
    		fail("I must not get this far");
    	} catch (WrongAccountHolderException e) {
    		assertNotNull(e);
    	}
    	
    	for (int i = 0; i < 10; i++) {
    		try {
    			b2.depositFromATM(usr2.getUserID(), 1);
    		} catch (TransactionsOverQuotaException | WrongAccountHolderException e) {
    			fail("Max transactions not exceeded yet!");
    		}
    	}
    	try {
    		b2.depositFromATM(usr2.getUserID(), 1);
    		fail("Should have raised exception");
    	} catch (TransactionsOverQuotaException e) {
    		assertNotNull(e);
    	} 
    	try {
    		b1.withdraw(usr1.getUserID(), 50000);
    		fail("Should not be able to do it");
    	} catch (WrongAccountHolderException e) {
    		fail();
    	} catch (NotEnoughFundsException e) {
    		assertNotNull(e);
    	}
    		
    }
   	
 }

