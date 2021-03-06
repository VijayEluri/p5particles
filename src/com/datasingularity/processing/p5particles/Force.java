package com.datasingularity.processing.p5particles;

/**
 * 
 * Represents a generic Force
 * 
 * @author bhelx
 * 
 */
public abstract class Force {

	protected Particle a;
	protected Particle b;
	protected boolean active = true;

	public final boolean involves(Particle p) {
		return p.equals(a) || p.equals(b);
	}

	public final void setParticles(Particle a, Particle b) {
		this.a = a;
		this.b = b;
	}

	public final Particle getA() {
		return a;
	}

	public final void setA(Particle a) {
		this.a = a;
	}

	public final Particle getB() {
		return b;
	}

	public final void setB(Particle b) {
		this.b = b;
	}

	public final void swapParticles(Particle original, Particle newP) {
		if (a.equals(original)) {
			a = newP;
		} else if (b.equals(original)) {
			b = newP;
		}
	}

	public final void setActive(boolean active) {
		this.active = active;
	}
	
	public abstract void apply();

}
