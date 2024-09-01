package aabb;

import org.snmp4j.*;

import org.snmp4j.event.ResponseEvent;

import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;

//用来获取设备信息
public class GetMessage {
    public String getMessageByIpAndOid(String ip,String oid) throws IOException {
        String result = null ;
        // 1. 创建 SNMP 管理器
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString("public"));
        target.setAddress(GenericAddress.parse("udp:"+ip+"/161"));
        target.setRetries(2);
        target.setTimeout(1000);

        TransportMapping<UdpAddress> transport = new DefaultUdpTransportMapping();
        Snmp snmp = new Snmp(transport);
        snmp.listen();

        // 2. 创建 OID
        OID oid1 = new OID(oid);

        // 3. 发送 SNMP 请求并处理响应
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(oid1));
        pdu.setType(PDU.GETNEXT);

        ResponseEvent event = snmp.send(pdu, target);
        PDU response = event.getResponse();

        if (response == null) {
            System.out.println("没有得到响应");
        } else {
            result = String.valueOf(response.get(0).getVariable());
        }

        // 4. 关闭 SNMP 管理器
        snmp.close();
        return result;
    }
}
