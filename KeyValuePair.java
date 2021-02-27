// --== CS400 File Header Information ==--
// Name: Guilhem Ane
// Email: gane@wisc.edu
// Team: Blue
// Group: JD
// TA: Xinyi
// Lecturer: Florian Heimerl
// Notes to Grader: None

public class KeyValuePair<KeyType, ValueType>{
	private KeyType key;
	private ValueType value;

    public KeyValuePair(KeyType key, ValueType value){
	    this.key = key;
	    this.value = value;
    }

    public KeyType getKey(){
	    return key;
    }

    public ValueType getValue(){
	    return value;
    }

    @Override
    public boolean equals(Object o){
	if(!(o instanceof KeyValuePair))
	   return false;
	   
	KeyValuePair other = (KeyValuePair) o;
	return other.getKey().equals(key) && other.getValue().equals(value);
    }

    @Override
    public String toString(){
	return "Key =  "+key+" : Value = "+value;
    }
}
