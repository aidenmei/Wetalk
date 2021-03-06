package wetalk.common;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.Font;


public class FriendLabel extends JLabel implements ListCellRenderer {

    private Border lineBorder = BorderFactory.createLineBorder(Color.blue, 1);
    private Border emptyBorder = BorderFactory.createEmptyBorder(2, 2, 2, 2);

    public FriendLabel() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected,
                                                  boolean cellHasFocus) {
        String s = value.toString();
        int beginIndex = s.indexOf("*src/head/");
        
        
        String picURL = s.substring(beginIndex + 1, s.indexOf("^"));
        
        String offLinePicURL = picURL.substring(0, picURL.indexOf("-") + 1) +
                               "2" +
                               picURL.substring(picURL.indexOf("-") + 2,
                                                picURL.length());
        
        int status = Integer.parseInt(s.substring(0, 1));
        this.setText(s.substring(1, beginIndex));
        this.setIcon(new ImageIcon(picURL));
        if (isSelected) {
            this.setBackground(list.getSelectionBackground());
            this.setForeground(list.getSelectionForeground());
        } else {
            this.setBackground(list.getBackground());
            this.setForeground(list.getForeground());
        }
        if (cellHasFocus) {
            this.setBorder(lineBorder);
        } else {
            this.setBorder(emptyBorder);
            this.setForeground(list.getForeground());
        }
        
        if (status == 0) {
            this.setIcon(new ImageIcon(offLinePicURL));
        } else if (status == 1) {
            this.setIcon(new ImageIcon(picURL));
        }
        this.setEnabled(list.isEnabled());
        //this.setFont();
        this.setOpaque(true);
        return this;
    }

    private void jbInit() throws Exception {
        this.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, 15));
    }
}