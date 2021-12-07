package co.edu.unbosque.ciudadanos4.resources;


import co.edu.unbosque.ciudadanos4.resources.filters.Logged;
import co.edu.unbosque.ciudadanos4.resources.pojos.PetPOJO;
import co.edu.unbosque.ciudadanos4.services.MascotaService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/propietarios/{username}/pets")
public class MascotaResource {
    /**
     * Method that creates a pet and save it in the db
     *
     * @param username owner's username
     * @param petPOJO  pet's pojo
     * @return a response status
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("username") String username, PetPOJO petPOJO) {

        //petPOJO.setOwner_username(username);
        String message = new MascotaService().savePet(petPOJO);

        return Response.
                status(Response.Status.CREATED)
                .entity(message)
                .build();
    }

    /**
     * Method that modify a pet. Compare the ids and update the data in the db
     *
     * @param petId   pet's id
     * @param petPojo pet's pojo
     * @return a response status
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{pet_id}")
    public Response modifyPet(@PathParam("pet_id") Integer petId, PetPOJO petPojo) {
        petPojo.setPet_id(petId);
        String message = new MascotaService().modifyPet(petPojo);
        return Response.status(Response.Status.OK).entity(message).build();
    }



    @Logged
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{pet_id}/visitsCasesAll")
    public Response listVisitsCasesAll(@PathParam("pet_id") Integer pet_id) {

        return Response.
                status(Response.Status.OK).
                //entity(new MascotaService().listVisitsAndCaseAll(pet_id)).
                        build();
    }

}