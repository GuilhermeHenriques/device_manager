package com.exercise.device.database.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "device")
public class Device {
  public static final int NAME_SIZE = 50;
  public static final int BRAND_SIZE = 150;

  private Integer id;
  private String name;
  private String brand;
  private Date creation;

  /**
   * Constructor without any parameter
   */
  public Device() {
  }

  /**
   * @return the id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  public Integer getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * @return the name
   */
  @Column(name = "name", nullable = false, length = NAME_SIZE)
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the brand
   */
  @Column(name = "brand", nullable = false, length = BRAND_SIZE)
  public String getBrand() {
    return brand;
  }

  /**
   * @param brand the brand to set
   */
  public void setBrand(String brand) {
    this.brand = brand;
  }

  /**
   * @return the creation
   */
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "creation", nullable = false, length = 10)
  public Date getCreation() {
    return creation;
  }

  /**
   * @param creation the creation to set
   */
  public void setCreation(Date creation) {
    this.creation = creation;
  }

}
