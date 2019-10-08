package br.com.g4flex.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "activity")
@NamedQuery(name="Activity.findAll", query="SELECT a FROM Activity a")
public class Activity {
	
	@Id
    @GeneratedValue
	private Long id;
	@Column( nullable=false, length=100)
	private String name;
	@Column(nullable=false)
	private Double value;
	
	public Activity() {}

	public Activity(String name, Double value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public Activity(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
	public Object[] toArray() {
		Object [] array = {this.getName() == null ? "N/A" : this.getName(), this.getValue() == null ? "N/A" : this.getValue()};
		return array;
	}
	
    
}
