package cn.skiner.crawler.model;

import cn.skiner.crawler.commons.MyHttpClientDownloader;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

@TargetUrl("https://github.com/\\w+/\\w+")
@HelpUrl("https://github.com/\\w+")
public class GithubRepo{

    @ExtractBy("//h1[@class='entry-title public']/strong/a/text()")
    private String name;

    @ExtractByUrl("https://github\\.com/(\\w+)/.*")
    private String author;

    @ExtractBy("//div[@id='readme']/tidyText()")
    private String readme;

    public static void main(String[] args){
        OOSpider.create(Site.me().setSleepTime(1000)
                ,new ConsolePageModelPipeline(),GithubRepo.class)
                .addUrl("https://github.com/code4craft").setDownloader(new MyHttpClientDownloader()).thread(5).run();
    }

}