package nye.teamC.Command;

public interface ICommand
{
    String Name();

    String Usage();

    boolean Execute(String args);
}
