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
 * 地区表
 * @author Capejor
 */
@ApiModel(description = "地区表 @author Capejor")
@Entity
@Table(name = "area")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    /**
     * 地区代码
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "地区代码", required = true)
    @Column(name = "area_code", length = 20, nullable = false)
    private String areaCode;


    /**
     * 地区名称
     */
    @Size(max = 100)
    @ApiModelProperty(value = "地区名称")
    @Column(name = "area_name", length = 100)
    private String areaName;



    /**
     * 邮政编码
     */
    @NotNull
    @Size(max = 20)
    @ApiModelProperty(value = "邮政编码", required = true)
    @Column(name = "zip_code", length = 20, nullable = false)
    private String zipCode;


    /**
     * 父地址编码
     */
    @NotNull
    @Size(max = 10)
    @ApiModelProperty(value = "父地址编码", required = true)
    @Column(name = "parent_id", length = 10, nullable = false)
    private String parentId;


    /**
     * 父地址名称
     */
    @Size(max = 100)
    @ApiModelProperty(value = "父地址名称")
    @Column(name = "parent_name", length = 100)
    private String parentName;


    /**
     * 深度
     */
    @NotNull
    @Size(max = 64)
    @ApiModelProperty(value = "深度", required = true)
    @Column(name = "depth", length = 64, nullable = false)
    private String depth;

    /**
     * 创建人员
     */
    @Size(max = 20)
    @ApiModelProperty(value = "创建人员", required = true)
    @Column(name = "created_by", length = 20, nullable = false)
    private String createdBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = true)
    @Column(name = "create_time", nullable = false)
    private Instant createTime;

    /**
     * 更新人员
     */
    @Size(max = 20)
    @ApiModelProperty(value = "更新人员", required = true)
    @Column(name = "updated_by", length = 20, nullable = false)
    private String updatedBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", required = true)
    @Column(name = "update_time", nullable = false)
    private Instant updateTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public Area areaCode(String areaCode) {
        this.areaCode = areaCode;
        return this;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public Area areaName(String areaName) {
        this.areaName = areaName;
        return this;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Area zipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getParentId() {
        return parentId;
    }

    public Area parentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public Area parentName(String parentName) {
        this.parentName = parentName;
        return this;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getDepth() {
        return depth;
    }

    public Area depth(String depth) {
        this.depth = depth;
        return this;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Area createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


    public Area createTime(Instant createTime) {
        this.createTime = createTime;
        return this;
    }
    public Instant getCreateTime() {

        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Area updatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public Area updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
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
        Area area = (Area) o;
        if (area.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), area.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Area{" +
            "id=" + getId() +
            ", areaCode='" + getAreaCode() + "'" +
            ", areaName='" + getAreaName() + "'" +
            ", zipCode='" + getZipCode() + "'" +
            ", parentId='" + getParentId() + "'" +
            ", parentName='" + getParentName() + "'" +
            ", depth='" + getDepth() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }

    public  Area(){}
}
