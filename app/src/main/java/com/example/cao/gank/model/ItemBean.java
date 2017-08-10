package com.example.cao.gank.model;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */

public class ItemBean {


    /**
     * error : false
     * results : [{"_id":"598029be421aa90c9203d448","createdAt":"2017-08-01T15:11:58.619Z","desc":"Storybook 3.2 引入 Vue.js 支持","publishedAt":"2017-08-03T12:08:07.258Z","source":"chrome","type":"Android","url":"https://zhuanlan.zhihu.com/p/28239408","used":true,"who":"王下邀月熊"},{"_id":"59809b0d421aa90ca3bb6bc9","createdAt":"2017-08-01T23:15:25.145Z","desc":"【66熟肉】新一代劳斯莱斯幻影的十大亮点，你不知道就没法装逼了","publishedAt":"2017-08-03T12:08:07.258Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av12824384/","used":true,"who":"LHF"},{"_id":"59815d0b421aa90c9203d45c","createdAt":"2017-08-02T13:03:07.497Z","desc":"从零开始打造一个VR视频播放器","images":["http://img.gank.io/5feda1ac-7412-4d8c-bc33-dd8102d24c6a"],"publishedAt":"2017-08-03T12:08:07.258Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/c7a3b1acb8b1","used":true,"who":"麦田哥"},{"_id":"598176ac421aa90ca3bb6bd7","createdAt":"2017-08-02T14:52:28.638Z","desc":"Android 插件：统计资源文件中每个标签被引用的次数","publishedAt":"2017-08-03T12:08:07.258Z","source":"web","type":"Android","url":"https://github.com/niorgai/Android-Resource-Usage-Count","used":true,"who":"drakeet"},{"_id":"59823dbe421aa90ca209c54b","createdAt":"2017-08-03T05:01:50.665Z","desc":"Easy More Operation ViewController in swift.","publishedAt":"2017-08-03T12:08:07.258Z","source":"web","type":"iOS","url":"https://github.com/yeziahehe/YFMoreViewController","used":true,"who":"Fan Ye"},{"_id":"59826564421aa90ca3bb6bda","createdAt":"2017-08-03T07:51:00.249Z","desc":"8-3","publishedAt":"2017-08-03T12:08:07.258Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034gy1fi678xgq1ij20pa0vlgo4.jpg","used":true,"who":"代码家"},{"_id":"59826b11421aa97de5c7ca13","createdAt":"2017-08-03T08:15:13.671Z","desc":"Java 实现的 DHT 协议，其实就是  BitTorrent，可以轻松在 Android 上实现一个 P2P 下载了。","images":["http://img.gank.io/7f51af04-cf74-448e-ab85-1ddbcf70d22b"],"publishedAt":"2017-08-03T12:08:07.258Z","source":"chrome","type":"Android","url":"https://github.com/atomashpolskiy/bt","used":true,"who":"代码家"},{"_id":"59826b73421aa97de5c7ca15","createdAt":"2017-08-03T08:16:51.545Z","desc":"炫彩风格的字符串，拿去 Show off 吧。","images":["http://img.gank.io/9d4ab866-7b3c-4966-9961-58b05fc9dcf5"],"publishedAt":"2017-08-03T12:08:07.258Z","source":"chrome","type":"前端","url":"https://github.com/bokub/gradient-string","used":true,"who":"代码家"},{"_id":"59826c07421aa90ca209c54c","createdAt":"2017-08-03T08:19:19.257Z","desc":"AlloyTeam 开源的一款前端移动图片剪裁组件。","images":["http://img.gank.io/269c9cc0-aa40-4437-a0cc-21a40c4dc524"],"publishedAt":"2017-08-03T12:08:07.258Z","source":"chrome","type":"前端","url":"https://github.com/AlloyTeam/AlloyCrop","used":true,"who":"代码家"},{"_id":"59826c71421aa90ca209c54e","createdAt":"2017-08-03T08:21:05.222Z","desc":"一款实现的很漂亮的卡片式搜索效果","images":["http://img.gank.io/fd930531-4606-49b0-9130-b390e1436f9e"],"publishedAt":"2017-08-03T12:08:07.258Z","source":"chrome","type":"Android","url":"https://github.com/limuyang2/CardSearchView","used":true,"who":"Jude"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 598029be421aa90c9203d448
         * createdAt : 2017-08-01T15:11:58.619Z
         * desc : Storybook 3.2 引入 Vue.js 支持
         * publishedAt : 2017-08-03T12:08:07.258Z
         * source : chrome
         * type : Android
         * url : https://zhuanlan.zhihu.com/p/28239408
         * used : true
         * who : 王下邀月熊
         * images : ["http://img.gank.io/5feda1ac-7412-4d8c-bc33-dd8102d24c6a"]
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
