import java.util.ArrayList;

public class Point implements Comparable<Point>{

	int positionx;
	int positiony;
	int status;//status peut prendre 4 valeur :{0 : Depart, 1 : Arrivé, 2 : case normale, 3 : case contenant un mur 
	int poids;
	int valeurPoidsTotal;
	int gCost;//distance a partir du noued du début
	int hCost;//distance qui s'épare ce point du point d arrivé
	int fCost;//gCost+fCost;
	ArrayList<Point> pointVisite;

	public Point(int positionx, int positiony, int status) {
		super();
		this.positionx = positionx;
		this.positiony = positiony;
		this.status = status;
		this.valeurPoidsTotal = 0;
		this.pointVisite = new ArrayList<>();
		this.gCost = 0;
		this.hCost = 0;
		this.fCost = 0;
	}
	
	
	public int getValeurPoidsTotal() {
		return valeurPoidsTotal;
	}

	public void setValeurPoidsTotal(int valeurPoidsTotal) {
		this.valeurPoidsTotal = valeurPoidsTotal;
	}

	public int getgCost() {
		return gCost;
	}

	public void setgCost(int gCost) {
		this.gCost = gCost;
	}

	public int gethCost() {
		return hCost;
	}

	public void sethCost(int hCost) {
		this.hCost = hCost;
	}

	public int getfCost() {
		return fCost;
	}

	public void setfCost(int fCost) {
		this.fCost = fCost;
	}

	public ArrayList<Point> getPointVisite() {
		return pointVisite;
	}

	public void setPointVisite(ArrayList<Point> pointVisite) {
		this.pointVisite = pointVisite;
	}

	public int getPositionx() {
		return positionx;
	}
	public void setPositionx(int positionx) {
		this.positionx = positionx;
	}
	public int getPositiony() {
		return positiony;
	}
	public void setPositiony(int positiony) {
		this.positiony = positiony;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPoids() {
		return poids;
	}
	public void setPoids(int poids) {
		this.poids = poids;
	}

	

	@Override
	public int compareTo(Point arg0) {
		return Integer.compare(fCost, arg0.fCost);
	}

}
