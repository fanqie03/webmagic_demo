package cn.skiner.crawler.pageprocessor;

import cn.skiner.crawler.pipeline.ExcelPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * @创建人 skiner
 * @创建时间 2018/8/3
 * @描述
 */
public class DiYiBaoMuPageProcessor implements PageProcessor, Crawler {

    Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    public void process(Page page) {
//        page.putField("html",page.getHtml());
//        page.putField("works",page.getHtml().xpath("//div[@class='hover media cfix']").all());
////        page.putField("");
////        page.putField();
//        page.putField("personLink",page.getHtml().links().regex("http://www.diyibaomu.com/[a-z]*/information-id-[0-9]*.html").all());
//        if(page)
        page.putField("url",page.getUrl());
        Html h = page.getHtml();
        if(page.getUrl().toString().contains("category-catid")){
            //列表页面
            page.addTargetRequests(page.getHtml().links().regex("http://www.diyibaomu.com/[a-z]*/information-id-[0-9]*.html").all());
//            page.addTargetRequests(h.links().regex("http://www.diyibaomu.com/category-catid-265-page-[0-9]*.html").all());
            page.setSkip(true);
        }else{
            //worker详情页面
            page.putField("标题",h.xpath("/html/body/div[9]/div[4]/div[1]/div[1]/div[1]/ul/div[1]/text()"));
            page.putField("区域",h.xpath("/html/body/div[9]/div[4]/div[1]/div[1]/div[3]/div[1]/ul/li[1]/text()"));
            page.putField("工资要求",h.xpath("/html/body/div[9]/div[4]/div[1]/div[1]/div[3]/div[1]/ul/li[2]/text()"));
            page.putField("是否中介",h.xpath("/html/body/div[9]/div[4]/div[1]/div[1]/div[3]/div[1]/ul/li[2]/text()"));
            page.putField("年龄大小",h.xpath("/html/body/div[9]/div[4]/div[1]/div[1]/div[3]/div[1]/ul/li[4]/text()"));
            page.putField("文化程度",h.xpath("/html/body/div[9]/div[4]/div[1]/div[1]/div[3]/div[1]/ul/li[5]/text()"));
            page.putField("工作经验",h.xpath("/html/body/div[9]/div[4]/div[1]/div[1]/div[3]/div[1]/ul/li[6]/text()"));
            page.putField("发布用户",h.xpath("/html/body/div[9]/div[4]/div[1]/div[1]/div[3]/div[2]/ul/li[1]/a/text()"));
            page.putField("发布用户连接",h.xpath("/html/body/div[9]/div[4]/div[1]/div[1]/div[3]/div[2]/ul/li[1]/a").links());
            page.putField("联系人",h.xpath("/html/body/div[9]/div[4]/div[1]/div[1]/div[3]/div[2]/ul/li[2]/text()"));
            page.putField("联系电话",h.xpath("/html/body/div[9]/div[4]/div[1]/div[1]/div[3]/div[2]/ul/li[3]/font/text()"));
            page.putField("QQ号码",h.xpath("/html/body/div[9]/div[4]/div[1]/div[1]/div[3]/div[2]/ul/li[4]/text()"));
            page.putField("信息详情",h.xpath("/html/body/div[9]/div[4]/div[1]/div[1]/div[7]/div[1]/p"));
            page.putField("发布时间",h.xpath("/html/body/div[9]/div[4]/div[1]/div[1]/div[1]/ul/div[3]/span[2]/text()").replace("发布， 次浏览",""));
//            page.putField("浏览次数",h.xpath("//*[@id=\"hit\"]/text()"));
        }
    }


    public Site getSite() {
        return site;
    }

    public static void main(String[] args){
        DiYiBaoMuPageProcessor pageProcessor = new DiYiBaoMuPageProcessor();
        pageProcessor.crawl();
    }

    public void crawl(){
        Spider.create(new DiYiBaoMuPageProcessor())
                .addUrl("http://www.diyibaomu.com/category-catid-265.html")
                .addUrl("http://www.diyibaomu.com/category-catid-265-page-2.html")
                .addUrl("http://www.diyibaomu.com/category-catid-265-page-3.html")
                .addUrl("http://www.diyibaomu.com/category-catid-265-page-4.html")
                .addUrl("http://www.diyibaomu.com/category-catid-265-page-5.html")
                .addUrl("http://www.diyibaomu.com/category-catid-265-page-6.html")
//                .addUrl("http://www.diyibaomu.com/shanghai/information-id-2378.html")
                .addPipeline(new ExcelPipeline("D:/data.xls"))
                .thread(5)

                .run();
        System.out.println("---> 结束");

        ExcelPipeline.flush();
    }
}
