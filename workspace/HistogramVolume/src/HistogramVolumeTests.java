import java.util.Arrays;

import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.RGBColour;


public class HistogramVolumeTests {
	public static void main(String args[]){
		compareValues();
		System.out.println("mm took: " + stressTest(new MaxMinHistogramVolume()));
		System.out.println("ws took: " +stressTest(new WatershedHistogramVolume()));
	}

	private static double stressTest(HistogramVolume hv) {
		long start,end;
		Histogram h;
		long sum = 0;
		double rounds = 10000;
		for(int i = 0; i < rounds; i++){
			h = Histogram.smoothRandomHistogram(100, 0, 640,3,100);
			start = System.currentTimeMillis();
			hv.volume(h);
			end = System.currentTimeMillis();
			sum += end - start;
		}
		return sum / rounds;
	}

	private static void compareValues() {
		MaxMinHistogramVolume mm = new MaxMinHistogramVolume();
		WatershedHistogramVolume ws = new WatershedHistogramVolume();
		for(int i = 0; i < 3; i++){
			Histogram h = Histogram.smoothRandomHistogram(640, 10, 20,3,100);
			MBFImage im = h.drawWaterFilled(mm);
			double histogramMass = h.sum();
			int water = 0;
			int land = 0;
			for(int y = 0; y < im.getHeight(); y++){
				for(int x = 0; x < im.getHeight(); x++){
					Float[] pix = im.getPixel(x, y);
					if(Arrays.equals(pix,RGBColour.BLUE)){
						water++;
					}
					if(Arrays.equals(pix,RGBColour.GREEN)){
						land++;
					}
				}
			}
			System.out.println("Actual prop: " + water / (double)land);
			DisplayUtilities.display(im);
			System.out.println("MM prop: " + mm.volume(h) / histogramMass);
			System.out.println("WS prop: " + ws.volume(h) / histogramMass);
			
			
		}
	}
}
