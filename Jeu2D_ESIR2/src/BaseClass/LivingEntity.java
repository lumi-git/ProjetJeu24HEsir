package BaseClass;

import Annimation.DamageTag;
import Utils.LifeBar;
import Utils.ReloadBar;
import entity.UsableObject.hands;

import java.awt.*;

//Entity that can have a weapon and life amount.
//by default, it cames with a life bar and a reload bar
public class LivingEntity extends GameObject {

    protected int life;
    protected boolean isInvicible = false;

    public float maxLife = 5;
    //divide by FPS
    protected int InvincibilityFrames = 240;
    protected int invincibilityCounter ;
    protected Weaponbase m_weapon;
    protected LifeBar m_lifeBar;
    protected ReloadBar m_reloadBar;
    protected boolean hasReloadBar = false;//toogle it in inheriting classes
    protected boolean hasLifeBar = false;//toogle it in inheriting classes
    public LivingEntity( boolean Collidable) {
        super(Collidable);
        //initialize attributes
        m_lifeBar = new LifeBar(this);
        m_reloadBar = new ReloadBar(this);

        invincibilityCounter = 0;
        m_weapon = new hands( false, this);

    }

    //call this methode to attack this entity from another entity
    //permite to set the entity invincible.
    public void RecieveAttak(float damage) {
        if(!isInvicible){

            life -= damage;

            new DamageTag(this.getRect(),damage);

            setIsInvicible(true);
        }

    }

    public Weaponbase getWeapon() {
        return m_weapon;
    }


    //call this methode matching with the wanted behavior of the entity, it will cann the use methode of the weapon use by the entity
    public void attack() {
        m_weapon.Use();
    }

    public void update(){

        super.update();
        invincibilityCounter++;
        if(invincibilityCounter>=InvincibilityFrames){
            setIsInvicible(false);
            invincibilityCounter=0;
        }
        m_lifeBar.update();
        m_reloadBar.update();
    }


    public void draw(Graphics2D g2) {
        super.draw(g2);
        if(hasLifeBar)
            m_lifeBar.draw(g2);
        if(hasReloadBar)
            m_reloadBar.draw(g2);
        m_weapon.draw(g2);
    }

    //to Implement in inheriting classes
    public LivingEntity getTarget() {
        return null;
    }


    public int getLife() {
        return life;
    }

    public void setLife(int life_) {
        life = life_;
    }

    public boolean IsInvicible() {
        return isInvicible;
    }

    public void setIsInvicible(boolean IsInvicible_) {
        isInvicible = IsInvicible_;
    }

    public void setWeapon(Weaponbase weapon_) {
        m_weapon = weapon_;
    }

    public Weaponbase getWeaponbase(){
        return m_weapon;
    }

}
