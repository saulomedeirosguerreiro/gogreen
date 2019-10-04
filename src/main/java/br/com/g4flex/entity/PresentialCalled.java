package br.com.g4flex.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "presential_called")
@NamedQuery(name="PresentialCalled.findAll", query="SELECT pc FROM PresentialCalled pc")
public class PresentialCalled {
	
	@Id
    @GeneratedValue
	private Long id;
	@Column( nullable=false, length=100)
	private String callNumber;
	@Column(length = 100, nullable=false)
	private String clientName;
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date activityDate;
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "fk_user")
	private User user;
	
	public PresentialCalled() {}

	public PresentialCalled(String callNumber, String clientName, Date activityDate, User user) {
		super();
		this.callNumber = callNumber;
		this.clientName = clientName;
		this.activityDate = activityDate;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCallNumber() {
		return callNumber;
	}

	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
