package nye.teamC.Command;

import nye.teamC.Map;

public class PrintMapCommand implements ICommand, IMapSetter
{
    Map map;
    @Override
    public String Name()
    {
        return "PrintMap";
    }

    @Override
    public String Usage()
    {
        return "PrintMap";
    }

    @Override
    public boolean Execute(String args)
    {
        map.PrintMap();
        return true;
    }

    @Override
    public void SetMap(Map setmap)
    {
        map = setmap;
    }
}
