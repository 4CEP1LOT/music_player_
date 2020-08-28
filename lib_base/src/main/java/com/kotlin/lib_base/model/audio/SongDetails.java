package com.kotlin.lib_base.model.audio;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.List;

//import io.reactivex.Observable;
//import io.reactivex.Observer;

@Entity
public class SongDetails implements Serializable{

//    private static final long serialVersionUID = 1L;
    /**
     * songs : [{"name":"海阔天空","id":347230,"pst":0,"t":0,"ar":[{"id":11127,"name":"Beyond","tns":[],"alias":[]}],"alia":[],"pop":100,"st":0,"rt":"600902000004240302","fee":1,"v":87,"crbt":null,"cf":"","al":{"id":34209,"name":"海阔天空","picUrl":"https://p1.music.126.net/QHw-RuMwfQkmgtiyRpGs0Q==/102254581395219.jpg","tns":[],"pic":102254581395219},"dt":326000,"h":{"br":320000,"fid":0,"size":13042459,"vd":0},"m":{"br":192000,"fid":0,"size":7825492,"vd":2310},"l":{"br":128000,"fid":0,"size":5217009,"vd":1324},"a":null,"cd":"1","no":1,"rtUrl":null,"ftype":0,"rtUrls":[],"djId":0,"copyright":1,"s_id":0,"mark":8192,"originCoverType":0,"single":0,"noCopyrightRcmd":null,"mst":9,"cp":7002,"mv":376199,"rtype":0,"rurl":null,"publishTime":746812800000}]
     * privileges : [{"id":347230,"fee":1,"payed":1,"st":0,"pl":999000,"dl":999000,"sp":7,"cp":1,"subp":1,"cs":false,"maxbr":999000,"fl":0,"toast":false,"flag":1284,"preSell":false,"playMaxbr":999000,"downloadMaxbr":999000,"chargeInfoList":[{"rate":128000,"chargeUrl":null,"chargeMessage":null,"chargeType":1},{"rate":192000,"chargeUrl":null,"chargeMessage":null,"chargeType":1},{"rate":320000,"chargeUrl":null,"chargeMessage":null,"chargeType":1},{"rate":999000,"chargeUrl":null,"chargeMessage":null,"chargeType":1}]}]
     * code : 200
     */
    private int code;
    private List<Songs> songs;



    public SongDetails() {
    }


    public int getCode() {
        return this.code;
    }



    public void setCode(int code) {
        this.code = code;
    }



    public List<Songs> getSongs() {
        return this.songs;
    }



    public void setSongs(List<Songs> songs) {
        this.songs = songs;
    }
@Entity
    public static class Songs  implements Serializable {
        /**
         * name : 海阔天空
         * id : 347230
         * pst : 0
         * t : 0
         * ar : [{"id":11127,"name":"Beyond","tns":[],"alias":[]}]
         * alia : []
         * pop : 100
         * st : 0
         * rt : 600902000004240302
         * fee : 1
         * v : 87
         * crbt : null
         * cf :
         * al : {"id":34209,"name":"海阔天空","picUrl":"https://p1.music.126.net/QHw-RuMwfQkmgtiyRpGs0Q==/102254581395219.jpg","tns":[],"pic":102254581395219}
         * dt : 326000
         * h : {"br":320000,"fid":0,"size":13042459,"vd":0}
         * m : {"br":192000,"fid":0,"size":7825492,"vd":2310}
         * l : {"br":128000,"fid":0,"size":5217009,"vd":1324}
         * a : null
         * cd : 1
         * no : 1
         * rtUrl : null
         * ftype : 0
         * rtUrls : []
         * djId : 0
         * copyright : 1
         * s_id : 0
         * mark : 8192
         * originCoverType : 0
         * single : 0
         * noCopyrightRcmd : null
         * mst : 9
         * cp : 7002
         * mv : 376199
         * rtype : 0
         * rurl : null
         * publishTime : 746812800000
         */
        @PrimaryKey
        private Long id;

        private String name;
        @TypeConverters(SongDetailAlConverter.class)

        private Al al;
        @TypeConverters(SongDetailArConverter.class)
        private List<Ar> ar;


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

        public Al getAl() {
            return al;
        }

        public void setAl(Al al) {
            this.al = al;
        }

        public List<Ar> getAr() {
            return ar;
        }

        public void setAr(List<Ar> ar) {
            this.ar = ar;
        }


    public static class Al implements Serializable{
            /**
             * id : 34209
             * name : 海阔天空
             * picUrl : https://p1.music.126.net/QHw-RuMwfQkmgtiyRpGs0Q==/102254581395219.jpg
             * tns : []
             * pic : 102254581395219
             */
            private Long id;
            private String name;
            private String picUrl;
            private long pic;

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

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public long getPic() {
                return pic;
            }

            public void setPic(long pic) {
                this.pic = pic;
            }
        }



        public  static class Ar implements Serializable{
            /**
             * id : 11127
             * name : Beyond
             * tns : []
             * alias : []
             */
            private  int id;

            private  String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }


        }
    }





}
