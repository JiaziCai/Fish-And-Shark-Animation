import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.gl2.GLUT;

import java.util.*;

public class Food {
	private GLUT glut;
	private Random rdm;
	
	public float x, y, z;
	public int food_obj;
	private float speed;
	public float radius;
	private boolean eaten;
	private Coord color;
	
	public Food() {
		glut = new GLUT();
		rdm = new Random();
		
		x = rdm.nextFloat()*8-4;
		y = 2.0f;
		z = rdm.nextFloat()*4- 2;
		speed = 0.02f;
		radius = 0.08f;
		eaten = false;
		color = new Coord(1.5f, 0.30f, 0.20f);
	}
	
	public void init(GL2 gl) {
		food_obj = gl.glGenLists(1);
		gl.glNewList(food_obj, GL2.GL_COMPILE);
		glut.glutSolidSphere(radius, 32, 20);
		gl.glEndList();
	}
	
	public void update(GL2 gl) {
		if (y > -1.87f) {
			y -= speed;
		}
		if (x > 2 || x< -2){
			eaten = true;
		}
		if (z> 1.9 || z< -1.9){
			eaten = true;
		}
	}
	
	public void draw(GL2 gl) {
		gl.glPushMatrix();
	    gl.glPushAttrib( GL2.GL_CURRENT_BIT );
	    gl.glTranslatef(x, y, z);
	    gl.glColor3f((float)color.x,(float)color.y,(float)color.z); // red
	    gl.glCallList( food_obj );
	    gl.glPopAttrib();
	    gl.glPopMatrix();
	}
	
	public boolean isEaten() {
		return eaten;
	}
	
	public void eaten() {
		eaten = true;
	}

}