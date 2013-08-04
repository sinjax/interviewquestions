
public class WatershedHistogramVolume implements HistogramVolume{

	@Override
	public int volume(Histogram h) {
		int volume = 0;
		int max = h.max();
		
		for(int i = 0; i < max; i++ ){
			int left = -1;
			boolean counting = true;
			for(int right = 0; right < h.values.length; right++){
				if(!counting){
					left = right;
					// inside a histogram area, look for the next gap
					if(h.values[right] < i){
						counting = true;
					}
				}
				else
				{
					// inside a histogram area, look for the next gap
					if(h.values[right] >= i){
						counting = false;
						if(left!=-1){
							volume += right - left;
						}
						left = right;
					}
				}
			}
		}
		return volume;
	}

	@Override
	public int maxHeightAt(Histogram h, int x) {
		return new MaxMinHistogramVolume().maxHeightAt(h, x);
	}
	
}
