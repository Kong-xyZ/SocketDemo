package udp;

import java.io.IOException;
import java.net.*;

/**
 * UDO搜索者，用于搜索服务支持方
 */
public class UDPSearcher {
    public static void main(String[] args) throws IOException {
        System.out.println("udp.UDPSearcher Started.");

        // 作为搜索方，让系统自动分配端口
        DatagramSocket ds = new DatagramSocket();

        // 构建一份回送数据
        String requestData = "Hello World!";
        byte[] requestDataBytes = requestData.getBytes();
        // 直接根据发送者构建一份回送信息
        DatagramPacket responsePacket = new DatagramPacket(requestDataBytes,
                requestDataBytes.length);
        // 本机20000端口
        responsePacket.setAddress(InetAddress.getLocalHost());
        responsePacket.setPort(20000);

        // 发送
        ds.send(responsePacket);

        // 构建接收实体
        final byte[] buf = new byte[512];
        DatagramPacket receivePack = new DatagramPacket(buf, buf.length);

        // 接收
        ds.receive(receivePack);

        // 打印接收到的信息与发送者的信息
        // 发送者的IP地址
        String ip = receivePack.getAddress().getHostAddress();
        int port = receivePack.getPort();
        int dataLen = receivePack.getLength();
        String data = new String(receivePack.getData(), 0, dataLen);
        System.out.println("udp.UDPSearcher receive from ip:" + ip
                + "\tport: " + port + "\tdata: " +data);

        // 完成
        System.out.println("udp.UDPSearcher Finished.");
        ds.close();
    }
}
