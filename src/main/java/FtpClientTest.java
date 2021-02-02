import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

public class FtpClientTest implements Runnable {
    private final String prefix;
    private final String hostname;
    private final String username;
    private final String password;
    private final String localPrefix;
    private final String remotePrefix;

    public FtpClientTest(
        String prefix, String hostname, String username, String password, String localPrefix, String remotePrefix
    ) {
        this.prefix = prefix;
        this.hostname = hostname;
        this.username = username;
        this.password = password;
        this.localPrefix = localPrefix;
        this.remotePrefix = remotePrefix;
    }

    public void run() {
        try {
            FTPClient ftp = new FTPClient();

            ftp.connect(this.hostname, 21);
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                System.out.println("Exception in connecting to FTP Server");
            }

            ftp.login(this.username, this.password);
            ftp.enterLocalPassiveMode();

            for (int i=0; i<10000; i++) {
                ftp.storeFile(remotePrefix + prefix + "-" + i, new FileInputStream(createFile(prefix + "-" + i)));
            }
            ftp.disconnect();
        } catch (Exception e) {
            System.out.println(prefix + " Failed " + e.getMessage());
        }
        System.out.println(prefix + " DONE");
    }

    public File createFile(String name) throws Exception {
        File file = new File(this.localPrefix + name);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.append(name);
        writer.close();
        return file;
    }
}
