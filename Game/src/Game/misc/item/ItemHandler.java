
package Game.misc.item;

import Game.base.GameBase;
import Game.base.World;
import Game.entity.Entity;
import Game.entity.EntityItem;
import Game.entity.EntityPlayer;
import Game.misc.MathHelper;
import Game.render.gui.ScreenWorld;

/**
 *
 * @author Richard Roe
 */
public class ItemHandler {
    
    /////////////////////////////////////////////////////
    //  Useless class that does not work. Do not use.  //
    /////////////////////////////////////////////////////
    
    public ItemHandler(){}
    
    /**
     * @param EntityItem 
     */
    public void handleItem(EntityItem var1) {
        EntityPlayer p = this.getNearestPlayerToItem(var1);
        if(p != null) {
            this.moveItem(var1, p);
        }
    }
    
    private void moveItem(EntityItem var1, EntityPlayer player) {
        if(player.getBlockX() - var1.getBlockX() < 0) {
           var1.setPosition(var1.getBlockX() - 1, var1.getBlockY()); 
        }else {
           var1.setPosition(var1.getBlockX() + 1, var1.getBlockY()); 
        }
        
        if(player.getBlockY() - var1.getBlockY() < 0) {
           var1.setPosition(var1.getBlockX(), var1.getBlockY() - 1);  
        }else {
           var1.setPosition(var1.getBlockX(), var1.getBlockY() + 1);  
        }
        
        if(MathHelper.getDistance(var1.getBlockX(), var1.getBlockY(), player.getBlockX(), player.getBlockY()) < 2) {
            ((ScreenWorld)GameBase.renderScreen).world.despawnEntity(var1);
            System.out.println("Giving player Item and despawning entity.");
        }
        
    }
    
    private EntityPlayer getNearestPlayerToItem(EntityItem var1) {
        EntityPlayer entity = null;
        if(GameBase.renderScreen instanceof ScreenWorld) {
            ScreenWorld world = (ScreenWorld)GameBase.renderScreen;
            for(Entity var : world.world.entityList) {
                if(var instanceof EntityPlayer) {
                    if(entity == null) {
                        entity = (EntityPlayer)var;
                    }else {
                        if(MathHelper.getDistance(var1.getBlockX(), var1.getBlockY(), var.getBlockX(), var.getBlockY()) <
                           MathHelper.getDistance(var1.getBlockX(), var1.getBlockY(), entity.getBlockX(), entity.getBlockY())) {
                            entity = (EntityPlayer)var;
                        }
                    }
                }
            }
        }
        return entity;
    }
    
}
