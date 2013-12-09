/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor.gui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

/**
 *
 * @author cefit
 */
public class TabPane extends JTabbedPane {

    /**
     * Creates new form TabPane
     */
    public TabPane() {
        initComponents();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filechooser = new javax.swing.JFileChooser();

        filechooser.setMultiSelectionEnabled(true);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser filechooser;
    // End of variables declaration//GEN-END:variables
    private void init() {
        this.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("control W"), "close_tab");
        this.getActionMap().put("close_tab", new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                final JComponent c = (JComponent) getSelectedComponent();
                if (c != null) {
                    eliminar(c);
                }
            }
        });
    }

    private File[] showOpenFileDialog() throws HeadlessException {
        File[] files = {};

        int ret = filechooser.showOpenDialog(this);
        if (ret == JFileChooser.APPROVE_OPTION) {
            files = filechooser.getSelectedFiles();
        }
        return files;
    }

    public void abrir() throws IOException {
        for (File file : showOpenFileDialog()) {
            agregar(file.toPath());
        }

    }

    public void guardar() throws IOException {
        TextPane c = (TextPane) getSelectedComponent();
        int index = getSelectedIndex();
        if (c != null) {
            Path p = mapIndex.get(index);
            final byte[] bytes = c.getTxtEditor().getText().getBytes();
            if (Files.exists(p)) {
                writeFile(p, bytes);
            } else {
                final Path createdFile = showSaveFileDialog(p);
                if (createdFile != null) {
                    writeFile(p, bytes);
                    updateIndex(index, createdFile);
                }
            }
        }
    }
    
    
    public void guardarComo() throws IOException {
        TextPane c = (TextPane) getSelectedComponent();
        int index = getSelectedIndex();
        if (c != null) {
            Path p = mapIndex.get(index);
            final byte[] bytes = c.getTxtEditor().getText().getBytes();

            final Path createdFile = showSaveFileDialog(p);
            if (createdFile != null) {
                writeFile(p, bytes);
                updateIndex(index, createdFile);
            }
        }
    }

    private void updateIndex(int index, final Path createdFile) {
        mapIndex.put(index, createdFile);
        this.setTitleAt(index, createdFile.getFileName().toString());
        this.setToolTipTextAt(index, createdFile.toString());
    }

    private void writeFile(Path p, final byte[] bytes) throws IOException {
        Files.write(p, bytes);
    }

    public void nuevo() throws IOException {
        agregar(null);
    }

    private void agregar(Path p) throws IOException {
        String title = (p == null ? "Nuevo " + this.getTabCount() : p.getFileName().toString());
        TextPane editor = (p == null ? new TextPane() : new TextPane(readFile(p)));
        String tooltip = (p == null? null: p.toString());
        
        this.addTab(title,null, editor,tooltip);
        int index = this.indexOfComponent(editor);
        this.setSelectedIndex(index);
        this.mapIndex.put(index, (p == null ? Paths.get(title) : p));
//        Object key = (p == null ? null : editor);
// 
//        if (p != null && !mapFile.containsKey(key)) {
//            this.mapIndex.put(index, p);
//            this.setFile.add(p);
//        }
    }

    private void eliminar(JComponent c) {
        final int index = this.indexOfComponent(c);
        this.removeTabAt(index);
        this.mapIndex.remove(index);
    }

    private final HashSet<Path> setFile = new HashSet<>();
    private final HashMap<Object, Path> mapIndex = new HashMap<>();

    private Path showSaveFileDialog(Path p) throws IOException {

        filechooser.setCurrentDirectory(p.toFile().getParentFile());
        filechooser.setSelectedFile(p.toFile());
        int ret = filechooser.showSaveDialog(this);
        if (ret == JFileChooser.APPROVE_OPTION) {
            final Path newFile = filechooser.getSelectedFile().toPath();
            Files.createFile(newFile);
            return newFile;
        }
        return null;
    }

    private String readFile(Path p) throws IOException {
        return new String(Files.readAllBytes(p),"utf-8");
    }

}
