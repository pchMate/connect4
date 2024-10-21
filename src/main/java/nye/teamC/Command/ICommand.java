package nye.teamC.Command;

public interface ICommand
{

    /** Getting CommandName
     * @return The Command Name
     */
    String name();


    /** Getting how the command should be used
     * @return Usage Parameter
     */
    String usage();

    /** Executing the command
     * @param args Command Argument (use empty if not have)
     * @return Success or failure
     */
    boolean execute(String args);

    /** Check the Command has an argument
     * @return True if Has, false if not.
     */
    boolean hasArgs();
}
