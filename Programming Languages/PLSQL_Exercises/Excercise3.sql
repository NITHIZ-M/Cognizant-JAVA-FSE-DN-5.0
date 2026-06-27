SET SERVEROUTPUT ON;

---------------------------------------------------
-- Drop tables if they already exist
---------------------------------------------------

BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE bank_accounts';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -942 THEN
         RAISE;
      END IF;
END;
/

BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE bank_employees';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -942 THEN
         RAISE;
      END IF;
END;
/

---------------------------------------------------
-- Create Tables
---------------------------------------------------

CREATE TABLE bank_accounts
(
    account_id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    account_type VARCHAR2(20),
    balance NUMBER(12,2)
);

CREATE TABLE bank_employees
(
    employee_id NUMBER PRIMARY KEY,
    employee_name VARCHAR2(50),
    department VARCHAR2(30),
    salary NUMBER(10,2)
);

---------------------------------------------------
-- Insert Account Records
---------------------------------------------------

INSERT INTO bank_accounts VALUES (101,1,'Savings',5000);
INSERT INTO bank_accounts VALUES (102,2,'Savings',8000);
INSERT INTO bank_accounts VALUES (103,3,'Current',12000);

COMMIT;

SELECT * FROM bank_accounts;

---------------------------------------------------
-- Procedure 1 : Process Monthly Interest
---------------------------------------------------

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest
IS
BEGIN

    UPDATE bank_accounts
    SET balance = balance + (balance * 0.01)
    WHERE account_type='Savings';

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Monthly interest processed successfully.');

END;
/

EXEC ProcessMonthlyInterest;

SELECT * FROM bank_accounts;

---------------------------------------------------
-- Insert Employee Records
---------------------------------------------------

INSERT INTO bank_employees VALUES (1,'Nithish','HR',30000);
INSERT INTO bank_employees VALUES (2,'Kavin','IT',50000);
INSERT INTO bank_employees VALUES (3,'Hariharan','IT',45000);

COMMIT;

SELECT * FROM bank_employees;

---------------------------------------------------
-- Procedure 2 : Update Employee Bonus
---------------------------------------------------

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus
(
    p_department IN VARCHAR2,
    p_bonus_percent IN NUMBER
)
IS
BEGIN

    UPDATE bank_employees
    SET salary = salary + (salary * p_bonus_percent/100)
    WHERE department = p_department;

    COMMIT;

    DBMS_OUTPUT.PUT_LINE('Bonus updated successfully.');

END;
/

EXEC UpdateEmployeeBonus('IT',10);

SELECT * FROM bank_employees;

---------------------------------------------------
-- Procedure 3 : Transfer Funds
---------------------------------------------------

CREATE OR REPLACE PROCEDURE TransferFunds
(
    p_from_account NUMBER,
    p_to_account NUMBER,
    p_amount NUMBER
)
IS
    v_balance NUMBER;
BEGIN

    SELECT balance
    INTO v_balance
    FROM bank_accounts
    WHERE account_id = p_from_account;

    IF v_balance >= p_amount THEN

        UPDATE bank_accounts
        SET balance = balance - p_amount
        WHERE account_id = p_from_account;

        UPDATE bank_accounts
        SET balance = balance + p_amount
        WHERE account_id = p_to_account;

        COMMIT;

        DBMS_OUTPUT.PUT_LINE('Fund transfer successful.');

    ELSE

        DBMS_OUTPUT.PUT_LINE('Insufficient Balance.');

    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Account Not Found.');

    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE(SQLERRM);

END;
/

---------------------------------------------------
-- Execute Transfer
---------------------------------------------------

BEGIN
    TransferFunds(103,101,2000);
END;
/

---------------------------------------------------
-- Final Output
---------------------------------------------------

SELECT * FROM bank_accounts;

SELECT * FROM bank_employees;