import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FtpTester {
    static ExecutorService executorService;

    public static void main(String[] args) {
        String letters = "abcdefghijklmnopqrstuvxyz";

        if (args.length < 7) {
            System.out.println("Needed more paramaters");
            System.out.println();
            System.out.println("Usage:");
            System.out.println();
            System.out.println(
                "java -jar target/ftptester-1.0-SNAPSHOT-jar-with-dependencies.jar test " +
                "[threadnum] [ipaddr] [username] [password] [localdirectory] [remote directory]"
            );
            System.exit(0);
        }
        int threads = Integer.parseInt(args[1]);
        String ip = args[2];
        String username= args[3];
        String password= args[4];
        String localPrefix= args[5];
        String remotePrefix= args[6];

        executorService = Executors.newFixedThreadPool(threads);
        try {
            for (String s : letters.split("")) {
                executorService.submit(new FtpClientTest(s, ip, username, password, localPrefix, remotePrefix));
            }
            executorService.shutdown();
            executorService.awaitTermination(3, TimeUnit.HOURS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}