package nye.teamC;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("yeet");

        Map map = null;
        try
        {
            map = new Map(6, 7);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        map.SetPlayer(1);
        map.SetPlayer(2);
        map.SetPlayer(3);
        map.SetPlayer(4);
        var check = map.Check4();
        if (check.getValue0())
        {
            System.out.println("won: " + check.getValue1());
        }
    }
}