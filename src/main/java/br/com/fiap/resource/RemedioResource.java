package br.com.fiap.resource;

import br.com.fiap.bo.RemedioBO;
import br.com.fiap.to.RemedioTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/megafarma")
public class RemedioResource {
    private RemedioBO remedioBO = new RemedioBO();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        ArrayList<RemedioTO> resultado = remedioBO.findAll();
        Response.ResponseBuilder response = null;
        if (resultado != null){
            response = Response.ok(); // 200 (Ok)
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("codigo") Long codigo){
        RemedioTO resultado = remedioBO.findByCodigo(codigo);
        Response.ResponseBuilder response = null;
        if (resultado != null){
            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();

    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response save(@Valid RemedioTO remedio){
//        RemedioTO resultado = remedioBO.save(remedio);
//        Response.ResponseBuilder response = null;
//        if (resultado != null){
//            response = Response.created(null); // 201 - CREATED
//        } else {
//            response = Response.status(400); // 400 - BAD REQUEST
//        }
//        response.entity(resultado);
//        return response.build();
//    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid RemedioTO remedio){
        RemedioTO resultado = remedioBO.save(remedio);
        Response.ResponseBuilder response = null;
        if (resultado != null){
            return Response.created(null).entity(resultado).build(); // 201 - CREATED
        }
        return Response.status(400).entity(resultado).build(); // 400 - BAD REQUEST
    }


    @DELETE
    @Path("/{codigo}")
    public Response delete(@PathParam("codigo") Long codigo) {
        Response.ResponseBuilder response = null;
        if(remedioBO.delete(codigo)){
            response = Response.status(204); // 204 NO CONTENT
        } else{
            response = Response.status(404); // 404 NOT FOUND
        }
        return response.build();
    }

    @PUT
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("codigo") Long codigo, RemedioTO remedio){
        RemedioTO resultado = remedioBO.edit(codigo, remedio);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok();
        } else{
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }
}
