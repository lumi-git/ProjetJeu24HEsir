package BaseClass;

import Utils.Rect;


public class Weaponbase extends GameObject {

    public LivingEntity m_owner;
    protected int m_damage;
    protected float Radius=15;
    protected int reloadSpeed;
    protected boolean isReloading = false;
    protected int ammo;
    protected int maxAmmo;
    protected int m_attackSpeed;
    protected int m_reloadRate;
    public Weaponbase(boolean Collidable, LivingEntity owner_) {
        super(Collidable);
        rect= new Rect(owner_.getRect().x,owner_.getRect().y,32,32);
        Hitbox = new Rect(owner_.getRect().copy().x,owner_.getRect().copy().y,32,32);
        direction =  owner_.direction;
        maxAmmo = 10;
        ammo=10;
        reloadSpeed = 50;
        m_owner = owner_;
        m_damage = 1;
        m_attackSpeed = 30;
        m_reloadRate = 1;
    }

    //call this methode in the inherited class to update the basic comportement of the weapon
    public void update() {

        super.update();
        if(ammo < maxAmmo && gp.GetFrame()%reloadSpeed==0){

            setIsReloading(true);
            setAmmo(getAmmo()+m_reloadRate);
        }
    }

    public void setRadius(float radius) {
        Radius = radius;
    }

    public float getRadius() {
        return Radius;
    }

    public int getDamage() {
        return m_damage;
    }
    public void setDamage(int damage_) {
        m_damage = damage_;
    }

    //will decrease the ammo amount and trigger the behavior of the weapon
    public void Use( ) {
        if(getAmmo()>0 && gp.GetFrame()%m_attackSpeed==0){
            Fire();
            setAmmo(getAmmo()-1);
        }
    }

    //code this methode to implement the behavior of the weapon
    protected void Fire( ){

    }

    public void setAmmo(int ammo_) {
        ammo = ammo_;

    }

    public int getAmmo() {
        return ammo;
    }

    public void setMaxAmmo(int maxAmmo_) {
        maxAmmo = maxAmmo_;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public void setReloadSpeed(int reloadSpeed_) {
        reloadSpeed = reloadSpeed_;
    }
    public void setIsReloading(boolean IsReloading_) {
        isReloading = IsReloading_;
    }

    public int getReloadSpeed() {
        return reloadSpeed;
    }

    public boolean IsReloading() {
        return isReloading;
    }





}
