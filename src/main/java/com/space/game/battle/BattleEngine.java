package com.space.game.battle;

import java.math.BigInteger;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.space.game.domain.Fleet;
import com.space.game.domain.Ship;

/**
 * Battle engine.
 * 
 * @author kapin
 *
 */
@Component
public class BattleEngine {
	
	private BattleEngine() {}
	
	/**
	 * Does a battle between attacker and defender.
	 * 
	 * @param attacker attacker fleet
	 * @param defender defender fleet
	 * 
	 * @return result of the battle
	 */
	public BattleResult battle(Fleet attacker, Fleet defender) {
		Map<Ship, BigInteger> attackerShipsAndCount = attacker.getShips();
		Map<Ship, BigInteger> defenderShipsAndCount = defender.getShips();
	
		Map<Ship, BigInteger> remainedDefenderShips = attackerShipsAndCount.entrySet().stream()
				.map(attackerShips -> defenderShipsAndCount.entrySet().stream()
						.map(defenderShips -> attack(defenderShips, attackerShips))
						.findFirst()
						.get())
				.map(attackerShips -> attack(attackerShips, defenderShipsAndCount))
				.findFirst()
				.get();
		
		Map<Ship, BigInteger> remainedAttackerShips = defenderShipsAndCount.entrySet().stream()
				.map(attackerShips -> attackerShipsAndCount.entrySet().stream()
						.map(defenderShips -> attack(attackerShips, defenderShips))
						.findFirst()
						.get())
				.map(defenderShips -> attack(defenderShips, attackerShipsAndCount))
				.findFirst()
				.get();
		

		return new BattleResult();
	}
	
	private Entry<Ship, BigInteger> attack(Entry<Ship,BigInteger> attackerShipsAndCount, Entry<Ship, BigInteger> defenderShipsAndCount) {
		Entry<Ship, BigInteger> result = defenderShipsAndCount;
		
		long totalAttackPower = getTotalAttackPower(attackerShipsAndCount);
		long totalDefenderHealth = getTotalDefenderHealth(defenderShipsAndCount);
		long totalDefenderShield = getTotalDefenderShield(defenderShipsAndCount);

		if(defenderShipsAndCount != null && totalAttackPower < totalDefenderShield) {
			result = defenderShipsAndCount;
		} else if(defenderShipsAndCount != null && totalAttackPower < totalDefenderHealth + totalDefenderShield) {
			long defenderRemainingHealth = totalDefenderHealth + totalDefenderShield - totalAttackPower;
			long totalDefenderRemainingShipCount = defenderRemainingHealth / defenderShipsAndCount.getKey().getHealth();
			result = Maps.immutableEntry(defenderShipsAndCount.getKey(), new BigInteger(String.valueOf(totalDefenderRemainingShipCount)));
		} else if(defenderShipsAndCount != null && totalAttackPower > totalDefenderHealth + totalDefenderShield) {
			result = Maps.immutableEntry(defenderShipsAndCount.getKey(), BigInteger.ZERO);
		}
		
		return result;
	}

	private Map<Ship, BigInteger> attack(Entry<Ship, BigInteger> attackerShips, Map<Ship, BigInteger> defenderShipsAndCount) {
		return defenderShipsAndCount.entrySet().stream()
			.map(defenderShips -> attack(attackerShips, defenderShips))
			.collect(Collectors.toMap(Entry::getKey, Entry::getValue));
	}
	
	private long getTotalAttackPower(Entry<Ship, BigInteger> attackerShipsAndCount) {
		return attackerShipsAndCount.getKey().getAttackPower() * attackerShipsAndCount.getValue().longValue();
	}
	
	private long getTotalDefenderShield(Entry<Ship, BigInteger> defenderShipsAndCount) {
		return defenderShipsAndCount != null ? 
				defenderShipsAndCount.getKey().getShield() * defenderShipsAndCount.getValue().longValue() : 0;
	}

	private long getTotalDefenderHealth(Entry<Ship, BigInteger> defenderShipsAndCount) {
		return defenderShipsAndCount != null ? 
				defenderShipsAndCount.getKey().getHealth() * defenderShipsAndCount.getValue().longValue() : 0;
	}

}
