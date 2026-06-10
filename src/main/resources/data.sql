-- Optional SQL script for manual database initialization
-- This is NOT required as DataInitializer.java handles admin user creation automatically
-- Use this only if you prefer SQL-based initialization

-- Note: Passwords need to be BCrypt hashed. The DataInitializer.java handles this automatically.
-- If you want to use SQL, you'll need to hash the password first.

-- Example (requires BCrypt hash):
-- INSERT INTO users (name, email, password, role) 
-- SELECT 'Admin User', 'admin@supermarket.com', '$2a$10$YourHashedPassword', 'ADMIN'
-- WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'admin@supermarket.com');

-- The DataInitializer.java is the recommended approach as it handles password hashing automatically.
