package com.prs.business;

import javax.persistence.*;

@Entity
public class Product {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="VendorID")
	private Vendor vendor;
	private String partNumber;
	private String name;
	private double price;
	private String unit;
	private String photopath;
	
	
	public Product() {
		super();
	}


	public Product(int id, Vendor vendor, String partNumber, String name, double price, String unit, String photopath) {
		super();
		this.id = id;
		this.vendor = vendor;
		this.partNumber = partNumber;
		this.name = name;
		this.price = price;
		this.unit = unit;
		this.photopath = photopath;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Vendor getVendor() {
		return vendor;
	}


	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}


	public String getPartNumber() {
		return partNumber;
	}


	public void setPartNbr(String partNumber) {
		this.partNumber = partNumber;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}


	public String getPhotopath() {
		return photopath;
	}


	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}


	@Override
	public String toString() {
		return "Product [id=" + id + ", vendor=" + vendor + ", partNumber=" + partNumber + ", name=" + name + ", price="
				+ price + ", unit=" + unit + ", photopath=" + photopath + "]";
	}
	
	
	
}
