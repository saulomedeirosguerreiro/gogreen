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
@Table(name = "control_on_duty")
@NamedQueries({
	@NamedQuery(name = "ControlOnDuty.findAll", query = "SELECT c FROM ControlOnDuty c ORDER BY c.date DESC"),
	@NamedQuery(name = "ControlOnDuty.countAll", query = "SELECT COUNT(c) FROM ControlOnDuty c "),
})
public class ControlOnDuty {

	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, length = 100)
	private String callNumber;
	@Column(length = 150)
	private String justification;
	@Column(length = 100, nullable = false)
	private String clientName;
	@Column(nullable = false, length = 20)
	private String dayOfWeek;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date date;
	@Temporal(TemporalType.TIME)
	private Date initialHour;
	@Temporal(TemporalType.TIME)
	private Date finalHour;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_user")
	private User user;

	public ControlOnDuty() {
	}
	
	

	public ControlOnDuty(String callNumber, String justification, String clientName, String dayOfWeek, Date date,
			Date initialHour, Date finalHour, User user) {
		super();
		this.callNumber = callNumber;
		this.clientName = clientName;
		setJustification(justification);
		setDayOfWeek(dayOfWeek);
		this.date = date;
		this.initialHour = initialHour;
		this.finalHour = finalHour;
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

	public Date getInitialHour() {
		return initialHour;
	}

	public void setInitialHour(Date initialHour) {
		this.initialHour = initialHour;
	}

	public Date getFinalHour() {
		return finalHour;
	}

	public void setFinalHour(Date finalHour) {
		this.finalHour = finalHour;
	}

	@Transient
	public String getWorkedHour() {
		int seconds;
		try {
			seconds = DateUtil.secondBetween(DateUtil.dateToString(getFinalHour()),
					DateUtil.dateToString(getInitialHour()));
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
				this.getCallNumber() == null ? "N/A" : this.getCallNumber(),
				this.getInitialHour() == null ? "N/A" : this.getInitialHour(),
				this.getFinalHour() == null ? "N/A" : this.getFinalHour(),
				this.getWorkedHour() == null ? "N/A" : this.getWorkedHour(), analista,
				this.getClientName() == null ? "N/A" : this.getClientName(),
				this.getJustification() == null ? "N/A" : this.getJustification() };
		return array;
	}

}
