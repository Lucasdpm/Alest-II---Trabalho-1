public enum PathType {
    VERTICAL('-'),
    teste('-'),
    HORIZONTAL('|'),
    LEFT('\\'),
    RIGHT('/');

    private final char value;
    PathType(char value){
		this.value = value;
	}
		
    public int getValue(){
		return value;
	}
}