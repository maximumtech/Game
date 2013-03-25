package Game.entity;


/**
 *
 * @author maximumtech
 */
public interface ICollector {
    
    public boolean onCollect(ICollectable collectable);
    
    public boolean canCollect(ICollectable collectable);
    
}
