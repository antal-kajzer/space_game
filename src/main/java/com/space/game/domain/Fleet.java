package com.space.game.domain;

import java.math.BigInteger;
import java.util.Map;

import org.hibernate.type.descriptor.java.ImmutableMutabilityPlan;

import java.util.Collections;

/**
 * Class to encapsulate Fleet specific data.
 * 
 * @author kapin
 *
 */
public class Fleet {
	private final Map<Ship, BigInteger> ships;

	private Fleet(Builder builder) {
		this.ships = builder.ships;
	}
	
	public Map<Ship, BigInteger> getShips() {
		return ships;
	}


	/**
	 * Creates builder to build {@link Fleet}.
	 * @return created builder
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link Fleet}.
	 */
	public static final class Builder {
		private Map<Ship, BigInteger> ships = Collections.emptyMap();

		private Builder() {
		}

		public Builder withShips(Map<Ship, BigInteger> ships) {
			this.ships = Collections.unmodifiableMap(ships);
			return this;
		}

		public Fleet build() {
			return new Fleet(this);
		}
	}
	
	
}
