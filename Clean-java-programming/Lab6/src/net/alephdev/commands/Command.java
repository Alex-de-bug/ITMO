package net.alephdev.commands;

public interface Command {
    /**
     * Method called when a command executed
     * @param args Given arguments
     */
    public void execute(String[] args);
    /**
     * Getting a description of command for command list
     * @return Description in command list
     */
    public String getDesctiption();
    /**
     * Getting a required arguments for command
     * @return Argument names
     */
    public String[] getArgumentNames();
}