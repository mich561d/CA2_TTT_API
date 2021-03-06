package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.AddressDTO;
import dto.HobbyDTO;
import dto.PersonDTO;
import entity.Person;
import facade.FCityInfo;
import facade.FHobby;
import facade.FPerson;
import facade.FPhone;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Jesper, Michael
 */
@Path("Person")
public class PersonResource {

    @Context
    private UriInfo context;

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu2", null);
    FPerson fPerson = new FPerson(emf);
    FPhone fPhone = new FPhone(emf);
    FHobby fHobby = new FHobby(emf);
    FCityInfo fCity = new FCityInfo(emf);

    @GET
    @Path("/Email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonByEmail(@PathParam("email") String email) {
        return Response.ok().entity(gson.toJson(fPerson.getPersonByEmail(email))).build();
    }

    @GET
    @Path("/Phone/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonByPhone(@PathParam("number") String number) {
        return Response.ok().entity(gson.toJson(fPerson.getPersonByPhone(fPhone.getPhoneByNumber(number)))).build();

    }

    @GET
    @Path("/All")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons(@PathParam("number") String number) {
        return Response.ok().entity(gson.toJson(fPerson.getAllPersons())).build();
    }

    @GET
    @Path("Hobby/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonsByHobby(@PathParam("name") String name) {
        return Response.ok().entity(gson.toJson(fPerson.getAllPersonsByHobby(new HobbyDTO(0, name, name)))).build();
    }

    @GET
    @Path("/City/{zipcode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersonsByCity(@PathParam("zipcode") String zipcode) {
        return Response.ok().entity(gson.toJson(fPerson.getAllPersonsByCity(fCity.getCityByZip(zipcode)))).build();
    }

    @GET
    @Path("/Address/{address}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersonsByAddress(@PathParam("address") String address) {
        return Response.ok().entity(gson.toJson(fPerson.getAllPersonsByAddress(new AddressDTO(0, address, "")))).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void createPerson(String content) {
        Person p = gson.fromJson(content, Person.class);
        fPerson.createPerson(p);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePerson(String content) {
        PersonDTO p = gson.fromJson(content, PersonDTO.class);
        fPerson.updatePerson(p);
    }

    @DELETE
    @Path("/Delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deletePerson(@PathParam("id") int id) {
        Person p = fPerson.getPersonByIDRaw(id);
        fPerson.deletePersonById(p.getId());
    }
}
