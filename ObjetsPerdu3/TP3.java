/**
 * TP#3 
 *
 */
import java.io.*;
public class TP3 {
    /*
     * ******** Constantes de classe **********
     */
    public final static String CHEMIN_FIC = "objPerdus.txt"; //chemin du fichier a ecrire
    public final static int NBR_CASIERS_INIT = 10; //Constante pour la longueur initiale du tableau

    /**
     * Affiche une breve presentation du logiciel. 
     */
    public static  void presentationLogiciel(){
        System.out.println("*************************\n* PROGRAMME OBJET PERDU *\n*************************");
        System.out.println("\nIL S'AGIT D'UN PROAGRAMME QUI PERMET LA GESTION");
        System.out.print("DES OBJETS PERDUS DU COMPTOIR DU CENTRE SPORTIF\n");
    }

    /**
     * Provoque l'arret du programme et demande d'appuyer ENTER pour afficher le menu principal.
     */
    public static void revenirMenuPrincipal(){
        System.out.print("\nAppuyer sur <ENTREE> pour revenir au menu principal.\n");
        Clavier.lireFinLigne();
    }

    /**
     * Provoque l'arret du programme et demande d'appuer ENTER pour continuer.
     */
    public static void pause(){
        System.out.print("\nAppuyer sur <ENTREE> pour continuer...\n");
        Clavier.lireFinLigne();
    }

    /**
     * Affiche le menu principal de 7 options. 
     */
    public static void afficherMenuPrincipal(){
        System.out.println("\n-------------------------\nGESTION DES OBJETS PERDUS\n-------------------------");
        System.out.println("\n1. Consigner un objet perdu\n2. Rendre un objet reclame");
        System.out.println("3. Rechercher un objet par mot-cle\n4. Rechercher un objet par date(s)");
        System.out.println("5. Rechercher un objet par type\n6. Afficher tous les objets consignes\n7. Quitter");
    }

    /**
     * Sollicite et lit un nombre entier et redemande le nombre entier 
     * tant que l'entier saisi n'est pas entre 1 et 7 exclusivement.
     * @param choix la valeur de choix pour tester l'entier saisi. Doit
     * etre entre 1 et 7 exclusivement.
     * 
     * @return un entier entre 1 et 7 exclusivement
     */
    public static int choixMenuPrincipal(int choix){
        do{
            try{
                System.out.print("\nVeuillez entrez votre choix : ");
                choix = Clavier.lireInt(); //risque de NumberFormatException 
                if (choix < 1 || choix > 7){
                    System.out.println("Erreur, le choix doit etre entre  1 et 7... recommencez!");
                }
            } catch (NumberFormatException e){ //gestion locale de l'exception
                System.out.println("Erreur, votre choix doit etre un nombre entier... recommencez!");
            }
        }while(choix < 1 || choix > 7);
        return choix;
    }

    /**
     * Affiche le sous-menu des types objets. 
     */
    public static void afficherSousMenuTypeObjet(){
        System.out.println("\nType de l'objet\n---------------");
        System.out.println("\n(1) bijou\n(2) vetement\n(3) porte-feuille ou argent\n(4) cles\n(5) autres");
    }

    /**
     * Sollicite et lit un nombre entier et redemande le nombre entier 
     * tant que l'entier saisi n'est pas entre 1 et 5 exclusivement.
     * @param choix la valeur de choix pour tester l'entier saisi. Doit
     * etre entre 1 et 5 exclusivement.
     * 
     * @return un entier entre 1 et 5 exclusivement
     */
    public static int choixTypeObjet(){
        int choix = 0;
        afficherSousMenuTypeObjet();
        do{
            try{
                System.out.print("\nEntrez le type : ");
                choix = Clavier.lireInt(); //risque de NumberFormatException
                if (choix < 1 || choix > 5){
                    System.out.println("Erreur, le choix doit etre entre  1 et 5... recommencez!");
                }
            } catch (NumberFormatException e){ //gestion locale de l'exception
                System.out.println("Erreur, votre choix doit etre un nombre entier... recommencez!");
            }
        }while(choix < 1 || choix > 5);
        return choix;
    }

    /**
     * Sollicite et lit l'annee, le mois, l'annee et
     * redemande a l'utilisateur d'entrer l'annee, le mois et 
     * le jour tant que la date saisie n'est pas valide
     * @return la date saisie.
     */
    public static Date dateDePerte(){
        Date dateSaisie = new Date();
        System.out.println("\nDate de perte de l'objet\n------------------------");
        do{
            do{
                try{
                    System.out.print("\n       Entrez le jour   : ");
                    dateSaisie.setJour(Clavier.lireInt()); //risque de NumberFormatException
                } catch (NumberFormatException e){ //gestion locale de l'exception
                    System.out.println("       Le jour doit etre un entier... recommencez!");
                    dateSaisie.setJour(0);
                }
            } while (dateSaisie.getJour() == 0); 
            do{
                try{
                    System.out.print("\n       Entrez le mois   : ");
                    dateSaisie.setMois(Clavier.lireInt()); //risque de NumberFormatException
                } catch (NumberFormatException e){ //gestion locale de l'exception
                    System.out.println("       Le mois doit etre un entier... recommencez!");
                    dateSaisie.setMois(0);
                }
            } while (dateSaisie.getMois() == 0);
            do{
                try{
                    System.out.print("\n       Entrez l'annee   : ");
                    dateSaisie.setAnnee(Clavier.lireInt()); //risque de NumberFormatException
                } catch (NumberFormatException e){ //gestion locale de l'exception
                    System.out.println("       L'anne doit etre un entier... recommencez!");
                    dateSaisie.setAnnee(0);
                }
            } while (dateSaisie.getAnnee() == 0);
            if(!dateSaisie.estDateValide()){
                System.out.println("\nErreur, date invalide!");
                System.out.println("Entrez une date valide entre 01/01/1900 et 21/11/2015.");
            }
        }while(!dateSaisie.estDateValide());

        return dateSaisie;
    }

    /**
     * Affiche le sous-menu rechercher par date. 
     */
    public static void afficherSousMenuRechercheDate(){
        System.out.println("Recherche une date\n------------------");
        System.out.println("\n1. Specifier une date\n2. Specifier une periode");

    }

    /**
     * Sollicite et lit un nombre entier et redemande le nombre entier 
     * tant que l'entier saisi est entre 1 ou 2.
     * @param choix la valeur de choix pour tester l'entier saisi. Doit
     * etre entre 1 ou 2.
     * @return un entier entre 1 ou 2
     */
    public static int choixRechercheDate(int choix){
        do{
            try{
                System.out.print("Entrez votre choix au menu : ");
                choix = Clavier.lireInt();
                if (choix == 1 || choix == 2){
                    System.out.println("Erreur, le choix doit etre 1 ou 2... recommencez!");
                }
            } catch (NumberFormatException e){//risque de NumberFormatException
                System.out.println("Erreur, votre choix doit etre un nombre entier... recommencez!");
            }
        }while(choix == 1 || choix == 2);
        return choix;
    }

    /**
     * Sollicite et saisie la description de l'objet perdu
     * @return une description de type String
     */
    public static String descriptionObjet(){
        String description;
        System.out.println("\nDescription de l'objet\n----------------------");
        System.out.print("\nEntrez la descrption : ");
        description = Clavier.lireString();
        return description;
    }

    /**
     * Methode retourne un entier et il parcoure un tableau 
     * jusqu'a la rencontre d'une case null
     * Si tab est null, incremente le compteur
     * @param tab un tableau de la classe ObjetPerdu
     * @return compteur un entier qui resulte l'incrementation 
     * des cases null
     */
    public static int nbrCasierLibre(ObjetPerdu[] tab){
        int compteur = 0;
        for (int i = 0; i < tab.length; i ++){
            if (tab[i] == null){
                compteur ++;
            }
        }
        return compteur;
    }

    /**
     * Methode retourne un tableau de deux fois sa longueur initiale.
     * Et recopie des elements de tab[i] dans tableauTemp[i]
     * @param tab un tableau de la classe ObjetPerdu.
     * @return tab un tableau donc la longueur a ete doublee.
     */
    public static ObjetPerdu[] doublerTableau(ObjetPerdu[] tab){
        ObjetPerdu[] tableauTemp = new ObjetPerdu[2 * tab.length];
        for (int i = 0; i < tab.length; i++) {
            tableauTemp[i] = tab[i];
            tab = tableauTemp;
        }
        return tab;
    }

    /**
     * Affiche 3 cases a la fois en parcourant le tableau 
     * des objets perdus et verifie si le tableau
     * n'est pas null
     * Si tab n'est pas null, la methode affiche 3 cases
     * @param tab un tableau de la classe ObjetPerdu
     */
    public static void afficheCasiersPar3(ObjetPerdu[] tab){
        int par3 = 0;
        for (int i = 0; i < tab.length; i++ ){
            if(tab[i] != null){
                System.out.println(tab[i]);
                System.out.println("-----------------------------");
                par3++;
                if(par3 != 0 && par3 % 3 == 0){
                    pause();
                }
            }
        }
        System.out.println("FIN DE LA LISTE DES OBJETS.");
    }

    /**
     * Methode retourne un entier et il parcoure un tableau 
     * jusqu'a la rencontre d'une case null
     * Si tab est null, une case libre sera indexe
     * et l'indice sera la taille du tableau
     * @param tab un tableau de la classe ObjetPerdu
     * @return casierLibre qui remplacera la case null
     */
    public static int premiereCasierLibre(ObjetPerdu[] tab){
        int casierLibre = 0;
        for(int i = 0; i < tab.length; i++){
            if(tab[i] == null){
                casierLibre = i;
                i = tab.length;
            }
        }
        return casierLibre;
    }

    /**
     * Methode qui retourne un nouveau tableau avec des cases
     * vides ou non tout en saisissant un entier. Et parcoure 
     * un tableau jusqu'au moment que l'entier saisi est egal 
     * au tableau indexe par le noID. 
     * 
     * Si noID est egal au tableau indexe, la methode affiche 
     * la casier correspondante et ainsi son numero 
     * d'identification. Et redemande s'il veut retirer 
     * l'objet tant que le choixRetirer n'est pas o ou n ou O ou N.
     * Si choixRetirer est egal a O, la methode replace le tableau
     * indexe par null et retoune un tableau.
     * Si choixRetier est egal a N, la methode retoune un tableau.
     * 
     * @param tab le tableau de la classe ObjetPerdu.
     * @retourne un tableau avec des cases vides ou non.
     */
    public static ObjetPerdu[] rendreObjetReclame(ObjetPerdu[] tab){
        int noId = 0;
        char choixRetirer = 'N';
        do{
            try {
                System.out.print("Entrez le numero d'identification de l'objet a rendre: ");
                noId = Clavier.lireInt(); //risque de NumberFormatException
                if (noId <= 0){
                    System.out.println("Numero d'identification invalide recommencer!");
                }
            } catch(NumberFormatException e){ //gestion locale de l'exception
                System.out.println("Erreur... le numero d'identification doit etre un entier... recommencez.");
            }
        } while (noId <= 0);
        try{
            for(int i = 0; i < tab.length; i++){
                if(noId == tab[i].getNoIdentification()){
                    System.out.println("L'objet dont le numero d'identification est " + noId + 
                        " est dans le casier no " + (i+1));
                    do{                    
                        try {                    
                            System.out.print("\nVoulez-vous retirer cet objet (O/N) : ");
                            choixRetirer = Clavier.lireCharLn();
                            if (choixRetirer != 'O' && choixRetirer != 'N' && choixRetirer != 'o' && choixRetirer != 'n'){
                                System.out.println("Erreur, vous devez repondre par (O)ui ou (N)on.. recommencez");
                            }
                        } catch (Exception e) { //gestion locale de l'exception
                            System.out.println("Erreur, vous devez repondre par (O)ui ou (N)on.. recommencez");
                        }
                        if (choixRetirer == 'o' || choixRetirer == 'n'){
                            choixRetirer = Character.toUpperCase(choixRetirer);
                        }
                    } while (choixRetirer != 'O' && choixRetirer != 'N');
                    if(choixRetirer == 'O'){
                        tab[i] = null;
                        revenirMenuPrincipal();
                        return tab;
                    } else if (choixRetirer == 'N'){
                        revenirMenuPrincipal();
                        return tab;
                    }
                }
            }
        } catch (NullPointerException e){
            System.out.println("L'objet dont le numero d'identification est " + noId + " n'est pas consigne!");
        }
        revenirMenuPrincipal();
        return tab;
    }

    /**
     * La methode affiche les mots-cles de tab correspondants aux
     * mot-cles inscrits. Sollicite et saisi le mot cle et parcoure le tableau.
     * 
     * Si tableau indexe possedant la description contient le mot-cle
     * saisi, la methode affiche le tableau et les objets trouves
     * trois par trois. 
     * Si par3 n'est pas zero et n'egale pas a zero quand on le divise
     * par 3, la methode affiche un message demandant au utilisateur
     * d'appuyer sur ENTRER pour continuer.
     * 
     * @param tab le tableau de la classe ObjetPerdu.
     */

    public static void rechercheMotCle(ObjetPerdu[] tab){
        int f = 0;
        int par3 = 0;
        String motCle = "";
        System.out.print("\nEntrez un mot-cle : ");
        motCle = Clavier.lireString();
        System.out.println("-----------------------------------------\nLISTE DES OBJETS PERDUS - MOT-CLE : " +
            motCle + "\n-----------------------------------------\n");
        for (int i = 0; i < tab.length; i++ ){
            try{
                if(tab[i].getDescription().contains(motCle)){
                    System.out.println(tab[i]);
                    System.out.println("-----------------------------");
                    par3++; 
                    if(par3 != 0 && par3 % 3 == 0){
                        pause(); 
                    }
                } 
            }catch (NullPointerException e) { //gestion locale de l'exception

            }
            f++;
            if (f == tab.length){
                System.out.println("AUCUN OBJET PERDU NE REPOND A CE CRITERE.");
            }

        }
        revenirMenuPrincipal();
    } 

    /**
     * Cette methode affiche les elements des dates de tab. Saisi
     * un entier et redemande tant que l'entier n'est pas 1 et 2.
     * 
     * Si entier n'est pas 1 et 2, la methode affiche erreur.
     * 
     * Si entier est 1, la methode parcoure le tableau jusqu'a la 
     * date demandee est egale a la date du perte et il affiche le tableau
     * les objets perdus trois par trois. 
     * 1.Si par3 n'est pas zero et n'egale pas a zero quand on le divise
     * par 3, la methode affiche un message demandant au utilisateur
     * d'appuyer sur ENTRER pour continuer.
     * 2.Si i est egal tab.length, la methode affiche un message
     * 
     * Si entier est 2, la methode redemande la date tant que la date 
     * du debut n'est pas plus petit que la date de la fin. Et parcoure
     * un tableau et affiche le tableau.
     * 1. Si la date du debut est plus petite ou egale a la date du perte indexe
     * et que la date de la fin est plus petite ou egale a la date du perte indexe
     * dans le tableau. La methode affiche un tableau les objets perdus trois par trois. 
     * 1.1. Si par3 n'est pas zero et n'egale pas a zero quand on le divise
     * par 3, la methode affiche un message demandant au utilisateur
     * d'appuyer sur ENTRER pour continuer.
     * 2.Si e est egal tab.length, la methode affiche un message
     * 
     * @param tab le tableau de la classe ObjetPerdu.
     */
    public static void rechercheParDate(ObjetPerdu[] tab){
        int choixMenuDate = 0;
        int par3 = 0;
        Date dateDebut = new Date();
        Date dateFin = new Date();
        System.out.println("Recherche par date\n-----------------");
        System.out.println("\n1. Specifier une date\n2. Specifier une periode");
        do{
            try{
                System.out.print("\nEntrez votre choix au menu : ");
                choixMenuDate = Clavier.lireInt();
            } catch (NumberFormatException e){ //gestion locale de l'exception
                System.out.println("Erreur, le choix doit etre entre 1 et 2... Recommencez");
            }
            if(choixMenuDate != 1 && choixMenuDate != 2){
                System.out.println("Erreur, le choix doit etre entre 1 et 2... Recommencez");
            }
        } while (choixMenuDate != 1 && choixMenuDate != 2);
        if(choixMenuDate  == 1){
            Date dateRecherche = dateDePerte();
            for (int i = 0; i < tab.length; i++ ){
                try{
                    if( dateRecherche.estEgale(tab[i].getDatePerte())){
                        System.out.println(tab[i]);
                        System.out.println("-----------------------------");
                        par3++;
                        if(par3 != 0 && par3 % 3 == 0){
                            pause();
                        }
                    } else if (i == tab.length){
                        System.out.println("AUCUN OBJET PERDU NE REPOND A CE CRITERE.");
                    }
                }catch (NullPointerException e) { //risque NullPointerException

                }
            }
            System.out.println("FIN DE LA LISTE DES OBJETS.");
            //revenirMenuPrincipal();
        } else if (choixMenuDate == 2){
            do{
                System.out.println("\nDATE DEBUT");
                dateDebut = dateDePerte();
                System.out.println("\nDATE FIN");
                dateFin = dateDePerte();

            } while( !dateDebut.estPlusPetite(dateFin) );
            for (int e = 0; e < tab.length; e++){
                try{
                    if (dateDebut.estPlusPetite(tab[e].getDatePerte()) || dateDebut.estEgale(tab[e].getDatePerte()) && 
                    tab[e].getDatePerte().estPlusPetite(dateFin) || dateFin.estEgale(tab[e].getDatePerte()) ){
                        System.out.println(tab[e]);
                        System.out.println("-----------------------------");
                        par3++;
                        if(par3 != 0 && par3 % 3 == 0){
                            pause();
                        }
                    } else if (e == tab.length){
                        System.out.println("AUCUN OBJET PERDU NE REPOND A CE CRITERE.");
                    }
                }catch (NullPointerException p) { //gestion locale de l'exception

                }
            }
            System.out.println("FIN DE LA LISTE DES OBJETS.");
        }
    }

    /**
     * Cette methode affiche les elements de types du tab. Parcoure
     * le tableau.
     * Si choix est egal a celui de la methode choixTypeObjet(). La
     * methode affiche le tableau des objets perdus trois par trois.
     * 1.Si par3 n'est pas zero et n'egale pas a zero quand on le divise
     * par 3, la methode affiche un message demandant au utilisateur.
     * Si i est egal tab.length, le methode affiche un message
     */
    public static void rechercheParType(ObjetPerdu[] tab){
        afficherSousMenuTypeObjet();
        int choix = choixTypeObjet();
        int par3 = 0;
        int f = 0;
        for (int i = 0; i < tab.length; i++ ){
            try{
                if( choix == (tab[i].getType())){
                    System.out.println(tab[i]);
                    System.out.println("-----------------------------");
                    par3++;
                    if(par3 != 0 && par3 % 3 == 0){
                        pause();
                    }
                } else if (i == tab.length){
                    System.out.println("AUCUN OBJET PERDU NE REPOND A CE CRITERE.");
                }
            }catch (NullPointerException e) { //gestion locale de l'exception

            }
            f++;
            if(f == tab.length){
                System.out.println("AUCUN OBJET PERDU NE REPOND A CE CRITERE.");
            }
        }
    }

    /**
     * Ecrire les elements contenues dans le tableau 
     * donnne en parametre dans le fichier OBJ_PERDUS.
     * @return tab le tableau casiers a ecrire 
     * dans le fichier OBJ_PERDUS.
     */
    public static void ecrireObjPerdu(ObjetPerdu[] tab) {
        //constantes qu'on va ecrire dans le fichier
        FileWriter ficAEcrire;
        PrintWriter sortie;
        try {
            ficAEcrire = new FileWriter(CHEMIN_FIC); //IOException possible
            sortie = new PrintWriter(ficAEcrire);
            //Ecriture dans le fichier ficSortie.txt
            //(s'il existe, il sera ecrase)
            sortie.println(tab.length);
            sortie.println(ObjetPerdu.getSequenceNoIdentification());
            for(int i= 0; i < tab.length; i++){
                try{
                    sortie.println(i);
                    sortie.println(tab[i].getNoIdentification());
                    sortie.println(tab[i].getDescription());
                    sortie.println(tab[i].getType());
                    sortie.println(tab[i].getDatePerte());

                }catch (NullPointerException e){
                }
            }

            sortie.close(); //flush : c'est a ce moment que le contenu a
            //ecrire est envoye au fichier
        } catch (IOException ioe) {
            System.out.println("Erreur d'entree/sortie !");
        }
    }

    public static void lireObjPerdu(ObjetPerdu[] tab) throws FileNotFoundException, IOException {
        BufferedReader entree =new BufferedReader(new FileReader(CHEMIN_FIC));
        String ligne;
        ligne = entree.readLine();
        
        while (ligne != null) { 
            ObjetPerdu.setSequenceNoIdentification(Integer.parseInt(ligne.trim()));
            ObjetPerdu[] casiers = new ObjetPerdu[Integer.parseInt(ligne.trim())];
            ligne = entree.readLine();
        }
        entree.close(); //fermer le flux
    }

    public static void main (String[] params){
        int choixMenuPrincipal = 0;
        ObjetPerdu[] casiers = new ObjetPerdu[NBR_CASIERS_INIT]; //creation du tableau
        presentationLogiciel();
        pause();
        do{
            afficherMenuPrincipal(); //presentation du menu principal
            choixMenuPrincipal = choixMenuPrincipal(choixMenuPrincipal);
            ObjetPerdu objet = new ObjetPerdu(); //creation du objet de la classe ObjetPerdu
            if (choixMenuPrincipal == 1){
                objet.setDescription(descriptionObjet()); //modifie la description par la valeur passee en parametre
                objet.setType(choixTypeObjet()); //modifie le choix par la valeur passee en parametre
                objet.setDatePerte(dateDePerte()); //modifie la date par la valeur passee en parametre
                if(nbrCasierLibre(casiers)== 0){
                    casiers = doublerTableau(casiers); //tableau deux fois plus long
                }
                casiers[premiereCasierLibre(casiers)] = objet;
                revenirMenuPrincipal();
            } else if (choixMenuPrincipal == 2){
                casiers = rendreObjetReclame(casiers); 
            } else if (choixMenuPrincipal == 3){
                rechercheMotCle(casiers);
            } else if (choixMenuPrincipal == 4){
                rechercheParDate(casiers);
                revenirMenuPrincipal();
            } else if (choixMenuPrincipal == 5){
                rechercheParType(casiers);
            } else if (choixMenuPrincipal == 6){
                afficheCasiersPar3(casiers);
                revenirMenuPrincipal();
            }

        } while(choixMenuPrincipal != 7);

        //ecrireObjPerdu(casiers);
        //lireObjPerdu();
    }
}
