package com.kotlin.lib_base.model.discoverymodel;

import java.io.Serializable;

public class DSDJ24hours implements Serializable {

    private int code;
    private Data data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data implements Serializable {


        private int total;
        private long updateTime;
        private java.util.List<List> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public java.util.List<List> getList() {
            return list;
        }

        public void setList(java.util.List<List> list) {
            this.list = list;
        }

        public static class List implements Serializable {


            private Program program;


            public Program getProgram() {
                return program;
            }

            public void setProgram(Program program) {
                this.program = program;
            }


            public static class Program implements Serializable {
                /**
                 * mainSong : {"name":"一吻 天荒 - 刘大壮","id":1466847369,"position":0,"alias":[],"status":0,"fee":0,"copyrightId":0,"disc":"","no":0,"artists":[{"name":"苏冷末-","id":0,"picId":0,"img1v1Id":0,"briefDesc":"","picUrl":"http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1Url":"http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","albumSize":0,"alias":[],"trans":"","musicSize":0,"topicPerson":0}],"album":{"name":"","id":0,"type":null,"size":0,"picId":0,"blurPicUrl":null,"companyId":0,"pic":0,"picUrl":"http://p2.music.126.net/UeTuwE7pvjBpypWLudqukA==/3132508627578625.jpg","publishTime":0,"description":"","tags":"","company":null,"briefDesc":"","artist":{"name":"","id":0,"picId":0,"img1v1Id":0,"briefDesc":"","picUrl":"http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1Url":"http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","albumSize":0,"alias":[],"trans":"","musicSize":0,"topicPerson":0},"songs":[],"alias":[],"status":0,"copyrightId":0,"commentThreadId":"R_AL_3_0","artists":[{"name":"","id":0,"picId":0,"img1v1Id":0,"briefDesc":"","picUrl":"http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1Url":"http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","albumSize":0,"alias":[],"trans":"","musicSize":0,"topicPerson":0}],"subType":null,"transName":null,"mark":0},"starred":false,"popularity":5,"score":5,"starredNum":0,"duration":13191,"playedNum":0,"dayPlays":0,"hearTime":0,"ringtone":"","crbt":null,"audition":null,"copyFrom":"","commentThreadId":"R_SO_4_1466847369","rtUrl":null,"ftype":0,"rtUrls":[],"copyright":1,"transName":null,"sign":null,"mark":0,"noCopyrightRcmd":null,"hMusic":{"name":null,"id":5106192369,"size":544381,"extension":"mp3","sr":44100,"dfsId":0,"bitrate":320000,"playTime":13191,"volumeDelta":0},"mMusic":null,"lMusic":{"name":null,"id":5106192369,"size":544381,"extension":"mp3","sr":44100,"dfsId":0,"bitrate":320000,"playTime":13191,"volumeDelta":0},"bMusic":{"name":null,"id":5106192369,"size":544381,"extension":"mp3","sr":44100,"dfsId":0,"bitrate":320000,"playTime":13191,"volumeDelta":0},"rtype":0,"rurl":null,"mvid":0,"mp3Url":null}
                 * songs : null
                 * dj : {"defaultAvatar":false,"province":330000,"authStatus":0,"followed":false,"avatarUrl":"http://p1.music.126.net/ghGIl-ugrzdHRWrMdD7Nyg==/109951164887160371.jpg","accountStatus":0,"gender":1,"city":330700,"birthday":824745600000,"userId":410626554,"userType":0,"nickname":"苏冷末-","signature":"我在人间贩卖星光只为收集世间温柔去见你","description":"","detailDescription":"","avatarImgId":109951164887160370,"backgroundImgId":109951163734596850,"backgroundUrl":"http://p1.music.126.net/IKpslQHe_-xY8iGcPn0WKA==/109951163734596849.jpg","authority":0,"mutual":false,"expertTags":null,"experts":null,"djStatus":10,"vipType":11,"remarkName":null,"avatarImgIdStr":"109951164887160371","backgroundImgIdStr":"109951163734596849","avatarImgId_str":"109951164887160371","brand":""}
                 * blurCoverUrl : http://music.163.com/api/dj/img/blur/109951164943152039
                 * radio : {"dj":null,"category":"创作|翻唱","buyed":false,"price":0,"originalPrice":0,"discountPrice":null,"purchaseCount":0,"lastProgramName":null,"videos":null,"finished":false,"underShelf":false,"liveInfo":null,"picUrl":"https://p1.music.126.net/OLtXgx4WO7MZVtBrt6U1Mw==/109951164943152039.jpg","desc":"🐧群:68450286\n你的宝藏电台啊❣\n每天8点更新节目⚘\n收集一些好听的翻唱鸭✨\n云边有个小卖铺，贩卖音乐和温柔✿.","categoryId":2001,"createTime":1542635229093,"picId":109951164943152030,"lastProgramId":2068415301,"feeScope":0,"radioFeeType":0,"programCount":216,"subCount":72951,"lastProgramCreateTime":1596240000000,"name":"云边有个小卖铺💭","id":792129517,"subed":false}
                 * duration : 13191
                 * buyed : false
                 * programDesc : null
                 * h5Links : null
                 * canReward : false
                 * auditStatus : 0
                 * videoInfo : null
                 * score : 0
                 * liveInfo : null
                 * alg : null
                 * titbitImages : null
                 * isPublish : true
                 * trackCount : 0
                 * description : 一吻天荒-刘大壮
                 * createTime : 1596153600000
                 * smallLanguageAuditStatus : 0
                 * commentThreadId : A_DJ_1_2068395758
                 * mainTrackId : 1466847369
                 * pubStatus : 1
                 * programFeeType : 0
                 * coverUrl : https://p1.music.126.net/OLtXgx4WO7MZVtBrt6U1Mw==/109951164943152039.jpg
                 * reward : false
                 * channels : []
                 * listenerCount : 113062
                 * subscribedCount : 0
                 * feeScope : 0
                 * serialNum : 215
                 * titbits : null
                 * bdAuditStatus : 2
                 * name : 一吻 天荒 - 刘大壮
                 * id : 2068395758
                 * subscribed : false
                 * shareCount : 2
                 * likedCount : 383
                 * commentCount : 44
                 */

                private MainSong mainSong;
                private Object songs;
                private Dj dj;
                private Radio radio;
                private int duration;

                private int trackCount;
                private String description;
                private String coverUrl;
                private int subscribedCount;
                private String name;
                private int id;
                private int shareCount;
                private int likedCount;
                private int commentCount;

                public MainSong getMainSong() {
                    return mainSong;
                }

                public void setMainSong(MainSong mainSong) {
                    this.mainSong = mainSong;
                }

                public Object getSongs() {
                    return songs;
                }

                public void setSongs(Object songs) {
                    this.songs = songs;
                }

                public Dj getDj() {
                    return dj;
                }

                public void setDj(Dj dj) {
                    this.dj = dj;
                }


                public int getTrackCount() {
                    return trackCount;
                }

                public void setTrackCount(int trackCount) {
                    this.trackCount = trackCount;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getCoverUrl() {
                    return coverUrl;
                }

                public void setCoverUrl(String coverUrl) {
                    this.coverUrl = coverUrl;
                }


                public int getSubscribedCount() {
                    return subscribedCount;
                }

                public void setSubscribedCount(int subscribedCount) {
                    this.subscribedCount = subscribedCount;
                }


                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getShareCount() {
                    return shareCount;
                }

                public void setShareCount(int shareCount) {
                    this.shareCount = shareCount;
                }


                public int getCommentCount() {
                    return commentCount;
                }

                public void setCommentCount(int commentCount) {
                    this.commentCount = commentCount;
                }


                public static class MainSong implements Serializable {
                    /**
                     * name : 一吻 天荒 - 刘大壮
                     * id : 1466847369
                     * position : 0
                     * alias : []
                     * status : 0
                     * fee : 0
                     * copyrightId : 0
                     * disc :
                     * no : 0
                     * artists : [{"name":"苏冷末-","id":0,"picId":0,"img1v1Id":0,"briefDesc":"","picUrl":"http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1Url":"http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","albumSize":0,"alias":[],"trans":"","musicSize":0,"topicPerson":0}]
                     * album : {"name":"","id":0,"type":null,"size":0,"picId":0,"blurPicUrl":null,"companyId":0,"pic":0,"picUrl":"http://p2.music.126.net/UeTuwE7pvjBpypWLudqukA==/3132508627578625.jpg","publishTime":0,"description":"","tags":"","company":null,"briefDesc":"","artist":{"name":"","id":0,"picId":0,"img1v1Id":0,"briefDesc":"","picUrl":"http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1Url":"http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","albumSize":0,"alias":[],"trans":"","musicSize":0,"topicPerson":0},"songs":[],"alias":[],"status":0,"copyrightId":0,"commentThreadId":"R_AL_3_0","artists":[{"name":"","id":0,"picId":0,"img1v1Id":0,"briefDesc":"","picUrl":"http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1Url":"http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","albumSize":0,"alias":[],"trans":"","musicSize":0,"topicPerson":0}],"subType":null,"transName":null,"mark":0}
                     * starred : false
                     * popularity : 5
                     * score : 5
                     * starredNum : 0
                     * duration : 13191
                     * playedNum : 0
                     * dayPlays : 0
                     * hearTime : 0
                     * ringtone :
                     * crbt : null
                     * audition : null
                     * copyFrom :
                     * commentThreadId : R_SO_4_1466847369
                     * rtUrl : null
                     * ftype : 0
                     * rtUrls : []
                     * copyright : 1
                     * transName : null
                     * sign : null
                     * mark : 0
                     * noCopyrightRcmd : null
                     * hMusic : {"name":null,"id":5106192369,"size":544381,"extension":"mp3","sr":44100,"dfsId":0,"bitrate":320000,"playTime":13191,"volumeDelta":0}
                     * mMusic : null
                     * lMusic : {"name":null,"id":5106192369,"size":544381,"extension":"mp3","sr":44100,"dfsId":0,"bitrate":320000,"playTime":13191,"volumeDelta":0}
                     * bMusic : {"name":null,"id":5106192369,"size":544381,"extension":"mp3","sr":44100,"dfsId":0,"bitrate":320000,"playTime":13191,"volumeDelta":0}
                     * rtype : 0
                     * rurl : null
                     * mvid : 0
                     * mp3Url : null
                     */

                    private String name;
                    private int id;
                    private int position;
                    private int status;

                    private int no;
                    private Album album;
                    private int duration;

                    private java.util.List<ArtistsX> artists;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getPosition() {
                        return position;
                    }

                    public void setPosition(int position) {
                        this.position = position;
                    }

                    public int getStatus() {
                        return status;
                    }

                    public void setStatus(int status) {
                        this.status = status;
                    }


                    public int getNo() {
                        return no;
                    }

                    public void setNo(int no) {
                        this.no = no;
                    }

                    public Album getAlbum() {
                        return album;
                    }

                    public void setAlbum(Album album) {
                        this.album = album;
                    }


                    public int getDuration() {
                        return duration;
                    }

                    public void setDuration(int duration) {
                        this.duration = duration;
                    }


                    public java.util.List<ArtistsX> getArtists() {
                        return artists;
                    }

                    public void setArtists(java.util.List<ArtistsX> artists) {
                        this.artists = artists;
                    }


                    public static class Album implements Serializable {
                        /**
                         * name :
                         * id : 0
                         * type : null
                         * size : 0
                         * picId : 0
                         * blurPicUrl : null
                         * companyId : 0
                         * pic : 0
                         * picUrl : http://p2.music.126.net/UeTuwE7pvjBpypWLudqukA==/3132508627578625.jpg
                         * publishTime : 0
                         * description :
                         * tags :
                         * company : null
                         * briefDesc :
                         * artist : {"name":"","id":0,"picId":0,"img1v1Id":0,"briefDesc":"","picUrl":"http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1Url":"http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","albumSize":0,"alias":[],"trans":"","musicSize":0,"topicPerson":0}
                         * songs : []
                         * alias : []
                         * status : 0
                         * copyrightId : 0
                         * commentThreadId : R_AL_3_0
                         * artists : [{"name":"","id":0,"picId":0,"img1v1Id":0,"briefDesc":"","picUrl":"http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","img1v1Url":"http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg","albumSize":0,"alias":[],"trans":"","musicSize":0,"topicPerson":0}]
                         * subType : null
                         * transName : null
                         * mark : 0
                         */

                        private String name;
                        private int id;
                        private int size;
                        private String picUrl;
                        private String description;
                        private Artist artist;
                        private java.util.List<Artists> artists;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }

                        public int getSize() {
                            return size;
                        }

                        public void setSize(int size) {
                            this.size = size;
                        }

                        public String getPicUrl() {
                            return picUrl;
                        }

                        public void setPicUrl(String picUrl) {
                            this.picUrl = picUrl;
                        }

                        public String getDescription() {
                            return description;
                        }

                        public void setDescription(String description) {
                            this.description = description;
                        }


                        public Artist getArtist() {
                            return artist;
                        }

                        public void setArtist(Artist artist) {
                            this.artist = artist;
                        }


                        public java.util.List<Artists> getArtists() {
                            return artists;
                        }

                        public void setArtists(java.util.List<Artists> artists) {
                            this.artists = artists;
                        }


                        public static class Artist implements Serializable {
                            /**
                             * name :
                             * id : 0
                             * picId : 0
                             * img1v1Id : 0
                             * briefDesc :
                             * picUrl : http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
                             * img1v1Url : http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
                             * albumSize : 0
                             * alias : []
                             * trans :
                             * musicSize : 0
                             * topicPerson : 0
                             */

                            private String name;
                            private int id;
                            private String picUrl;

                            public String getName() {
                                return name;
                            }

                            public void setName(String name) {
                                this.name = name;
                            }

                            public int getId() {
                                return id;
                            }

                            public void setId(int id) {
                                this.id = id;
                            }


                            public String getPicUrl() {
                                return picUrl;
                            }

                            public void setPicUrl(String picUrl) {
                                this.picUrl = picUrl;
                            }


                        }

                        public static class Artists implements Serializable {
                            /**
                             * name :
                             * id : 0
                             * picId : 0
                             * img1v1Id : 0
                             * briefDesc :
                             * picUrl : http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
                             * img1v1Url : http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
                             * albumSize : 0
                             * alias : []
                             * trans :
                             * musicSize : 0
                             * topicPerson : 0
                             */

                            private String name;
                            private int id;
                            private String picUrl;

                            public String getName() {
                                return name;
                            }

                            public void setName(String name) {
                                this.name = name;
                            }

                            public int getId() {
                                return id;
                            }

                            public void setId(int id) {
                                this.id = id;
                            }


                            public String getPicUrl() {
                                return picUrl;
                            }

                            public void setPicUrl(String picUrl) {
                                this.picUrl = picUrl;
                            }

                        }
                    }

                    public static class ArtistsX implements Serializable {
                        /**
                         * name : 苏冷末-
                         * id : 0
                         * picId : 0
                         * img1v1Id : 0
                         * briefDesc :
                         * picUrl : http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
                         * img1v1Url : http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
                         * albumSize : 0
                         * alias : []
                         * trans :
                         * musicSize : 0
                         * topicPerson : 0
                         */

                        private String name;
                        private int id;

                        public String getPicUrl() {
                            return picUrl;
                        }

                        public void setPicUrl(String picUrl) {
                            this.picUrl = picUrl;
                        }

                        private String picUrl;


                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }
                    }
                }

                public static class Dj implements Serializable {
                    /**
                     * defaultAvatar : false
                     * province : 330000
                     * authStatus : 0
                     * followed : false
                     * avatarUrl : http://p1.music.126.net/ghGIl-ugrzdHRWrMdD7Nyg==/109951164887160371.jpg
                     * accountStatus : 0
                     * gender : 1
                     * city : 330700
                     * birthday : 824745600000
                     * userId : 410626554
                     * userType : 0
                     * nickname : 苏冷末-
                     * signature : 我在人间贩卖星光只为收集世间温柔去见你
                     * description :
                     * detailDescription :
                     * avatarImgId : 109951164887160370
                     * backgroundImgId : 109951163734596850
                     * backgroundUrl : http://p1.music.126.net/IKpslQHe_-xY8iGcPn0WKA==/109951163734596849.jpg
                     * authority : 0
                     * mutual : false
                     * expertTags : null
                     * experts : null
                     * djStatus : 10
                     * vipType : 11
                     * remarkName : null
                     * avatarImgIdStr : 109951164887160371
                     * backgroundImgIdStr : 109951163734596849
                     * avatarImgId_str : 109951164887160371
                     * brand :
                     */

                    private boolean defaultAvatar;
                    private int province;
                    private String avatarUrl;
                    private int gender;
                    private int city;
                    private long birthday;
                    private long userId;
                    private String nickname;
                    private String signature;
                    private String description;
                    private String backgroundUrl;

                    public boolean isDefaultAvatar() {
                        return defaultAvatar;
                    }

                    public void setDefaultAvatar(boolean defaultAvatar) {
                        this.defaultAvatar = defaultAvatar;
                    }

                    public int getProvince() {
                        return province;
                    }

                    public void setProvince(int province) {
                        this.province = province;
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

                    public long getUserId() {
                        return userId;
                    }

                    public void setUserId(long userId) {
                        this.userId = userId;
                    }

                    public String getNickname() {
                        return nickname;
                    }

                    public void setNickname(String nickname) {
                        this.nickname = nickname;
                    }

                    public String getSignature() {
                        return signature;
                    }

                    public void setSignature(String signature) {
                        this.signature = signature;
                    }

                    public String getDescription() {
                        return description;
                    }

                    public void setDescription(String description) {
                        this.description = description;
                    }

                    public String getBackgroundUrl() {
                        return backgroundUrl;
                    }

                    public void setBackgroundUrl(String backgroundUrl) {
                        this.backgroundUrl = backgroundUrl;
                    }

                }

                public static class Radio implements Serializable {
                    /**
                     * dj : null
                     * category : 创作|翻唱
                     * buyed : false
                     * price : 0
                     * originalPrice : 0
                     * discountPrice : null
                     * purchaseCount : 0
                     * lastProgramName : null
                     * videos : null
                     * finished : false
                     * underShelf : false
                     * liveInfo : null
                     * picUrl : https://p1.music.126.net/OLtXgx4WO7MZVtBrt6U1Mw==/109951164943152039.jpg
                     * desc : 🐧群:68450286
                     * 你的宝藏电台啊❣
                     * 每天8点更新节目⚘
                     * 收集一些好听的翻唱鸭✨
                     * 云边有个小卖铺，贩卖音乐和温柔✿.
                     * categoryId : 2001
                     * createTime : 1542635229093
                     * picId : 109951164943152030
                     * lastProgramId : 2068415301
                     * feeScope : 0
                     * radioFeeType : 0
                     * programCount : 216
                     * subCount : 72951
                     * lastProgramCreateTime : 1596240000000
                     * name : 云边有个小卖铺💭
                     * id : 792129517
                     * subed : false
                     */

                    private Object dj;
                    private Object videos;
                    private String picUrl;

                    private long picId;
                    private int programCount;
                    private String name;
                    private int id;

                    public Object getDj() {
                        return dj;
                    }

                    public void setDj(Object dj) {
                        this.dj = dj;
                    }

                    public Object getVideos() {
                        return videos;
                    }

                    public void setVideos(Object videos) {
                        this.videos = videos;
                    }


                    public String getPicUrl() {
                        return picUrl;
                    }

                    public void setPicUrl(String picUrl) {
                        this.picUrl = picUrl;
                    }

                    public long getPicId() {
                        return picId;
                    }

                    public void setPicId(long picId) {
                        this.picId = picId;
                    }


                    public int getProgramCount() {
                        return programCount;
                    }

                    public void setProgramCount(int programCount) {
                        this.programCount = programCount;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                }
            }
        }
    }
}

