package csci6461;

import java.util.ArrayList;

/** 
 * Class created to simulate a cache slot
 */
class CacheSlot {
	private String data;
	private int memoryAddress; // It works as a tag
	
	public CacheSlot(String data, int memoryAddress) {
		super();
		this.data = data;
		this.memoryAddress = memoryAddress;
	}

	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getMemoryAddress() {
		return memoryAddress;
	}
	public void setMemoryAddress(int memoryAddress) {
		this.memoryAddress = memoryAddress;
	}
	
}

/** 
 * Class created to manage instructions regarding cache
 */
public class Cache {
	
	//Only can be one instance of cache in the application
	private Cache instance = null;
	//Cache slots, representing the size and data into the cache
	private ArrayList<CacheSlot> cacheSlots;
	//Size of the cache
	private int cacheSize;
	//Flag to handle FIFO
	private int addCounter;
	
	private static int MEMORY_SIZE = 2048;

	/**
	 * 
	 * @param size - Normally 2048 - To make a fully associative cache
	 * 				 with same size than memory in this case.
	 */
	private Cache(int size) {
		
		cacheSize = size;
		cacheSlots = new ArrayList<>();
		for(int i = 0; i < size; i ++){
			cacheSlots.add(null);
		}
		this.addCounter = 0;
	}
	
	/**
	 * Singleton call for cache
	 * @return the cache
	 */
	public Cache getInstance() {
	      if(instance == null) {
	         instance = new Cache(MEMORY_SIZE);
	      }
	      return instance;
   }
	
	/**
	 * Check if cache has in the memory address what the machine needs
	 * @param memoryAddress
	 * @return data from memory address after fetching it from cache
	 * 		   or adding it to the cache if it wasn't stored it. 
	 */
	public String checkCache(int memoryAddress){
		CacheSlot slot = find(memoryAddress);
		if(slot == null){
			return bringMemoryAddressToCache(memoryAddress);
		}
		return slot.getData();
	}
	
	
	/**
	 * Write data to cache 
	 * @param memoryAddress 
	 * @param newData 
	 */
	public void updateData(int memoryAddress, String newData){
		CacheSlot slot = find(memoryAddress);
		//Verify first if the the memory address to be updated is in the cache or not
		if(slot != null){
			writeThrough(memoryAddress, slot, newData);
		} else {
			bringMemoryAddressToCache(memoryAddress);
			slot = find(memoryAddress);
			writeThrough(memoryAddress, slot, newData);
		}
	}
	
	/**
	 * Find if a memory address is in cache
	 * @param memoryAddress
	 * @return cache slot if it's found or null if it's not
	 */
	private CacheSlot find(int memoryAddress) {
		for(int i = 0; i < cacheSize; i++){
			if(cacheSlots.get(i) != null && cacheSlots.get(i).getMemoryAddress() == memoryAddress){
				return cacheSlots.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Find if a memory address is in cache
	 * @param memoryAddress
	 * @return cache position where is found
	 */
	private Integer findPosition(int memoryAddress) {
		for(int i = 0; i < cacheSize; i++){
			if(cacheSlots.get(i) != null && cacheSlots.get(i).getMemoryAddress() == memoryAddress){
				return i;
			}
		}
		return null;
	}

	/**
	 * Put memory information in cache
	 * @param memoryAddress
	 */
	private String bringMemoryAddressToCache(int memoryAddress){
		String data = FrontPanel.memory[memoryAddress].getText();
		CacheSlot slot = new CacheSlot(data, memoryAddress);
		if(cacheSize == MEMORY_SIZE){
			System.out.println("Bringing data from memory postion " + memoryAddress + " to cache position " + memoryAddress);
			cacheSlots.set(memoryAddress, slot);
			return data;
		} else {
			cacheSlots.set(addCounter, slot);
			System.out.println("Bringing data from memory address postion " + memoryAddress + " to position " + addCounter);
			incrementAddCounter();
			return data;
		}
	}
	
	/**
	 * Write to memory (after write to cache)
	 * @param memoryAddress
	 * @param cacheAddress
	 * @param data
	 */
	private void writeThrough(int memoryAddress, CacheSlot slot, String data){
		System.out.println("Writing data to cache postion " + findPosition(memoryAddress));
		slot.setData(data);
		System.out.println("Writing data to memory address postion " + memoryAddress);
		FrontPanel.memory[memoryAddress].setText(slot.getData());
	}

	/**
	 * Increment or initialize flag for FIFO handling in the cache
	 */
	private void incrementAddCounter(){
		//Verify first if cache is full
		if(addCounter == cacheSize - 1){
			addCounter = 0;
		} else {
			addCounter++;
		}
	}
	
}
