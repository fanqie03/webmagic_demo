package cn.skiner.crwaler;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @创建人 skiner
 * @创建时间 2018/8/3
 * @描述
 */
public class RegexTest {
    @Test
    public void test(){

        System.out.println("\t浦东".replaceAll("^\\s*|\t*",""));
        int age = 53;
//        int year = new Date(System.currentTimeMillis()).getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,-age);
        int year = calendar.get(Calendar.YEAR);
        System.out.println(year);
        Random random = new Random();
        int mouth = random.nextInt(12)+1;
        int day = random.nextInt(28)+1;

        String date = ""+year+(mouth<10?"0"+mouth:mouth)+(day<10?"0"+day:day);
        System.out.println("340311********0087".replaceAll("\\*{8}",date));
    }
}
