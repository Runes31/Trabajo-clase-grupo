package views;
import controllers.ContentController;
import controllers.UserController;
import dataStructures.TipoContenido;
import helpers.ImageHelper;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;

public abstract class VistaPrincipal extends JFrame{
    //panelGeneral
    private JPanel panelGeneral = new JPanel();
    //imagen
    private Image imagenLogo = ImageHelper.getLogo();
    //nombre app
    private static final String NOMBREAPLICACION= "Nombre Aplicacion";
    private JOptionPane mensajeError = new JOptionPane();

    JPanel panelContenido = new JPanel();
    GridBagLayout grid1 = new GridBagLayout();
    GridBagConstraints gbc1 = new GridBagConstraints();

    private JPanel panelLista = new JPanel();
    private GridBagLayout grid2 = new GridBagLayout();
    GridBagConstraints gbc2 = new GridBagConstraints();

    private JList<String> lista = new JList<String>();
    private JTextField buscador = new JTextField("Buscar");
    private JButton desconectar = new JButton("Desconectar");

    private int mHoveredJListIndex = -1;

    public void crearPanel(){
        setTitle(NOMBREAPLICACION);
        setIconImage(imagenLogo);
        add(panelGeneral);
        setLocationRelativeTo(null);
        panelGeneral.setBackground(Color.BLACK);
        panelContenido.setBackground(Color.BLACK);
        panelLista.setBackground(Color.BLACK);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        super.setResizable(false);
    }
    //getter
    public JPanel getPanelGeneral() {
        return panelGeneral;
    }

    public void pintar(List<String> mensaje) {
        String cadenaFinal="";
        for (Object object : mensaje) {
            String cadena;
            cadena = (String) object;
            cadenaFinal += cadena + "\n";
        }
        mensajeError.showMessageDialog(this , cadenaFinal  ,"Error", JOptionPane.ERROR_MESSAGE);
    }

    public void pintar(String mensaje){
        mensajeError.showMessageDialog(this , mensaje ,"Error",JOptionPane.ERROR_MESSAGE);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.setColor(Color.WHITE);
    }

    void crearMenu(){
        crearPaneles();
//	    BUSCADOR
        gbc2.gridx=0;
        gbc2.gridy=0;

        //Si presiona enter teniendo seleccionado el jtextfield realiza una busqueda
        buscador.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    ContentController contentController = new ContentController();
                    contentController.buscarContenido(buscador.getText());
                }
            }
        });

        //Placeholder text
        buscador.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(buscador.getText().equals("Buscar")){
                    buscador.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (buscador.getText().equals("")){
                    buscador.setText("Buscar");
                }
            }
        });

        panelLista.add(buscador,gbc2);

//	    LISTA
        String [] contenidoMenu = {"Listado completo", "Películas" ,"Libros", "Música","Mis prestamos"
                , "Películas","Libros","Música" };
        DefaultListCellRenderer renderer =  (DefaultListCellRenderer)lista.getCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        renderer.setVerticalAlignment(JLabel.BOTTOM);
        EstilosBotones.setCursor(lista);
        lista.setFont(new Font("serif",Font.ITALIC , 15));
        lista.setSelectedIndex(0);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lista.setListData(contenidoMenu);
        lista.setFixedCellHeight(50);
        //Listener de la lista
        lista.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ContentController contentController = new ContentController();

                switch (lista.getSelectedIndex()){
                    case 0: contentController.initHome(); break;
                    case 1: contentController.initHome(TipoContenido.PELICULA); break;
                    case 2: contentController.initHome(TipoContenido.LIBRO); break;
                    case 3: contentController.initHome(TipoContenido.MUSICA); break;
                    case 4: contentController.initHome(TipoContenido.PRESTAMO); break;
                    case 5: contentController.initHome(TipoContenido.PRESTAMO_PELICULA); break;
                    case 6: contentController.initHome(TipoContenido.PRESTAMO_LIBRO); break;
                    case 7: contentController.initHome(TipoContenido.PRESTAMO_MUSICA); break;
                }
            }
        });
        //FIN listener de la lista

        gbc2.gridy=1;
        panelLista.add(lista,gbc2);

//		BOTON DESCONECTAR
        gbc2.gridy=2;
        panelLista.add(desconectar,gbc2);
        EstilosBotones.setCursor(desconectar);
        EstilosBotones.setColor(desconectar, Color.RED);
        desconectar.addMouseListener(new MouseListener() {


            @Override
            public void mouseClicked(MouseEvent e) {
                UserController userController = new UserController();
                userController.logout();

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void crearPaneles(){
        GridBagLayout gridAuxiliar = new GridBagLayout();
        GridBagConstraints gbcAux = new GridBagConstraints();
        gridAuxiliar.columnWidths = new int[]{800 , 200};
        getPanelGeneral().setLayout(gridAuxiliar);

        //panelLista
        grid2.columnWidths=new int[]{196};
        grid2.rowHeights=new int []{50,470,50};
        gbc2.fill=GridBagConstraints.BOTH;
        panelLista.setLayout(grid2);
        gbcAux.gridx=1;
        getPanelGeneral().add(panelLista,gbcAux);


        //panelContenido
        grid1.columnWidths=new int[]{160,160,160,160,160};
        grid1.rowHeights=new int []{100,100,100,100,100,100};
        panelContenido.setLayout(grid1);
        gbcAux.gridx=0;
        gbcAux.gridy=0;
        getPanelGeneral().add(panelContenido,gbcAux);
    }
}
