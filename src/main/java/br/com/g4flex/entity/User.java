package br.com.g4flex.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "users")
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

}
