
class DataMover extends Thread {
//GENERAL VARIBALES
    int TimeOfMove=123;        //time of a move
   static Thread Movers[];              //Thread Array
static int data[];
  static  int NOfThreads;
static int MoveTime;
    //////////////////////////
    private static final Object lock = new Object();


int SleepTime; //SleepTime for thread
int index;
DataMover(int ST,int index){SleepTime=ST;this.index=index;}





public static void main(String[] args) {
    

    //INITIATE MOVERS ARRAY
    if(args.length>0)
    {MoveTime=Integer.valueOf(args[0]);
    NOfThreads=args.length-1;
    Movers= new Thread[NOfThreads];
    data= new int[NOfThreads];
    for (int i =0;i<NOfThreads;i++){
        Movers[i]=new DataMover(Integer.valueOf(args[i+1]),i);
        data[i]=i*1000;
    }




    }
    else 
    {   
        NOfThreads=3;
        MoveTime=123;
        Movers= new Thread[NOfThreads];
            data= new int[NOfThreads];

        Movers[0]=new DataMover(111,0);
        Movers[1]=new DataMover(256,1);
        Movers[2]=new DataMover(404,2);
        data[0]=0;
        data[1]=1000;
        data[2]=2000;
    }






    //////////////////////////Running threads
    for(int i=0;i<NOfThreads;i++){
Movers[i].start();

    }
    try{
for(int i=0;i<NOfThreads;i++){
Movers[i].join();

    }
}
catch (Exception InterruptedException ){

    System.out.println("problem in join");
}
    //////////////////////////Running threads FINISH

    for (int value : data) {
        System.out.println(value);
    }
}

@Override
public void run() {


for (int i=0;i<10;i++){

    ///Initially, the thread does nothing. But sleep

    try {
        sleep(SleepTime);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    ///Initially, the thread does nothing. But sleep FINISH

    //Substract index from data
    synchronized(lock){

    
      
    data[index]=data[index]-index;
    
    System.out.println(getName() + ": Data "+index+ "=="+data[index] );
    }

    ///Second, the thread does nothing. But sleep by move time

    try {
        sleep(MoveTime);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    ///Second, the thread does nothing. But sleep by move timeFINISH
synchronized(lock)
   { if(index+1<NOfThreads){
        data[index+1]+=index;
    System.out.println(getName() + ": Data "+index+ "=="+data[index] );

    }
    else {
        data[0]+=index;


    }
}





//System.out.println(getName()+" "+SleepTime    );
}
}









}