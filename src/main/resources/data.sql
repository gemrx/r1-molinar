INSERT INTO accounts (client_name, account_number, balance, status, created_at, updated_at)
VALUES
    ('John Doe', '9001', 1000.50, 'active', NOW(), NOW()),
    ('Jane Smith', '9002', 2500.75, 'active', NOW(), NOW()),
    ('Bob Johnson', '9003', 500.00, 'inactive', NOW(), NOW()),
    ('Alice Davis', '9004', 1234.56, 'active', NOW(), NOW());
