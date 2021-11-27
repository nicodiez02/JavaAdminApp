/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
/**
 * @web https://www.jc-mouse.net
 * @author Mouse
 */
public class HeaderCellRenderer implements TableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        JComponent jcomponent = null;

        if(value instanceof String ) {
            jcomponent = new JLabel((String) " " + value);
            ((JLabel)jcomponent).setHorizontalAlignment(SwingConstants.LEFT );
            ((JLabel)jcomponent).setSize(30, jcomponent.getWidth() );
            ((JLabel)jcomponent).setPreferredSize( new Dimension(6, jcomponent.getWidth()) );
        } 

 
        jcomponent.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(255, 255, 255)));
        jcomponent.setOpaque(true);
      
        jcomponent.setBackground( new Color(65,65,65) );
        jcomponent.setToolTipText("Colum No. "+(column+1));
        jcomponent.setForeground(Color.white);

        return jcomponent;
    }

}