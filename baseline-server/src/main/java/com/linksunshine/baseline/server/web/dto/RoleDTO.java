package com.linksunshine.baseline.server.web.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RoleDTO implements Serializable {
    /**
     * 主键
     */
    private String pRoleId;

    /**
     * 角色名称
     */
    private String roleName;

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
     * @return p_role_id - 主键
     */
    public String getpRoleId() {
        return pRoleId;
    }

    /**
     * 设置主键
     *
     * @param pRoleId 主键
     */
    public void setpRoleId(String pRoleId) {
        this.pRoleId = pRoleId;
    }

    /**
     * 获取角色名称
     *
     * @return role_name - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
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


    private List<PermissionDTO> permissionDTOs;
    private List<String> rolePermissions;

    public List<String> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(List<String> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

    public List<PermissionDTO> getPermissionDTOs() {
        return permissionDTOs;
    }

    public void setPermissionDTOs(List<PermissionDTO> permissionDTOs) {
        this.permissionDTOs = permissionDTOs;
    }
}