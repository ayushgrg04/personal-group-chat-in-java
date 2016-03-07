
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ayushgrg
 */
public class grpchatwindow extends javax.swing.JFrame {

    /**
     * Creates new form grpchatwindow
     */
    public grpchatwindow() {
        initComponents();
        grpMemIp = new HashMap<>();
        grpMemPort = new HashMap<>();
        grpmem = new ArrayList<>();
        message.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e)
            {
              if (e.getKeyCode() == KeyEvent.VK_ENTER)
              {
                  String msg = message.getText().toString();
                  if(msg.equals("") || msg.equals(null)){
                      JOptionPane.showMessageDialog(null, "Enter message first");
                  }
                  else{
                      sendMessage(msg);
                      
                  }
              }
            }
        });
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        msgwindow = new javax.swing.JTextArea();
        hd = new javax.swing.JLabel();
        message = new javax.swing.JTextField();
        send = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        msgwindow.setEditable(false);
        msgwindow.setColumns(20);
        msgwindow.setRows(5);
        jScrollPane1.setViewportView(msgwindow);

        hd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hd.setText("CONNECTED WITH: ");

        message.setToolTipText("Type message to send");
        message.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        send.setText("SEND ");
        send.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(send, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(hd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hd, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(message, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
        // send
        String msg = message.getText().toString();
        if(msg.equals("") || msg.equals(null)){
            JOptionPane.showMessageDialog(this, "Enter message first");
        }
        else{
            sendMessage(msg);

        }
    }//GEN-LAST:event_sendActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(grpchatwindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(grpchatwindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(grpchatwindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(grpchatwindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new grpchatwindow().setVisible(true);
            }
        });
    }
    
    void sendMessage(String str){
        String tt = client+": "+str;
        String m = grpname+"#"+client+": "+str;
        msgwindow.setSelectedTextColor(Color.red);
        msgwindow.append(tt+"\n");
        msgwindow.setSelectedTextColor(Color.BLACK);
        message.setText("");
        for(String mem : grpmem){
            String ipaddress = grpMemIp.get(mem).toString();
            String port = grpMemPort.get(mem).toString();
            System.out.println("grp "+ ipaddress + " "+ port );
            try {
                    Socket s = new Socket(ipaddress,Integer.parseInt(port));
                    dout = new DataOutputStream(s.getOutputStream());
                    dout.writeUTF(m);
                    System.out.println("msg sent");
                    din = new DataInputStream(s.getInputStream());
                    String read = din.readUTF();
                    if(read.equals("Sent")){
                        
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Some error occured!!!");
                    }
                    s.close();

                } catch (IOException ex) {
                    msgwindow.append(mem+" has disconnected.\n");
                    grpmem.remove(mem);
                //    System.exit(0);
                    Logger.getLogger(clientmain.class.getName()).log(Level.SEVERE, null, ex);

                }
        }
    
        
    }
    
    
    
    
    void setMessage(String name, String msg){
        msgwindow.append(name+": "+msg+"\n");
    }
    
    public void setgrpName(String grpnm){
        grpname = grpnm;
    }
    
    public void setClientName(String cl){
        client = cl;
    }
    
    public void setgrpMem(ArrayList<String> arl){
        grpmem = arl;
    }
    
    public void setgrpMemIP(HashMap<String, String> hm1){
        grpMemIp = hm1;
    }
    
    public void setgrpMemPort(HashMap<String, String> hm1){
        grpMemPort = hm1;
    }
    

    public String grpname = "";
    private String client = "";
    ArrayList<String> grpmem;
    HashMap<String, String> grpMemIp;
    HashMap<String, String> grpMemPort;
    ServerSocket msgSocket;
    private DataOutputStream dout;
    private DataInputStream din;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel hd;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField message;
    private javax.swing.JTextArea msgwindow;
    private javax.swing.JButton send;
    // End of variables declaration//GEN-END:variables
}