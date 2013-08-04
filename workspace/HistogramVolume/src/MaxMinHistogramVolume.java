
public class MaxMinHistogramVolume implements HistogramVolume{
	
	private boolean[] seen;

	public MaxMinHistogramVolume(){
		
	}
	
	public void setup(Histogram h){
		this.seen = new boolean[h.values.length];
	}
	
	class MaxHolder{
		int maxValLeft;
		int maxXLeft;
		
		int maxValRight;
		int maxXRight;

		MaxHolder(){
			reset();
		}

		void reset() {
			maxValLeft = -Integer.MAX_VALUE;
			maxXLeft = -1;
			maxValRight = -Integer.MAX_VALUE;
			maxXRight = -1;
			
		}

		public void noteLeft(int x, int v) {
			if(v > maxValLeft){
				maxXLeft = x;
				maxValLeft = v;
			}
		}
		
		public void noteRight(int x, int v) {
			if(v > maxValRight){
				maxXRight = x;
				maxValRight = v;
			}
		}
		
	}
	@Override
	public int volume(Histogram h) {
		setup(h);
		int volume = 0;
		MaxHolder holder = new MaxHolder();
		this.seen[0] = true;
		for(int x = 1; x < h.values.length; ){
			if(this.seen[x]) continue;
			this.seen[x] = true;
			holder.reset();
			int xVal = h.values[x];
			// look left
			if(h.values[x-1] > xVal) holder.noteLeft(x-1,h.values[x-1]);
			// look right
			for(int rightX = x+1; rightX < h.values.length; rightX++){
				if(h.values[rightX] > xVal) holder.noteRight(rightX,h.values[rightX]);
			}
			
			if(holder.maxXLeft==-1 && holder.maxXRight == -1){
				// Global maxima carry on
				x++;
				continue;
			}
			int seenLeft = 0;
			int seenRight = h.values.length;
			int level = -1;
			
			if( holder.maxXRight != -1){
				seenRight = holder.maxXRight;
			}
			
			if( holder.maxXLeft != -1){
				seenLeft = holder.maxXLeft;
			}
			if(holder.maxXLeft != -1 && holder.maxXRight != -1){
				level = Math.min(holder.maxValLeft, holder.maxValRight);
				for(x = seenLeft+1; x < seenRight; x++){
					if(h.values[x] >= level){break;}
					this.seen[x] = true;
					volume += level - h.values[x];
				}
			}
			else{
				x++;
			}
			
		}
		return volume;
	}

	@Override
	public int maxHeightAt(Histogram h, int x) {
		MaxHolder holder = new MaxHolder();
		int xVal = h.values[x];
		// look left
		for(int leftX = x-1; leftX >= 0; leftX--){
			if(h.values[leftX] > xVal) holder.noteLeft(leftX,h.values[leftX]);
		}
		// look right
		for(int rightX = x+1; rightX < h.values.length; rightX++){
			if(h.values[rightX] > xVal) holder.noteRight(rightX,h.values[rightX]);
		}
		if(holder.maxXLeft!=-1 && holder.maxXRight != -1){
			return Math.min(holder.maxValLeft, holder.maxValRight);
		}
		return xVal;
	}

}
