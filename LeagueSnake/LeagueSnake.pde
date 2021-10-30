//*
// ***** SEGMENT CLASS *****
// This class will be used to represent each part of the moving snake.
//*

class Segment {

  //Add x and y member variables. They will hold the corner location of each segment of the snake.
  int x;
  int y;

  // Add a constructor with parameters to initialize each variable.
  public Segment(int x, int y) {
    this.x=x;
    this.y=y;
  }
}


//*
// ***** GAME VARIABLES *****
// All the game variables that will be shared by the game methods are here
//*
Segment segmentHead;
int foodX;
int foodY;
int directionOfSnake=UP;
int eatenFood=0;
int MAX_X=500;
int MAX_Y=500;
int SPEED=5;
int MIN_X=0;
int MIN_Y=0;
int foodSize=10;
int headSize=15;
ArrayList<Segment> snakeTail= new ArrayList<Segment>();

//*
// ***** SETUP METHODS *****
// These methods are called at the start of the game.
//*


void setup() {
  size(500, 500);
  segmentHead=new Segment(10, 10);
  frameRate(20);
  dropFood();
}

void dropFood() {
  //Set the food in a new random location
  foodX = ((int)random(50)*10);
  foodY= ((int)random(50)*10);
}



//*
// ***** DRAW METHODS *****
// These methods are used to draw the snake and its food 
//*

void draw() {
  background(237, 139, 91);
  drawFood();
  move();
  drawSnake();
  eat();
}

void drawFood() {
  //Draw the food
  fill(5, 4, 4);
  square(foodX, foodY, foodSize);
}


void drawSnake() {
  //Draw the head of the snake followed by its tail
  fill(96, 185, 84);
  rect( segmentHead.x, segmentHead.y, headSize, headSize);
  manageTail();
}


//*
// ***** TAIL MANAGEMENT METHODS *****
// These methods make sure the tail is the correct length.
//*
void addToTail() {
  Segment temp = new Segment(segmentHead.x, segmentHead.y); 
  snakeTail.add(temp);
}
void drawTail() {
  //Draw each segment of the tail
  // for loop trying to go through each element. 

  fill(96, 185, 84);
  for (int i=0; i<snakeTail.size(); i++) {
    Segment tail= snakeTail.get(i); // retrieving/ access it
    rect(tail.x, tail.y, 15, 15); //take the tail and draw a rec
  }
}

void manageTail() {
  //After drawing the tail, add a new segment at the "start" of the tail and remove the one at the "end" 
  //This produces the illusion of the snake tail moving.
  checkTailCollision();
  drawTail();  
  // 108-109 lines add a new segment for tail 
  addToTail();
  snakeTail.remove(0); // removing the first element
}

void checkTailCollision() {
  //If the snake crosses its own tail, shrink the tail back to one segment
  for (int i=0; i<snakeTail.size(); i++) {
    Segment current= snakeTail.get(i);

    if (segmentHead.x == current.x && segmentHead.y == current.y) {
      eatenFood=0;
      snakeTail.clear();
      addToTail();
      break;
    }
  }
}



//*
// ***** CONTROL METHODS *****
// These methods are used to change what is happening to the snake
//*

void keyPressed() {
  //Set the direction of the snake according to the arrow keys pressed
  if (key!=CODED) {
  } else if (keyCode==UP) {
    directionOfSnake=UP;
  } else if (keyCode==DOWN) {
    directionOfSnake=DOWN;
  } else if (keyCode==LEFT) {
    directionOfSnake=LEFT;
  } else if (keyCode==RIGHT) {
    directionOfSnake=RIGHT;
  }
}


void move() {
  //Change the location of the Snake head based on the direction it is moving.

  switch(directionOfSnake) {
  case UP:
    // move head up here 

    segmentHead.y=segmentHead.y-SPEED;
    break;
  case DOWN:
    segmentHead.y=segmentHead.y+SPEED;
    // move head down here 
    break;
  case LEFT:
    segmentHead.x=segmentHead.x-SPEED;
    // figure it out 
    break;
  case RIGHT:
    // mystery code goes here 
    segmentHead.x=segmentHead.x+SPEED;
    break;
  }
  checkBoundaries();
}

void checkBoundaries() {
  //If the snake leaves the frame, make it reappear on the other side
  if (segmentHead.x>=MAX_X) {
    segmentHead.x=MIN_X;
  } else if (segmentHead.y>=MAX_Y) {
    segmentHead.y=MIN_Y;
  } else if (segmentHead.x<=MIN_X) {
    segmentHead.x=MAX_X;
  } else if (segmentHead.y<=MIN_Y) {
    segmentHead.y=MAX_Y;
  }
}



void eat() {
  //When the snake eats the food, its tail should grow and more food appear
  boolean hit=isHit();
  if (hit) { 
    eatenFood=eatenFood+1;
    // only if the snake eats the food a new segment will be added and then a new food will appear
    addToTail();
    dropFood();
  }
}
boolean isHit() { 
  boolean hit=false;
  int delta=5;
  int headCenterX=segmentHead.x+5;
  int headCenterY=segmentHead.y+5;
  int deltaX= abs(headCenterX-foodX);
  int deltaY= abs(headCenterY-foodY);
  if ((deltaX<=delta) && (deltaY<=delta)) {
    hit=true;
  } else if (deltaX>delta) {
    //  System.out.print("dX: "+deltaX);
    // System.out.print("  sHX: "+segmentHead.x);
    //  System.out.println("  fx: "+foodX);
  } else if (deltaY>delta) {
    // System.out.print("dY: "+deltaY);
    // System.out.print("  sHY: "+segmentHead.y);
    //  System.out.println("  fY: "+foodY);
  }
  return hit;
}

void checkHeadPos() {
  //for(){}
}

boolean isFoodEaten(int x, int y) { //going through food x's and y's, if it makes a match they got a head 
  boolean eaten=false;
  int xMin=foodX;
  int xMax=xMin+foodSize;
  int yMin=foodY;
  int yMax=yMin-foodSize;
  for (int xx= xMin; xx<xMax-1; xx++) {

    for (int yy=yMin; yy<yMax-1; yy++) {
      if ((x==xx) && (y==yy)) {
        eaten=true;
      }
    }
  }
  return eaten;
}
