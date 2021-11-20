/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Nico
 */
public class Colores extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col) {

        super.getTableCellRendererComponent(table,value,Selected,hasFocus,row,col);
        
        if(table.getValueAt(row, 10).toString().equals("Si")){
          setBackground(new Color(168, 240, 161));
                     
        }else{
             setBackground(new Color(255,108,108)); 
        }
        
      
        
       return this;
    }

}
