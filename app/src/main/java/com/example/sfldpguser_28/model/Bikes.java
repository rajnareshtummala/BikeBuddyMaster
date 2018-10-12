package com.example.sfldpguser_28.model;

import java.math.BigDecimal;

/**
 * Created by SFLDPGUSER-28 on 10/12/2018.
 */
//@XmlRootElement(name = "bikes")
public class Bikes {
        private String nameOfStation;
        private BigDecimal latitude;
        private BigDecimal longitude;
        private Integer typeResult;
      public Bikes() {
      }
    // getter

    public Bikes(String nameOfStation,BigDecimal latitude, BigDecimal longitude, Integer typeResult ) {
        super();
        this.nameOfStation = nameOfStation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.typeResult = typeResult;

    }

    /**
     * @return the id
     */
    public String nameOfStation() {
        return nameOfStation;
    }
    public BigDecimal latitude() {
        return latitude;
    }
    public BigDecimal longitude() {
        return longitude;
    }
    public Integer typeResult() {
        return typeResult;
    }
    /**
     * @param nameOfStation the empty_slots to set
     */
    public void setnameOfStation(String nameOfStation) {
        this.nameOfStation = nameOfStation;
    }
    public void setlatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
    public void settypeResult(Integer typeResult) {
        this.typeResult = typeResult;
    }
//    /**
//     * @return the name
//     */
//    public String getName() {
//        return name;
//    }
//
//    /**
//     * @param name the name to set
//     */
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    /**
//     * @return the author
//     */
//    public String getAuthor() {
//        return author;
//    }
//
//    /**
//     * @param author the author to set
//     */
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    /*
//     * (non-Javadoc)
//     *
//     * @see java.lang.Object#toString()
//     */
    @Override
    public String toString() {
        return "Bike [nameOfStation=" + nameOfStation + "]";
    }

}


