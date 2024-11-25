package nye.teamC.Command;

import nye.teamC.Managers.LeaderBoardManager;

public final class LeaderBoardPrintCommand implements ICommand
{
    @Override
    public String name()
    {
        return "LeaderBoard";
    }

    @Override
    public String usage()
    {
        return "Leaderboard";
    }

    @Override
    public boolean execute(final String args)
    {
        LeaderBoardManager.print();
        return true;
    }

    @Override
    public boolean hasArgs()
    {
        return false;
    }
}
