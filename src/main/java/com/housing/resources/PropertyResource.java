package com.housing.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.housing.model.Property;
import com.housing.dao.PropertyDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.List;
import java.sql.SQLException;

@Path("/properties")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PropertyResource {
    private final PropertyDAO propertyDAO;
    private final ObjectMapper objectMapper;
    
    public PropertyResource() {
        this.propertyDAO = new PropertyDAO();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @GET
    public Response getAllProperties() {
        try {
            List<Property> properties = propertyDAO.getAllProperties();
            String jsonResponse = objectMapper.writeValueAsString(properties);
            return Response.ok(jsonResponse, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getProperty(@PathParam("id") Long id) {
        try {
            Property property = propertyDAO.getProperty(id);
            if (property != null) {
                String jsonResponse = objectMapper.writeValueAsString(property);
                return Response.ok(jsonResponse, MediaType.APPLICATION_JSON).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @POST
    public Response createProperty(Property property) {
        try {
            Property created = propertyDAO.createProperty(property);
            String jsonResponse = objectMapper.writeValueAsString(created);
            return Response.status(Response.Status.CREATED)
                          .entity(jsonResponse)
                          .type(MediaType.APPLICATION_JSON)
                          .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}