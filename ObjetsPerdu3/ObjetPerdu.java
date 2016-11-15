/**
 * A COMPLETER...
 */
public class ObjetPerdu {

    /*
     * ******** Constantes de classe **********
     */

    //constantes pour les types des objets perdus
    public static final int TYPE_BIJOU = 1;
    public static final int TYPE_VETEMENT = 2;
    public static final int TYPE_ARGENT = 3;
    public static final int TYPE_CLES = 4;
    public static final int TYPE_AUTRES = 5;

    /*
     * ********** Attribut de classe ************
     */

    //sequence servant a assigner un numero d'identification 
    //unique aux objets perdus crees.
    private static int sequenceNoIdentification = 1;

    /*
     * ********* Attributs d'instance **********
     */

    //declarer ici les 4 attributs d'instance :
    
    private String description;
    private int type;
    private Date datePerte;
    private int noIdentification;

    /*
     * ********** Constructeurs ***************
     */

    /**
     * Assigne un numero d'identification unique a l'objet perdu cree. Le premier
     * objet cree a le numero 1, le deuxieme a le numero 2, et ainsi de suite. La
     * description est initialisee a la chaine vide, le type de l'objet est
     * initialise a TYPE_AUTRES, et la date de la perte est null.
     */
    public ObjetPerdu() {
        this.noIdentification = sequenceNoIdentification++;  
        description = "";
        type = TYPE_AUTRES;
        datePerte = null;
    }

    /**
     * Initialise les attributs description, type et datePerte de cet objet perdu
     * avec les valeurs passees en parametres et assigne un numero
     * d'identification unique a cet objet perdu. Le premier objet cree a le
     * numero 1, le deuxieme a le numero 2, et ainsi de suite.
     *
     * @param description la description de l'objet perdu.
     * @param type le type de l'objet perdu.
     * @param datePerte la date de la perte de l'objet perdu.
     */

    public ObjetPerdu(String description, int type, Date datePerte) {
        noIdentification = sequenceNoIdentification++;
        this.description = description;
        this.type = type;
        this.datePerte = datePerte;
    }

    /**
     * Cree un objet perdu dont le numero d'identification est initialise
     * avec celui passe en parametre. La description est initialisee a la 
     * chaine vide, le type de l'objet est initialise a TYPE_AUTRES, 
     * et la date de la perte est null.
     * 
     * Attention : - ce constructeur ne verifie pas que noId est unique.
     *             - sequenceNoIdentification n'est PAS affectee.
     *
     * @param noId le numero d'identification de cet objet perdu.
     */
    public ObjetPerdu(int noId) {
        this.noIdentification = noIdentification;
        description = "";
        type = TYPE_AUTRES;
        datePerte = null;
    }

    /*
     * *********** getters **************
     */

    //les getters pour les 4 attributs d'instance.
    public String getDescription(){   
        return description;
    }

    public int getType(){   
        return type;
    }

    public Date getDatePerte(){
        return datePerte;
    }

    public int getNoIdentification(){
        return noIdentification;
    } 

    /*
     * *********** setters **************
     */

    //A COMPLETER : ecrivez les setters pour description, type et datePerte
    public void setDescription(String description){   
        this.description = description;
    }

    public void setType(int type){
        this.type = type;
    }

    public void setDatePerte(Date datePerte){
        this.datePerte = datePerte;
    }

    /*
     * ********* Autres methodes d'instances ***********
     */

    /**
     * Teste si le mot donne est dans la description de cet objet perdu.
     * Plus precisement, on recherche si le patron de caracteres de mot
     * est present dans la description de cet objet perdu. Aussi, cette 
     * methode ne tient pas compte de la casse : un 'e' est aussi un 'E'.
     * 
     * Exemples pour une description = "parapluie rouge raye bleu":
     * 
     *      mot = "paRApluie", methode retourne true.
     *       mot = "uie r", methode retourne true.
     *       mot = "raye bleu", methode retourne true.  
     *       mot = "rouges", methode retourne false.    
     * 
     * @param mot le patron de caracteres a rechercher dans la description 
     *            de cet objet perdu.
     * @return true si le mot est dans la description de cet objet perdu,
     *         false sinon.
     */
    public boolean aCeMotDansSaDescription(String mot) {
       mot = mot.trim();
       String description = this.description;
       
       if (!mot.contains(description)){
           return false;
       } else {
           return true; //pour compilation
        }
    }

    
    /**
     * Construit une representation de cet objet perdu sous forme de chaine
     * de caracteres.
     * 
     * Exemple :
     * 
     * NO IDENTIFICATION : 3
     * DESCRIPTION       : parapluie carote vert et bleu marin
     * TYPE              : 5
     * DATE              : 18/06/2014
     * 
     * @return une representation de cet objet perdu sous forme de chaine
     *         de caracteres.
     */
    public String toString() {
        String s;
        s = "NO IDENTIFICATION: " + noIdentification + "\nDESCRIPTION: " + 
        description + "\nTYPE: " + type + "\nDATE: " + datePerte;
        return s;
        //return null; //pour compilation
    }

    /*
     * ********** Methodes de classe ***************
     */

    /**
     * Permet de modfier la valeur de l'attribut de classe
     * sequenceNoIdentification avec la valeur donnee en parametre.
     *
     * @param noSeq la nouvelle valeur pour sequenceNoIdentification.
     */
    public static void setSequenceNoIdentification(int noSeq) {
        sequenceNoIdentification = noSeq;
    }

    /**
     * Permet d'obtenir la valeur de l'attribut de classe 
     * sequenceNoIdentification.
     * 
     * @return la valeur de sequenceNoIdentification. 
     */
    public static int getSequenceNoIdentification() {
        
        return sequenceNoIdentification;
         
    }

}

