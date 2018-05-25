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
		@NamedQuery(name="product.id", query="FROM Product p WHERE p.id=:product.id"),
		@NamedQuery(name="product.name", query="FROM Product p WHERE p.name=:product.name"),
		@NamedQuery(name="product.category", query="FROM Product p WHERE p.category=:product.category"),
		@NamedQuery(name="product.price", query="FROM Product p WHERE p.price>:lowerBoundary and p.price<:upperBoundary")
})
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String productName;
	private Category category;
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
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
}
