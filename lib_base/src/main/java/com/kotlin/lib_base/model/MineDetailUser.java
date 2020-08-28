package com.kotlin.lib_base.model;

import java.util.List;

public class MineDetailUser  {

    /**
     * level : 10
     * listenSongs : 26360
     * userPoint : {"userId":32953014,"balance":891,"updateTime":1596519764299,"version":10,"status":1,"blockBalance":0}
     * mobileSign : false
     * pcSign : false
     * profile : {"avatarImgIdStr":"109951165198750822","vipType":11,"userType":0,"createTime":1407747900967,"mutual":false,"followed":false,"remarkName":null,"authStatus":0,"detailDescription":"","experts":{},"expertTags":null,"djStatus":0,"accountStatus":0,"province":440000,"city":440300,"defaultAvatar":false,"backgroundImgId":109951163792144620,"backgroundUrl":"http://p1.music.126.net/WLTBvNL_l9ZKlslFwaCM9Q==/109951163792144631.jpg","nickname":"binaryify","birthday":768931200000,"avatarImgId":109951165198750820,"avatarUrl":"http://p1.music.126.net/tSBa_zGs2jKgVgmm47mAjg==/109951165198750822.jpg","gender":1,"description":"","userId":32953014,"backgroundImgIdStr":"109951163792144631","signature":"深圳市爱猫人士","authority":0,"followeds":26,"follows":9,"blacklist":false,"eventCount":21,"allSubscribedCount":0,"playlistBeSubscribedCount":3,"avatarImgId_str":"109951165198750822","followTime":null,"followMe":false,"artistIdentity":[],"cCount":0,"sDJPCount":0,"playlistCount":17,"sCount":0,"newFollows":9}
     * peopleCanSeeMyPlayRecord : false
     * bindings : [{"expiresIn":2147483647,"refreshTime":1592285666,"bindingTime":1426295169224,"tokenJsonStr":null,"expired":false,"url":"","userId":32953014,"id":28098251,"type":1},{"expiresIn":2628968,"refreshTime":1507142393,"bindingTime":1407747883151,"tokenJsonStr":null,"expired":true,"url":"http://weibo.com/u/5144142752","userId":32953014,"id":18574366,"type":2}]
     * adValid : false
     * code : 200
     * createTime : 1407747900967
     * createDays : 2185
     */

    private int level;
    private int listenSongs;
    private UserPoint userPoint;
    private boolean mobileSign;
    private boolean pcSign;
    private Profile profile;
    private boolean peopleCanSeeMyPlayRecord;
    private boolean adValid;
    private int code;
    private long createTime;
    private int createDays;
    private List<Bindings> bindings;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getListenSongs() {
        return listenSongs;
    }

    public void setListenSongs(int listenSongs) {
        this.listenSongs = listenSongs;
    }

    public UserPoint getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(UserPoint userPoint) {
        this.userPoint = userPoint;
    }

    public boolean isMobileSign() {
        return mobileSign;
    }

    public void setMobileSign(boolean mobileSign) {
        this.mobileSign = mobileSign;
    }

    public boolean isPcSign() {
        return pcSign;
    }

    public void setPcSign(boolean pcSign) {
        this.pcSign = pcSign;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public boolean isPeopleCanSeeMyPlayRecord() {
        return peopleCanSeeMyPlayRecord;
    }

    public void setPeopleCanSeeMyPlayRecord(boolean peopleCanSeeMyPlayRecord) {
        this.peopleCanSeeMyPlayRecord = peopleCanSeeMyPlayRecord;
    }

    public boolean isAdValid() {
        return adValid;
    }

    public void setAdValid(boolean adValid) {
        this.adValid = adValid;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getCreateDays() {
        return createDays;
    }

    public void setCreateDays(int createDays) {
        this.createDays = createDays;
    }

    public List<Bindings> getBindings() {
        return bindings;
    }

    public void setBindings(List<Bindings> bindings) {
        this.bindings = bindings;
    }

    public static class UserPoint {
        /**
         * userId : 32953014
         * balance : 891
         * updateTime : 1596519764299
         * version : 10
         * status : 1
         * blockBalance : 0
         */

        private int userId;
        private int balance;
        private long updateTime;
        private int version;
        private int status;
        private int blockBalance;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getBlockBalance() {
            return blockBalance;
        }

        public void setBlockBalance(int blockBalance) {
            this.blockBalance = blockBalance;
        }
    }

    public static class Profile {
        /**
         * avatarImgIdStr : 109951165198750822
         * vipType : 11
         * userType : 0
         * createTime : 1407747900967
         * mutual : false
         * followed : false
         * remarkName : null
         * authStatus : 0
         * detailDescription :
         * experts : {}
         * expertTags : null
         * djStatus : 0
         * accountStatus : 0
         * province : 440000
         * city : 440300
         * defaultAvatar : false
         * backgroundImgId : 109951163792144620
         * backgroundUrl : http://p1.music.126.net/WLTBvNL_l9ZKlslFwaCM9Q==/109951163792144631.jpg
         * nickname : binaryify
         * birthday : 768931200000
         * avatarImgId : 109951165198750820
         * avatarUrl : http://p1.music.126.net/tSBa_zGs2jKgVgmm47mAjg==/109951165198750822.jpg
         * gender : 1
         * description :
         * userId : 32953014
         * backgroundImgIdStr : 109951163792144631
         * signature : 深圳市爱猫人士
         * authority : 0
         * followeds : 26
         * follows : 9
         * blacklist : false
         * eventCount : 21
         * allSubscribedCount : 0
         * playlistBeSubscribedCount : 3
         * avatarImgId_str : 109951165198750822
         * followTime : null
         * followMe : false
         * artistIdentity : []
         * cCount : 0
         * sDJPCount : 0
         * playlistCount : 17
         * sCount : 0
         * newFollows : 9
         */

        private String avatarImgIdStr;
        private int vipType;
        private int userType;
        private long createTime;
        private boolean mutual;
        private boolean followed;
        private Object remarkName;
        private int authStatus;
        private String detailDescription;
        private Experts experts;
        private Object expertTags;
        private int djStatus;
        private int accountStatus;
        private int province;
        private int city;
        private boolean defaultAvatar;
        private long backgroundImgId;
        private String backgroundUrl;
        private String nickname;
        private long birthday;
        private long avatarImgId;
        private String avatarUrl;
        private int gender;
        private String description;
        private int userId;
        private String backgroundImgIdStr;
        private String signature;
        private int authority;
        private int followeds;
        private int follows;
        private boolean blacklist;
        private int eventCount;
        private int allSubscribedCount;
        private int playlistBeSubscribedCount;
        private String avatarImgId_str;
        private Object followTime;
        private boolean followMe;
        private int cCount;
        private int sDJPCount;
        private int playlistCount;
        private int sCount;
        private int newFollows;
        private List<?> artistIdentity;

        public String getAvatarImgIdStr() {
            return avatarImgIdStr;
        }

        public void setAvatarImgIdStr(String avatarImgIdStr) {
            this.avatarImgIdStr = avatarImgIdStr;
        }

        public int getVipType() {
            return vipType;
        }

        public void setVipType(int vipType) {
            this.vipType = vipType;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public boolean isMutual() {
            return mutual;
        }

        public void setMutual(boolean mutual) {
            this.mutual = mutual;
        }

        public boolean isFollowed() {
            return followed;
        }

        public void setFollowed(boolean followed) {
            this.followed = followed;
        }

        public Object getRemarkName() {
            return remarkName;
        }

        public void setRemarkName(Object remarkName) {
            this.remarkName = remarkName;
        }

        public int getAuthStatus() {
            return authStatus;
        }

        public void setAuthStatus(int authStatus) {
            this.authStatus = authStatus;
        }

        public String getDetailDescription() {
            return detailDescription;
        }

        public void setDetailDescription(String detailDescription) {
            this.detailDescription = detailDescription;
        }

        public Experts getExperts() {
            return experts;
        }

        public void setExperts(Experts experts) {
            this.experts = experts;
        }

        public Object getExpertTags() {
            return expertTags;
        }

        public void setExpertTags(Object expertTags) {
            this.expertTags = expertTags;
        }

        public int getDjStatus() {
            return djStatus;
        }

        public void setDjStatus(int djStatus) {
            this.djStatus = djStatus;
        }

        public int getAccountStatus() {
            return accountStatus;
        }

        public void setAccountStatus(int accountStatus) {
            this.accountStatus = accountStatus;
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

        public boolean isDefaultAvatar() {
            return defaultAvatar;
        }

        public void setDefaultAvatar(boolean defaultAvatar) {
            this.defaultAvatar = defaultAvatar;
        }

        public long getBackgroundImgId() {
            return backgroundImgId;
        }

        public void setBackgroundImgId(long backgroundImgId) {
            this.backgroundImgId = backgroundImgId;
        }

        public String getBackgroundUrl() {
            return backgroundUrl;
        }

        public void setBackgroundUrl(String backgroundUrl) {
            this.backgroundUrl = backgroundUrl;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public long getBirthday() {
            return birthday;
        }

        public void setBirthday(long birthday) {
            this.birthday = birthday;
        }

        public long getAvatarImgId() {
            return avatarImgId;
        }

        public void setAvatarImgId(long avatarImgId) {
            this.avatarImgId = avatarImgId;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getBackgroundImgIdStr() {
            return backgroundImgIdStr;
        }

        public void setBackgroundImgIdStr(String backgroundImgIdStr) {
            this.backgroundImgIdStr = backgroundImgIdStr;
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

        public boolean isBlacklist() {
            return blacklist;
        }

        public void setBlacklist(boolean blacklist) {
            this.blacklist = blacklist;
        }

        public int getEventCount() {
            return eventCount;
        }

        public void setEventCount(int eventCount) {
            this.eventCount = eventCount;
        }

        public int getAllSubscribedCount() {
            return allSubscribedCount;
        }

        public void setAllSubscribedCount(int allSubscribedCount) {
            this.allSubscribedCount = allSubscribedCount;
        }

        public int getPlaylistBeSubscribedCount() {
            return playlistBeSubscribedCount;
        }

        public void setPlaylistBeSubscribedCount(int playlistBeSubscribedCount) {
            this.playlistBeSubscribedCount = playlistBeSubscribedCount;
        }

        public String getAvatarImgId_str() {
            return avatarImgId_str;
        }

        public void setAvatarImgId_str(String avatarImgId_str) {
            this.avatarImgId_str = avatarImgId_str;
        }

        public Object getFollowTime() {
            return followTime;
        }

        public void setFollowTime(Object followTime) {
            this.followTime = followTime;
        }

        public boolean isFollowMe() {
            return followMe;
        }

        public void setFollowMe(boolean followMe) {
            this.followMe = followMe;
        }

        public int getCCount() {
            return cCount;
        }

        public void setCCount(int cCount) {
            this.cCount = cCount;
        }

        public int getSDJPCount() {
            return sDJPCount;
        }

        public void setSDJPCount(int sDJPCount) {
            this.sDJPCount = sDJPCount;
        }

        public int getPlaylistCount() {
            return playlistCount;
        }

        public void setPlaylistCount(int playlistCount) {
            this.playlistCount = playlistCount;
        }

        public int getSCount() {
            return sCount;
        }

        public void setSCount(int sCount) {
            this.sCount = sCount;
        }

        public int getNewFollows() {
            return newFollows;
        }

        public void setNewFollows(int newFollows) {
            this.newFollows = newFollows;
        }

        public List<?> getArtistIdentity() {
            return artistIdentity;
        }

        public void setArtistIdentity(List<?> artistIdentity) {
            this.artistIdentity = artistIdentity;
        }

        public static class Experts {
        }
    }

    public static class Bindings {
        /**
         * expiresIn : 2147483647
         * refreshTime : 1592285666
         * bindingTime : 1426295169224
         * tokenJsonStr : null
         * expired : false
         * url :
         * userId : 32953014
         * id : 28098251
         * type : 1
         */

        private int expiresIn;
        private int refreshTime;
        private long bindingTime;
        private Object tokenJsonStr;
        private boolean expired;
        private String url;
        private int userId;
        private long id;
        private int type;

        public int getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(int expiresIn) {
            this.expiresIn = expiresIn;
        }

        public int getRefreshTime() {
            return refreshTime;
        }

        public void setRefreshTime(int refreshTime) {
            this.refreshTime = refreshTime;
        }

        public long getBindingTime() {
            return bindingTime;
        }

        public void setBindingTime(long bindingTime) {
            this.bindingTime = bindingTime;
        }

        public Object getTokenJsonStr() {
            return tokenJsonStr;
        }

        public void setTokenJsonStr(Object tokenJsonStr) {
            this.tokenJsonStr = tokenJsonStr;
        }

        public boolean isExpired() {
            return expired;
        }

        public void setExpired(boolean expired) {
            this.expired = expired;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
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
