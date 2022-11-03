package com.nokia;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketCheck {
    private ServerSocket server;

    public SocketCheck() {
        try {
                this.server = new ServerSocket(16788, 1, InetAddress.getByName("localhost"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SocketAgain() {
        try {
            this.server = new ServerSocket(16788, 1, InetAddress.getByName("localhost"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listen() throws Exception {
        String data = null;
        Socket client = this.server.accept();
        String clientAddress = client.getInetAddress().getHostAddress();
        System.out.println("\r\nNew connection from " + clientAddress);
    }

    public InetAddress getSocketAddress() {
        return this.server.getInetAddress();
    }

    public int getPort() {
        return this.server.getLocalPort();
    }

    public void checkSocketClose() {
        if(this.server.isBound())
            System.out.println("Socket available");
        else
            System.out.println("Socket do not appers");
    }

    public static void main(String[] args) throws Exception {
        SocketCheck socket = new SocketCheck();

        System.out.println("\r\nRunning Server: " +
                            "Host=" + socket.getSocketAddress().getHostAddress() +
                            " Port=" + socket.getPort());
        socket.checkSocketClose();
        //socket.SocketAgain();
        socket.listen();
    }
}
