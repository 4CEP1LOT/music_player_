package com.kotlin.lib_base.model.audio;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.List;
//
//import io.reactivex.Observable;
//import io.reactivex.Observer;

@Entity(tableName = "songdata")
public class SongUrl  implements Serializable {


    /**
     * data : [{"id":33894312,"url":"http://m7.music.126.net/20200806210500/1a5d59bbb0d328783ddfb3b6dbc5c7fd/ymusic/0fd6/4f65/43ed/a8772889f38dfcb91c04da915b301617.mp3","br":320000,"size":10691439,"md5":"a8772889f38dfcb91c04da915b301617","code":200,"expi":1200,"type":"mp3","gain":0,"fee":0,"uf":null,"payed":0,"flag":0,"canExtend":false,"freeTrialInfo":null,"level":"exhigh","encodeType":"mp3"}]
     * code : 200
     */
    @PrimaryKey
    private Long code;
    @TypeConverters(SongUrlDataConverter.class)
    private List<Data> data;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }



    public static class Data  implements Serializable{

        /**
         * id : 33894312
         * url : http://m7.music.126.net/20200806210500/1a5d59bbb0d328783ddfb3b6dbc5c7fd/ymusic/0fd6/4f65/43ed/a8772889f38dfcb91c04da915b301617.mp3
         * br : 320000
         * size : 10691439
         * md5 : a8772889f38dfcb91c04da915b301617
         * code : 200
         * expi : 1200
         * type : mp3
         * gain : 0
         * fee : 0
         * uf : null
         * payed : 0
         * flag : 0
         * canExtend : false
         * freeTrialInfo : null
         * level : exhigh
         * encodeType : mp3
         */




        private Long id;
        private String url;
        private int br;
        private int size;
        private String md5;
        private int code;
        private int expi;
        private String type;
        private int gain;
        private int fee;
        private int payed;
        private int flag;
        private boolean canExtend;
        private String level;
        private String encodeType;


        public Long getId() {
            return id;
        }


        public void setId(Long id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getBr() {
            return br;
        }

        public void setBr(int br) {
            this.br = br;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getExpi() {
            return expi;
        }

        public void setExpi(int expi) {
            this.expi = expi;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getGain() {
            return gain;
        }

        public void setGain(int gain) {
            this.gain = gain;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }



        public int getPayed() {
            return payed;
        }

        public void setPayed(int payed) {
            this.payed = payed;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public boolean isCanExtend() {
            return canExtend;
        }

        public void setCanExtend(boolean canExtend) {
            this.canExtend = canExtend;
        }



        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getEncodeType() {
            return encodeType;
        }

        public void setEncodeType(String encodeType) {
            this.encodeType = encodeType;
        }

    }
}
