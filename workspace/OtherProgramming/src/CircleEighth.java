import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CircleEighth {
	public static final double deltaTheta = Math.PI/100;

	public Point2D[] drawEighthOfCircle(int radius){
		Point2D[] out = new Point2D[(int) ((Math.PI/4) / deltaTheta) + 1];
		int i = 0;
		for(double theta = 0; theta <= Math.PI/4 ; theta+=deltaTheta){
			double x = Math.sin(theta) * radius;
			double y = Math.cos(theta) * radius;
			
			out[i++] = new Point2D.Double(x,y);
 		}
		return out;
	}
	
	public void drawAsciiCircle(Point2D[] valid, int cx, int cy,int width, int height){
		Map<Double, List<Point2D>> orderedPoints = organiseByY(valid,cx,cy);
		for(int y = 0; y < height; y++){
			List<Point2D> points = orderedPoints.get(new Double(y));
			if(points == null){
				for(int x = 0; x < width; x++){
					System.out.print("   ");
				}
			}
			else{
				int[] filledXPoints = filledX(points);
				for(int x = 0; x < width; x++){
					if(Arrays.binarySearch(filledXPoints, x) <= 0)
						System.out.print("   ");
					else
						System.out.print("\u25A0\u25A0\u25A0");
				}
			}
			
			System.out.println();
		}
		
	}

	private int[] filledX(List<Point2D> points) {
		int[] out = new int[points.size()];
		int i = 0;
		for(Point2D x : points){
			out[i++] = (int) Math.round(x.getX()); 
		}
		return out;
	}

	private Map<Double, List<Point2D>> organiseByY(Point2D[] valid, int cx, int cy) {
		Map<Double,List<Point2D>> ret = new HashMap<Double,List<Point2D>>();
		for(Point2D p1 : valid){
			for(Point2D p : reflectAllPoints(p1,cx,cy)){
				List<Point2D> points = null;
				Double pY = (double) Math.round(p.getY());
				if(!ret.containsKey(pY)){
					points = new ArrayList<Point2D>();
					ret.put(pY, points);
				}
				else{
					points = ret.get(pY);
				}
				points.add(p);
			}
		}
		
		return ret;
	}
	
	private List<Point2D> reflectAllPoints(Point2D p, int cx, int cy) {
		List<Point2D> out = new ArrayList<Point2D>(8);
		double x = p.getX();
		double y = p.getY();
		out.add(new Point2D.Double(x+cx,y+cy));
		out.add(new Point2D.Double(x+cx,-y+cy));
		out.add(new Point2D.Double(-x+cx,y+cy));
		out.add(new Point2D.Double(-x+cx,-y+cy));
		out.add(new Point2D.Double(y+cx,x+cy));
		out.add(new Point2D.Double(y+cx,-x+cy));
		out.add(new Point2D.Double(-y+cx,x+cy));
		out.add(new Point2D.Double(-y+cx,-x+cy));
		return out;
	}

	public static void main(String args[]){
		CircleEighth c = new CircleEighth();
		Point2D[] circlePoints = c.drawEighthOfCircle(5);
		c.drawAsciiCircle(circlePoints, 10, 10, 20, 20);
	}
}
