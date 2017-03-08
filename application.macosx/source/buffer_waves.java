import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class buffer_waves extends PApplet {


Minim minim;
AudioInput in;

int cols, rows;
int scl = 10;
int w = 500;
int h = 500;
float flying = 0;
float[][] terrain;

public void setup(){
   
 minim = new Minim(this);
 in = minim.getLineIn();
 
 w=in.bufferSize() -1;

cols = w / scl;
rows = h/ scl;
terrain = new float[cols][rows];
strokeWeight(1);
fill(0);
stroke(255);
noFill();
}

public void draw(){
  background(0);
  lights();
  //translate(0,frameCount,frameCount);
  float yoff = flying;
for(int y = rows-1; y >= 0; y--){
  for(int x = 0; x < cols; x++){
     if(y==0)terrain[x][y] = in.left.get(x)*50+in.right.get(x)*50;
     else{
       terrain[x][y] = terrain[x][y-1];
  }
  yoff += 0.01f;
}}
flying += 0.05f;
translate(width/2, height/2);
rotateX(PI/3);

translate(-w/2,-h/2);
for(int y = 0; y < rows-1; y++){
  beginShape(TRIANGLE_STRIP);
for(int x = 0; x < cols; x++){
vertex(x*10, y*scl*2, terrain[x][y]);
}
endShape();
}
}
  public void settings() {  fullScreen(P3D);  pixelDensity(displayDensity()); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--hide-stop", "buffer_waves" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
