package com.hibegin.http.server.web.cookie;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cookie {

    public static String JSESSIONID = "JSESSIONID";
    private String name;
    private String value;
    private String domain;
    private String path = "/";
    private Date expireDate;
    private boolean create;
    private boolean httpOnly;

    public Cookie(boolean create) {
        this.setCreate(create);
    }

    public Cookie() {
        //
    }

    public static Cookie[] saxToCookie(String cookieStr) {
        String[] kvArr = cookieStr.split(";");
        List<Cookie> cookieList = new ArrayList<>();
        for (String aKvArr : kvArr) {
            String kvStr = aKvArr.trim();
            String[] kv = kvStr.split("=");
            if (kv.length > 0) {
                Cookie cookie = new Cookie();
                cookie.setName(kv[0]);
                if (kv.length > 1) {
                    cookie.setValue(kvStr.substring(kvStr.indexOf("="), kvStr.length()));
                } else {
                    cookie.setValue("");
                }
                cookieList.add(cookie);
            }
        }
        return cookieList.toArray(new Cookie[cookieList.size()]);
    }

    public static String getJSessionId(String cookieStr) {
        String[] kvArr = cookieStr.split(";");
        for (String aKvArr : kvArr) {
            String kvStr = aKvArr.trim();
            String[] kv = kvStr.split("=");
            if (JSESSIONID.equals(kv[0]) && kv.length > 1) {
                return kvStr.substring(kvStr.indexOf("="), kvStr.length());
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        String cookieStr;
        if (expireDate == null) {
            cookieStr = name + "=" + value + ";" + "Path=" + path;
        } else {
            cookieStr = name + "=" + value + ";" + "Path=" + path + ";Expires=" + expireDate;
        }
        if (httpOnly) {
            cookieStr += ";HttpOnly";
        }
        return cookieStr;
    }

    public boolean isCreate() {
        return create;
    }

    public void setCreate(boolean create) {
        this.create = create;
    }

    public boolean isHttpOnly() {
        return httpOnly;
    }

    public void setHttpOnly(boolean httpOnly) {
        this.httpOnly = httpOnly;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}
