import java.util.GregorianCalendar;
import java.util.Calendar;

/**
 * INF1120-40 - Aut 2015. 
 * Cette classe doit etre utilisee pour le TP3 et ne doit pas etre modifiee.
 *
 * @author melanie lord
 * @version 2015
 */
public class Date {

   /**
    * *** Constantes de classe ****
    */
   
   public static final int ANNEE_MIN = 1900; //annee min valide
   public static final int MOIS_MIN = 1;     //mois min valide
   public static final int MOIS_MAX = 12;    //mois max valide
   public static final int JOUR_MIN = 1;     //jour min valide
   public static final int JOUR_MAX = 31;    //jour max valide

   /**
    * *** Attributs d'instance ****
    */
   
   private int annee;  //l'annee de la date
   private int mois;   //le mois de la date
   private int jour;   //le jour de la date

   /**
    * Construit un objet Date avec le jour = 1, le mois = 1, et l'annee =
    * ANNEE_MIN.
    */
   public Date() {
      this.jour = 1;
      this.mois = 1;
      this.annee = ANNEE_MIN;
   }

   /**
    * Constructeur qui initialise les attributs de cette date avec les valeurs
    * passees en parametres. Ce constructeur ne verifie pas que la date creee
    * est valide.
    *
    * @param jour le jour de cette Date.
    * @param mois le mois de cette Date.
    * @param annee l'annee de cette Date.
    */
   public Date(int jour, int mois, int annee) {
      this.jour = jour;
      this.mois = mois;
      this.annee = annee;
   }

   /**
    * *** getters ****
    */
   
   /**
    * Permet d'obtenir l'annee de cette date.
    *
    * @return int l'annee de cette date.
    */
   public int getAnnee() {
      return annee;
   }

   /**
    * Permet d'obtenir le mois de cette date.
    *
    * @return int le mois de cette date.
    */
   public int getMois() {
      return mois;
   }

   /**
    * Permet d'obtenir le jour de cette date.
    *
    * @return int le jour de cette date.
    */
   public int getJour() {
      return jour;
   }

   /**
    * *** setters ****
    */
   
   /**
    * Permet de modifier l'annee de cette date.
    *
    * @param annee la nouvelle annee de cette date.
    */
   public void setAnnee(int annee) {
      this.annee = annee;
   }

   /**
    * Permet de modifier le mois de cette date.
    *
    * @param mois le nouveau mois de cette date.
    */
   public void setMois(int mois) {
      this.mois = mois;
   }

   /**
    * Permet de modifier le jour de cette date.
    *
    * @param jour le nouveau jour de cette date.
    */
   public void setJour(int jour) {
      this.jour = jour;
   }

   /**
    * *** Autres methodes d'instance publiques ****
    */
   
   /**
    * Verifie que le jour, le mois, et l'annee de cette date forment  
    * une date valide. Une date est consideree valide si son mois est entre 
    * 1 et 12 inclusivement, si son annee est superieure ou egale a 
    * ANNEE_MIN et si son jour est valide selon son mois et selon que 
    * son annee est bissextile ou non.
    *
    * @return true si la date est valide, false sinon.
    */
   public boolean estDateValide() {
      return annee >= ANNEE_MIN
              && mois >= MOIS_MIN && mois <= MOIS_MAX
              && jourValide();
   }

   /**
    * Construit une representation de cette Date sous forme de
    * chaine de caracteres, sous le format jj/mm/aaaa.
    *
    * @return cette date sous forme de chaine de caracteres.
    */
   @Override
   public String toString() {
      String codeJour = "" + jour;
      String codeMois = "" + mois;

      if (jour < 10) {
         codeJour = "0" + jour;
      }
      if (mois < 10) {
         codeMois = "0" + mois;
      }
      return (codeJour + "/" + codeMois + "/" + annee);
   }

   /**
    * Teste si cette date est egale a la date passee en
    * parametre.
    *
    * @param autreDate la date a comparer avec cette date.
    * @return true si cette date est egale a autreDate, false sinon.
    */
   public boolean estEgale(Date autreDate) {
      return (annee == autreDate.annee && mois == autreDate.mois 
              && jour == autreDate.jour);

   }

   /**
    * Teste si cette date est plus petite que la date passee en
    * parametre. Si date1 est plus petite que date2, c'est que date1 vient
    * avant date2.
    *
    * @param autreDate la date a comparer avec cette date.
    * @return true si cette date est strictement plus petite que autreDate, 
    *         false sinon.
    */
   public boolean estPlusPetite(Date autreDate) {
      boolean avant = false;

      if (annee < autreDate.getAnnee()) {
         avant = true;
      } else if (annee == autreDate.getAnnee()) {
         if (mois < autreDate.getMois()) {
            avant = true;
         } else if (mois == autreDate.getMois()) {
            if (jour < autreDate.getJour()) {
               avant = true;
            }
         }
      }
      return avant;
   }

   /**
    * ******* Methode de classe publique **********
    */
   
   /**
    * Cette methode retourne la date courante (la date du jour).
    *
    * @return une date initialisee a la date du jour.
    */
   public static Date dateDuJour() {
      Date dJour = new Date();
      Calendar calendar = new GregorianCalendar();
      dJour.setAnnee(calendar.get(Calendar.YEAR));
      dJour.setMois(calendar.get(Calendar.MONTH) + 1);
      dJour.setJour(calendar.get(Calendar.DATE));
      return dJour;
   }

   /**
    * ******* Methodes d'instance privees *********
    */
   
   /**
    * Cette methode verifie si l'annee de cette date est bissextile.
    *
    * @return true si l'annee de cette Date est bissextile, false sinon.
    */
   private boolean estBissex() {
      return (annee % 400 == 0)
              || (annee % 4 == 0 && annee % 100 != 0);
   }
   
   /**
    * Determine si le jour de cette date est valide selon le mois de cette
    * date et selon que l'annee de cette date est bissextile ou non.
    * @return true si le jour de cette date est valide, false sinon.
    */
   private boolean jourValide() {
      boolean valide;
      
      if (jour < JOUR_MIN) {
         valide = false;
      } else {
         //mois de 31 jours
         if (mois == 1 || mois == 3 || mois == 5 || mois == 7
                 || mois == 8 || mois == 10 || mois == 12) {
            valide = jour <= JOUR_MAX;  

         //mois de fevrier
         } else if (mois == 2) {
            valide = (estBissex() && jour <= JOUR_MAX - 2) //29 jours
                    || (jour <= JOUR_MAX - 3);  //28 jours

         //mois de 30 jours
         } else {
            valide = jour <= JOUR_MAX - 1; //30 jours
         }
      }
      return valide;
   }
}
