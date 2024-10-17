package nye.teamC.Command;

import nye.teamC.Map;

public class CreateCommand implements ICommand, IMapGetter
{
    Map map;

    @Override
    public String Name()
    {
        return "Create";
    }

    @Override
    public String Usage()
    {
        return "Create Width;Height";
    }

    @Override
    public boolean Execute(String args)
    {
        try
        {
            var dimension = args.split(";");
            int w = Integer.parseInt(dimension[0]);
            int h = Integer.parseInt(dimension[1]);
            map = new Map(w, h);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public Map GetMap()
    {
        return map;
    }
}
