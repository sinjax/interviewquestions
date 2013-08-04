import java.util.List;


public class Gaussian {

	private double middle;
	private double amplitude;
	private double std;

	public Gaussian(double middle, double amplitude, double std) {
		this.middle = middle;
		this.amplitude = amplitude;
		this.std = std;
	}
	
	public String toString(){
		return "x =" + this.middle + ", a=" + this.amplitude + ", std=" + this.std;
	}
	
	public double getValueAt(int x){
		double top = (x - this.middle) * (x - this.middle);
		return (this.amplitude * Math.exp(- top/(2.0 * this.std * this.std) ));
	}
	
	public static int getSummedValue(List<Gaussian> gs, int x){
		int sum = 0;
		for(Gaussian g : gs){
			sum+=g.getValueAt(x);
		}
		return sum;
	}
	
	public static void main(String args[]){
		double std = Math.sqrt(0.5);
		double x = -2;
		double amp = 1.0/ (std * Math.sqrt(2 * Math.PI));
		Gaussian g = new Gaussian(x,amp,std);
		int[] vals = new int[20];
		for(int i = -10; i < 10; i++){
			System.out.println(g.getValueAt(i));
			vals[i + 10] = (int) (g.getValueAt(i) * 10.0);
		}
		
		Histogram h = new Histogram(vals);
		h.draw();
	}

}
