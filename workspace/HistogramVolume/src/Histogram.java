
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.RGBColour;
import org.openimaj.math.geometry.shape.Rectangle;


public class Histogram {

	int[] values;

	public Histogram(int[] values){
		this.values = values;
	}
	
	public static Histogram smoothRandomHistogram(int length, int min, int max, int nPeaks, int maxSTD){
		int[] values = new int[length];
		Random r = new Random();
		int nGaussians = nPeaks;
		List<Gaussian> listOGaussians = new ArrayList<Gaussian>();
		for(int i = 0; i < nGaussians; i++){
			int amplitude = min + r.nextInt(max - min);
			int middle = r.nextInt(length/nGaussians) + (length / nGaussians) * i;
			int std = r.nextInt(length/2);
			Gaussian g = new Gaussian(middle, amplitude, std);
//			System.out.println("Adding gaussian: " + g);
			listOGaussians.add(g);
		}
		
		for(int i = 0; i < length; i++){
			values[i] = Gaussian.getSummedValue(listOGaussians, i);
		}
		return new Histogram(values);
	}

	public MBFImage draw() {
		int w = 640;
		int h = 480;
		int paddingRight = 0;
		int paddingTop = 20;
		MBFImage f = new MBFImage(w,h,3);
		
		int barWidthWithPadding = w / this.values.length;
		int barWidth = barWidthWithPadding - paddingRight;
		
		
		int maxBar = this.max();
		double unitHeight = (h - paddingTop)/(double)maxBar;
		for(int i = 0; i < values.length; i++){
			int bar = values[i];
			int barHeight = (int) (unitHeight * bar);
			int barX = i * barWidthWithPadding;
			int barY = h - barHeight;
			f.drawShapeFilled(new Rectangle(barX,barY,barWidth,barHeight), RGBColour.RED);
		}
		return f;
	}
	
	public MBFImage drawWaterFilled(HistogramVolume vol) {
		int w = 640;
		int h = 480;
		int paddingRight = 0;
		int paddingTop = 0;
		MBFImage f = new MBFImage(w,h,3);
		
		int barWidthWithPadding = w / this.values.length;
		int barWidth = barWidthWithPadding - paddingRight;
		
		
		int maxBar = this.max();
		double unitHeight = (h - paddingTop)/(double)maxBar;
		for(int i = 0; i < values.length; i++){
			int bar = vol.maxHeightAt(this, i);
			int barHeight = (int) (unitHeight * bar);
			int barX = i * barWidthWithPadding;
			int barY = h - barHeight;
			f.drawShapeFilled(new Rectangle(barX,barY,barWidth,barHeight), RGBColour.BLUE);
			bar = values[i];
			barHeight = (int) (unitHeight * bar);
			barX = i * barWidthWithPadding;
			barY = h - barHeight;
			f.drawShapeFilled(new Rectangle(barX,barY,barWidth,barHeight), RGBColour.GREEN);
		}
		return f;
	}

	int max() {
		int x = - Integer.MAX_VALUE;
		for(int i : values){
			if(i > x) x = i;
		}
		return x;
	}
	
	public static void main(String args[]){
//		Histogram h = Histogram.smoothRandomHistogram(640, 10, 20,3,100);
//		h.draw();
		Histogram.drawRandomlyForever(640, 10, 20,3,100);
	}

	private static void drawRandomlyForever(int length, int min, int max, int nPeaks, int maxSTD) {
		JFrame waterFilled = null;
		JFrame normal = null;
		HistogramVolume vol = new MaxMinHistogramVolume();
		while(true){
			Histogram h = Histogram.smoothRandomHistogram(length, min, max, nPeaks, maxSTD);
			if(waterFilled == null)
			{
				waterFilled = DisplayUtilities.display(h.drawWaterFilled(vol));
				normal = DisplayUtilities.display(h.draw());
				waterFilled.setBounds(normal.getWidth(), 0, waterFilled.getWidth(), waterFilled.getHeight());
			}
			else
			{
				DisplayUtilities.display(h.draw(),normal);
				DisplayUtilities.display(h.drawWaterFilled(vol),waterFilled);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int sum() {
		int vol = 0;
		for(int x : this.values) vol += x;
		return vol;
	}
}
