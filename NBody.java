public class NBody {
	public static double readRadius(String argg) {
		/*if (!argg) {
			System.out.println("Please supply the name of .txt file ");
			System.exit(0);
		}
		*/

		In in = new In(argg);
		double radius = 0.0;

		//while (!in.isEmpty()) {
			int numplanets = in.readInt();
			radius = in.readDouble();

		//}
		return radius;
	}
	public static Planet[] readPlanets(String name) {
		In in = new In(name);
		double radius = 0.0;
		int numplanets = 0;
		numplanets = in.readInt();
		radius = in.readDouble();
		Planet[] arr = new Planet[numplanets];
		double xx, yy, xy, yx,jk;
		String nm;
		for (int i = 0; i < numplanets; ++i) {

			xx = in.readDouble();
			yy = in.readDouble();
			xy = in.readDouble();
			yx = in.readDouble();
			jk = in.readDouble();
			nm = in.readString();
			arr[i] = new Planet(xx,yy,xy,yx,jk,nm);
		}
		//while (!in.isEmpty()) {
			//int numplanets = in.readInt();
		

		//}
		return arr;
	}
	public static void drawField(String nam) {
		StdDraw.setScale(-1*readRadius(nam), readRadius(nam));
		StdDraw.picture(75, 75, "images/starfield.jpg");
	}
	public static void main(String args[]) {
		
		double T, dt;
		T = Double.parseDouble(args[0]);
		dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] myPlanets = readPlanets(filename);
		
		StdDraw.enableDoubleBuffering();
		double curtime = 0;
		System.out.println(T);
		System.out.println(dt);
		while (curtime < T) {
			double[] xForces = new double[myPlanets.length];
			double[] yForces = new double[myPlanets.length];
			for (int i = 0; i < myPlanets.length; ++i) {
				xForces[i] = myPlanets[i].calcNetForceExertedByX(myPlanets);
				yForces[i] = myPlanets[i].calcNetForceExertedByY(myPlanets);
				myPlanets[i].update(dt, xForces[i], yForces[i]);

			}
			drawField(filename);
			for(int i = 0; i < myPlanets.length; ++i) {
				myPlanets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			curtime +=dt;
		}
		System.out.println("hello");

	}

}