package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CompanyDTO;
import entity.Company;
import facade.FCityInfo;
import facade.FCompany;
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
 * @author Jesper
 */
@Path("company")
public class CompanyResource {

    @Context
    private UriInfo context;
    Gson gson;
    EntityManagerFactory emf;
    FCompany fCompany;
    FPhone fPhone;
    FCityInfo fCity;

    /**
     * Creates a new instance of CompanyResource
     */
    public CompanyResource() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        emf = Persistence.createEntityManagerFactory("pu2", null);
        fCompany = new FCompany(emf);
        fPhone = new FPhone(emf);
        fCity = new FCityInfo(emf);
        
    }

    @GET
    @Path("/email={email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompanyByEmail(@PathParam("email") String email) {
        return Response.ok().entity(gson.toJson(fCompany.getCompanyByEmail(email))).build();
    }

    @GET
    @Path("/company/phone={number}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompanyByPhone(@PathParam("number") String number) {
        return Response.ok().entity(gson.toJson(fCompany.getCompanyByPhone(fPhone.getPhoneByNumber(number)))).build();
    }

    @GET
    @Path("/cvr={cvr}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompanyByCVR(@PathParam("cvr") int cvr) {
        return Response.ok().entity(gson.toJson(fCompany.getCompanyByCVR(cvr))).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCompanies() {
        return Response.ok().entity(gson.toJson(fCompany.getAllCompanies())).build();
    }
//
//    @GET
//    @Path("/zipcode={zipcode}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response getCompanyByZipCode(@PathParam("zipcode") String number) {
//        return Response.ok().entity(gson.toJson(fCompany.getAllCompaniessByCity(fCity.getCityByZip(number)))).build();
//    }
//
//    //Facade metode ikke implementeret, da vi mangler korrekte joins på vores entities 
//    @GET
//    @Path("/address={address}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response getCompanyByAddress(@PathParam("address") String address) {
//        return null;
//    }
//
//    @GET
//    @Path("/empcount={amount}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response getCompanyWithMoreEmployeesThan(@PathParam("amount") int amount) {
//        return Response.ok().entity(gson.toJson(fCompany.getAllCompaniesWithNumEmployeesOver(amount))).build();
//    }
//
//    @GET
//    @Path("/value={value}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response get(@PathParam("number") int value) {
//        return Response.ok().entity(gson.toJson(fCompany.getAllCompaniesWithMarketValueOver(value))).build();
//    }
//
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    public void createHobby(String content) {
//        Company c = gson.fromJson(content, Company.class);
//        fCompany.createCompany(c);
//    }
//
//    @PUT
//    @Produces(MediaType.APPLICATION_JSON)
//    public void updateCompany(String content) {
//        CompanyDTO c = gson.fromJson(content, CompanyDTO.class);
//        fCompany.updateCompany(c);
//    }
//
//    @DELETE
//    @Produces(MediaType.APPLICATION_JSON)
//    public void deleteCompany(int id) {
//        Company c = fCompany.getCompanyByID(id);
//        fCompany.deleteCompany(c.getId());
//    }

}
