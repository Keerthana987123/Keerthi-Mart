```sql
-- ==========================================
-- KEERTHANA MART - SAMPLE DATA
-- ==========================================

-- Insert users
INSERT INTO users (name, email, password, role)
VALUES
('Admin', 'admin@keerthanamart.com', 'admin123', 'ADMIN'),
('Seller One', 'seller@keerthanamart.com', 'seller123', 'SELLER'),
('Buyer One', 'buyer@keerthanamart.com', 'buyer123', 'BUYER');

-- Insert categories
INSERT INTO categories (category_name)
VALUES
('Electronics'),
('Fashion'),
('Books'),
('Home & Kitchen');

-- Insert products
INSERT INTO products
(seller_id, category_id, product_name, description, price, stock, image_url)
VALUES
(2, 1, 'Wireless Headphones',
 'Bluetooth wireless headphones', 1499.00, 20, 'headphones.jpg'),

(2, 1, 'Smart Watch',
 'Fitness and smart watch', 2499.00, 15, 'smartwatch.jpg'),

(2, 3, 'Java Programming Book',
 'Learn Java programming', 599.00, 30, 'java-book.jpg');