package com.linksunshine.baseline.server.web.dto;

import java.io.Serializable;
import java.util.Date;

public class PermissionDTO implements Serializable {
    private String PermissionId;

    /**
     * 权限字符串
     */
    private String permission;

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
    private String deletionState;

    /**
     * 备注
     */
    private String description;

    /**
     * 获取主键
     *
     * @return p_permission_id - 主键
     */
    public String getPermissionId() {
        return PermissionId;
    }

    /**
     * 设置主键
     *
     * @param permissionId 主键
     */
    public void setPermissionId(String permissionId) {
        PermissionId = permissionId;
    }

    /**
     * 获取权限字符串
     *
     * @return permission - 权限字符串
     */
    public String getPermission() {
        return permission;
    }

    /**
     * 设置权限字符串
     *
     * @param permission 权限字符串
     */
    public void setPermission(String permission) {
        this.permission = permission;
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