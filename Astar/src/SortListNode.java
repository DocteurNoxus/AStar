import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SortListNode{

	public Set<Point> sortNode(List<Point> list) {

		Set<Point> result = new TreeSet<>();
		for (Point p : list) {
			result.add(p);
		}
		return result;
	}
}