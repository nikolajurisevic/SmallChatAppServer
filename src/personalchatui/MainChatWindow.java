/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personalchatui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.scene.layout.Border;
import javax.swing.SwingUtilities;

/**
 *
 * @author PreBoosted
 */
public class MainChatWindow extends javax.swing.JFrame {

    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;
    private String message;
        int xMouse;
        int yMouse;
        
    public MainChatWindow() {
        
        super("SERVER CHAT");
       
        this.setUndecorated(true);
        this.setBackground(new Color(0, 255, 0, 0));
        initComponents();
        
       jScrollPane1.setOpaque(false);
       jScrollPane1.getViewport().setOpaque(false);
       jScrollPane1.getViewport().setBackground(new Color(0,0,0,0));
       jScrollPane1.getViewport().setBorder(null);
       jScrollPane1.setBorder(null);
        userText.addActionListener(
        
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message = (e.getActionCommand());
                sendMessage(message);
                userText.setText("");
                }
            }
        );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        userText = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatWindow = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        kuracBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setSize(new java.awt.Dimension(400, 300));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userText.setForeground(new java.awt.Color(0, 0, 0));
        userText.setBorder(null);
        userText.setOpaque(false);
        userText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userTextActionPerformed(evt);
            }
        });
        jPanel1.add(userText, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 257, 280, 40));

        chatWindow.setColumns(20);
        chatWindow.setRows(5);
        chatWindow.setBorder(null);
        chatWindow.setOpaque(false);
        jScrollPane1.setViewportView(chatWindow);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 360, 190));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/props/sendIcon.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.setRolloverEnabled(true);
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/props/sendIconPressed.png"))); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton1MouseReleased(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 250, 60, 53));

        jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel1MouseDragged(evt);
            }
        });
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 320, 20));

        kuracBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/props/bckGrndTest.png"))); // NOI18N
        jPanel1.add(kuracBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 330));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userTextActionPerformed

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();

        this.setLocation(x - xMouse, y - yMouse-20);
    }//GEN-LAST:event_jLabel1MouseDragged

    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
        
        sendMessage(userText.getText());
        userText.setText("");
        
    }//GEN-LAST:event_jButton1MouseReleased
    //set up and run the server

    public void startRunning() throws IOException {

        try {
            server = new ServerSocket(6790, 100);
            while (true) {
                try {
                    waitForConnection();
                    setupStreams();
                    whileChatting();
                } catch (EOFException eofException) {
                    showMessage("\n Server ended the connection!");
                } finally {
                    closeCrap();
                }
            }
        } catch (IOException ioException) {
          
        }
    }

    //wait for connection , then display connection information
    private void waitForConnection() throws IOException {

        showMessage("Waiting for someone to connect...\n");
        connection = server.accept();
        showMessage("Now connected to" + connection.getInetAddress().getHostName());
    }

    //get stream to send and recieve data
    private void setupStreams() throws IOException {

        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
        showMessage("\n Streams are now set \n");

    }

    //during the chat conversation
    private void whileChatting() throws IOException {
        String message = "You are now connected";
        sendMessage(message);
        ableToType(true);

        do {
            //have a conversation4
            try {
                message = (String) input.readObject();
                showMessage("\n" + message);
            } catch (ClassNotFoundException classNotFoundException) {
                showMessage("\n Idk wtf that user sent");
            }
        } while (!message.equals("CLIENT - END"));

    }

    private void closeCrap() {

        showMessage("\n Closing connections... \n");
        ableToType(false);

        try {
            output.close();
            input.close();
            connection.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    //let user type stuff into their box
    private void ableToType(final boolean b) {

        SwingUtilities.invokeLater(
                new Runnable() {
            public void run() {

                userText.setEditable(b);

            }
        }
        );

    }

    private void sendMessage(String message) {
        try {

            output.writeObject("SERVER - " + message);
            output.flush();
            showMessage("\nSERVER - " + message);
        } catch (IOException ioException) {
            chatWindow.append("\n ERROR: DUDE I CAN'T SEND THAT MESSAGE");
        }
    }

    //updates chatWindow
    private void showMessage(final String text) {

        SwingUtilities.invokeLater(
                new Runnable() {
            public void run() {

                chatWindow.append(text);

            }
        }
        );

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatWindow;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel kuracBackground;
    private javax.swing.JTextField userText;
    // End of variables declaration//GEN-END:variables
}
