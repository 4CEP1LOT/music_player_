package com.kotlin.lib_search.event;

import com.kotlin.lib_base.surface.audio.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class SearchTrend extends BaseModel {


    /**
     * code : 200
     * data : [{"searchWord":"官方回答","score":2184691,"content":"我用官方的回答了他的废话","source":0,"iconType":1,"iconUrl":"https://p1.music.126.net/2zQ0d1ThZCX5Jtkvks9aOQ==/109951163968000522.png","url":"","alg":"alg_statistics"},{"searchWord":"爱存在","score":1834646,"content":"对你的爱 会一直存在~","source":0,"iconType":1,"iconUrl":"https://p1.music.126.net/2zQ0d1ThZCX5Jtkvks9aOQ==/109951163968000522.png","url":"","alg":"alg_statistics"},{"searchWord":"薛之谦新歌","score":1829785,"content":"新专首波主打《天外来物》！","source":1,"iconType":1,"iconUrl":"https://p1.music.126.net/2zQ0d1ThZCX5Jtkvks9aOQ==/109951163968000522.png","url":"","alg":"featured"},{"searchWord":"信仰","score":1825450,"content":"情歌王子张信哲的经典收录","source":0,"iconType":1,"iconUrl":"https://p1.music.126.net/2zQ0d1ThZCX5Jtkvks9aOQ==/109951163968000522.png","url":"","alg":"alg_statistics"},{"searchWord":"你走","score":1549093,"content":"松紧先生的歌唱到，不想TA走记得挽留~","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"},{"searchWord":"海底","score":1147535,"content":"温柔的人会将你带离海底","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"},{"searchWord":"薛之谦","score":1068146,"content":"薛老师一发歌就要掀起狂潮！","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"},{"searchWord":"溯","score":937958,"content":"慵懒歌曲，失眠的首选","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"},{"searchWord":"Troye Sivan","score":884389,"content":"听着戳爷新歌 生活So Easy","source":1,"iconType":1,"iconUrl":"https://p1.music.126.net/2zQ0d1ThZCX5Jtkvks9aOQ==/109951163968000522.png","url":"","alg":"featured"},{"searchWord":"脆弱星球","score":858415,"content":"想去你的星球种一颗温柔的树","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"},{"searchWord":"在你身边就失去超能力","score":857282,"content":"我的男孩是个超人~","source":0,"iconType":2,"iconUrl":"https://p1.music.126.net/szWeddITZIVxpvQ0QywzcQ==/109951163967989323.png","url":"","alg":"alg_statistics"},{"searchWord":"江南","score":786575,"content":"林俊杰经典歌曲全收录！","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"},{"searchWord":"蔡徐坤","score":749756,"content":"新生代偶像艺人了解一下","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"},{"searchWord":"赤伶","score":673152,"content":"戏腔与古风相结合的绝美唱段！","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"},{"searchWord":"林俊杰","score":632258,"content":"一千年以后，还是学不会爱你","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"},{"searchWord":"你啊你啊","score":626724,"content":"你啊 是我藏在心底的记忆啊~","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"},{"searchWord":"后来","score":619051,"content":"从前的一念之差，后来的天各一方","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"},{"searchWord":"我想念","score":595604,"content":"想念那个不太热的夏天","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"},{"searchWord":"心墙","score":582436,"content":"在你的心墙上 发现一扇窗","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"},{"searchWord":"天下","score":582015,"content":"一起感受张杰歌里的壮志凌云","source":0,"iconType":0,"iconUrl":null,"url":"","alg":"alg_statistics"}]
     * message : success
     */

    private int code;
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class Data {
        /**
         * searchWord : 官方回答
         * score : 2184691
         * content : 我用官方的回答了他的废话
         * source : 0
         * iconType : 1
         * iconUrl : https://p1.music.126.net/2zQ0d1ThZCX5Jtkvks9aOQ==/109951163968000522.png
         * url :
         * alg : alg_statistics
         */

        private String searchWord;
        private String content;
        private String iconUrl;
        private String score;

        public String getSearchWord() {
            return searchWord;
        }

        public String getScore() {
            return score;
        }

        public void setSearchWord(String searchWord) {
            this.searchWord = searchWord;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }
    }
}
