package cn.skiner.crawler.pageprocessor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @创建人 skiner
 * @创建时间 2018/8/3
 * @描述
 */
public class WuYouBaoMuPageProcessor implements PageProcessor ,Crawler{

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page) {

    }

    @Override
    public Site getSite() {
        return site;
    }

    public void craw(){

    }

}
