package aabb;

import org.junit.Test;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

//实现流量监控
public class TrafficMonitoring {
    public void getTrafficMonitoring(int time) throws IOException {
        //获取oid
        MySnmp mySnmp = new MySnmp();
        //输入流
        FileOutputStream fos = null;
        OutputStreamWriter writer = null;
        BufferedWriter bf = null;
        //根据oid获取结果
        GetMessage getMessage = new GetMessage();
        try {
            //表示内容的追加
            fos = new FileOutputStream("logFile.log",true);
            writer = new OutputStreamWriter(fos);
            bf = new BufferedWriter(writer);
            bf.write("====================切割线==========================\n");
            //格式化当前时间
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int i = 0 ; i<time ; i++) {
                Date date = new Date(System.currentTimeMillis());
                //获得当前时间
                String nowTime = formatter.format(date);
                //活得当前流量
                String If_In_Octets = getMessage.getMessageByIpAndOid("127.0.0.1", mySnmp.If_In_Octets);
                String If_Out_Octets = getMessage.getMessageByIpAndOid("127.0.0.1", mySnmp.If_Out_Octets);
                bf.write(nowTime+"-"+"If_In_Octets:"+If_In_Octets+"bytes,If_Out_Octets:"+If_Out_Octets+"bytes\n");
                Thread.sleep(1000);
            }
            bf.flush();
            System.out.println(time+"秒内的流量监控完毕,请查看日志文件");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (fos != null) {
                fos.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (bf != null) {
                bf.close();
            }
        }
    }
}
