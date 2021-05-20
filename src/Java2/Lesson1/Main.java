package Java2.Lesson1;

public class Main {

    public static void main(String[] args) {
        Object[] objects = new Object[3];
        objects[0] = new Man("Сергей",25,true,true,900,1);
        objects[1] = new Cat("Barsik",Color.BLACK,true,true,300,1.5);
        objects[2] = new Robot("Verter",OS.UNIX,true,false,125,1);

        Barrier[] barrier = new Barrier[4];
        barrier[0] = new Wall(1.2);
        barrier[1] = new Treadmill(228);
        barrier[2] = new Wall(0.9);
        barrier[3] = new Treadmill(551);

        for (int objectIndex = 0; objectIndex < objects.length; objectIndex++) {
            for (int barrierIndex = 0; barrierIndex < barrier.length; barrierIndex++) {
                if (objects[objectIndex] instanceof Man) {
                    Man tempObject = (Man) objects[objectIndex];
                    if (tempObject.isNextStep()) {
                        tempObject.makeMovement(barrier[barrierIndex]);
                    }
                }
                if (objects[objectIndex] instanceof Cat) {
                    Cat tempObject = (Cat) objects[objectIndex];
                    if (tempObject.isNextStep()) {
                        tempObject.makeMovement(barrier[barrierIndex]);
                    }
                }
                if (objects[objectIndex] instanceof Robot) {
                    Robot tempObject = (Robot) objects[objectIndex];
                    if (tempObject.isNextStep()) {
                        tempObject.makeMovement(barrier[barrierIndex]);
                    }
                }


            }
        }


    }
}
