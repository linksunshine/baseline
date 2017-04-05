package com.linksunshine.baseline.server.web.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "p_role_permission")
public class RolePermission {
    /**
     * 主键
     */
    @Id
    @Column(name = "p_role_permission_id")
    private String pRolePermissionId;

    /**
     * 外键角色id
     */
    @Column(name = "p_role_id")
    private String pRoleId;

    /**
     * 外键角色id
     */
    @Column(name = "p_permission_id")
    private String pPermissionId;

    /**
     * 创建者
     */
    private String createdby;

    /**
     * 创建时间
     */
    private Date createdon;

    /**
     * 修改者
     */
    private String modifiedby;

    /**
     * 最后修改时间
     */
    private Date modifiedon;

    /**
     * 删除状态
     */
    @Column(name = "deletion_state")
    private String deletionState;

    /**
     * 备注
     */
    private String description;

    /**
     * 获取主键
     *
     * @return p_role_permission_id - 主键
     */
    public String getpRolePermissionId() {
        return pRolePermissionId;
    }

    /**
     * 设置主键
     *
     * @param pRolePermissionId 主键
     */
    public void setpRolePermissionId(String pRolePermissionId) {
        this.pRolePermissionId = pRolePermissionId;
    }

    /**
     * 获取外键角色id
     *
     * @return p_role_id - 外键角色id
     */
    public String getpRoleId() {
        return pRoleId;
    }

    /**
     * 设置外键角色id
     *
     * @param pRoleId 外键角色id
     */
    public void setpRoleId(String pRoleId) {
        this.pRoleId = pRoleId;
    }

    /**
     * 获取外键角色id
     *
     * @return p_permission_id - 外键角色id
     */
    public String getpPermissionId() {
        return pPermissionId;
    }

    /**
     * 设置外键角色id
     *
     * @param pPermissionId 外键角色id
     */
    public void setpPermissionId(String pPermissionId) {
        this.pPermissionId = pPermissionId;
    }

    /**
     * 获取创建者
     *
     * @return createdby - 创建者
     */
    public String getCreatedby() {
        return createdby;
    }

    /**
     * 设置创建者
     *
     * @param createdby 创建者
     */
    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    /**
     * 获取创建时间
     *
     * @return createdon - 创建时间
     */
    public Date getCreatedon() {
        return createdon;
    }

    /**
     * 设置创建时间
     *
     * @param createdon 创建时间
     */
    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    /**
     * 获取修改者
     *
     * @return modifiedby - 修改者
     */
    public String getModifiedby() {
        return modifiedby;
    }

    /**
     * 设置修改者
     *
     * @param modifiedby 修改者
     */
    public void setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby;
    }

    /**
     * 获取最后修改时间
     *
     * @return modifiedon - 最后修改时间
     */
    public Date getModifiedon() {
        return modifiedon;
    }

    /**
     * 设置最后修改时间
     *
     * @param modifiedon 最后修改时间
     */
    public void setModifiedon(Date modifiedon) {
        this.modifiedon = modifiedon;
    }

    /**
     * 获取删除状态
     *
     * @return deletion_state - 删除状态
     */
    public String getDeletionState() {
        return deletionState;
    }

    /**
     * 设置删除状态
     *
     * @param deletionState 删除状态
     */
    public void setDeletionState(String deletionState) {
        this.deletionState = deletionState;
    }

    /**
     * 获取备注
     *
     * @return description - 备注
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置备注
     *
     * @param description 备注
     */
    public void setDescription(String description) {
        this.description = description;
    }
}