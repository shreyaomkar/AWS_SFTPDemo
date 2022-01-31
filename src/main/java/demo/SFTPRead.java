package demo;

public class SFTPRead 
{
	public static void main(String[] args) 
	{
		JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession("user", "127.0.0.1", 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("password");
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;

            InputStream stream = sftpChannel.get("/usr/home/testfile.txt");
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }

            } catch (IOException io) {
                System.out.println("Exception occurred during reading file from SFTP server due to " + io.getMessage());
                io.getMessage();

            } catch (Exception e) {
                System.out.println("Exception occurred during reading file from SFTP server due to " + e.getMessage());
                e.getMessage();

            }

            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        }
    }

	}

}
