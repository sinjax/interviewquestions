
public class RectangleOverlap {
	public static class Point2d{
		public int x;
		public int y;
		public Point2d(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Point2d plusXY(int width, int height) {
			return new Point2d(x+width,y+height);
		}
		
	}
	public static class Rectangle{
		public Point2d p;
		public int width;
		public int height;
		public Rectangle(int x, int y, int width, int height) {
			this.p = new Point2d(x,y);
			this.width = width;
			this.height = height;
		}
		
		public Point2d tl(){
			return p;
		}
		
		public Point2d tr(){
			return p.plusXY(width,0);
		}
		
		public Point2d br(){
			return p.plusXY(width,height);
		}
		
		public Point2d bl(){
			return p.plusXY(0,height);
		}

		public boolean rightOf(Rectangle b) {
			return this.tl().x > b.tr().x;
		}
		public boolean below(Rectangle b) {
			return this.tl().y > b.bl().y;
		}
		
	}
	public boolean isOverlapping(Rectangle a, Rectangle b){
		return !(a.rightOf(b) || b.rightOf(b) || a.below(b) || b.below(a)); 
	}
}
