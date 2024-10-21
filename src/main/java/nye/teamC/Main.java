package nye.teamC;

import nye.teamC.Managers.GameManager;

public final class Main
{
    private Main()
    {

    }

    public static void main(final String[] args)
    {
        GameManager gameManager = new GameManager();
        gameManager.start();
        while (!gameManager.gameEnded())
        {
            String commandName = gameManager.getScanner().next();
            gameManager.useCommand(commandName);
        }
    }
}
