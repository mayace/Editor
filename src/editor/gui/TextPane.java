/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package editor.gui;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author cefit
 */
public class TextPane extends JScrollPane {

    /**
     * Creates new form TextPane
     */
    public TextPane() {
        initComponents();
    }
    
    public TextPane(String text){
        initComponents();
        txtEditor.setText(text);
    }

    public JTextArea getTxtEditor() {
        return txtEditor;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtEditor = new javax.swing.JTextArea();

        txtEditor.setColumns(20);
        txtEditor.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        txtEditor.setRows(5);
        txtEditor.setTabSize(4);
        setViewportView(txtEditor);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea txtEditor;
    // End of variables declaration//GEN-END:variables
}
