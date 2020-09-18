package br.com.g4flex.dto;

import java.util.Date;

public class FiltersDTO {

		private String userName;
		
		private Date initialDate;
		
		private Date finalDate;
		
		private String clientName;
		
		public FiltersDTO(String userName, Date initialDate, Date finalDate) {
			super();
			this.userName = userName;
			this.initialDate = initialDate;
			this.finalDate = finalDate;
		}
		
		public FiltersDTO(String clientName) {
			super();
			this.clientName = clientName;
		}

		public String getUserName() {
			return userName;
		}

		public Date getInitialDate() {
			return initialDate;
		}

	

		public Date getFinalDate() {
			return finalDate;
		}

		public String getClientName() {
			return clientName;
		}
		
		


		
}
