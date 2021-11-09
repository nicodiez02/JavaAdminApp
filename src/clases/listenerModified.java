/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Nico
 */
public class listenerModified implements ActionListener {

    private int parameter;

    public listenerModified(int m) {
        parameter = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (parameter == 0) {
            //haz lo que sea
        }
    }
}
