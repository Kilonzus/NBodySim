public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public static final double Gvar = 6.67e-11;
	String imgFileName;
	public Planet(Double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public static double sqrt(double val) {
		double accuracy = 100;
		double min = 0;
		double max = val;
		double target = 0;
		while (accuracy !=0) {
               target=(min+max)/2;
               if(target*target==val) 
               {
                       return target;
               }
               else if(target*target > val)

               {
                       max = target-1;
               }
               else
               {
                       min = target+1;
               }
			accuracy--;

		}
		return target;

	}
	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}
	public double calcDistance(Planet g) {
		double distance = 0.0;
		double xdif2 = 0.0;
		double ydif2 = 0.0;

		xdif2 = (g.xxPos - xxPos)* (g.xxPos - xxPos);
		ydif2 = (g.yyPos - yyPos)* (g.yyPos - yyPos);
		distance = sqrt(xdif2+ydif2);


		return distance;
	}
	public double calcForceExertedBy(Planet slim) {
		double res = 0;
		double dis = calcDistance(slim);
		double m_fin = mass * slim.mass;
		res = (Gvar * m_fin)/(dis*dis);

		return res;
	}
	public double calcForceExertedByX(Planet jim) {
		double fx = 0;
		double ftot=  calcForceExertedBy(jim);
		double rad = calcDistance(jim);
		double xdis = 0;
		if(jim.xxPos < xxPos){
			xdis = (jim.xxPos - xxPos);
			fx = (ftot * xdis)/rad;
			fx = fx * -1;
			//xdis = xdis * -1;
		} else {
			xdis = (jim.xxPos - xxPos);
			fx = (ftot * xdis)/rad;
		}
		
		//fx = (ftot * xdis)/rad;

		return fx;
	}
	public double calcForceExertedByY(Planet bilbon) {
		double fy = 0;
		double ftot = calcForceExertedBy(bilbon);
		double rad = calcDistance(bilbon);
		double ydis = 0;
		if(bilbon.yyPos < yyPos){
			ydis = (bilbon.yyPos - yyPos);
			ydis = ydis * -1;
		} else {
			ydis = (bilbon.yyPos - yyPos);
		}
		fy = (ftot * ydis)/rad;


		return fy;
	}
	public double calcNetForceExertedByX(Planet [] arr) {
		double fxtot = 0.0;
		for (int i = 0; i < arr.length; ++i) {
			
			if(!arr[i].equals(this)) {
				fxtot += calcForceExertedByX(arr[i]);
			}
		}

		return fxtot;
	}
	public double calcNetForceExertedByY(Planet [] arrg) {
		double fytot = 0.0;
		for (int i = 0; i < arrg.length; ++i) {

			if(!arrg[i].equals(this)) {
				fytot += calcForceExertedByY(arrg[i]);
			}
		}

		return fytot;
	}
	public void update(double w, double t, double f) {
		double accX = 0.0;
		double accY = 0.0;

		accY = f/this.mass;
		accX = t/this.mass;
		
		this.xxVel = (w*accX) + this.xxVel;
		this.yyVel += (w*accY);

		this.xxPos += (w*this.xxVel);
		this.yyPos += (w*this.yyVel);
	}
	public void draw() {
		String truFilePath = "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, truFilePath);
	}
}