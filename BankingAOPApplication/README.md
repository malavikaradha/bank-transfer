# Banking Application using Spring AOP

A Java 17 + Spring Boot + Maven console application demonstrating deposits, withdrawals, balance management, validation, and transaction audit logging through Spring AOP.

## Requirements
- JDK 17 or later
- Maven 3.6+
- Internet access on the first build so Maven can download dependencies

## Run
From this project folder:

```bash
mvn spring-boot:run
```

## Run unit tests

```bash
mvn test
```

## Eclipse setup
1. Extract `BankingAOPApplication.zip`.
2. Open Eclipse and select **File → Import → Maven → Existing Maven Projects**.
3. Browse to the extracted `BankingAOPApplication` folder and select `pom.xml`.
4. Click **Finish**, wait for Maven dependencies to download, then run `BankingAopApplication.java` as **Java Application**.

## Requirements covered
- Initial balance is 1000.00.
- Deposit and withdrawal operations update the balance only after validation.
- Withdrawal above available funds is rejected with a meaningful message.
- Zero, negative, and non-finite amounts are rejected.
- `TransactionLoggingAspect` uses `@Before`, `@AfterReturning`, and `@AfterThrowing`.
- Banking rules remain in `BankAccountService`; logging is separated into the AOP aspect.
- JUnit tests cover initial balance, successful operations, invalid amounts, and insufficient funds.

The balance is held in memory and resets to 1000.00 whenever the application restarts. This is a learning/demo application, not a production banking system.
