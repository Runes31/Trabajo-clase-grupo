package views;

import dataStructures.Contenido;
import helpers.ImageHelper;

import java.awt.*;

import javax.swing.*;

public abstract class VistaElemento extends VistaPrincipal{
    private JLabel imagen = new JLabel("", JLabel.CENTER);
    private JLabel titulo =new JLabel();
    private JLabel codigo =new JLabel();
    
    private JPanel panel1 = new JPanel(); 
    private JPanel panel2 = new JPanel();
    
    private GridBagLayout grid1 = new GridBagLayout();
    private GridBagConstraints gbc1 = new GridBagConstraints();
    
    private GridBagLayout grid2 = new GridBagLayout();
    private GridBagConstraints gbc2 = new GridBagConstraints();
    
    

    public VistaElemento(Contenido contenido) {
        super.setSize(1000,600);
        super.crearPanel();
        GridBagLayout gridAuxiliar = new GridBagLayout();
        GridBagConstraints gbcAux = new GridBagConstraints();
        gridAuxiliar.columnWidths = new int[]{400 , 400};
        super.getPanelGeneral().setLayout(gridAuxiliar);

        crearMenu();

        setVariablesContenido(contenido);

        //panel izquierda
        panel1.setPreferredSize(new Dimension(400,600));
        grid1.rowHeights=new int []{400,50,50,50,50};
        panel1.setLayout(grid1);
        gbcAux.gridx=0;
        gbcAux.gridy=0;
        crearPanel1();
        panelContenido.add(panel1,gbcAux);
        
        //panel derecha
        gbcAux.anchor=GridBagConstraints.NORTHWEST;
        panel2.setPreferredSize(new Dimension(400,400));
        panel2.setLayout(grid2);
        gbcAux.gridx=1;
        gbcAux.gridy=0;
        crearPanel2();
        panelContenido.add(panel2,gbcAux);
    }

    private void setVariablesContenido(Contenido contenido) {
        Image img = ImageHelper.getImagen(contenido.getImagen()).getScaledInstance(80, 100, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(img);
        imagen.setIcon(icon);
        titulo.setText("Título: " + contenido.getTitulo());
        codigo.setText("Código: " + contenido.getCodigo());
    }

    private void crearPanel1() {
        gbc1.gridx=0;
        gbc1.gridy = 0;
        panel1.add(imagen, gbc1);
        
        gbc1.gridy=1;
        panel1.add(codigo, gbc1);
    }
    
    private void crearPanel2() {
        gbc2.insets= new Insets(30, 0, 0, 0);
        gbc2.weighty = 1;
        gbc2.gridx=0;
        gbc2.gridy = 0;
        panel2.add(titulo, gbc2);
        gbc2.gridx=0;
        gbc2.gridy = 1;
        panel2.add(new JLabel("Hola"), gbc2);
        
    }
    
    
    public JLabel getImagen() {
        return imagen;
    }

    public void setImagen(JLabel imagen) {
        this.imagen = imagen;
    }

    public JLabel getTitulo() {
        return titulo;
    }
    
    public void setTitulo(JLabel titulo) {
        this.titulo = titulo;
    }
    
    public JLabel getCodigo() {
        return codigo;
    }
    
    public void setCodigo(JLabel codigo) {
        this.codigo = codigo;
    }
    
    public JPanel getPanel1() {
        return panel1;
    }
    
    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }
    
    public JPanel getPanel2() {
        return panel2;
    }
    
    public void setPanel2(JPanel panel2) {
        this.panel2 = panel2;
    }

    public GridBagLayout getGrid1() {
        return grid1;
    }

    public void setGrid1(GridBagLayout grid1) {
        this.grid1 = grid1;
    }

    public GridBagConstraints getGbc1() {
        return gbc1;
    }

    public void setGbc1(GridBagConstraints gbc1) {
        this.gbc1 = gbc1;
    }

    public GridBagLayout getGrid2() {
        return grid2;
    }

    public void setGrid2(GridBagLayout grid2) {
        this.grid2 = grid2;
    }

    public GridBagConstraints getGbc2() {
        return gbc2;
    }

    public void setGbc2(GridBagConstraints gbc2) {
        this.gbc2 = gbc2;
    }
}
