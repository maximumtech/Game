package Game.base.backend;

/**
 *
 * @author Richard Roe
 * 
 * @see This is an interface designed to be implemented by all lower-end classes that could use
 * any lower level data. IE: This creates a data hierarchy that can easily retrieve data across 
 * the game. DO NOT change this, it'll be crucial for moving on to multiplayer, and for creating 
 * a thread safe environment. 
 * 
 * This marks the beginning of me re-working the base to improve it. Max - Continue adding things, 
 * I'll go in after you and make changes to the backend to keep it working in a safe way. If you
 * don't understand why I do something like I do, DON'T CHANGE IT. Leave it, and I'll explain it 
 * to you when we're both on Skype. There's a method to my madness.
 */
public interface Backend {
    
    public final SingleplayerDebug singlePlayerDebug = new SingleplayerDebug();
    
}
