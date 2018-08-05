package cn.skiner.crawler.model;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * @创建人 skiner
 * @创建时间 2018/8/3
 * @描述
 */
//@TargetUrl(value = "",sourceRegion = )
public class BabyNurseModel {

    @ExtractBy("//*[@id=\"lblID\"]")
    private String id;
    private String star;
    private String region;
    private String introduction;
    private String specialty;
    private String salary;
    private String reserveTel;
//    private
    public static void main(String[] args){
        OOSpider.create(Site.me().setSleepTime(1000),new ConsolePageModelPipeline(),BabyNurseModel.class)
                .addUrl("http://www.51baomu.cn/baomu-521107.html")
                .thread(5).run();
    }
}
