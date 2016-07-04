package com.basic.dto;

import com.basic.po.Person;
import com.basic.po.PersonContractor;

public class PersonContractorDto {

	private Person person;
	private PersonContractor contractor;

	public PersonContractorDto(){}
	
	public PersonContractorDto(Person person, PersonContractor contractor) {
		this.person = person;
		this.contractor = contractor;
	}

	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}

	public PersonContractor getContractor() {
		return contractor;
	}

	public void setContractor(PersonContractor contractor) {
		this.contractor = contractor;
	}

}