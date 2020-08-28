package com.kotlin.lib_base.model.user;


import com.kotlin.lib_base.surface.audio.BaseModel;

import java.util.List;

public class User extends BaseModel {


    /**
     * loginType : 1
     * code : 200
     * account : {"id":89773352,"userName":"1_13394311279","type":1,"status":0,"whitelistAuthority":0,"createTime":1441221172278,"salt":"[B@4329a094","tokenVersion":0,"ban":0,"baoyueVersion":1,"donateVersion":0,"vipType":11,"viptypeVersion":1589115360022,"anonimousUser":false}
     * token : 0e85defd638da2a916cd4cde27cfff7ed66f3b8e5db96d2120beb50812b76bd933a649814e309366
     * profile : {"backgroundUrl":"https://p3.music.126.net/jpHhkykK5_u8NdJMwT44Zg==/109951163310643021.jpg","detailDescription":"","followed":false,"avatarImgIdStr":"3298534889154997","backgroundImgIdStr":"109951163310643021","userId":89773352,"mutual":false,"remarkName":null,"province":1000000,"city":1000100,"birthday":-1388563200000,"userType":0,"authStatus":0,"djStatus":0,"avatarUrl":"https://p4.music.126.net/Rer7QdFfxpjL24Z7UKBnjQ==/3298534889154997.jpg","defaultAvatar":false,"gender":0,"accountStatus":0,"vipType":11,"avatarImgId":3298534889154997,"nickname":"RICARDO94","expertTags":null,"experts":{},"backgroundImgId":109951163310643020,"description":"","signature":"","authority":0,"followeds":0,"follows":14,"eventCount":0,"playlistCount":4,"playlistBeSubscribedCount":0}
     * bindings : [{"refreshTime":1492409991,"tokenJsonStr":"{\"countrycode\":\"\",\"cellphone\":\"13394311279\",\"hasPassword\":true}","url":"","userId":89773352,"expiresIn":2147483647,"bindingTime":1492409991829,"expired":false,"id":3108380060,"type":1}]
     * cookie : MUSIC_U=0e85defd638da2a916cd4cde27cfff7ed66f3b8e5db96d2120beb50812b76bd933a649814e309366; Expires=Wed, 19-Aug-2020 06:46:08 GMT; Path=/;__remember_me=true; Expires=Wed, 19-Aug-2020 06:46:08 GMT; Path=/;__csrf=b745ec920d5a77b4153429dfb26d285b; Expires=Wed, 19-Aug-2020 06:46:18 GMT; Path=/
     */

    private int loginType;
    private int code;
    private Account account;
    private String token;
    private Profile profile;
    private String cookie;
    private List<Bindings> bindings;

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public List<Bindings> getBindings() {
        return bindings;
    }

    public void setBindings(List<Bindings> bindings) {
        this.bindings = bindings;
    }

    public static class Account {
        /**
         * id : 89773352
         * userName : 1_13394311279
         * type : 1
         * status : 0
         * whitelistAuthority : 0
         * createTime : 1441221172278
         * salt : [B@4329a094
         * tokenVersion : 0
         * ban : 0
         * baoyueVersion : 1
         * donateVersion : 0
         * vipType : 11
         * viptypeVersion : 1589115360022
         * anonimousUser : false
         */

        private long id;
        private String userName;
        private int type;
        private int status;
        private int whitelistAuthority;
        private long createTime;
        private String salt;
        private int baoyueVersion;
        private int donateVersion;
        private int vipType;
        private long viptypeVersion;
        private boolean anonimousUser;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getWhitelistAuthority() {
            return whitelistAuthority;
        }

        public void setWhitelistAuthority(int whitelistAuthority) {
            this.whitelistAuthority = whitelistAuthority;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }




        public int getBaoyueVersion() {
            return baoyueVersion;
        }

        public void setBaoyueVersion(int baoyueVersion) {
            this.baoyueVersion = baoyueVersion;
        }

        public int getDonateVersion() {
            return donateVersion;
        }

        public void setDonateVersion(int donateVersion) {
            this.donateVersion = donateVersion;
        }

        public int getVipType() {
            return vipType;
        }

        public void setVipType(int vipType) {
            this.vipType = vipType;
        }

        public long getViptypeVersion() {
            return viptypeVersion;
        }

        public void setViptypeVersion(long viptypeVersion) {
            this.viptypeVersion = viptypeVersion;
        }

        public boolean isAnonimousUser() {
            return anonimousUser;
        }

        public void setAnonimousUser(boolean anonimousUser) {
            this.anonimousUser = anonimousUser;
        }
    }

    public static class Profile {
        /**
         * backgroundUrl : https://p3.music.126.net/jpHhkykK5_u8NdJMwT44Zg==/109951163310643021.jpg
         * detailDescription :
         * followed : false
         * avatarImgIdStr : 3298534889154997
         * backgroundImgIdStr : 109951163310643021
         * userId : 89773352
         * mutual : false
         * remarkName : null
         * province : 1000000
         * city : 1000100
         * birthday : -1388563200000
         * userType : 0
         * authStatus : 0
         * djStatus : 0
         * avatarUrl : https://p4.music.126.net/Rer7QdFfxpjL24Z7UKBnjQ==/3298534889154997.jpg
         * defaultAvatar : false
         * gender : 0
         * accountStatus : 0
         * vipType : 11
         * avatarImgId : 3298534889154997
         * nickname : RICARDO94
         * expertTags : null
         * experts : {}
         * backgroundImgId : 109951163310643020
         * description :
         * signature :
         * authority : 0
         * followeds : 0
         * follows : 14
         * eventCount : 0
         * playlistCount : 4
         * playlistBeSubscribedCount : 0
         */

        private String backgroundUrl;
        private String detailDescription;
        private boolean followed;
        private String avatarImgIdStr;
        private String backgroundImgIdStr;
        private int userId;
        private boolean mutual;
        private Object remarkName;
        private int province;
        private int city;
        private long birthday;
        private int userType;
        private int authStatus;
        private int djStatus;
        private String avatarUrl;
        private boolean defaultAvatar;
        private int gender;
        private int accountStatus;
        private int vipType;
        private long avatarImgId;
        private String nickname;
        private Object expertTags;
        private Experts experts;
        private long backgroundImgId;
        private String description;
        private String signature;
        private int authority;
        private int followeds;
        private int follows;
        private int eventCount;
        private int playlistCount;
        private int playlistBeSubscribedCount;

        public String getBackgroundUrl() {
            return backgroundUrl;
        }

        public void setBackgroundUrl(String backgroundUrl) {
            this.backgroundUrl = backgroundUrl;
        }

        public String getDetailDescription() {
            return detailDescription;
        }

        public void setDetailDescription(String detailDescription) {
            this.detailDescription = detailDescription;
        }

        public boolean isFollowed() {
            return followed;
        }

        public void setFollowed(boolean followed) {
            this.followed = followed;
        }

        public String getAvatarImgIdStr() {
            return avatarImgIdStr;
        }

        public void setAvatarImgIdStr(String avatarImgIdStr) {
            this.avatarImgIdStr = avatarImgIdStr;
        }

        public String getBackgroundImgIdStr() {
            return backgroundImgIdStr;
        }

        public void setBackgroundImgIdStr(String backgroundImgIdStr) {
            this.backgroundImgIdStr = backgroundImgIdStr;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public boolean isMutual() {
            return mutual;
        }

        public void setMutual(boolean mutual) {
            this.mutual = mutual;
        }

        public Object getRemarkName() {
            return remarkName;
        }

        public void setRemarkName(Object remarkName) {
            this.remarkName = remarkName;
        }

        public int getProvince() {
            return province;
        }

        public void setProvince(int province) {
            this.province = province;
        }

        public int getCity() {
            return city;
        }

        public void setCity(int city) {
            this.city = city;
        }

        public long getBirthday() {
            return birthday;
        }

        public void setBirthday(long birthday) {
            this.birthday = birthday;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public int getAuthStatus() {
            return authStatus;
        }

        public void setAuthStatus(int authStatus) {
            this.authStatus = authStatus;
        }

        public int getDjStatus() {
            return djStatus;
        }

        public void setDjStatus(int djStatus) {
            this.djStatus = djStatus;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public boolean isDefaultAvatar() {
            return defaultAvatar;
        }

        public void setDefaultAvatar(boolean defaultAvatar) {
            this.defaultAvatar = defaultAvatar;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getAccountStatus() {
            return accountStatus;
        }

        public void setAccountStatus(int accountStatus) {
            this.accountStatus = accountStatus;
        }

        public int getVipType() {
            return vipType;
        }

        public void setVipType(int vipType) {
            this.vipType = vipType;
        }

        public long getAvatarImgId() {
            return avatarImgId;
        }

        public void setAvatarImgId(long avatarImgId) {
            this.avatarImgId = avatarImgId;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public Object getExpertTags() {
            return expertTags;
        }

        public void setExpertTags(Object expertTags) {
            this.expertTags = expertTags;
        }

        public Experts getExperts() {
            return experts;
        }

        public void setExperts(Experts experts) {
            this.experts = experts;
        }

        public long getBackgroundImgId() {
            return backgroundImgId;
        }

        public void setBackgroundImgId(long backgroundImgId) {
            this.backgroundImgId = backgroundImgId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public int getAuthority() {
            return authority;
        }

        public void setAuthority(int authority) {
            this.authority = authority;
        }

        public int getFolloweds() {
            return followeds;
        }

        public void setFolloweds(int followeds) {
            this.followeds = followeds;
        }

        public int getFollows() {
            return follows;
        }

        public void setFollows(int follows) {
            this.follows = follows;
        }

        public int getEventCount() {
            return eventCount;
        }

        public void setEventCount(int eventCount) {
            this.eventCount = eventCount;
        }

        public int getPlaylistCount() {
            return playlistCount;
        }

        public void setPlaylistCount(int playlistCount) {
            this.playlistCount = playlistCount;
        }

        public int getPlaylistBeSubscribedCount() {
            return playlistBeSubscribedCount;
        }

        public void setPlaylistBeSubscribedCount(int playlistBeSubscribedCount) {
            this.playlistBeSubscribedCount = playlistBeSubscribedCount;
        }

        public static class Experts {
        }
    }

    public static class Bindings {
        /**
         * refreshTime : 1492409991
         * tokenJsonStr : {"countrycode":"","cellphone":"13394311279","hasPassword":true}
         * url :
         * userId : 89773352
         * expiresIn : 2147483647
         * bindingTime : 1492409991829
         * expired : false
         * id : 3108380060
         * type : 1
         */

        private int refreshTime;
        private String tokenJsonStr;
        private String url;
        private long userId;
        private long expiresIn;
        private long bindingTime;
        private boolean expired;
        private long id;
        private int type;

        public int getRefreshTime() {
            return refreshTime;
        }

        public void setRefreshTime(int refreshTime) {
            this.refreshTime = refreshTime;
        }

        public String getTokenJsonStr() {
            return tokenJsonStr;
        }

        public void setTokenJsonStr(String tokenJsonStr) {
            this.tokenJsonStr = tokenJsonStr;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public long getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(int expiresIn) {
            this.expiresIn = expiresIn;
        }

        public long getBindingTime() {
            return bindingTime;
        }

        public void setBindingTime(long bindingTime) {
            this.bindingTime = bindingTime;
        }

        public boolean isExpired() {
            return expired;
        }

        public void setExpired(boolean expired) {
            this.expired = expired;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}

