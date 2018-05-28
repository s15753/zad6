package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries({
		@NamedQuery(name="product.all", query="SELECT p FROM Product p"),
		@NamedQuery(name="product.id", query="SELECT p FROM Product p WHERE p.id=:productId"),
		@NamedQuery(name="product.name", query="SELECT p FROM Product p WHERE p.productName=:productName"),
		@NamedQuery(name="product.category", query="SELECT p FROM Product p WHERE p.category=:productCategory"),
		@NamedQuery(name="product.price", query="SELECT p FROM Product p WHERE p.price>:lowerBoundary and p.price<:upperBoundary")
})
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String productName;
	private String category;
	private Double price;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return productName;
	}
	public void setName(String productName) {
		this.productName = productName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
}
