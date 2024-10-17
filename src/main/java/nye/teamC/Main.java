package nye.teamC;

import nye.teamC.Managers.GameManager;

public class Main
{
    public static void main(String[] args)
    {
        GameManager gameManager = new GameManager();
        gameManager.Start();
        while (!gameManager.GameEnded())
        {
            String CommandName = gameManager.in.next();
            String CommandParameter = "";
            if (!CommandName.equals("Quit"))
            {
                CommandParameter = gameManager.in.next();
            }
            gameManager.UseCommand(CommandName, CommandParameter);
        }

        //gameManager.UseCommand("Create", "6;7");
    }
}