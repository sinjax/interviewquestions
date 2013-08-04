
public class Buffer<Data> {
	Data[] data;
	private int index;
	Buffer(Data[] data){
		this.data = data;
		this.index = 0;
	}
	
	public synchronized void add(Data d){
		while(index == data.length - 1){
			try{
				wait();
			}catch(Exception e){
				
			}
		}
		data[index++] = d;
		notifyAll();
	}
	
	public synchronized Data get(){
		while(index == 0){
			try{
				wait();
			}catch(Exception e){
				
			}
		}
		Data toRet = data[--index];
		notifyAll();
		return toRet;
	}
	
}
