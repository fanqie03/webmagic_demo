package cn.skiner.crawler.pageprocessor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.Calendar;
import java.util.Random;

/**
 * @创建人 skiner
 * @创建时间 2018/8/3
 * @描述
 */
public class WuYouBaoMuPageProcessor implements PageProcessor ,Crawler{

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    private Random random = new Random();

    @Override
    public void process(Page page) {
        Html h = page.getHtml();
        page.putField("保姆编号",h.xpath("//*[@id=\"lblID\"]/text()"));
        page.putField("客户评价",random.nextInt(3)+3);
        page.putField("所在地区",h.xpath("//*[@id=\"lblSuozaidi\"]/text()"));
        page.putField("期望工资",h.xpath("//*[@id=\"lblGongzi\"]/text()"));

        page.putField("自我介绍", h.xpath("//*[@id=\"lblZwjs\"]/text()"));
        page.putField("个人特长",h.xpath("//*[@id=\"lblGrtc\"]/text()"));
//        page.putField("预约联系电话",h.xpath("//*[@id=\"randomNum\"]/text()"));

        page.putField("图片地址",h.xpath("//*[@id=\"imgZhaopian\"]/@src"));

        int age = Integer.parseInt(h.xpath("//*[@id=\"lblNl\"]/text()").replace("岁","").toString());

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,-age);
        int year = calendar.get(Calendar.YEAR);
        System.out.println(year);
        int mouth = random.nextInt(12)+1;
        int day = random.nextInt(28)+1;

        String date = ""+year+(mouth<10?"0"+mouth:mouth)+(day<10?"0"+day:day);

        page.putField("身份证号",h.xpath("//*[@id=\"lblSfzh\"]/text()").replace("\\*{8}",date));
        page.putField("手机号码",h.xpath("//*[@id=\"lblLxfs\"]/text()").replace("\\*{4}",String.valueOf(random.nextInt(8000)+1000)));
//        page.putField("类型",h.xpath("//*[@id=\"lblLx\"]/text()"));
        page.putField("籍贯",h.xpath("//*[@id=\"lblJg\"]/text()"));
        page.putField("民族",h.xpath("//*[@id=\"lblMz\"]/text()"));
        page.putField("婚姻", h.xpath("//*[@id=\"lblHy\"]/text()").toString().equals("已婚") ? 1 : 0);
        page.putField("当前城市",h.xpath("//*[@id=\"lblDqjzcs\"]/text()"));
        page.putField("年龄",age);
        page.putField("属相",h.xpath("//*[@id=\"lblSx\"]/text()"));
        page.putField("星座",h.xpath("//*[@id=\"lblXz\"]/text()"));
        page.putField("最高学历",h.xpath("//*[@id=\"lblZgxl\"]/text()"));
        page.putField("目前状态",random.nextInt(2));

        page.putField("工作能力",h.xpath("//*[@id=\"lblGznl\"]/text()"));
        page.putField("工作经验",h.xpath("//*[@id=\"lblGzjy\"]/text()"));
        page.putField("目标城市",h.xpath("//*[@id=\"lblGzchengshi\"]/text()"));
        page.putField("住址",h.xpath("//*[@id=\"lblDqjzdz\"]/text()"));
        page.putField("每月休息",h.xpath("//*[@id=\"lblMyxxts\"]/text()"));

    }

    @Override
    public Site getSite() {
        return site;
    }

    public void craw(){
        Spider.create(new WuYouBaoMuPageProcessor())
//                .addPipeline()
                .addUrl("http://www.51baomu.cn/baomu-521107.html")
                .thread(1)
                .run();
    }

    public static void main(String[] args){
        WuYouBaoMuPageProcessor pageProcessor = new WuYouBaoMuPageProcessor();
        pageProcessor.craw();
    }

}
