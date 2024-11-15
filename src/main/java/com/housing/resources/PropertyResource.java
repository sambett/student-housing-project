package com.housing.resources;

// Jersey imports
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// Project imports
import com.housing.model.Property;
import com.housing.dao.PropertyDAO;

// Jackson imports for JSON formatting
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

// Java utilities
import java.util.List;
import java.sql.SQLException;

@Path("/properties")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PropertyResource {
    private final PropertyDAO propertyDAO;
    
    // Initialize without Jackson if you're having issues
    public PropertyResource() {
        this.propertyDAO = new PropertyDAO();
    }

    @GET
    public Response getAllProperties() {
        try {
            List<Property> properties = propertyDAO.getAllProperties();
            return Response.ok(properties).build();
        } catch (SQLException e) {
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
                return Response.ok(property).build();
            }
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @POST
    public Response createProperty(Property property) {
        try {
            Property created = propertyDAO.createProperty(property);
            return Response.status(Response.Status.CREATED)
                          .entity(created)
                          .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}