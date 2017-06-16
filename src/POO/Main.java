/* Jeux de runes  */
package POO;

import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/** * * @author Denis */
public class Main extends javax.swing.JFrame {
 
    /**   * Creates new form Main;     */
    public Main() throws IOException {
        initComponents();
        setIcon();
        initRunes();
    }
    
    private void setIcon() {
         setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
    }

    private void initRunes() {
        listeRunes=new Rune[nbRunes];
        
        String nomsRunes[]={
             "01-fehu"
            ,"02-uruz"
            ,"03-thurisaz"
            ,"04-ansuz"
            ,"05-raido"
            ,"06-kauna"
            ,"07-gebo"
            ,"08-wunjo"
            ,"09-haglaz"
            ,"10-naudiz"
            ,"11-isaz"
            ,"12-jeran"
            ,"13-iwaz"
            ,"14-pertho"
            ,"15-algiz"
            ,"16-sowilo"
            ,"17-tiwaz"
            ,"18-berkanan"
            ,"19-ehwaz"
            ,"20-mannaz"
            ,"21-laukaz"
            ,"22-ingwaz"
            ,"23-dagaz"
            ,"24-othalan"    
        } ;
        
        int nRune=0;
        for (int i=0; i<nbRunes;i++){
            listeRunes[i]=new Rune( "/images/"+nomsRunes[nRune]+".png","/images/stele.png", true,nRune,nomsRunes[nRune]);
            listeRunes[i].tournerVersDos();
            
            nRune++;
            if (nRune==nbRunes/2) nRune=0;
         }
        melanger();
        afficherRunesAvecEvt();
    }
    
    private void afficherRunesAvecEvt(){
        
        for (int i=0; i<nbRunes;i++){
            boiteRunes.add(listeRunes[i]);
        
            // Créer un écouteur d'événement sur une rune
            listeRunes[i].addMouseListener(new java.awt.event.MouseAdapter() {
               @Override
               public void mouseReleased(java.awt.event.MouseEvent evt){
                   runeMouseReleased(evt);
               }
            });
        }
    }
    
    private void runeMouseReleased(java.awt.event.MouseEvent evt){
        nbTotalClicks++;
        runeCliquee=(Rune) evt.getSource();
        filResultats=filResultats+runeCliquee.getNomRune().substring(0,2)+"       "+runeCliquee.getNomRune().substring(3)+"\n";
        resultatsTfd1.setText(filResultats);

       
//       if (runeCliquee.isCoteDos()) runeCliquee.tournerVersFace();
//       else runeCliquee.tournerVersDos();
        
        if (aRetourner){
            premiereRune.tournerVersDos();
            deuxiemeRune.tournerVersDos();
            aRetourner=false;            
        }
       
       if (nbClick==0){
           premiereRune=runeCliquee;
           nbClick=1;
           premiereRune.tournerVersFace();
           // Supprimer l'écouteur d'événement
           premiereRune.removeMouseListener(premiereRune.getMouseListeners()[0]);
       }
       else if(nbClick==1){
           deuxiemeRune=runeCliquee;
           nbClick=0;
           deuxiemeRune.tournerVersFace();
           if (deuxiemeRune.getId()!=premiereRune.getId()) {
               aRetourner=true;
               premiereRune.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseReleased(java.awt.event.MouseEvent evt){
                        runeMouseReleased(evt);
                    }
                });
           }
           else {
                deuxiemeRune.removeMouseListener(deuxiemeRune.getMouseListeners()[0]);
                nbPairesRetournees+=2;
                
                if (nbPairesRetournees>=nbRunes) {
                    resultatsTfd2.setText(  "Bravo! Vous avez retourné toutes les stèles en "+nbTotalClicks/2+" essais.");
                    new Message(this, true) ;
                    retournerToutesLesRunes();
                    boiteRunes.removeAll();
                    initRunes();
                    nbPairesRetournees=0;
                }
           }
        }
        resultatsTfd2.setText(  "Nombre de paires retournées: "+nbPairesRetournees/2+"\n"+
                                "Nombre d'essais: "+nbTotalClicks/2);
    };                

    private int auHasardEntre(int min, int max){
        int nbAlea=(int) ((max-min)*Math.random()+min);
        return nbAlea;
    }

    private void melanger(){
        Rune tmp;
        int indAlea;
        
        for (int i=0; i<nbRunes;i++){
            tmp=listeRunes[i];
            indAlea=auHasardEntre(i+1,listeRunes.length-1);
            listeRunes[i]=listeRunes[indAlea];
            listeRunes[indAlea]=tmp;
        }
    }

    private void retournerToutesLesRunes(){
        for (int i=0; i<nbRunes;i++){
            listeRunes[i].tournerVersDos();
        }
    };       
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boiteRunes = new javax.swing.JPanel();
        resultatsPnl1 = new javax.swing.JScrollPane();
        resultatsTfd1 = new javax.swing.JTextArea();
        resultatsPnl2 = new javax.swing.JScrollPane();
        resultatsTfd2 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jeu de runes");
        setPreferredSize(new java.awt.Dimension(1700, 1000));

        boiteRunes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "retourner toutes les stèles, paire par paire, en cliquant dessus", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 24))); // NOI18N
        boiteRunes.setMinimumSize(new java.awt.Dimension(800, 500));
        boiteRunes.setPreferredSize(new java.awt.Dimension(1600, 700));
        boiteRunes.setLayout(new java.awt.GridLayout(4, 12));

        resultatsPnl1.setBackground(new java.awt.Color(216, 216, 216));
        resultatsPnl1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Numéro et nom de la rune", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        resultatsPnl1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        resultatsTfd1.setEditable(false);
        resultatsTfd1.setBackground(new java.awt.Color(216, 216, 216));
        resultatsTfd1.setColumns(20);
        resultatsTfd1.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24)); // NOI18N
        resultatsTfd1.setRows(5);
        resultatsTfd1.setAutoscrolls(false);
        resultatsTfd1.setMargin(new java.awt.Insets(1, 250, 1, 1));
        resultatsPnl1.setViewportView(resultatsTfd1);

        resultatsTfd2.setEditable(false);
        resultatsTfd2.setBackground(new java.awt.Color(216, 216, 216));
        resultatsTfd2.setColumns(20);
        resultatsTfd2.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 18)); // NOI18N
        resultatsTfd2.setRows(5);
        resultatsPnl2.setViewportView(resultatsTfd2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(resultatsPnl1, javax.swing.GroupLayout.PREFERRED_SIZE, 715, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resultatsPnl2, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(boiteRunes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1600, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(boiteRunes, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(resultatsPnl1, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(resultatsPnl2))
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private Rune [] listeRunes;
    private static int nbRunes=48;
    private Rune runeCliquee, premiereRune, deuxiemeRune;
    private int nbClick=0;
    private int nbTotalClicks=0;
    private boolean aRetourner=false; 
    private int nbPairesRetournees=0;
    private String [] nomsRunes;
    private String filResultats="";
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boiteRunes;
    private javax.swing.JScrollPane resultatsPnl1;
    private javax.swing.JScrollPane resultatsPnl2;
    private javax.swing.JTextArea resultatsTfd1;
    private javax.swing.JTextArea resultatsTfd2;
    // End of variables declaration//GEN-END:variables
}
