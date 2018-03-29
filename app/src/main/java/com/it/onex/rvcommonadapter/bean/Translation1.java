package com.it.onex.rvcommonadapter.bean;

import java.util.List;

/**
 * Created by Linsa on 2018/3/26:10:47.
 * des: 有道词典进行
 */

public class Translation1 {

    /**
     * status : 0
     * content : {"ph_en":"ˈændrɔɪd","ph_am":"ˈænˌdrɔɪd","ph_en_mp3":"http://res.iciba.com/resource/amp3/oxford/0/e0/61/e061e936d929f00e0957a07a8c9b563a.mp3","ph_am_mp3":"http://res.iciba.com/resource/amp3/1/0/c3/1b/c31b32364ce19ca8fcd150a417ecce58.mp3","ph_tts_mp3":"http://res-tts.iciba.com/c/3/1/c31b32364ce19ca8fcd150a417ecce58.mp3","word_mean":["n. 机器人;基于Linux平台的开源手机操作系统，主要使用于便携设备。目前尚未有统一中文名称，中国大陆地区较多人称为安卓;"]}
     */

    private int status;
    private ContentBean content;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * ph_en : ˈændrɔɪd
         * ph_am : ˈænˌdrɔɪd
         * ph_en_mp3 : http://res.iciba.com/resource/amp3/oxford/0/e0/61/e061e936d929f00e0957a07a8c9b563a.mp3
         * ph_am_mp3 : http://res.iciba.com/resource/amp3/1/0/c3/1b/c31b32364ce19ca8fcd150a417ecce58.mp3
         * ph_tts_mp3 : http://res-tts.iciba.com/c/3/1/c31b32364ce19ca8fcd150a417ecce58.mp3
         * word_mean : ["n. 机器人;基于Linux平台的开源手机操作系统，主要使用于便携设备。目前尚未有统一中文名称，中国大陆地区较多人称为安卓;"]
         */

        private String ph_en;
        private String ph_am;
        private String ph_en_mp3;
        private String ph_am_mp3;
        private String ph_tts_mp3;
        private List<String> word_mean;

        public String getPh_en() {
            return ph_en;
        }

        public void setPh_en(String ph_en) {
            this.ph_en = ph_en;
        }

        public String getPh_am() {
            return ph_am;
        }

        public void setPh_am(String ph_am) {
            this.ph_am = ph_am;
        }

        public String getPh_en_mp3() {
            return ph_en_mp3;
        }

        public void setPh_en_mp3(String ph_en_mp3) {
            this.ph_en_mp3 = ph_en_mp3;
        }

        public String getPh_am_mp3() {
            return ph_am_mp3;
        }

        public void setPh_am_mp3(String ph_am_mp3) {
            this.ph_am_mp3 = ph_am_mp3;
        }

        public String getPh_tts_mp3() {
            return ph_tts_mp3;
        }

        public void setPh_tts_mp3(String ph_tts_mp3) {
            this.ph_tts_mp3 = ph_tts_mp3;
        }

        public List<String> getWord_mean() {
            return word_mean;
        }

        public void setWord_mean(List<String> word_mean) {
            this.word_mean = word_mean;
        }
    }
}
