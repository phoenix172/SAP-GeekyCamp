package api;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import db.Connection;
import entities.Note;

@Path("/")
public class Endpoints {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response getAll() {
		EntityManager em = null;
		try {
			em = Connection.factory.createEntityManager();
			TypedQuery<Note> query = em.createQuery("SELECT n FROM Note n", Note.class);
			List<Note> notes = query.getResultList();
			Gson gson = new Gson();
			String json = gson.toJson(notes);
			return Response.status(200).entity(json).build();
		}
		finally {
			if(em != null)
				em.close();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response create(String body) {
		EntityManager em = null;
		try {
			em = Connection.factory.createEntityManager();
			Gson gson = new Gson();
			Note newNote = gson.fromJson(body, Note.class);
			em.getTransaction().begin();
			em.persist(newNote);
			em.getTransaction().commit();
			String jsonResponse = gson.toJson(newNote);
			return Response.status(200).entity(jsonResponse).build();
		}
		finally{
			if(em != null)
				em.close();
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
		EntityManager em = null;
		try {
			em = Connection.factory.createEntityManager();
			em.getTransaction().begin();
			Note note = em.find(Note.class, id);
			em.remove(note);
			em.getTransaction().commit();
			return Response.status(200).build();
		}
		finally{
			if(em != null)
				em.close();
		}
	}
}
