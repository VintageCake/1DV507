package ls223qx_lab4.Priotiry_Queues_and_Binary_Heaps;

public interface Task<T> {
		public Object getTask(); // 'Tasks' are universal, can be any data type
		public void setTask(T t);
		
		public String getDescription();
		public void setDescription(String s);
	
		public int getPriority();
		public void setPriority(int p);
		public int hashCode();
		public boolean equals(Object t);
}
