# projectNine
Bank System Project

```mermaid
classDiagram
    class HasMenu {
        <<interface>>
        +menu() String
        +start() void
    }

    class CheckingAccount {
        <<ser>>
        -balance double
        +CheckingAccount()
        +CheckingAccount(balance)
        +main() void
        +menu() String
        +start() String
        +getBalance() double
        +getBalanceString() String
        +setBalance(balance) void
        +checkBalance() void
        -getDouble() double
        +makeDeposit() void
        +makeWithdrawal() void
    }

    class SavingsAccount {
        -interestRate double
        +main(*)
        +calcInterest() void
        +setInterestRate() void
        +getInterestRate() void
    }

    class User {
        <<abstract>>
        -userName String
        -PIN String
        +login() boolean
        +login(userName, PIN) boolean
        +setUserName(userName) void
        +getUserName() String
        +setPIN(PIN) void
        +getPIN() String
        +getReport()* String
    }

    class Customer {
        <<ser>>
        -checking CheckingAccount
        -savings SavingsAccount
        +main() void
        +Customer()
        +start() void
        +menu() String
        +changePIN() void
        +getReport() String
    }

    class Admin {
        +Admin()
        +menu() String
        +getReport() String
    }

    class Bank {
        -admin Admin
        -customers ArrayList
        +Bank()
        +main() void
        +loadSampleCustomers() void
        +loadCustomers() void
        +saveCustomers() void
        +fullCustomerReport() void
        +addUser() void
        +applyInterest() void
        +loginAsCustomer() void
        +menu() void
        +start() void
        +startAdmin() void
    }

    SavingsAccount --|> CheckingAccount : Extends
    CheckingAccount ..|> HasMenu : Implements
    User ..|> HasMenu : Implements
    Customer --|> User : Extends
    Admin --|> User : Extends
    Bank "1" o-- "1" Admin
    Bank "1" o-- "*" Customer
```
