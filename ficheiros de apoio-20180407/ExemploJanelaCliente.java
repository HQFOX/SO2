package so2.t1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ExemploJanelaCliente
    implements ActionListener
{

    String DEV_EMAIL="exemplo@uevora.pt";
    String APP_NAME="Sistemas Operativos II";    

    protected static String ENVIAR= "ENVIAR";
    protected static String SAIR= "SAIR";

    private JTextArea qresult,tRef1,tRef2,tRef3,lnomes2,tDescr1,tUnid2,tDescr4;
    private static final Font fontLabel= new Font("Arial",Font.PLAIN,11);
    private JEditorPane outPane= null;


    private JFrame frame;
    JRadioButton rbt1= new JRadioButton("operacao A");
    JRadioButton rbt2= new JRadioButton("operacao B");
    
    
    ButtonGroup bgroup;

    int LARGURA=665;
    int ALTURA=600;


    public ExemploJanelaCliente() {	
        bgroup = new ButtonGroup();
        bgroup.add(rbt1);
        bgroup.add(rbt2);
    }            

    

    /**
     * Metodo de execucao desta aplicação de demonstração
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args)
	throws Exception
    {
	ExemploJanelaCliente e= new ExemploJanelaCliente();
       
	e.initX();
    }
    



    public void initX( ) {
	frame= new JFrame( APP_NAME );
	frame.setSize( new Dimension(LARGURA,ALTURA) );
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	frame.setLocationRelativeTo(null);

        tRef1= new JTextArea(1,8);

        tDescr1= new JTextArea(1,20);

        tRef2= new JTextArea(1,8);
        tUnid2= new JTextArea(1,4);
        lnomes2= new JTextArea(1,8);

	// campo para mostrar informacao, nao editavel
        qresult= new JTextArea(8,70);       
        qresult.setEditable(false);

	// mostrar
	updateFrame();
    }



    // actualizar o frame de acordo com o estado
    private void updateFrame() {        

        try {
	    if (outPane != null)
	    	frame.setSize( new Dimension(LARGURA,ALTURA) );

            JPanel jp = new JPanel( new BorderLayout() );

            Component centro= desenhaCentro();
            jp.add(centro, BorderLayout.CENTER);

            frame.setContentPane(jp);
        }
        catch (Exception e) {
            System.err.println("ERROR while updating frame: "+e);
            e.printStackTrace();
            System.exit(1);
        }
        // actualizar ==========
	if (outPane == null)
	    frame.pack();
        frame.setVisible(true);
        // ---------------------
        //System.out.println("          == PAINEL ACTUALIZADO");
    }


    

    private Component desenhaCentro()
        throws Exception
    {
	if (outPane != null) {    // done, falta so' mostrar
	    JScrollPane scrollPane = new JScrollPane(outPane); 
	    return scrollPane;
	}
	else {    //
	    JPanel buttonP = new JPanel(new BorderLayout());	

	    JPanel p = new JPanel(new BorderLayout());
            //p.setBackground(Color.yellow);

	    JPanel p1 = new JPanel(new BorderLayout());
	    JPanel p12 = new JPanel(new BorderLayout());
	    JPanel p3 = new JPanel(new BorderLayout());
	    JPanel p4 = new JPanel(new BorderLayout());
            JPanel p2= new JPanel(new GridLayout(1,8));

            // *************************
            // campos das operacoes
            JPanel pbase= new JPanel(new GridLayout(1,5,2,3));
            pbase.setBackground(Color.yellow);

            JPanel pbase1= new JPanel(new GridLayout(3,1));
            pbase1.add( rbt1 );
            pbase1.add( new JLabel("campo aa") );
            pbase1.add( tRef1 );
            pbase.add( pbase1 );
            
            JPanel pbase2= new JPanel(new GridLayout(7,1));
            pbase2.add( rbt2 );
            pbase2.add( new JLabel("campo xx") );
            pbase2.add( tRef2 );
            pbase2.add( new JLabel("campo yy") );
            pbase2.add( tUnid2 );
            pbase2.add( new JLabel("campo zz") );
            pbase2.add( lnomes2 );

            pbase.add( pbase2 );

	    p2.setAlignmentX(JPanel.CENTER_ALIGNMENT);
            // *************************
	    
	    p12.add( pbase , BorderLayout.WEST );
	    p12.add( p1 , BorderLayout.CENTER );
	    


	    // ****************
            JLabel lch= new JLabel("resultado");
	    lch.setFont( fontLabel);
	    p2.add(qresult);
	    p2.setAlignmentX(JPanel.CENTER_ALIGNMENT);
	    
	    p3.add(lch,BorderLayout.NORTH );
	    p3.add(p2,BorderLayout.CENTER );
	    
	    JButton button = new JButton(" enviar ");
	    button.addActionListener(this); 
	    button.setActionCommand(ENVIAR);
	    
	    buttonP.add(button,BorderLayout.CENTER );	
	    
	    p4.add(p3,BorderLayout.NORTH );
	    p4.add(buttonP,BorderLayout.CENTER );
	    // ****************

	    p.add( new JLabel("Operações:") , BorderLayout.NORTH );
	    p.add( p12 , BorderLayout.WEST );
	    p.add( p4 , BorderLayout.SOUTH ); // resultado e botao enviar
	    return p;
	}
    }




    // EVENTO SOBRE O FRAME "ACTION"
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        //System.out.println("COMMAND: "+command);
        // ********************************************

         try {
             if ( command.equals(SAIR) ) {
		 frame.dispose();
		 System.exit(0);
	     }

	     
             if ( command.equals(ENVIAR) ) {
		 try {
		     String s= "EXEMPLO PARA: ";

                     // que operacao esta seleccionada?
                     if (rbt1.isSelected()) {  // pesquisa voos por destino
			// debug
                        s+= "op A:  1º CAMPO: "+ tRef1.getText();

                        

                     }
                     else if(rbt2.isSelected()) { // compra N lugares
			String nomeStr="";
			String[] arrayNomes= lnomes2.getText().split(";");
			for (String nome: arrayNomes) {
			    nome= nome.trim();
			    // aceder a cada nome individualmente...

			    nomeStr= nomeStr +"\n"+nome;
			}
			// debug:
                        s+= "op B: xx =="+ tRef2.getText();

                        // idem p outro 2 campos...
                        
                     }

                    



		     System.err.println("DEBUG: "+s);
		     if ( s != null ) {
			 qresult.setText(s);
		     
		     }
		 }
		 catch (Exception eNum) {
		     qresult.setText("problemas: "+eNum.getMessage());
		     eNum.printStackTrace();
		 }
		 updateFrame();
	     }
	     
         }
         catch (Exception e1) {
             System.err.println(e1);
             try{
		 Thread.sleep(1000);
	     }
             catch(Exception e2){
             }
             System.exit(0);
          }
    }



    
}
