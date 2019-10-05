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
import javax.persistence.Transient;

import org.hibernate.annotations.NamedQuery;

import br.com.g4flex.utils.DateUtil;

@Entity
@Table(name = "extra_activity")
@NamedQuery(name="ExtraActivity.findAll", query="SELECT ea FROM ExtraActivity ea")
public class ExtraActivity {
	
	
	@Id
    @GeneratedValue
	private Long id;
	@Column( nullable=false, length=150)
	private String description;
	@Column(length = 100, nullable=false)
	private String clientName;
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date activityDate;
	@Temporal(TemporalType.TIME)
	private Date initialHour;
	@Temporal(TemporalType.TIME)
	private Date finalHour;
	@Column(length = 100)
	private String protocolNumber;
	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "fk_user")
	private User user;
	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "fk_activity")
	private Activity activity;
	
	public ExtraActivity() {}

	public ExtraActivity(String description, String clientName, Date activityDate, Date initialHour, Date finalHour,
			String protocolNumber, User user, Activity activity) {
		super();
		this.description = description;
		this.clientName = clientName;
		this.activityDate = activityDate;
		this.initialHour = initialHour;
		this.finalHour = finalHour;
		this.protocolNumber = protocolNumber;
		this.user = user;
		this.activity = activity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Transient
	public String getActivityDateFormatted() {
		return DateUtil.dateToString(getActivityDate(),DateUtil.PATTERN_SCREEN_DATE);
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
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

	public String getProtocolNumber() {
		return protocolNumber;
	}

	public void setProtocolNumber(String protocolNumber) {
		this.protocolNumber = protocolNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	


}
