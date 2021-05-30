package moteur_police;
import java.lang.Object;
import java.lang.Comparable;

public class FaceBundle
extends java.lang.Object
implements java.io.Serializable, java.lang.Comparable {
	
	public int length;
	
	double[] avgF;
    double[][] wk;
    double[][] eigV;
    java.lang.String[] files;
    
	public FaceBundle(double[] avgF,
            double[][] wk,
            double[][] eigV,
            java.lang.String[] files) {
		this.avgF = avgF;
		this.wk = wk;
		this.eigV = eigV;
		this.files = files;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}