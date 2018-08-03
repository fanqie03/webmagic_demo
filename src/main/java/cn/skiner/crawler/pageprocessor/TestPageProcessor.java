package cn.skiner.crawler.pageprocessor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @创建人 skiner
 * @创建时间 2018/8/3
 * @描述
 */
public class TestPageProcessor implements PageProcessor {
    Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    public void process(Page page) {
        page.putField("html",page.getHtml());
        page.putField("works",page.getHtml().xpath("//div[@class='hover media cfix']").all());
//        page.putField("");
//        page.putField();
        page.putField("personLink",page.getHtml().links().regex("http://www.diyibaomu.com/[a-z]*/information-id-[0-9]*.html").all());
//        if(page)
        page.putField("url",page.getUrl());
    }


    public Site getSite() {
        return site;
    }

    public static void main(String[] args){
        Spider.create(new TestPageProcessor())
//                .addUrl("http://www.diyibaomu.com/category-catid-265.html")
//                .addUrl("http://www.diyibaomu.com/category-catid-265-page-2.html")
//                .addUrl("http://www.diyibaomu.com/category-catid-265-page-3.html")
//                .addUrl("http://www.diyibaomu.com/category-catid-265-page-4.html")
//                .addUrl("http://www.diyibaomu.com/category-catid-265-page-5.html")
//                .addUrl("http://www.diyibaomu.com/category-catid-265-page-6.html")
                .addUrl("http://www.diyibaomu.com/shanghai/information-id-2378.html")
                .thread(1)
                .run();
    }
}
