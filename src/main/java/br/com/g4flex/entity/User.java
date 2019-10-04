package br.com.g4flex.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "profile")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User {
	
	@Id
    @GeneratedValue
	private Long id;
	@Column( nullable=false, length=100)
	private String name;
	@Column(unique=true, length = 150, nullable=false)
	private String email;
	@Column(  nullable=false, length=100)
	private String password;
	
	public User() {}
	
	public User(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	
	

}
