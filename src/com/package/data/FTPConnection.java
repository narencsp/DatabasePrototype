package data;

import java.io.IOException;
import java.io.InputStream;

import com.jcraft.jsch.*;

public class FTPConnection {
    private final String user;
    private final String pass;
    private String host = "timberlea.cs.dal.ca";
    private int port = 22;
    private JSch jsch;
    private Session session;
    private Channel channel;
    private ChannelSftp sftpChannel;

    public FTPConnection(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void open() {
        jsch = new JSch();
        session = null;
        try {
            session = jsch.getSession(user, host, port);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(pass);
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            sftpChannel = (ChannelSftp) channel;
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    public void copyFile(String source, String destination) {
        try {
            sftpChannel.get(source, destination);
        } catch (SftpException e) {
            e.printStackTrace();
        }
    }

    public InputStream getFileInputStream(String filePath) {
        InputStream inputStream = null;
        try {
            inputStream = sftpChannel.get(filePath);
        } catch (SftpException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    public void close() {
        sftpChannel.exit();
        session.disconnect();
    }

}
