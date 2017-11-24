package com.emcloud.loc.domain;

public class AlArea {
    private Long id;
    private String name;

    public AlArea(Long id, String name, String parentid, String parentname, String areacode, String zipcode, String depth) {
        this.id = id;
        this.name = name;
        this.parentid = parentid;
        this.parentname = parentname;
        this.areacode = areacode;
        this.zipcode = zipcode;
        this.depth = depth;
    }

    public AlArea() {
    }

    private String parentid;
    private String parentname;
    private String areacode;
    private String zipcode;
    private String depth;


    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String  getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
