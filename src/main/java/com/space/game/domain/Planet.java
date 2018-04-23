package com.space.game.domain;

import java.util.Set;
import java.util.Collections;

/**
 * Class to encapsulate Planet specific data.
 * 
 * @author kapin
 *
 */
public final class Planet {
	private final Coordinate coordinate;
	private final Fleet fleet;
	private final Set<Building> buildings;
	
	private Planet(Builder builder) {
		this.coordinate = builder.coordinate;
		this.fleet = builder.fleet;
		this.buildings = builder.buildings;
	}
	
	/**
	 * Creates builder to build {@link Planet}.
	 * @return created builder
	 */
	public static Builder builder() {
		return new Builder();
	}
	
	/**
	 * Builder to build {@link Planet}.
	 */
	public static final class Builder {
		private Coordinate coordinate;
		private Fleet fleet;
		private Set<Building> buildings = Collections.emptySet();

		private Builder() {
		}

		public Builder withCoordinate(Coordinate coordinate) {
			this.coordinate = coordinate;
			return this;
		}

		public Builder withFleet(Fleet fleet) {
			this.fleet = fleet;
			return this;
		}

		public Builder withBuildings(Set<Building> buildings) {
			this.buildings = buildings;
			return this;
		}

		public Planet build() {
			return new Planet(this);
		}
	}

}
