/**
 * Creat by Alexandre (Noxus)
 * 22/10/2017
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;


public class Main {

	static Point[][] list;
	static int k = 0;
	static ArrayList<Point> depArrivePoint;//liste contenant la position du point de depart et du point d arrivé
	static int resultat = 0;
	static ArrayList<Integer> listNoeudSucces = new ArrayList<>();
	static ArrayList<Point> listPotentielDeNoeud;
	static ArrayList<Point> listVisite;
	static boolean end = false;
	static boolean impossible = false;
	static String listChemin ="";
	/**
	 * Méthode main du programme
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("################Generation de la map################");
		list = new Point[10][10];
		generateContour();
		randomMur();
		depArrivePoint = generateDepartAndArrive();
		afficherMap();
		chercherPoint();
		if(impossible) {
			System.out.println("Impossible a résoudre");
			System.exit(-1);
		}
		else {
			System.out.println("Fin du programme !");
		}
		
	}

	/**
	 * Méthode permettant de trier une liste de Point dans l'ordre croissant
	 * @param laListe
	 */
	private static void TriListe(ArrayList<Point> laListe) {
		Collections.sort(laListe);
	}

	/**
	 * 
	 */
	private static void chercherPoint() {
		Point depart = depArrivePoint.get(0);
		Point arrive = depArrivePoint.get(1);
		foundArrive(depart,arrive);
	}

	/**
	 * Méthode faisant la recherche a partir du point de depart
	 * @param d
	 * @param a 
	 */
	private static void foundArrive(Point d, Point a) {
		listPotentielDeNoeud = new ArrayList<Point>();
		listVisite = new ArrayList<>();
		listVisite.add(d);

		if(d.positiony-1 >0) {
			if(list[d.positionx][d.positiony-1].status!= 3) {
				list[d.positionx][d.positiony-1].gCost += 10;
				list[d.positionx][d.positiony-1].hCost = foundShortArrive(list[d.positionx][d.positiony-1],a);
				list[d.positionx][d.positiony-1].fCost = list[d.positionx][d.positiony-1].gCost + list[d.positionx][d.positiony-1].hCost;
				listPotentielDeNoeud.add(list[d.positionx][d.positiony-1]);
				list[d.positionx][d.positiony-1].pointVisite.add(d);
			}
		}
		if(d.positionx-1 >0) {
			if(list[d.positionx-1][d.positiony].status!= 3) {
				list[d.positionx-1][d.positiony].gCost += 10;
				list[d.positionx-1][d.positiony].hCost = foundShortArrive(list[d.positionx-1][d.positiony],a);
				list[d.positionx-1][d.positiony].fCost = list[d.positionx-1][d.positiony].gCost + list[d.positionx-1][d.positiony].hCost;
				listPotentielDeNoeud.add(list[d.positionx-1][d.positiony]);
				list[d.positionx-1][d.positiony].pointVisite.add(d);
			}
		}

		if(d.positiony+1<=9){
			if(list[d.positionx][d.positiony+1].status!=3) {
				list[d.positionx][d.positiony+1].gCost +=10 ;
				list[d.positionx][d.positiony+1].hCost = foundShortArrive(list[d.positionx][d.positiony+1],a);
				list[d.positionx][d.positiony+1].fCost = list[d.positionx][d.positiony+1].gCost + list[d.positionx][d.positiony+1].hCost;
				listPotentielDeNoeud.add(list[d.positionx][d.positiony+1]);
				list[d.positionx][d.positiony+1].pointVisite.add(d);
			}
		}
		if(d.positionx+1<=9){
			if(list[d.positionx+1][d.positiony].status!=3) {
				list[d.positionx+1][d.positiony].gCost +=10 ;
				list[d.positionx+1][d.positiony].hCost = foundShortArrive(list[d.positionx+1][d.positiony],a);
				list[d.positionx+1][d.positiony].fCost = list[d.positionx+1][d.positiony].gCost + list[d.positionx+1][d.positiony].hCost;
				listPotentielDeNoeud.add(list[d.positionx+1][d.positiony]);
				list[d.positionx+1][d.positiony].pointVisite.add(d);
			}
		}

		if(d.positionx-1 >0 && d.positiony-1 >0) {
			if(list[d.positionx-1][d.positiony-1].status!=3) {
				list[d.positionx-1][d.positiony-1].gCost +=14 ;
				list[d.positionx-1][d.positiony-1].hCost = foundShortArrive(list[d.positionx-1][d.positiony-1],a);
				list[d.positionx-1][d.positiony-1].fCost = list[d.positionx-1][d.positiony-1].gCost + list[d.positionx-1][d.positiony-1].hCost;
				listPotentielDeNoeud.add(list[d.positionx-1][d.positiony-1]);
				list[d.positionx-1][d.positiony-1].pointVisite.add(d);
			}
		}
		if(d.positionx+1<=9&&d.positiony+1<=9){
			if(list[d.positionx+1][d.positiony+1].status!=3) {
				list[d.positionx+1][d.positiony+1].gCost +=14 ;
				list[d.positionx+1][d.positiony+1].hCost = foundShortArrive(list[d.positionx+1][d.positiony+1],a);
				list[d.positionx+1][d.positiony+1].fCost = list[d.positionx+1][d.positiony+1].gCost + list[d.positionx+1][d.positiony+1].hCost;
				listPotentielDeNoeud.add(list[d.positionx+1][d.positiony+1]);
				list[d.positionx+1][d.positiony+1].pointVisite.add(d);
			}
		}
		if(d.positionx-1>0 && d.positiony+1<=9){
			if(list[d.positionx-1][d.positiony+1].status!=3) {
				list[d.positionx-1][d.positiony+1].gCost +=14 ;
				list[d.positionx-1][d.positiony+1].hCost = foundShortArrive(list[d.positionx-1][d.positiony+1],a);
				list[d.positionx-1][d.positiony+1].fCost = list[d.positionx-1][d.positiony+1].gCost + list[d.positionx-1][d.positiony+1].hCost;
				listPotentielDeNoeud.add(list[d.positionx-1][d.positiony+1]);
				list[d.positionx-1][d.positiony+1].pointVisite.add(d);
			}
		}
		if(d.positionx+1<=9 && d.positiony-1>0){
			if(list[d.positionx+1][d.positiony-1].status!=3) {
				list[d.positionx+1][d.positiony-1].gCost +=14 ;
				list[d.positionx+1][d.positiony-1].hCost = foundShortArrive(list[d.positionx+1][d.positiony-1],a);
				list[d.positionx+1][d.positiony-1].fCost = list[d.positionx+1][d.positiony-1].gCost + list[d.positionx+1][d.positiony-1].hCost;
				listPotentielDeNoeud.add(list[d.positionx+1][d.positiony-1]);
				list[d.positionx+1][d.positiony-1].pointVisite.add(d);
			}
		}

		while(!end) {
			TriListe(listPotentielDeNoeud);
			//affiche l'ensemble des points pouvant avoir un chemins qui va jusqu au point final
			for (int i = 0; i < listPotentielDeNoeud.size(); i++) {
				System.out.println("coordonné du point : "+listPotentielDeNoeud.get(i).positionx+" "+listPotentielDeNoeud.get(i).positiony+" avec comme gCost : "+listPotentielDeNoeud.get(i).gCost+" + hCost : "+listPotentielDeNoeud.get(i).hCost+" = fCost : "+listPotentielDeNoeud.get(i).fCost);
			}
			System.out.println("");
			
			//s'il n'y a pas/plus de noeud potentiel, on en déduit que le parcours est impossible
			if(listPotentielDeNoeud.isEmpty()) {
				impossible  = true;
				break;
			}
			//si le noeud en haut de la liste de noeud potentiel est l'arrivée alors on affiche ses coordonnées, la liste de tout les autres points que l'on a parcourut avant d'y arriver et on arrete la boucle
			if(listPotentielDeNoeud.get(0).status == 1) {
				end=true;
				System.out.println("coordonné du point Final : "+listPotentielDeNoeud.get(0).positionx+" "+listPotentielDeNoeud.get(0).positiony+" avec comme valeur : "+listPotentielDeNoeud.get(0).gCost+" ");
				System.out.println("liste du chemin de l'arrive vers le depart :");
				for (int i = 0; i < listPotentielDeNoeud.get(0).pointVisite.size(); i++) {
					getListVisiter(listPotentielDeNoeud.get(0));
					listChemin +=" x : "+listPotentielDeNoeud.get(0).positionx+" y : "+listPotentielDeNoeud.get(0).positiony;
					System.out.println(listChemin);
				}
				break;
			}

			if(end)break;
			checkNode(listPotentielDeNoeud.get(0),a);//on regarde en carre les voisins de ce noeud
			listVisite.add(listPotentielDeNoeud.get(0));//on ajout le noeud qu on va regarder dans la liste des noeuds deja visité

			//Affiche les index
			for (int i = 0; i < listVisite.size(); i++) {
				System.out.println(" index i :"+i+ "  "+listVisite.get(i).positionx +","+listVisite.get(i).positiony+" valeur gCost : "+listVisite.get(i).gCost+" valeur hCost : "+listVisite.get(i).hCost+" = valeur fCost "+listVisite.get(i).fCost);
			}

			if(!listPotentielDeNoeud.isEmpty())
				listPotentielDeNoeud.remove(0);//on supprime le noeuds qu'on vient de regarder de la liste des noeuds potentiel
		}
	}

	/**
	 * Méthode ^permettant d'afficher le chemin prit pour rejoindre le point d'arrivee
	 * @param p
	 */
	private static void getListVisiter(Point p) {
		if(p.pointVisite.isEmpty()) listChemin+=" ";
		else {
			getListVisiter(p.pointVisite.get(0));
			for (int i = 0; i < p.pointVisite.size(); i++) {
				listChemin +="x :"+p.pointVisite.get(i).positionx+" y :"+p.pointVisite.get(i).positiony+" ,";
			}
			
		}
	}
	
	/**
	 * Méthode permettant de regarder le parcours du point paraissant le plus interessant
	 * @param point
	 */
	private static void checkNode(Point d, Point a) {
		if(d.positiony-1 >0) {
			if(list[d.positionx][d.positiony-1].status!= 3 && !listVisite.contains(list[d.positionx][d.positiony-1])&&!listPotentielDeNoeud.contains(list[d.positionx][d.positiony-1])) {
				list[d.positionx][d.positiony-1].gCost = d.gCost + 10;
				list[d.positionx][d.positiony-1].hCost = foundShortArrive(list[d.positionx][d.positiony-1],a);
				list[d.positionx][d.positiony-1].fCost = list[d.positionx][d.positiony-1].gCost + list[d.positionx][d.positiony-1].hCost;
				listPotentielDeNoeud.add(list[d.positionx][d.positiony-1]);
				list[d.positionx][d.positiony-1].pointVisite.add(d);
			}
		}
		if(d.positionx-1 >0) {
			if(list[d.positionx-1][d.positiony].status!= 3 && !listVisite.contains(list[d.positionx-1][d.positiony])&&!listPotentielDeNoeud.contains(list[d.positionx-1][d.positiony])) {
				list[d.positionx-1][d.positiony].gCost = d.gCost + 10;
				list[d.positionx-1][d.positiony].hCost = foundShortArrive(list[d.positionx-1][d.positiony],a);
				list[d.positionx-1][d.positiony].fCost = list[d.positionx-1][d.positiony].gCost + list[d.positionx-1][d.positiony].hCost;
				listPotentielDeNoeud.add(list[d.positionx-1][d.positiony]);
				list[d.positionx-1][d.positiony].pointVisite.add(d);
			}
		}

		if(d.positiony+1<=9){
			if(list[d.positionx][d.positiony+1].status!=3 && !listVisite.contains(list[d.positionx][d.positiony+1])&&!listPotentielDeNoeud.contains(list[d.positionx][d.positiony+1])) {
				list[d.positionx][d.positiony+1].gCost = d.gCost + 10;
				list[d.positionx][d.positiony+1].hCost = foundShortArrive(list[d.positionx][d.positiony+1],a);
				list[d.positionx][d.positiony+1].fCost = list[d.positionx][d.positiony+1].gCost + list[d.positionx][d.positiony+1].hCost;
				listPotentielDeNoeud.add(list[d.positionx][d.positiony+1]);
				list[d.positionx][d.positiony+1].pointVisite.add(d);
			}
		}
		if(d.positionx+1<=9){
			if(list[d.positionx+1][d.positiony].status!=3 && !listVisite.contains(list[d.positionx+1][d.positiony])&&!listPotentielDeNoeud.contains(list[d.positionx+1][d.positiony])) {
				list[d.positionx+1][d.positiony].gCost = d.gCost + 10;
				list[d.positionx+1][d.positiony].hCost = foundShortArrive(list[d.positionx+1][d.positiony],a);
				list[d.positionx+1][d.positiony].fCost = list[d.positionx+1][d.positiony].gCost + list[d.positionx+1][d.positiony].hCost;
				listPotentielDeNoeud.add(list[d.positionx+1][d.positiony]);
				list[d.positionx+1][d.positiony].pointVisite.add(d);
			}
		}

		if(d.positionx-1 >0 && d.positiony-1 >0) {
			if(list[d.positionx-1][d.positiony-1].status!=3  && !listVisite.contains(list[d.positionx-1][d.positiony-1])&&!listPotentielDeNoeud.contains(list[d.positionx-1][d.positiony-1])) {
				list[d.positionx-1][d.positiony-1].gCost = d.gCost + 14;
				list[d.positionx-1][d.positiony-1].hCost = foundShortArrive(list[d.positionx-1][d.positiony-1],a);
				list[d.positionx-1][d.positiony-1].fCost = list[d.positionx-1][d.positiony-1].gCost + list[d.positionx-1][d.positiony-1].hCost;
				listPotentielDeNoeud.add(list[d.positionx-1][d.positiony-1]);
				list[d.positionx-1][d.positiony-1].pointVisite.add(d);
			}
		}
		if(d.positionx+1<=9&&d.positiony+1<=9){
			if(list[d.positionx+1][d.positiony+1].status!=3 && !listVisite.contains(list[d.positionx+1][d.positiony+1])&&!listPotentielDeNoeud.contains(list[d.positionx+1][d.positiony+1])) {
				list[d.positionx+1][d.positiony+1].gCost = d.gCost + 14;
				list[d.positionx+1][d.positiony+1].hCost = foundShortArrive(list[d.positionx+1][d.positiony+1],a);
				list[d.positionx+1][d.positiony+1].fCost = list[d.positionx+1][d.positiony+1].gCost + list[d.positionx+1][d.positiony+1].hCost;
				listPotentielDeNoeud.add(list[d.positionx+1][d.positiony+1]);
				list[d.positionx+1][d.positiony+1].pointVisite.add(d);
			}
		}
		if(d.positionx-1>0 && d.positiony+1<=9){
			if(list[d.positionx-1][d.positiony+1].status!=3 && !listVisite.contains(list[d.positionx-1][d.positiony+1])&&!listPotentielDeNoeud.contains(list[d.positionx-1][d.positiony+1])) {
				list[d.positionx-1][d.positiony+1].gCost = d.gCost + 14;
				list[d.positionx-1][d.positiony+1].hCost = foundShortArrive(list[d.positionx-1][d.positiony+1],a);
				list[d.positionx-1][d.positiony+1].fCost = list[d.positionx-1][d.positiony+1].gCost + list[d.positionx-1][d.positiony+1].hCost;
				listPotentielDeNoeud.add(list[d.positionx-1][d.positiony+1]);
				list[d.positionx-1][d.positiony+1].pointVisite.add(d);
			}
		}
		if(d.positionx+1<=9 && d.positiony-1>0){
			if(list[d.positionx+1][d.positiony-1].status!=3 && !listVisite.contains(list[d.positionx+1][d.positiony-1])&&!listPotentielDeNoeud.contains(list[d.positionx+1][d.positiony-1])) {
				list[d.positionx+1][d.positiony-1].gCost = d.gCost + 14;
				list[d.positionx+1][d.positiony-1].hCost = foundShortArrive(list[d.positionx+1][d.positiony-1],a);
				list[d.positionx+1][d.positiony-1].fCost = list[d.positionx+1][d.positiony-1].gCost + list[d.positionx+1][d.positiony-1].hCost;
				listPotentielDeNoeud.add(list[d.positionx+1][d.positiony-1]);
				list[d.positionx+1][d.positiony-1].pointVisite.add(d);
			}
		}

	}

	/**
	 * Méthode permetant d'obtenir l'heristique d'un point vers l'arrivée
	 * @param d
	 * @param a
	 * @return
	 */
	private static int foundShortArrive(Point d, Point a) {
		int dx= Math.abs(a.positionx-d.positionx);
		int dy= Math.abs(a.positiony-d.positiony);

		return Math.min(dx, dy) * 14 + Math.abs(dx - dy) * 10;
	}

	/**
	 * Méthode génerant le point de départ et le point d arrivé
	 * @return 
	 */
	private static ArrayList<Point> generateDepartAndArrive() {
		ArrayList<Point> listxyDep= new ArrayList<>();
		Random r = new Random();
		int a = 0, b = 0, c = 0, d = 0;

		a = r.nextInt(8)+1;

		b = r.nextInt(8)+1;

		list[a][b].status=0;

		while(true) {
			c =r.nextInt(9);
			if(c!=a && c!=0) {
				break;
			}

		}
		d = r.nextInt(8)+1;
		list[c][d].status=1;

		listxyDep.add(list[a][b]);
		listxyDep.add(list[c][d]);

		return listxyDep;
	}


	/**
	 * Méthode permettant de generer des murs aléatoirement sur la carte (entre 0 et 50)
	 */
	private static void randomMur() {
		Random r = new Random();
		int c = r.nextInt(50);
		int a = 0;
		int b = 0;
		System.out.println("valeur de c : "+c);
		for (int i = 0; i < c; i++) {
			a = r.nextInt(9);
			b = r.nextInt(9);
			list[a][b].setStatus(3);
			list[a][b].setPoids(-1);
			System.out.println("x : "+list[a][b].positionx+" y : "+list[a][b].positiony);
		}
		System.out.println("");
	}

	/**
	 * Méthode générant les contours de la carte
	 */
	private static void generateContour() {
		Point point;
		for(int i = 0; i<list.length;i++) {
			for(int j = 0; j<list.length;j++) {
				point = new Point(i, j, 0);
				if(i==0 || j ==0 || i==list.length-1||j==list.length-1) {
					list[i][j] = point;
					list[i][j].setStatus(3);
					list[i][j].setPoids(-1);;
				}
				else {
					list[i][j] = point;
					list[i][j].setStatus(2);
				}
			}
		}

	}

	/**
	 * Méthode permettant d'afficher la carte
	 * 
	 */
	private static void afficherMap() {
		for(int i = 0; i<list.length;i++) {
			for(int j = 0; j<list.length;j++) {
				switch(list[i][j].status) {
				case 0 : System.out.print("D");
				break;
				case 1 : System.out.print("A");
				break;
				case 2 : System.out.print(".");
				break;
				case 3 : System.out.print("*");
				break;
				}
			}
			System.out.println("");
		}
	}
}
