package co.edu.unbosque.ciudadanos4.resources;

import co.edu.unbosque.ciudadanos4.resources.filters.Logged;
import co.edu.unbosque.ciudadanos4.resources.pojos.OwnerPOJO;
import co.edu.unbosque.ciudadanos4.services.PService;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/propietarios/{username}")
public class PResource {
    @Logged
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello(@HeaderParam("role") String role) {

        // If role doesn't match
        if (!"owner".equals(role))
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("Role " + role + " cannot access to this method")
                    .build();

        return Response.ok()
                .entity("Hello " + role + " !!")
                .build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("username") String username,OwnerPOJO ownerPOJO) {
        ownerPOJO.setUsername(username);
        String reply = new PService().createOwner(ownerPOJO);

        return Response.
                status(Response.Status.CREATED)
                .entity(reply)
                .build();

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyOwner(OwnerPOJO ownerPojo) {
        String reply = new PService().editOwner(ownerPojo);
        return Response.status(Response.Status.OK).entity(reply).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOwner(@PathParam("username") String username) {
        String reply = new PService().deleteOwner(username);
        return Response.status(Response.Status.OK).entity(reply).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response totalOwners() {
        List<OwnerPOJO> ownerPOJO = new PService().listOwners();
        return Response.ok().entity(ownerPOJO).build();
    }

}