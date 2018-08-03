package cn.skiner.crawler.pageprocessor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @创建人 skiner
 * @创建时间 2018/7/19
 * @描述
 */
public class BiQuGePageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    public void process(Page page) {
        page.putField("page",page.getHtml());
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args){
        Spider.create(new BiQuGePageProcessor())
                .addUrl("http://www.biquge.com.tw/14_14055/")
                .thread(1)
                .run();
    }
}
