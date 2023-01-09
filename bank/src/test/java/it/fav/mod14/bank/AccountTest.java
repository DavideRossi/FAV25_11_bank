package it.fav.mod14.bank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AccountTest {
	private static final double EPSILON = 1E-7;
	
	@Test
	void testSuccessfulMakeTransfer() {
		final String ACCOUNT = "XYZ123";
		final double BALANCE = 100.0;
		final double AMOUNT = 50.0;
		final double BALANCE_LEFT = 50.0;
		Account account = Account.builder()
				.setOwner("Superpippo")
				.setPhoneNumber("1234")
				.setEmail("super@pippo.com")
				.setBalance(BALANCE).build();

		assertTrue(account.makeTransfer(ACCOUNT, AMOUNT));
		assertEquals(BALANCE_LEFT, account.getBalance(), EPSILON);
	}

	@Test
	void testInsufficientFundsMakeTransfer() {
		final String ACCOUNT = "XYZ123";
		final double BALANCE = 100.0;
		final double AMOUNT = 200.0;
		Account account = Account.builder()
				.setOwner("Superpippo")
				.setPhoneNumber("1234")
				.setEmail("super@pippo.com")
				.setBalance(BALANCE).build();

		assertFalse(account.makeTransfer(ACCOUNT, AMOUNT));
		assertEquals(100.0, account.getBalance(), EPSILON);
	}
	
	@Test
	void testAroundBalanceMakeTransfer() {
		final String ACCOUNT = "XYZ123";
		final double AMOUNT = 100.0;
		final double DELTA = 0.1;
		Account account = Account.builder()
				.setOwner("Superpippo")
				.setPhoneNumber("1234")
				.setEmail("super@pippo.com")
				.setBalance(AMOUNT).build();

		assertFalse(account.makeTransfer(ACCOUNT, AMOUNT+DELTA));
		assertEquals(AMOUNT, account.getBalance(), EPSILON);

		assertTrue(account.makeTransfer(ACCOUNT, AMOUNT-DELTA));
		assertEquals(DELTA, account.getBalance(), EPSILON);

		account.setBalance(AMOUNT);
		assertTrue(account.makeTransfer(ACCOUNT, AMOUNT));
		assertEquals(0.0, account.getBalance(), EPSILON);
	}
}
