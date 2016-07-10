package com.basic.dto;

import com.basic.po.Person;
import com.basic.po.Contractor;

public class ContractorDto {

	private Person person;
	private Contractor contractor;

	public ContractorDto(){}
	
	public ContractorDto(Person person, Contractor contractor) {
		this.person = person;
		this.contractor = contractor;
	}

	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}

	public Contractor getContractor() {
		return contractor;
	}

	public void setContractor(Contractor contractor) {
		this.contractor = contractor;
	}

}