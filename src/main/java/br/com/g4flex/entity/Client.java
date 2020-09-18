package br.com.g4flex.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "client")
@NamedQueries({
	@NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c "),
	@NamedQuery(name = "Client.findByClient", query = "SELECT c FROM Client c WHERE lower(c.clientName) LIKE :clientName"),
	@NamedQuery(name = "Client.countAll", query = "SELECT COUNT(c) FROM Client c "),
})
public class Client {

	@Id
	@GeneratedValue
	private Long id;	
	@Column(length = 100, nullable = false)
	private String clientName;
	@Column(length = 45)
	private String port;
	@Column(length = 60)
	private String externalIp;
	@Column(length = 60)
	private String internalIp;
	@Column(length = 60)
	private String databaseIp;
	@Column(columnDefinition = "integer default 0")
	private int qtySubsidiary;
	@Column(length = 60)
	private String versionFlexuc;
	@Column(length = 60)
	private String versionLinux;
	 @Column(columnDefinition = "boolean default false")
	private boolean hasCallback;
	 @Column(columnDefinition = "boolean default false")
	private boolean hasInquiry;
	 @Column(columnDefinition = "boolean default false")
	private boolean hasFlexsms; 
	 @Column(columnDefinition = "boolean default false")
	private boolean hasVpn;
	 @Column(columnDefinition = "boolean default false")
	private boolean hasScriptDisk;
	 @Column(columnDefinition = "boolean default false")
	private boolean hasScriptCallcenter;
	 @Column(columnDefinition = "boolean default false")
	private boolean hasContractTerminated;
	

   
 
   

	public Client() {
	}
	
	
	public Client(Long id, String clientName, String port, String externalIp, String internalIp, String databaseIp,
			int qtySubsidiary, String versionFlexuc, String versionLinux, boolean hasCallback, boolean hasInquiry,
			boolean hasFlexsms, boolean hasVpn, boolean hasScriptDisk, boolean hasScriptCallcenter,
			boolean hasContractTerminated) {
		super();
		this.id = id;
		this.clientName = clientName;
		this.port = port;
		this.externalIp = externalIp;
		this.internalIp = internalIp;
		this.databaseIp = databaseIp;
		this.qtySubsidiary = qtySubsidiary;
		this.versionFlexuc = versionFlexuc;
		this.versionLinux = versionLinux;
		this.hasCallback = hasCallback;
		this.hasInquiry = hasInquiry;
		this.hasFlexsms = hasFlexsms;
		this.hasVpn = hasVpn;
		this.hasScriptDisk = hasScriptDisk;
		this.hasScriptCallcenter = hasScriptCallcenter;
		this.hasContractTerminated = hasContractTerminated;
	}



	public Client(String clientName, String port, String externalIp, String internalIp, String databaseIp,
			int qtySubsidiary, String versionFlexuc, String versionLinux, boolean hasCallback, boolean hasInquiry,
			boolean hasFlexsms, boolean hasVpn, boolean hasScriptDisk, boolean hasScriptCallcenter,
			boolean hasContractTerminated) {
		super();
		this.clientName = clientName;
		this.port = port;
		this.externalIp = externalIp;
		this.internalIp = internalIp;
		this.databaseIp = databaseIp;
		this.qtySubsidiary = qtySubsidiary;
		this.versionFlexuc = versionFlexuc;
		this.versionLinux = versionLinux;
		this.hasCallback = hasCallback;
		this.hasInquiry = hasInquiry;
		this.hasFlexsms = hasFlexsms;
		this.hasVpn = hasVpn;
		this.hasScriptDisk = hasScriptDisk;
		this.hasScriptCallcenter = hasScriptCallcenter;
		this.hasContractTerminated = hasContractTerminated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getExternalIp() {
		return externalIp;
	}

	public void setExternalIp(String externalIp) {
		this.externalIp = externalIp;
	}

	public String getInternalIp() {
		return internalIp;
	}

	public void setInternalIp(String internalIp) {
		this.internalIp = internalIp;
	}

	public String getDatabaseIp() {
		return databaseIp;
	}

	public void setDatabaseIp(String databaseIp) {
		this.databaseIp = databaseIp;
	}

	public int getQtySubsidiary() {
		return qtySubsidiary;
	}

	public void setQtySubsidiary(int qtySubsidiary) {
		this.qtySubsidiary = qtySubsidiary;
	}

	public String getVersionFlexuc() {
		return versionFlexuc;
	}

	public void setVersionFlexuc(String versionFlexuc) {
		this.versionFlexuc = versionFlexuc;
	}

	public String getVersionLinux() {
		return versionLinux;
	}

	public void setVersionLinux(String versionLinux) {
		this.versionLinux = versionLinux;
	}

	public boolean isHasCallback() {
		return hasCallback;
	}

	public void setHasCallback(boolean hasCallback) {
		this.hasCallback = hasCallback;
	}

	public boolean isHasInquiry() {
		return hasInquiry;
	}

	public void setHasInquiry(boolean hasInquiry) {
		this.hasInquiry = hasInquiry;
	}

	public boolean isHasFlexsms() {
		return hasFlexsms;
	}

	public void setHasFlexsms(boolean hasFlexsms) {
		this.hasFlexsms = hasFlexsms;
	}

	public boolean isHasVpn() {
		return hasVpn;
	}

	public void setHasVpn(boolean hasVpn) {
		this.hasVpn = hasVpn;
	}

	public boolean isHasScriptDisk() {
		return hasScriptDisk;
	}

	public void setHasScriptDisk(boolean hasScript_disk) {
		this.hasScriptDisk = hasScript_disk;
	}

	public boolean isHasScriptCallcenter() {
		return hasScriptCallcenter;
	}

	public void setHasScriptCallcenter(boolean hasScriptCallcenter) {
		this.hasScriptCallcenter = hasScriptCallcenter;
	}

	public boolean isHasContractTerminated() {
		return hasContractTerminated;
	}

	public void setHasContractTerminated(boolean hasContractTerminated) {
		this.hasContractTerminated = hasContractTerminated;
	}
	
	public Object[] toArray() {
		Object[] array = { this.getClientName() == null ? "N/A" : this.getClientName(),
				this.getPort() == null ? "N/A" : this.getPort(),
				this.getExternalIp() == null ? "N/A" : this.getExternalIp(),
				this.getInternalIp() == null ? "N/A" : this.getInternalIp(),
				this.getDatabaseIp() == null ? "N/A" : this.getDatabaseIp(),
				this.getQtySubsidiary(),		
				this.getVersionFlexuc() == null ? "N/A" : this.getVersionFlexuc(),
				this.getVersionLinux() == null ? "N/A" : this.getVersionLinux(),
				this.isHasCallback(),
				this.isHasInquiry(),
				this.isHasFlexsms(), 
				this.isHasVpn(),
				this.isHasScriptDisk(),
				this.isHasScriptCallcenter(),
			    this.isHasContractTerminated()
				 };
		return array;
	}


	
	

}
