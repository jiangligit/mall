package com.rimi.mall.pojo;

import java.io.Serializable;

/**
 * user_role
 * @author 
 */
public class UserRole implements Serializable {
    /**
     * 用户ID
     */
    private Long uId;

    /**
     * 角色ID
     */
    private Long rId;

    private static final long serialVersionUID = 1L;

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }
}