package nye.teamC.Command;

import nye.teamC.Map;

public final class CreateCommand implements ICommand, IMapGetter
{
    private Map map;

    @Override
    public String name()
    {
        return "Create";
    }

    @Override
    public String usage()
    {
        return "Create Width;Height";
    }

    @Override
    public boolean execute(final String args)
    {
        try
        {
            var dimension = args.split(";");
            int w = Integer.parseInt(dimension[0]);
            int h = Integer.parseInt(dimension[1]);
            map = new Map(w, h);
            return true;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean hasArgs()
    {
        return true;
    }

    @Override
    public Map getMap()
    {
        return map;
    }
}
