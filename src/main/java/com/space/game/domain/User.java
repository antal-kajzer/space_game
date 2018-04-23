package com.space.game.domain;

import java.math.BigInteger;
import java.util.Set;

import javax.annotation.Generated;
import java.util.Collections;

/**
 * Class to encapsulate User specific data.
 * 
 * @author kapin
 *
 */
public final class User {
	private final BigInteger id;
	private final String userName;
	private final String password;
	private final Set<Planet> planets;
	
	private User(Builder builder) {
		this.id = builder.id;
		this.userName = builder.userName;
		this.password = builder.password;
		this.planets = builder.planets;
	}
	
	/**
	 * Creates builder to build {@link User}.
	 * @return created builder
	 */
	public static Builder builder() {
		return new Builder();
	}
	
	/**
	 * Builder to build {@link User}.
	 */
	public static final class Builder {
		private BigInteger id;
		private String userName;
		private String password;
		private Set<Planet> planets = Collections.emptySet();
		private Set<Planet> researches = Collections.emptySet();

		private Builder() {
		}

		public Builder withId(BigInteger id) {
			this.id = id;
			return this;
		}

		public Builder withUserName(String userName) {
			this.userName = userName;
			return this;
		}

		public Builder withPassword(String password) {
			this.password = password;
			return this;
		}

		public Builder withPlanets(Set<Planet> planets) {
			this.planets = planets;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}
	
	

}
