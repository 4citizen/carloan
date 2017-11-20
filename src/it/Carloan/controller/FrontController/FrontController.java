package it.Carloan.controller.FrontController;
/*
 * 
 * la funzione nextid aggiorna un id di volta in volta
 * ma se si chiude il software riparte da 1
 * bisogna far si che essa continui dall'ultimo inserito.... 
 * 
 * */
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import javafx.collections.ObservableList;

public class FrontController {
	
	public static String idimpiegato;
	public static String agenziaimpiegato;
	public static String idcontratto;
	
	public static int sottostringacontratto = 0;
	public static int sottostringafissa = 23;
	
	
	public static String mailUtente;

	public static String temponoleggio;
	public static String fasciaauto;
	public static String tipochilometraggio;
	
	public static String agenziaritiro;
	public static String idagenziaritiro;
	public static String agenziaconsegna;
	public static String idagenziaconsegna;	
	
	public static int tariffabase;
	public static int acconto;
	public static int prezzofinale;
	public static int residuo;
	public static int mora;
	
	public static String datainizio;
	public static String datalimite;
	public static String datarientro;
	
	public static String disponibilitaSI;
	public static String disponibilitaNO;
	public static String targaauto;
	public static int kmpercorsi;
	

	public static String chiuso;	
	
	public static int idcontrattounico = 0;
	

	public static int nextId() {
        return idcontrattounico;     
    } 
	
	public static void incrementaid() {
       idcontrattounico = idcontrattounico+1;     
    } 
	
}
