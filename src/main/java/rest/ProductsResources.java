package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Product;
import domain.Category;

@Path("/product")
@Stateless
public class ProductsResources {
	
	@PersistenceContext
	EntityManager em;
	
	// Add record
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Add(Product product) {
		em.persist(product);
		return Response.ok(product.getId()).build();
	}
	
	// Get all products
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAll(){
		return em.createNamedQuery("product.all", Product.class).getResultList();
	}
	
	// Get record by id
	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") int id){
		Product result = em.createNamedQuery("product.id", Product.class)
					.setParameter("productId", id).getSingleResult();
		if(result==null){
			return Response.status(404).build();
		}
		return Response.ok(result).build();
	}
	
	// Get record by name
	@GET
	@Path("/name/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByName(@PathParam("name") int name){
		Product result = em.createNamedQuery("product.name", Product.class)
				.setParameter("productName", name).getSingleResult();
		if(result==null){
			return Response.status(404).build();
		}
		return Response.ok(result).build();
	}
	
	// Get record by category
	@GET
	@Path("/category/{category}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getByCategory(@PathParam("category") String category){
		return em.createNamedQuery("product.category", Product.class)
					.setParameter("productCategory", category).getResultList();
	}
	
	// Get record by price
	@GET
	@Path("/price/{lowerBoundary}-{upperBoundary}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getByPrice(@PathParam("lowerBoundary") Double lowerBoundry,
								  @PathParam("upperBoundary") Double upperBoundry){
		return em.createNamedQuery("product.price", Product.class)
					.setParameter("lowerBoundry", lowerBoundry)
					.setParameter("upperBoundry", upperBoundry).getResultList();
	}
	
	// Update record by id
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Product p){
		Product result = em.createNamedQuery("product.id", Product.class)
				.setParameter("productId", id)
				.getSingleResult();
		if(result==null){
			return Response.status(404).build();
		}
		result.setName(p.getName());
		result.setCategory(p.getCategory());
		result.setPrice(p.getPrice());
		em.persist(result);
		return Response.ok().build();
	}
	
	// Delete record by id
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") int id){
		Product result = em.createNamedQuery("product.id", Product.class)
				.setParameter("productId", id). getSingleResult();
		if(result==null){
			return Response.status(404).build();
		}
		em.remove(result);
		return Response.ok().build();
	}
		
}
