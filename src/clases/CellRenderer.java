/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
/**
 * @web https://www.jc-mouse.net
 * @author Mouse
 */
public class CellRenderer  extends DefaultTableCellRenderer {

    private Font normal = new Font( "Arial",Font.PLAIN ,12 );
    private Color color1 = new Color(255,255,255);
    private Color color2 = new Color(249,249,249);

    @Override
    public Component getTableCellRendererComponent ( JTable table, Object value, boolean selected, boolean focused, int row, int column )
    {    
        Color colorFondo = null;
        Color colorFondoPorDefecto=new Color( 192, 192, 192);
        Color colorFondoSeleccion=new Color( 140, 140 , 140);
        
        if (selected) {                
            this.setBackground(colorFondoPorDefecto );   
        }
        else
        {
            this.setBackground(Color.white);
        }
        
      if (focused) {
       colorFondo=colorFondoSeleccion;
      }else{
       colorFondo= colorFondoPorDefecto;
      }
            this.setHorizontalAlignment( JLabel.LEFT );
            this.setText( (String) value );
            //this.setForeground( (selected)? new Color(255,255,255) :new Color(0,0,0) );   
            //this.setForeground( (selected)? new Color(255,255,255) :new Color(32,117,32) );
            this.setBackground( (selected)? colorFondo :Color.WHITE); 
            this.setFont(normal);   
            //this.setFont(bold);
            return this;
        
  
    }

}