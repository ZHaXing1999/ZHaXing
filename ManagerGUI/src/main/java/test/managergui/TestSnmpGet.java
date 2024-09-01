package test.managergui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import junit.framework.TestFailure;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;

import static test.managergui.SnmpData.snmpAsynWalk;

/**
 * @filename SnmpGet.java
 * @author code by jianghuiwen
 * @mail jianghuiwen2012@163.com
 *
 * 下午2:21:53
 */
public class TestSnmpGet {
    @FXML
    public TextArea textArea;
    @FXML
    public TextField ip;
    @FXML
    public TextField port;
    @FXML
    public ChoiceBox DEFAULT_PROTOCOL;
    @FXML
    public ChoiceBox oid;
    @FXML
    public ChoiceBox community;
    @FXML
    public ChoiceBox request;
    @FXML
    public TextField timeout;
    @FXML
    public TextArea IPaddresses;
    Map<String,String> oidMap=new HashMap<>();
    @FXML
    public void initialize() {
        oidMap.put("系统基本信息","1.3.6.1.2.1.1.1.0");
        oidMap.put("监控时间","1.3.6.1.2.1.1.3.0");
        oidMap.put("获取机器名","1.3.6.1.2.1.1.5.0");
        oidMap.put("机器提供的服务","1.3.6.1.2.1.1.7.0");
        oidMap.put("网络接口的数目","1.3.6.1.2.1.1.7.0");
        DEFAULT_PROTOCOL.getItems().clear();
        DEFAULT_PROTOCOL.getItems().addAll("UDP","TCP");
        oid.getItems().clear();
        oid.getItems().addAll("系统基本信息","监控时间","获取机器名","机器提供的服务","网络接口的数目");
        community.getItems().clear();
        community.getItems().addAll("public","private");
    }
    @Test
    public void testGet()
    {
        String ip = "10.255.7.28";
        String community = "public";
        String oidval = "1.3.6.1.2.1.1.6.0";
        SnmpData.snmpGet(ip, community, oidval);
    }
    @Test
    public void test2(){
        IPaddresses.clear();
        try {
            Enumeration<NetworkInterface> faces = NetworkInterface.getNetworkInterfaces();
 //遍历网络地址
            while (faces.hasMoreElements()) {
                NetworkInterface face = faces.nextElement();
                if (face.isLoopback() || face.isVirtual() || !face.isUp()) {
                    continue;
                }
                System.out.print("网络接口名：" + face.getDisplayName() + "，地址：");
                Enumeration<InetAddress> address = face.getInetAddresses();
//遍历网络地址
                while (address.hasMoreElements()) {
                    InetAddress addr = address.nextElement();
                    if (!addr.isLoopbackAddress() && addr.isSiteLocalAddress() && !addr.isAnyLocalAddress()) {

                        System.out.print(addr.getHostAddress() + " ");
                        IPaddresses.appendText( face.getDisplayName() + "，地址："+addr.getHostAddress() + " \n");
                    }
                }

                System.out.println("");
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void setIPaddresses(ActionEvent actionEvent){
        test2();
    }
    @FXML
    public void snmpView(ActionEvent actionEvent) {
        textArea.setText(String.valueOf(SnmpData.snmpGet(ip.getText(),(String) community.getValue(),oidMap.get((String) oid.getValue()))));
    }
    @Test
    public void testGetList()
    {
        String ip = "127.0.0.1";
        String community = "public";
        List<String> oidList=new ArrayList<String>();
        oidList.add("1.3.6.1.2.1.1.5.0");
        oidList.add("1.3.6.1.2.1.1.7.0");
        SnmpData.snmpGetList(ip, community, oidList);
    }
    @Test
    public void testGetAsyList()
    {
        String ip = "127.0.0.1";
        String community = "public";
        List<String> oidList=new ArrayList<String>();
        oidList.add("1.3.6.1.2.1");
        oidList.add("1.3.6.1.2.12");
        SnmpData.snmpAsynGetList(ip, community, oidList);
        System.out.println("i am first!");
    }

    @Test
    public void testWalk()
    {
        String ip = "127.0.0.1";
        String community = "public";
        String targetOid = "1.3.6.1.2.1.1.5.0";
        SnmpData.snmpWalk(ip, community, targetOid);
    }

    @Test
    public void testAsyWalk()
    {
        String ip = "127.0.0.1";
        String community = "public";
// 异步采集数据
        snmpAsynWalk(ip, community, "1.3.6.1.2.1.25.4.2.1.2");
    }
    @Test
    public void testSetPDU() throws Exception
    {
        String ip = "127.0.0.1";
        String community = "public";
        SnmpData.setPDU(ip, community, "1.3.6.1.2.1.1.6.0","zhaxing");
    }
    @Test
    public void testVersion()
    {
        System.out.println(org.snmp4j.version.VersionInfo.getVersion());
    }

}