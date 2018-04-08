/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalchatui;

import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author PreBoosted
 */
public class PersonalChatUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
      MainChatWindow mcw = new MainChatWindow();
      mcw.setLocationRelativeTo(null);
        mcw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          mcw.setVisible(true);
        mcw.startRunning();
      
    
    }
    
}
