package com.supermarket.controller;

import com.supermarket.dto.*;
import com.supermarket.security.JwtUtil;
import com.supermarket.service.OrderService;
import com.supermarket.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User Controller
 * Handles user-specific endpoints (requires USER role)
 */
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    private Long getUserIdFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtUtil.extractEmail(token);
        return userService.getUserByEmail(email).getId();
    }
    
    @PostMapping("/checkout")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<OrderDTO> checkout(@Valid @RequestBody CheckoutRequest checkoutRequest, 
                                             HttpServletRequest request) {
        Long userId = getUserIdFromRequest(request);
        OrderDTO order = orderService.createOrder(userId, checkoutRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
    
    @GetMapping("/orders")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<OrderDTO>> getUserOrders(HttpServletRequest request) {
        Long userId = getUserIdFromRequest(request);
        List<OrderDTO> orders = orderService.getUserOrders(userId);
        return ResponseEntity.ok(orders);
    }
    
    @GetMapping("/orders/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id, HttpServletRequest request) {
        Long userId = getUserIdFromRequest(request);
        OrderDTO order = orderService.getOrderById(id);
        
        // Verify that the order belongs to the user
        if (!order.getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        
        return ResponseEntity.ok(order);
    }
}
