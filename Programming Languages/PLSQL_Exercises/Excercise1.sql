SET SERVEROUTPUT ON;

-- Drop tables if they already exist
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE loans CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;
/

BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE customers CASCADE CONSTRAINTS';
EXCEPTION
    WHEN OTHERS THEN
        IF SQLCODE != -942 THEN
            RAISE;
        END IF;
END;
/

-- Create Customers table
CREATE TABLE customers (
    customer_id NUMBER PRIMARY KEY,
    customer_name VARCHAR2(50),
    age NUMBER,
    balance NUMBER,
    isvip VARCHAR2(5)
);

-- Create Loans table
CREATE TABLE loans (
    loan_id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    interest_rate NUMBER(5,2),
    due_date DATE,
    CONSTRAINT fk_customer
    FOREIGN KEY (customer_id)
    REFERENCES customers(customer_id)
);

-- Insert Customers
INSERT INTO customers VALUES (101,'Nithish',67,25000,'FALSE');
INSERT INTO customers VALUES (102,'Hemachandran',42,8500,'FALSE');
INSERT INTO customers VALUES (103,'Sanjaiy',72,30000,'FALSE');
INSERT INTO customers VALUES (104,'Karthik',58,18000,'FALSE');

COMMIT;

-- Insert Loans
INSERT INTO loans VALUES (1001,101,9.50,SYSDATE+15);
INSERT INTO loans VALUES (1002,102,8.75,SYSDATE+45);
INSERT INTO loans VALUES (1003,103,10.25,SYSDATE+12);
INSERT INTO loans VALUES (1004,104,7.80,SYSDATE+28);

COMMIT;

PROMPT ===== INITIAL CUSTOMERS =====
SELECT * FROM customers;

PROMPT ===== INITIAL LOANS =====
SELECT * FROM loans;

---------------------------------------------------
-- Exercise 1 : Apply 1% Interest Discount
---------------------------------------------------

BEGIN
    FOR c IN (
        SELECT c.customer_id,
               c.age,
               l.loan_id,
               l.interest_rate
        FROM customers c
        JOIN loans l
        ON c.customer_id = l.customer_id
    )
    LOOP
        IF c.age > 60 THEN

            UPDATE loans
            SET interest_rate = interest_rate - 1
            WHERE loan_id = c.loan_id;

            DBMS_OUTPUT.PUT_LINE(
                'Discount applied to Customer ID '
                || c.customer_id
            );

        END IF;
    END LOOP;

    COMMIT;
END;
/

---------------------------------------------------
-- Update Balance
---------------------------------------------------

UPDATE customers
SET balance = 50000
WHERE customer_id = 101;

COMMIT;

---------------------------------------------------
-- Exercise 2 : Mark VIP Customers
---------------------------------------------------

BEGIN

    FOR c IN (
        SELECT customer_id
        FROM customers
        WHERE balance > 10000
    )
    LOOP

        UPDATE customers
        SET isvip = 'TRUE'
        WHERE customer_id = c.customer_id;

        DBMS_OUTPUT.PUT_LINE(
            'Customer ID '
            || c.customer_id
            || ' is now a VIP customer'
        );

    END LOOP;

    COMMIT;

END;
/

---------------------------------------------------
-- Exercise 3 : Loan Due Reminder
---------------------------------------------------

BEGIN

    FOR c IN (
        SELECT loan_id,
               due_date
        FROM loans
        WHERE due_date BETWEEN SYSDATE AND SYSDATE + 30
    )
    LOOP

        DBMS_OUTPUT.PUT_LINE(
            'Loan ID '
            || c.loan_id
            || ' is due within the next 30 days.'
        );

    END LOOP;

END;
/

---------------------------------------------------
-- Final Output
---------------------------------------------------

PROMPT ===== FINAL CUSTOMERS =====
SELECT * FROM customers;

PROMPT ===== FINAL LOANS =====
SELECT * FROM loans;