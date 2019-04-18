package com.rimi.mall.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * role
 * @author jiangli
 */
public class Role implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    private Set<Permission> permissions = new HashSet<>();

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }



    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null) {
            return false;
        }
        if (name != null ? !name.equals(role.name) : role.name != null) {
            return false;
        }
        return permissions != null ? permissions.equals(role.permissions) : role.permissions == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (permissions != null ? permissions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}