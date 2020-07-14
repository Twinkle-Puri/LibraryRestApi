package com.dxc.libraryapi.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;



@Entity
@Table(name="books")
public class Library implements Serializable{
	
	@Id
	@Column(name = "bcode")
	private Integer bcode;
	
	@Column(name="title" , nullable = false)
	//@NotBlank(message="book title can not be balnk")
//	@Size(min=5,max=30,message="book title characters length should be between 5 to 30 chars")
	private String title;
	
	@Column(name="price" , nullable = false)
//	@Min(value = 100,message = "price is expected to be not  less than 100")
 //   @Max(value=2500, message ="price is expected not more than 25000")
	private double price;
	
	@Column(name="pdate" , nullable = true)
  //  @NotBlank(message = "date can not be blank")
    @PastOrPresent(message ="Published Date can not be future date")
    @DateTimeFormat(iso = ISO.DATE)
	private LocalDate publishedDate;
	
	@Column(name="subject",nullable = true)
	private String subject;
	
	public Library() {
		//left unimplemented
	}

	public Library(Integer bcode, String title, double price, LocalDate publishedDate,String subject) {
		super();
		this.bcode = bcode;
		this.title = title;
		this.price = price;
		this.publishedDate = publishedDate;
		this.subject = subject;
	}



	public Integer getBcode() {
		return bcode;
	}

	public void setBcode(Integer bcode) {
		this.bcode = bcode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	

}
