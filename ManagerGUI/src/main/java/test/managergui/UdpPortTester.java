package test.managergui;
import java.net.DatagramSocket;
import java.net.SocketException;
public class UdpPortTester{
    public static void main(String[]args) {
        int port = 161;//要测试UDP端口号
        if (isUdpPortOpen(port))
            System.out.println("UDP端口" + port + "已打开");
        else
            System.out.println("UDP端口" + port + "未打开");
    }
    public static boolean isUdpPortOpen (int port){
        try (DatagramSocket socket= new DatagramSocket(port)) {
            return true;
        } catch(SocketException e){
            return false;
        }
    }
}
