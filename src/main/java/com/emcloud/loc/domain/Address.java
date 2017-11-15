package com.emcloud.loc.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * 地址表
 * @author Capejor
 */
@ApiModel(description = "地址表 @author Capejor")
@Entity
@Table(name = "address")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 地址名称
     */
    @Size(max = 200)
    @ApiModelProperty(value = "地址名称")
    @Column(name = "address_name", length = 200)
    private String addressName;

    /**
     * 地址代码
     */
    @NotNull
    @Size(max = 64)
    @ApiModelProperty(value = "地址代码", required = true)
    @Column(name = "address_code", length = 64, nullable = false)
    private String addressCode;

    /**
     * 经度
     */
    @NotNull
    @ApiModelProperty(value = "经度", required = true)
    @Column(name = "longitude", nullable = false)
    private Integer longitude;

    /**
     * 纬度
     */
    @NotNull
    @ApiModelProperty(value = "纬度", required = true)
    @Column(name = "latitude", nullable = false)
    private Integer latitude;

    /**
     * 地区代码
     */
    @Size(max = 64)
    @ApiModelProperty(value = "地区代码")
    @Column(name = "area_code", length = 64)
    private String areaCode;

    /**
     * 是否有效
     */
    @NotNull
    @ApiModelProperty(value = "是否有效", required = true)
    @Column(name = "jhi_enable", nullable = false)
    private Boolean enable;

    /**
     * 创建人员
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "创建人员", required = true)
    @Column(name = "created_by", length = 20, nullable = false)
    private String createdBy;

    /**
     * 创建时间
     */
    @NotNull
    @ApiModelProperty(value = "创建时间", required = true)
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    /**
     * 更新人员
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "更新人员", required = true)
    @Column(name = "updated_by", length = 20, nullable = false)
    private String updatedBy;

    /**
     * 更新时间
     */
    @NotNull
    @ApiModelProperty(value = "更新时间", required = true)
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    @OneToOne
    @JoinColumn(unique = true)
    private Area area;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressName() {
        return addressName;
    }

    public Address addressName(String addressName) {
        this.addressName = addressName;
        return this;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressCode() {
        return addressCode;
    }

    public Address addressCode(String addressCode) {
        this.addressCode = addressCode;
        return this;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public Address longitude(Integer longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public Address latitude(Integer latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public Address areaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Boolean isEnable() {
        return enable;
    }

    public Address enable(Boolean enable) {
        this.enable = enable;
        return this;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Address createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public Address createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Address updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public Address updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public Area getArea() {
        return area;
    }

    public Address area(Area area) {
        this.area = area;
        return this;
    }

    public void setArea(Area area) {
        this.area = area;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        if (address.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), address.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Address{" +
            "id=" + getId() +
            ", addressName='" + getAddressName() + "'" +
            ", addressCode='" + getAddressCode() + "'" +
            ", longitude='" + getLongitude() + "'" +
            ", latitude='" + getLatitude() + "'" +
            ", areaCode='" + getAreaCode() + "'" +
            ", enable='" + isEnable() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
