/*This code is based on the online resources of github and
 * use potential functions and other openGL functions to 
 * design the fish,shark,and food to interact with each
 * other and to control by commands. This model is mostly based on the
 * lab7 about the fish with more aspects to consider.
 * 
 */

import javax.media.opengl.*;
import com.jogamp.opengl.util.*;
import java.util.*;

public class Vivarium
{
  private Tank tank;
  public Fish fish;
  public Shark shark;
  public ArrayList<Food> foodlist;
  public ArrayList<Fish> fishlist;
  private boolean foodAdded;
  private boolean fishAdded;
  
  public Vivarium()
  {
    tank = new Tank( 4.0f, 4.0f, 4.0f );
    fish = new Fish(0, 0, 0, 0.1f, 0.5f,this);
    shark = new Shark(-2, 0, -1, 1.3f, 0.5f, this);
    foodlist = new ArrayList<Food>();
    fishlist = new ArrayList<Fish>();
    foodAdded = false;
    fishAdded = false;
 
  }

  public void init( GL2 gl )
  {
    tank.init( gl );
    fish.init(gl);
    fish.addPredator(shark);
    shark.addPrey(fish);
    shark.init( gl );
    for (Food food: foodlist) {
    	food.init( gl );
    }
  }

  public void update( GL2 gl )
  {
    tank.update( gl );
    if (fishAdded) {
    	fish.init(gl);
    	fishAdded = false;
    }else{
    	
    	fish.update(gl);
    }
    shark.update(gl);
    
/*    for (ListIterator<Fish> iter = this.fishlist.listIterator(); iter.hasNext();){
    	Fish fish = iter.next();
    	if (fish.isEaten()) {
    		iter.remove();
    	}
    }*/
    if (foodAdded) {
    	for (Food food : foodlist) {
    		food.init(gl);
    	}
    	foodAdded = false;
    }
    
    for (Food food : foodlist) {
    	food.update(gl);
    }
    
    for (ListIterator<Food> iter = this.foodlist.listIterator(); iter.hasNext();) {
    	Food f = iter.next();
    	if (f.isEaten()) {
    		iter.remove();
    	}
    }
  }

  public void draw( GL2 gl )
  {
    tank.draw( gl );
    shark.draw( gl );
    fish.draw(gl);
    for (Food food : foodlist) {
    	food.draw(gl);
    }
  }
  public void addFood() {
	  Food f = new Food();
	  foodlist.add(f);
	  foodAdded=true;
  }
  public void newFish() {
	  fish = new Fish(0, 0, 0, 0.1f, 0.5f,this);
	  fishAdded = true;
	  shark.addPrey( fish );
	  fish.addPredator(shark);
  }
}

