package br.com.g4flex.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


import br.com.g4flex.utils.DateUtil;

@Entity
@Table(name = "point")
@NamedQueries({
	@NamedQuery(name = "Point.findAll", query = "SELECT p FROM Point p "),
	@NamedQuery(name = "Point.findByUserNameAndActivityDate", query = "SELECT p FROM Point p WHERE lower(p.user.name) LIKE :userName AND p.date BETWEEN :initialDate AND :finalDate "),
	@NamedQuery(name = "Point.findByUserName", query = "SELECT p FROM Point p  WHERE lower(p.user.name) LIKE :userName "),
	@NamedQuery(name = "Point.findByActivityDate", query = "SELECT p FROM Point p  WHERE p.date BETWEEN :initialDate AND :finalDate"),
	@NamedQuery(name = "Point.countAll", query = "SELECT COUNT(p) FROM Point p"),	
})
public class Point {

	@Id
	@GeneratedValue
	private Long id;
	@Column(length = 150)
	private String justification;
	@Column(length = 20)
	private String dayOfWeek;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date date;
	@Temporal(TemporalType.TIME)
	private Date checkInHour;
	@Temporal(TemporalType.TIME)
	private Date CheckOutHour;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_user")
	private User user;

	public Point() {
	}

	public Point(String justification, String dayOfWeek, User user) {
		super();
		this.date = new Date();
		this.checkInHour = new Date();
		setJustification(justification);
		setDayOfWeek(dayOfWeek);
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		if (justification != null && !justification.isEmpty())
			this.justification = justification;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		if (dayOfWeek != null && !dayOfWeek.isEmpty())
			this.dayOfWeek = dayOfWeek;
	}

	public Date getDate() {
		return date;
	}

	@Transient
	public String getDateFormatted() {
		return DateUtil.dateToString(getDate(), DateUtil.PATTERN_SCREEN_DATE);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getCheckInHour() {
		return checkInHour;
	}

	public void setCheckInHour(Date checkInHour) {
		this.checkInHour = checkInHour;
	}

	public Date getCheckOutHour() {
		return CheckOutHour;
	}

	public void setCheckOutHour(Date checkOutHour) {
		CheckOutHour = checkOutHour;
	}

	@Transient
	public String getWorkedHour() {
		int seconds;
		try {
			seconds = DateUtil.secondBetween(DateUtil.dateToString(getCheckOutHour()),
					DateUtil.dateToString(getCheckInHour()));
		} catch (Exception e) {
			return null;
		}
		return DateUtil.convert(seconds);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Object[] toArray() {
		String analista = this.getUser() == null ? "N/A" : this.getUser().getName();
		Object[] array = { this.getDate() == null ? "N/A" : this.getDateFormatted(),
				this.getDayOfWeek() == null ? "N/A" : this.getDayOfWeek(),
				this.getCheckInHour() == null ? "N/A" : this.getCheckInHour(),
				this.getCheckOutHour() == null ? "N/A" : this.getCheckOutHour(),
				this.getWorkedHour() == null ? "N/A" : this.getWorkedHour(), analista,
				this.getJustification() == null ? "N/A" : this.getJustification() };
		return array;
	}

}
