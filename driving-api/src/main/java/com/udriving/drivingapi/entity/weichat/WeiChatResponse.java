package com.udriving.drivingapi.entity.weichat;

import lombok.ToString;

/**
 * @Coder shihaiyang
 * @Date 2019-01-01 16:19
 */
@ToString
public class WeiChatResponse {

    /**
     * errcode : 40029
     * errmsg : invalid code, hints: [ req_id: LoHoHa06898732 ]
     */

    private int errcode = -1;
    private String errmsg;
    /**
     * session_key : JZR8yQh13AFG9na4v3sM2A==
     * openid : osfk65DBbVQp4YE2H8wJyaCiZcns
     */

    private String session_key;
    private String openid;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
