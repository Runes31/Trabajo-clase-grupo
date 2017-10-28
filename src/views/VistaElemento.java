package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class VistaElemento extends VistaPrincipal{
    private String imagen="";
    private JLabel titulo =new JLabel("Título: ");
    private JLabel codigo =new JLabel("Código: ");
    private JLabel stock =new JLabel("Stock: ");
    private JLabel reservado =new JLabel("Reservado: ");
    
    private JPanel panel1 = new JPanel(); 
    private JPanel panel2 = new JPanel();
    
    private GridBagLayout grid1 = new GridBagLayout();
    private GridBagConstraints gbc1 = new GridBagConstraints();
    
    private GridBagLayout grid2 = new GridBagLayout();
    private GridBagConstraints gbc2 = new GridBagConstraints();
    
    

    public VistaElemento() {
        super.setSize(850,600);
        super.crearPanel();
        GridBagLayout gridAuxiliar = new GridBagLayout();
        GridBagConstraints gbcAux = new GridBagConstraints();
        gridAuxiliar.columnWidths = new int[]{400 , 400};
        super.getPanel().setLayout(gridAuxiliar);
        
        //panel izquierda
        panel1.setPreferredSize(new Dimension(400,600));
        grid1.rowHeights=new int []{400,50,50,50,50};
        panel1.setLayout(grid1);
        gbcAux.gridx=0;
        gbcAux.gridy=0;
        crearPanel1(imagen);
        panel1.setBackground(Color.BLUE);
        super.getPanel().add(panel1,gbcAux);
        
        //panel derecha
        panel2.setPreferredSize(new Dimension(400,400));
        panel2.setLayout(grid2);
        gbcAux.gridx=1;
        gbcAux.gridy=0;
        crearPanel2();
        panel2.setBackground(Color.BLACK);
        super.getPanel().add(panel2,gbcAux);
    }
    
    private void crearPanel1(String rutaImagen) {
        ImageIcon imagen = new ImageIcon(rutaImagen);
        gbc1.weightx=1;
        
        gbc1.gridx=0;
        gbc1.gridy = 0;
        panel1.add(new JLabel(imagen), gbc1);
        
        gbc1.gridy=1;
        panel1.add(codigo, gbc1);
        
        gbc1.gridy=2;
        panel1.add(stock, gbc1);
        
        gbc1.gridy=3;
        panel1.add(reservado, gbc1);       
    }
    
    private void crearPanel2() {
        gbc2.anchor=GridBagConstraints.NORTH;
        gbc2.insets = new Insets(0, 0, 20, 0);
        gbc2.gridx=0;
        gbc2.gridy = 0;
        panel2.add(titulo, gbc2);
        gbc2.gridx=0;
        gbc2.gridy = 1;
        panel2.add(new JLabel("Hola"), gbc2);
        
    }
    
    public String getImagen() {
        return imagen;
    }
    
    public void setImagen(String imagen) {
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
    
    public JLabel getStock() {
        return stock;
    }
    
    public void setStock(JLabel stock) {
        this.stock = stock;
    }
    
    public JLabel getReservado() {
        return reservado;
    }
    
    public void setReservado(JLabel reservado) {
        this.reservado = reservado;
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
