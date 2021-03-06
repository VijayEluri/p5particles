package com.datasingularity.processing.p5particles;

import processing.core.PVector;

/**
 * 
 * @author bhelx
 */
public class Particle {

	private static final float DEFAULT_MASS = 1f;
	private static final float DEFAULT_CHARGE = 0f;
	private static long pid = 0L;
	
	//should i have to do this?
	private static synchronized long genID() {
		return ++pid;
	}

	protected PVector loc;
	protected PVector vel;
	protected PVector acc;
	protected float mass;
	protected boolean fixed;
//	protected boolean axisConstraintX;
//	protected boolean axisConstraintY;
	protected float charge;
	protected long age = 0L;
	protected long lifeSpan;
	protected long id;
	

	/**
	 * Creates an empty particle
	 * 
	 */
	public Particle() {
		this(new PVector(), new PVector(), 1f, false);
	}

	/**
	 * Creates a particle at location loc with a default mass and non-fixed
	 * 
	 * @param loc
	 */
	public Particle(PVector loc) {
		this(loc, 1f, false);
	}

	/**
	 * Creates a Particle with given location, velocity, and acceleration
	 * 
	 * @param loc
	 * @param vel
	 * @param acc
	 */
	public Particle(PVector loc, PVector vel, PVector acc) {
//		this.axisConstraintX = false;
//		this.axisConstraintY = false;
		this.loc = loc;
		this.vel = vel;
		this.acc = acc;
		this.mass = DEFAULT_MASS;
		this.charge = DEFAULT_CHARGE;
		id = Particle.genID();
	}

	/**
	 * Creates Particle at given location with given mass and freedom (fixed)
	 * 
	 * @param loc
	 * @param mass
	 * @param fixed
	 */
	public Particle(PVector loc, float mass, boolean fixed) {
		this(loc, new PVector(), new PVector());
		this.mass = mass;
		this.fixed = fixed;
	}

	/**
	 * Creates Particle at given location and velocity with given mass and
	 * freedom (fixed)
	 * 
	 * @param loc
	 * @param vel
	 * @param mass
	 * @param fixed
	 */
	public Particle(PVector loc, PVector vel, float mass, boolean fixed) {
		this(loc, vel, new PVector());
		this.mass = mass;
		this.fixed = fixed;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Particle)) return false;
		
		Particle p = (Particle) other;
		return p.id == this.id;
	}

	/**
	 * Apply a force to a particle. Internally it divides the force vector by the
	 * particle's mass and adds to the acceleration.
	 * 
	 * @param force
	 */
	public final void applyForce(PVector force) {
		force.div(mass);
		acc.add(force);
	}

	/**
	 * Is the particle alive? has the kill() method been called?
	 * 
	 * @return
	 */
	public final boolean isAlive() {
		return lifeSpan == 0L || age <= lifeSpan;
	}

	/**
	 * Kill a particle. After calling this method, the particle will be removed
	 * from the system after the next tick() event.
	 * 
	 */
	public final void kill() {
		age = lifeSpan + 1;
	}
	
	public void setLifeSpan(int lifeSpan) {
		setLifeSpan((long)lifeSpan);
	}
	
	public Particle setLifeSpan(long lifeSpan) {
		this.lifeSpan = lifeSpan;
		return this;
	}
	
	public long getLifeSpan() {
		return this.lifeSpan;
	}
	
	public final void onUpdate() {
		age++;
	}

	/**
	 * Clears out the acceleration vector.
	 * 
	 */
	public final void clearForce() {
		this.acc.mult(0f);
	}

	/**
	 * returns the location PVector of the Particle.
	 * 
	 * @return
	 */
	public final PVector getLoc() {
		return loc;
	}

	public final Particle setLoc(PVector loc) {
		this.loc = loc;
		return this;
	}

	public final Particle setLoc(float x, float y) {
		loc.set(x, y, 0f);
		return this;
	}

	public final Particle setLoc(float x, float y, float z) {
		loc.set(x, y, z);
		return this;
	}

	/**
	 * Gets the PVector velocity of this particle.
	 * 
	 * @return
	 */
	public final PVector getVel() {
		return vel;
	}

	/**
	 * Sets the PVector velocity of this particle.
	 * 
	 * @param vel
	 * @return
	 */
	public final Particle setVel(PVector vel) {
		this.vel = vel;
		return this;
	}

	public final Particle setVel(float x, float y) {
		vel.set(x, y, 0f);
		return this;
	}

	public final Particle setVel(float x, float y, float z) {
		vel.set(x, y, z);
		return this;
	}

	public final PVector getAcc() {
		return acc;
	}

	public final Particle setAcc(PVector acc) {
		this.acc = acc;
		return this;
	}

	public final Particle setAcc(float x, float y) {
		acc.set(x, y, 0f);
		return this;
	}

	public final Particle setAcc(float x, float y, float z) {
		acc.set(x, y, z);
		return this;
	}

	public final float getMass() {
		return mass;
	}

	/**
	 * Sets the mass of the particle
	 * 
	 * @param mass
	 * @return
	 */
	public final Particle setMass(float mass) {
		this.mass = mass;
		return this;
	}
	
	public final long getAge() {
		return age;
	}

	/**
	 * Gets the charge of the particle. Will be 0 by default.
	 * 
	 * @return
	 */
	public final float getCharge() {
		return charge;
	}

	/**
	 * Sets the charge of the particle
	 * 
	 * @param charge
	 * @return
	 */
	public final Particle setCharge(float charge) {
		this.charge = charge;
		return this;
	}

	/**
	 * Determines is the Particle is charged or not.
	 * 
	 * @return
	 */
	public final boolean isCharged() {
		return charge != 0f;
	}

	/**
	 * Determines if the particle is fixed or not.
	 * 
	 * @return
	 */
	public final boolean isFixed() {
		return fixed;
	}

	/**
	 * Set the fixed property
	 * 
	 * @param fixed
	 *            true for fixed and false for free
	 * @return
	 */
	public final Particle setFixed(boolean fixed) {
		this.fixed = fixed;
		return this;
	}
	
//	public boolean isAxisConstraintX() {
//		return axisConstraintX;
//	}
//
//	public void setAxisConstraintX(boolean axisContraintX) {
//		this.axisConstraintX = axisContraintX;
//	}
//
//	public boolean isAxisConstraintY() {
//		return axisConstraintY;
//	}
//
//	public void setAxisConstraintY(boolean axisContraintY) {
//		this.axisConstraintY = axisContraintY;
//	}
//	
//	public boolean isConstrained() {
//		return this.axisConstraintX || this.axisConstraintY;
//	}

	/**
	 * Doesn't do anything by default. You must override and implement this
	 * method
	 * 
	 */
	public void render() {
	}
}
