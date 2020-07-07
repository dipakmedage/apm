package com.nclinic.apm.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
@Table(name=Payment.TABLE_NAME_PAYMENT)
public class Payment {
	
	
	public static final String TABLE_NAME_PAYMENT="payment_details";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String patient_name;
	
	private int payment;
	
	private int remaining_payment;
	
	private int advance_payment;
	
	private String transaction_id;
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date created_at;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date modified_at;
	
	private String created_by;
	
	private String modified_by;

}
