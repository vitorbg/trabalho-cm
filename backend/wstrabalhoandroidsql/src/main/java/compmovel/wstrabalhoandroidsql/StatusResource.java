/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compmovel.wstrabalhoandroidsql;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author vitor
 */
@Path("status")
public class StatusResource {

    @Path("retorna")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getStatus() {
        System.out.println("Chegou Requisição !!!!!");
        return "s";
    }
}
