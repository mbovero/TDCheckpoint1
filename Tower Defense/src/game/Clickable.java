/**
 * This interface gives the ability for an object to be a Clickable type.
 * Classes that implement this interface include menu button and similar 
 * game objects that require mouse interaction from the user.
 *
 * @author Miles Bovero, Kirt Robinson
 * @version November, 20, 2022
 */
package game;

public interface Clickable
{
    boolean consumeClick(int mouseX, int mouseY);
}
