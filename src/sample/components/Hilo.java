package sample.components;

public class Hilo extends Thread
{
    public Hilo(String name)
    {
        setName(name);
    }
    @Override
    public void run()
    {
        super.run();
        for(int i=1;i<=10;i++)
        {
            System.out.println("Vuelta "+i+" -> "+getName());
            try
            {
                sleep((long)(Math.random()*5000));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("Llegaste a la meta -> "+getName());
    }
}
