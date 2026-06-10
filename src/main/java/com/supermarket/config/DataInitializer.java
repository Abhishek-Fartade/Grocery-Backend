package com.supermarket.config;

import com.supermarket.entity.Category;
import com.supermarket.entity.Role;
import com.supermarket.entity.User;
import com.supermarket.repository.CategoryRepository;
import com.supermarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Data Initializer
 * Automatically creates an admin user and default categories on application startup if they don't exist
 */
@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    // Default admin credentials
    private static final String DEFAULT_ADMIN_EMAIL = "admin@supermarket.com";
    private static final String DEFAULT_ADMIN_PASSWORD = "admin123";
    private static final String DEFAULT_ADMIN_NAME = "Admin User";
    
    // Default categories
    private static final List<String> DEFAULT_CATEGORIES = Arrays.asList(
        "Grocery",
        "Fruits",
        "Vegetables",
        "Dairy",
        "Snacks"
    );
    
    @Override
    public void run(String... args) throws Exception {
        // Initialize admin user
        initializeAdminUser();
        
        // Initialize default categories
        initializeCategories();
    }
    
    private void initializeAdminUser() {
        // Check if admin user already exists
        if (!userRepository.existsByEmail(DEFAULT_ADMIN_EMAIL)) {
            // Create default admin user
            User admin = new User();
            admin.setName(DEFAULT_ADMIN_NAME);
            admin.setEmail(DEFAULT_ADMIN_EMAIL);
            admin.setPassword(passwordEncoder.encode(DEFAULT_ADMIN_PASSWORD));
            admin.setRole(Role.ADMIN);
            
            userRepository.save(admin);
            
            System.out.println("========================================");
            System.out.println("ADMIN USER CREATED SUCCESSFULLY!");
            System.out.println("========================================");
            System.out.println("Email: " + DEFAULT_ADMIN_EMAIL);
            System.out.println("Password: " + DEFAULT_ADMIN_PASSWORD);
            System.out.println("Role: ADMIN");
            System.out.println("========================================");
            System.out.println("You can now login to the admin panel with these credentials.");
            System.out.println("========================================");
        } else {
            System.out.println("Admin user already exists. Skipping creation.");
        }
    }
    
    private void initializeCategories() {
        int createdCount = 0;
        
        for (String categoryName : DEFAULT_CATEGORIES) {
            if (!categoryRepository.existsByName(categoryName)) {
                Category category = new Category();
                category.setName(categoryName);
                category.setDescription("Category for " + categoryName);
                categoryRepository.save(category);
                createdCount++;
            }
        }
        
        if (createdCount > 0) {
            System.out.println("Created " + createdCount + " default category/categories.");
        }
    }
}
