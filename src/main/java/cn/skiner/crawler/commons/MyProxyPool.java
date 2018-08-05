package cn.skiner.crawler.commons;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.ProxyProvider;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 skiner
 * @创建时间 2018/8/4
 * @描述
 */
public class MyProxyPool implements ProxyProvider {
    @Override
    public void returnProxy(Proxy proxy, Page page, Task task) {
        List<Proxy> proxies = new ArrayList<>();

        SimpleProxyProvider proxyProvider = new SimpleProxyProvider(proxies);
    }

    @Override
    public Proxy getProxy(Task task) {
        return null;
    }
}
