package com.housing.dao;

import com.housing.model.Property;
import com.housing.config.DatabaseConfig;
import java.sql.*;
import java.util.*;

public class PropertyDAO {
    
    public List<Property> getAllProperties() throws SQLException {
        List<Property> properties = new ArrayList<>();
        String sql = "SELECT * FROM properties";
        
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Property property = new Property();
                property.setId(rs.getInt("id"));
                property.setTitle(rs.getString("title"));
                property.setDescription(rs.getString("description"));
                property.setPrice(rs.getDouble("price"));
                property.setRooms(rs.getInt("rooms"));
                property.setAddress(rs.getString("address"));
                property.setNeighborhood(rs.getString("neighborhood"));
                property.setContactPhone(rs.getString("contact_phone"));
                properties.add(property);
            }
        }
        return properties;
    }

    public Property getProperty(Long id) throws SQLException {
        String sql = "SELECT * FROM properties WHERE id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Property property = new Property();
                property.setId(rs.getInt("id"));
                property.setTitle(rs.getString("title"));
                property.setDescription(rs.getString("description"));
                property.setPrice(rs.getDouble("price"));
                property.setRooms(rs.getInt("rooms"));
                property.setAddress(rs.getString("address"));
                property.setNeighborhood(rs.getString("neighborhood"));
                property.setContactPhone(rs.getString("contact_phone"));
                return property;
            }
        }
        return null;
    }

    public Property createProperty(Property property) throws SQLException {
        String sql = "INSERT INTO properties (title, description, price, rooms, address, neighborhood, contact_phone) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, property.getTitle());
            stmt.setString(2, property.getDescription());
            stmt.setDouble(3, property.getPrice());
            stmt.setInt(4, property.getRooms());
            stmt.setString(5, property.getAddress());
            stmt.setString(6, property.getNeighborhood());
            stmt.setString(7, property.getContactPhone());
            
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                property.setId(rs.getInt(1));
            }
            return property;
        }
    }
}